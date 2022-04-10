package com.ruochen.mapper;

import com.ruochen.domain.BookApply;
import org.apache.ibatis.annotations.Insert;

public interface BookApplyMapper {
    /**
     * 添加教材申请记录
     *
     * @param bookApply
     */
    @Insert("insert into book_apply (id, bid, tid, time, count) VALUES (null, #{bId}, #{tId}, #{time}, #{count})")
    void addBookApply(BookApply bookApply);
}
