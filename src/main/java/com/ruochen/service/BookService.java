package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;

public interface BookService {
    PageInfo<Book> selectBooksAll(Integer pageNum, Integer pageSize);
}
