package com.ruochen.mapper;

import com.ruochen.domain.BookPurchase;
import com.ruochen.domain.BookPurchaseSearch;
import com.ruochen.domain.Statistics;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookPurchaseMapper {
    /**
     * 添加教材采购记录
     *
     * @param bookPurchase
     */
    @Insert("insert into book_purchase (id, bid, aid, time, count, price) values (null, #{bid}, #{aid}, #{time},#{count}, #{price})")
    void addBookPurchase(BookPurchase bookPurchase);

    /**
     * 查询所有教材采购记录
     *
     * @param bookPurchaseSearch
     * @param adminDeptId
     * @return
     */
    List<BookPurchase> selectBookPurchaseAll(@Param("bookPurchaseSearch") BookPurchaseSearch bookPurchaseSearch, @Param("adminDeptId") Integer adminDeptId);

    /**
     * 根据ID 删除采购记录
     *
     * @param id
     */
    @Delete("delete from book_purchase where id = #{id}")
    void deleteBookPurchaseById(int id);

    /**
     * 统计近7天采购数据
     *
     * @return
     */
    List<Statistics> selectCountLastSevenDays();
}
