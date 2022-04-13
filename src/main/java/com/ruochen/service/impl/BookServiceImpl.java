package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;
import com.ruochen.mapper.BookMapper;
import com.ruochen.service.BookService;
import com.ruochen.utils.Constants;
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
    public PageInfo<Book> selectBooksAll(Integer pageNum, Integer pageSize, Book book) {
        PageHelper.startPage(pageNum, pageSize);
        List<Book> books = bookMapper.selectBooksAll(book);
        return new PageInfo<>(books);
    }

    @Override
    public Integer addBook(Book book) {
        if (null != bookMapper.selectBookByBookId(book.getBookId())) {
            // 教材编号已存在
            return Constants.BOOK_EXIST_CODE;
        }
        bookMapper.addBook(book);
        return Constants.OK_CODE;
    }

    @Override
    public Book selectBookById(Integer id) {
        return bookMapper.selectBookById(id);
    }

    @Override
    public Integer updateBook(Book book, String oldBookId) {
        if (!book.getBookId().equals(oldBookId) && null != bookMapper.selectBookByBookId(book.getBookId())) {
            // 教材编号已存在
            return Constants.BOOK_EXIST_CODE;
        }
        bookMapper.updateBook(book);
        return Constants.OK_CODE;
    }

    @Override
    public void deleteBookByIds(List<String> ids) {
        for (String id : ids) {
            bookMapper.deleteBookById(Integer.parseInt(id));
        }
    }
}
