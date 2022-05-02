package com.ruochen.mapper;

import com.ruochen.domain.BookReceive;
import com.ruochen.domain.BookReceiveSearch;
import com.ruochen.domain.Statistics;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

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
     * @param adminDeptId
     * @return
     */
    List<BookReceive> selectBookReceiveAll(@Param("bookReceiveSearch") BookReceiveSearch bookReceiveSearch, @Param("adminDeptId") Integer adminDeptId);

    /**
     * 根据ID 删除教材领取记录
     *
     * @param id
     */
    @Delete("delete from book_receive where id = #{id}")
    void deleteBookReceiveById(int id);

    /**
     * 统计近7天领取数据
     *
     * @return
     */
    List<Statistics> selectCountLastSevenDays();
}
