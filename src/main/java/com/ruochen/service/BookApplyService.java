package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;
import com.ruochen.domain.BookApply;
import com.ruochen.domain.BookApplySearch;
import com.ruochen.domain.Statistics;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BookApplyService {
    /**
     * 添加教材申请记录
     *
     * @param book
     * @param bookApply
     * @param request
     */
    void addBookApply(Book book, BookApply bookApply, HttpServletRequest request);

    /**
     * 查询当前教师的教材申请记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookApplySearch
     * @param request
     * @return
     */
    PageInfo<BookApply> selectBookApplyByTea(Integer pageNum, Integer pageSize, BookApplySearch bookApplySearch, HttpServletRequest request);

    /**
     * 根据ID 更新教材申请状态
     *
     * @param ids
     * @param status
     */
    void updateBookApplyStatusByIds(List<String> ids, Integer status);

    /**
     * 查询所有教材申请记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookApplySearch
     * @param request
     * @return
     */
    PageInfo<BookApply> selectBookApplyAll(Integer pageNum, Integer pageSize, BookApplySearch bookApplySearch, HttpServletRequest request);

    /**
     * 查询所有已通过的申请记录，通过教材ID分组，并计算教材价格总和
     *
     * @param pageNum
     * @param pageSize
     * @param bookApplySearch
     * @return
     */
    PageInfo<BookApply> selectBookNeedPurchaseAll(Integer pageNum, Integer pageSize, BookApplySearch bookApplySearch);


    /**
     * 统计近7天申请数据
     *
     * @return
     */
    List<Statistics> selectCountLastSevenDays();

    /**
     * 添加评分记录
     *
     * @param applyId
     * @param score
     */
    void addScore(Integer applyId, Integer score);
}
