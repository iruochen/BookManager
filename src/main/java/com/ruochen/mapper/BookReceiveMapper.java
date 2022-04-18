package com.ruochen.mapper;

import com.ruochen.domain.BookReceive;
import org.apache.ibatis.annotations.Insert;

public interface BookReceiveMapper {

    /**
     * 添加教材领取记录
     *
     * @param bookReceive
     */
    @Insert("insert into book_receive (id, bid, sid, time, count) values (null, #{bId}, #{sId}, #{time}, #{count})")
    void addBookReceive(BookReceive bookReceive);
}
