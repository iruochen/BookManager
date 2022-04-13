package com.ruochen.mapper;

import com.ruochen.domain.BookPurchase;
import org.apache.ibatis.annotations.Insert;

public interface BookPurchaseMapper {
    /**
     * 添加教材采购记录
     *
     * @param bookPurchase
     */
    @Insert("insert into book_purchase (id, bid, aid, time, count, price) values (null, #{bid}, #{aid}, #{time},#{count}, #{price})")
    void addBookPurchase(BookPurchase bookPurchase);
}
