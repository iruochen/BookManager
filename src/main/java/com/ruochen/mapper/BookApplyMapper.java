package com.ruochen.mapper;

import com.ruochen.domain.BookApply;
import com.ruochen.domain.BookApplySearch;
import org.apache.ibatis.annotations.Insert;

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
}
