package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;
import com.ruochen.domain.BookApply;
import com.ruochen.domain.BookApplySearch;

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
     * @param ids
     * @param status
     */
    void updateBookApplyStatusByIds(List<String> ids, Integer status);
}
