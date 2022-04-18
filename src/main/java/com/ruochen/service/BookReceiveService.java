package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;
import com.ruochen.domain.BookReceive;
import com.ruochen.domain.BookReceiveSearch;

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
}
