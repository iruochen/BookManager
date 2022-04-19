package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BookReceiveService {

    /**
     * 添加教材申请记录
     *
     * @param book
     * @param bookReceive
     * @param request
     * @return
     */
    Integer addBookReceive(Book book, BookReceive bookReceive, HttpServletRequest request);

    /**
     * 查询当前学生的教材领取记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookReceiveSearch
     * @param request
     * @return
     */
    PageInfo<BookReceive> selectBookReceiveByStu(Integer pageNum, Integer pageSize, BookReceiveSearch bookReceiveSearch, HttpServletRequest request);

    /**
     * 根据ID 删除教材领取记录
     *
     * @param ids
     */
    void deleteBookReceiveByIds(List<String> ids);

    /**
     * 查询所有学生教材领取记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookReceiveSearch
     * @return
     */
    PageInfo<BookReceive> selectBookApplyAll(Integer pageNum, Integer pageSize, BookReceiveSearch bookReceiveSearch);


    /**
     * 统计近7天领取数据
     *
     * @return
     */
    List<Statistics> selectCountLastSevenDays();
}
