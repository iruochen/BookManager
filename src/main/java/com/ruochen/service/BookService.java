package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;

import java.util.List;

public interface BookService {
    /**
     * 查询所有教材
     *
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return PageInfo
     */
    PageInfo<Book> selectBooksAll(Integer pageNum, Integer pageSize, Book book);

    /**
     * 添加教材
     *
     * @param book
     */
    void addBook(Book book);

    /**
     * 根据ID查询教材信息
     *
     * @param id
     * @return
     */
    Book selectBookById(Integer id);

    /**
     * 修改教材信息
     *
     * @param book
     */
    void updateBook(Book book);

    /**
     * 根据ID删除教材
     *
     * @param ids
     */
    void deleteBookByIds(List<String> ids);
}
