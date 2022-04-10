package com.ruochen.service.impl;

import com.ruochen.domain.Book;
import com.ruochen.domain.BookApply;
import com.ruochen.domain.User;
import com.ruochen.mapper.BookApplyMapper;
import com.ruochen.service.BookApplyService;
import com.ruochen.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BookApplyServiceImpl implements BookApplyService {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private BookApplyMapper bookApplyMapper;

    @Override
    public void addBookApply(Book book, BookApply bookApply, HttpServletRequest request) {
        Integer bId = book.getId();
        // 根据userId 查询当前教师ID
        User user = (User) request.getSession().getAttribute("user");
        Integer tId = teacherService.selectTeacherByUserId(user.getId()).getId();
        bookApply.setbId(bId);
        bookApply.settId(tId);
        bookApplyMapper.addBookApply(bookApply);
    }
}
