package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.*;
import com.ruochen.mapper.BookMapper;
import com.ruochen.mapper.BookReceiveMapper;
import com.ruochen.service.BookReceiveService;
import com.ruochen.service.StudentService;
import com.ruochen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class BookReceiveServiceImpl implements BookReceiveService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private BookReceiveMapper bookReceiveMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Integer addBookReceive(Book book, BookReceive bookReceive, HttpServletRequest request) {
        Integer bId = book.getId();
        // 获取教材数量，进行校验
        // 这里不在前端校验，防止数据不一致
        int diff = bookMapper.selectBookNumById(bId) - bookReceive.getCount();
        if (diff <= 0) {
            // 库存数量不足
            return Constants.BOOK_NUM_NOT_ENOUGH;
        }
        // 根据userId 查询当前学生ID
        User user = (User) request.getSession().getAttribute("user");
        Integer sId = studentService.selectStudentByUserId(user.getId()).getId();
        bookReceive.setbId(bId);
        bookReceive.setsId(sId);
        // 添加领取记录
        bookReceiveMapper.addBookReceive(bookReceive);
        // 更新教材库存数量
        bookMapper.updateBookNumById(bId, diff);
        return Constants.OK_CODE;
    }

    @Override
    public PageInfo<BookReceive> selectBookReceiveByStu(Integer pageNum, Integer pageSize, BookReceiveSearch bookReceiveSearch, HttpServletRequest request) {
        // 根据userId 查询当前学生ID
        User user = (User) request.getSession().getAttribute("user");
        Integer currentStudentId = studentService.selectStudentByUserId(user.getId()).getId();
        bookReceiveSearch.setCurrentStudentId(currentStudentId);
        PageHelper.startPage(pageNum, pageSize);
        List<BookReceive> bookReceives = bookReceiveMapper.selectBookReceiveByStu(bookReceiveSearch);
        return new PageInfo<>(bookReceives);
    }

    @Override
    public void deleteBookReceiveByIds(List<String> ids) {
        for (String id : ids) {
            bookReceiveMapper.deleteBookReceiveById(Integer.parseInt(id));
        }
    }
}
