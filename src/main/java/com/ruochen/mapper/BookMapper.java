package com.ruochen.mapper;

import com.ruochen.domain.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    /**
     * 查询所有教材
     *
     * @return
     */
    @Select("select * from book")
    @Results(id = "bookMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "book_id", property = "bookId"),
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
    @Insert("insert into book(id, book_id, book_name, book_author, book_press, book_price, book_num, book_img_url) values (null, #{bookId}, #{bookName}, #{bookAuthor}, #{bookPress}, #{bookPrice}, #{bookNum}, #{bookImgUrl})")
    void addBook(Book book);

    /**
     * 根据ID查询教材信息
     *
     * @param id
     */
    @Select("select * from book where id = #{id};")
    @ResultMap(value = {"bookMap"})
    Book selectBookById(Integer id);

    /**
     * 修改教材信息
     *
     * @param book
     */
    @Update("update book set book_id=#{bookId}, book_name=#{bookName}, book_author=#{bookAuthor}, book_press=#{bookPress}, book_price=#{bookPrice}, book_num=#{bookNum}, book_img_url=#{bookImgUrl} where id=#{id}")
    void updateBook(Book book);
}
