package com.ruochen;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;
import com.ruochen.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestBook {

    @Autowired
    private BookService bookService;

    @Test
    public void testSelectBooksAll() {
        PageInfo<Book> pageInfo = bookService.selectBooksAll(3, 1);
        List<Book> bookList = pageInfo.getList();
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}
