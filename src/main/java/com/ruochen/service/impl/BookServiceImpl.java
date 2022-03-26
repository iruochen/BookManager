package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;
import com.ruochen.mapper.BookMapper;
import com.ruochen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<Book> selectBooksAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Book> books = bookMapper.selectBooksAll();
        return new PageInfo<>(books);
    }

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public Book selectBookById(Integer id) {
        return bookMapper.selectBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public void deleteBookByIds(List<String> ids) {
        for (String id : ids) {
            bookMapper.deleteBookById(Integer.parseInt(id));
        }
    }
}
