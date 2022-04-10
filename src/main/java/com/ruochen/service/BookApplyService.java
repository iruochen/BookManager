package com.ruochen.service;

import com.ruochen.domain.Book;
import com.ruochen.domain.BookApply;

import javax.servlet.http.HttpServletRequest;

public interface BookApplyService {
    /**
     * 添加教材申请记录
     *
     * @param book
     * @param bookApply
     * @param request
     */
    void addBookApply(Book book, BookApply bookApply, HttpServletRequest request);
}
