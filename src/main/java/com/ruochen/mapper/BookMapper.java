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
    @Select("<script> "
            + "select id, book_id as bookId, book_name as bookName, book_author as bookAuthor, book_press as bookPress, book_price as bookPrice, book_num as bookNum, book_img_url as bookImgUrl "
            + "from book "
            + " <where> "
            + " <if test='bookId!=null'>and book_id like concat('%',#{bookId},'%')</if> "
            + " <if test='bookName!=null'>and book_name like concat('%',#{bookName},'%')</if> "
            + " <if test='bookPress!=null'>and book_press like concat('%',#{bookPress},'%')</if> "
            + " </where> "
            + " </script> ")
    List<Book> selectBooksAll(Book book);

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
    Book selectBookById(Integer id);

    /**
     * 修改教材信息
     *
     * @param book
     */
    @Update("update book set book_id=#{bookId}, book_name=#{bookName}, book_author=#{bookAuthor}, book_press=#{bookPress}, book_price=#{bookPrice}, book_num=#{bookNum}, book_img_url=#{bookImgUrl} where id=#{id}")
    void updateBook(Book book);

    /**
     * 根据ID删除教材
     *
     * @param id
     */
    @Delete("delete from book where id = #{id}")
    void deleteBookById(int id);

    /**
     * 根据教材ID 查询教材信息
     *
     * @param bookId
     * @return
     */
    @Select("select * from book where book_id = #{bookId};")
    @ResultMap(value = {"bookMap"})
    Book selectBookByBookId(String bookId);

    /**
     * 根据ID 更新教材数量
     *
     * @param id
     * @param bookNum
     */
    @Update("update book set book_num = #{bookNum} where id = #{id}")
    void updateBookNumById(@Param("id") Integer id, @Param("bookNum") int bookNum);

    /**
     * 根据ID 获取教材数量
     *
     * @param bId
     * @return
     */
    @Select("select book_num from book where id = #{bId}")
    Integer selectBookNumById(Integer bId);

    /**
     * 查询教材总数
     *
     * @return
     */
    @Select("select count(*) from book")
    Integer selectCount();
}
