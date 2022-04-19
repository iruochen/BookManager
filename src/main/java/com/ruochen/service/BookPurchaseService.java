package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.BookPurchase;
import com.ruochen.domain.BookPurchaseSearch;
import com.ruochen.domain.Statistics;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BookPurchaseService {
    /**
     * 教材采购
     *
     * @param bookId
     * @param bookPurchase
     * @param request
     */
    void bookPurchase(String bookId, BookPurchase bookPurchase, HttpServletRequest request);

    /**
     * 查询所有教材采购记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookPurchaseSearch
     * @return
     */
    PageInfo<BookPurchase> selectBookPurchaseAll(Integer pageNum, Integer pageSize, BookPurchaseSearch bookPurchaseSearch);

    /**
     * 根据ID 删除采购记录
     *
     * @param ids
     */
    void deleteBookPurchaseByIds(List<String> ids);


    /**
     * 统计近7天采购数据
     *
     * @return
     */
    List<Statistics> selectCountLastSevenDays();
}
