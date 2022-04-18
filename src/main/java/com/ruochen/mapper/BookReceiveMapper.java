package com.ruochen.mapper;

import com.ruochen.domain.BookReceive;
import com.ruochen.domain.BookReceiveSearch;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface BookReceiveMapper {

    /**
     * 添加教材领取记录
     *
     * @param bookReceive
     */
    @Insert("insert into book_receive (id, bid, sid, time, count) values (null, #{bId}, #{sId}, #{time}, #{count})")
    void addBookReceive(BookReceive bookReceive);

    /**
     * 查询当前学生的教材领取记录
     *
     * @param bookReceiveSearch
     * @return
     */
    List<BookReceive> selectBookReceiveByStu(BookReceiveSearch bookReceiveSearch);

    /**
     * 查询所有学生的教材领取记录
     *
     * @param bookReceiveSearch
     * @return
     */
    List<BookReceive> selectBookReceiveAll(BookReceiveSearch bookReceiveSearch);

    /**
     * 根据ID 删除教材领取记录
     *
     * @param id
     */
    @Delete("delete from book_receive where id = #{id}")
    void deleteBookReceiveById(int id);
}
