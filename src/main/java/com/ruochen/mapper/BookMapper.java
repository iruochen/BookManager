package com.ruochen.mapper;

import com.ruochen.domain.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {

    /**
     * 查询所有教材
     *
     * @return
     */
    @Select("select * from book")
    @Results(id = "book", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "book_name", property = "bookName"),
            @Result(column = "book_author", property = "bookAuthor"),
            @Result(column = "book_press", property = "bookPress"),
            @Result(column = "book_price", property = "bookPrice"),
            @Result(column = "book_num", property = "bookNum"),
            @Result(column = "book_img_url", property = "bookImgUrl")
    })
    List<Book> selectBooksAll();

    /**
     * 添加教材
     *
     * @param book
     */
    @Insert("insert into book(id, book_name, book_author, book_press, book_price, book_num, book_img_url) values (#{id}, #{bookName}, #{bookAuthor}, #{bookPress}, #{bookPrice}, #{bookNum}, #{bookImgUrl})")
    void addBook(Book book);
}
