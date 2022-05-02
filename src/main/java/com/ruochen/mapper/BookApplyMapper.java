package com.ruochen.mapper;

import com.ruochen.domain.BookApply;
import com.ruochen.domain.BookApplySearch;
import com.ruochen.domain.Statistics;
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
     * @param adminDeptId
     * @return
     */
    List<BookApply> selectBookApplyAll(@Param("bookApplySearch") BookApplySearch bookApplySearch, @Param("adminDeptId") Integer adminDeptId);

    /**
     * 查询所有已通过的申请记录，通过教材ID分组，并计算教材价格总和
     *
     * @param bookApplySearch
     * @return
     */
    List<BookApply> selectBookApplyGroupByBookId(BookApplySearch bookApplySearch);

    /**
     * 根据ID 和状态修改教材申请状态
     *
     * @param bid
     * @param oldStatus
     * @param status
     */
    @Update("update book_apply set status = #{status} where bid = #{bid} and status = #{oldStatus}")
    void updateBookApplyStatusBybIdAndStatus(@Param("bid") int bid, @Param("oldStatus") Integer oldStatus, @Param("status") Integer status);

    /**
     * 统计近7天申请数据
     *
     * @return
     */
    List<Statistics> selectCountLastSevenDays();
}
