package com.ruochen.service.impl;

import com.ruochen.domain.Admin;
import com.ruochen.domain.Book;
import com.ruochen.domain.BookPurchase;
import com.ruochen.domain.User;
import com.ruochen.mapper.AdminMapper;
import com.ruochen.mapper.BookApplyMapper;
import com.ruochen.mapper.BookMapper;
import com.ruochen.mapper.BookPurchaseMapper;
import com.ruochen.service.BookPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BookPurchaseServiceImpl implements BookPurchaseService {

    @Autowired
    private BookPurchaseMapper bookPurchaseMapper;

    @Autowired
    private BookApplyMapper bookApplyMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void bookPurchase(String bookId, BookPurchase bookPurchase, HttpServletRequest request) {
        Book book = bookMapper.selectBookByBookId(bookId);
        // 当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        Admin admin = adminMapper.selectAdminByUserId(user.getId());
        bookPurchase.setAid(admin.getId());
        bookPurchase.setBid(book.getId());
        bookPurchase.setBook(book);
        bookPurchase.setAdmin(admin);
        Date date = new Date(new Date().getTime());
        bookPurchase.setTime(date);
        // 更新教材申请状态
        bookApplyMapper.updateBookApplyStatusBybIdAndStatus(book.getId(), 1, 3);
        // 更新教材库存
        bookMapper.updateBookNumById(book.getId(), book.getBookNum() + bookPurchase.getCount());
        // 记录写入教材采购表
        bookPurchaseMapper.addBookPurchase(bookPurchase);
    }
}
