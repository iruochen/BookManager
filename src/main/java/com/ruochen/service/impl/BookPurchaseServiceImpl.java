package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.*;
import com.ruochen.mapper.AdminMapper;
import com.ruochen.mapper.BookApplyMapper;
import com.ruochen.mapper.BookMapper;
import com.ruochen.mapper.BookPurchaseMapper;
import com.ruochen.service.BookPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @Override
    public PageInfo<BookPurchase> selectBookPurchaseAll(Integer pageNum, Integer pageSize, BookPurchaseSearch bookPurchaseSearch) {
        PageHelper.startPage(pageNum, pageSize);
        List<BookPurchase> bookPurchases = bookPurchaseMapper.selectBookPurchaseAll(bookPurchaseSearch);
        return new PageInfo<>(bookPurchases);
    }

    @Override
    public void deleteBookPurchaseByIds(List<String> ids) {
        for (String id : ids) {
            bookPurchaseMapper.deleteBookPurchaseById(Integer.parseInt(id));
        }
    }

    @Override
    public List<Statistics> selectCountLastSevenDays() {
        List<Statistics> statistics = bookPurchaseMapper.selectCountLastSevenDays();
        for (Statistics statistic : statistics) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    //格式化规则
            Date date = statistic.getDate();         //获得你要处理的时间 Date型
            String strDate = sdf.format(date); //格式化成yyyy-MM-dd格式的时间字符串
            try {
                Date newDate = sdf.parse(strDate);
                java.sql.Date resultDate = new java.sql.Date(newDate.getTime());
                statistic.setDate(resultDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return statistics;
    }
}
