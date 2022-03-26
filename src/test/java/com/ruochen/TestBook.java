package com.ruochen;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;
import com.ruochen.mapper.BookMapper;
import com.ruochen.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestBook {

    @Autowired
    private BookService bookService;

    /**
     * 测试查询所有教材
     */
    @Test
    public void testSelectBooksAll() {
        PageInfo<Book> pageInfo = bookService.selectBooksAll(1, 3);
        List<Book> bookList = pageInfo.getList();
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    /**
     * 测试添加教材
     */
    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setBookId("1013");
        book.setBookName("操作系统");
        book.setBookAuthor("作者4");
        book.setBookPress("高教社");
        book.setBookPrice(55);
        book.setBookImgUrl("url");
        bookService.addBook(book);
    }

    /**
     * 测试根据ID查询教材
     */
    @Test
    public void testSelectById() {
        Book book = bookService.selectBookById(2);
        System.out.println(book);
    }

    /**
     * 测试更新教材
     */
    @Test
    public void testUpdateBook() {
        Book book = new Book();
        book.setId(9);
        book.setBookId("1017");
        book.setBookName("测试1017");
        book.setBookAuthor("作者");
        book.setBookPress("出版社");
        book.setBookPrice(11);
        book.setBookImgUrl("url");
        bookService.updateBook(book);
    }


    /**
     * 测试根据ID删除教材
     */
    @Test
    public void testDeleteBookByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("11");
        ids.add("12");
        bookService.deleteBookByIds(ids);
    }
}
