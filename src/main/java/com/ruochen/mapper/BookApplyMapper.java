package com.ruochen.mapper;

import com.ruochen.domain.BookApply;
import com.ruochen.domain.BookApplySearch;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookApplyMapper {
    /**
     * 添加教材申请记录
     *
     * @param bookApply
     */
    @Insert("insert into book_apply (id, bid, tid, time, count) VALUES (null, #{bId}, #{tId}, #{time}, #{count})")
    void addBookApply(BookApply bookApply);

    /**
     * 查询当前教师的教材申请记录
     *
     * @param bookApplySearch
     * @return
     */
    List<BookApply> selectBookApplyByTea(BookApplySearch bookApplySearch);

    /**
     * 根据ID 更新教材申请状态
     *
     * @param id
     * @param status
     */
    @Update("update book_apply set status = #{status} where id = #{id}")
    void updateBookApplyStatusById(@Param("id") int id, @Param("status") Integer status);

    /**
     * 查询所有教材申请记录
     *
     * @param bookApplySearch
     * @return
     */
    List<BookApply> selectBookApplyAll(BookApplySearch bookApplySearch);
}
