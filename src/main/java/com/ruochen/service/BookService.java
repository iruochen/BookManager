package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;

public interface BookService {
    /**
     * 查询所有教材
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return PageInfo
     */
    PageInfo<Book> selectBooksAll(Integer pageNum, Integer pageSize);

    /**
     * 添加教材
     *
     * @param book
     */
    void addBook(Book book);
}
