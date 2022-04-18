package com.ruochen.service;

import com.ruochen.domain.Book;
import com.ruochen.domain.BookReceive;

import javax.servlet.http.HttpServletRequest;

public interface BookReceiveService {

    /**
     * 添加教材申请记录
     *
     * @param book
     * @param bookReceive
     * @param request
     * @return
     */
    Integer addBookReceive(Book book, BookReceive bookReceive, HttpServletRequest request);
}
