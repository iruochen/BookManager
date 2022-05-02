package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.*;
import com.ruochen.mapper.BookMapper;
import com.ruochen.mapper.BookReceiveMapper;
import com.ruochen.service.BookReceiveService;
import com.ruochen.service.StudentService;
import com.ruochen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookReceiveServiceImpl implements BookReceiveService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private BookReceiveMapper bookReceiveMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Integer addBookReceive(Book book, BookReceive bookReceive, HttpServletRequest request) {
        Integer bId = book.getId();
        // 获取教材数量，进行校验
        // 这里不在前端校验，防止数据不一致
        int diff = bookMapper.selectBookNumById(bId) - bookReceive.getCount();
        if (diff <= 0) {
            // 库存数量不足
            return Constants.BOOK_NUM_NOT_ENOUGH;
        }
        // 根据userId 查询当前学生ID
        User user = (User) request.getSession().getAttribute("user");
        Integer sId = studentService.selectStudentByUserId(user.getId()).getId();
        bookReceive.setbId(bId);
        bookReceive.setsId(sId);
        // 添加领取记录
        bookReceiveMapper.addBookReceive(bookReceive);
        // 更新教材库存数量
        bookMapper.updateBookNumById(bId, diff);
        return Constants.OK_CODE;
    }

    @Override
    public PageInfo<BookReceive> selectBookReceiveByStu(Integer pageNum, Integer pageSize, BookReceiveSearch bookReceiveSearch, HttpServletRequest request) {
        // 根据userId 查询当前学生ID
        User user = (User) request.getSession().getAttribute("user");
        Integer currentStudentId = studentService.selectStudentByUserId(user.getId()).getId();
        bookReceiveSearch.setCurrentStudentId(currentStudentId);
        PageHelper.startPage(pageNum, pageSize);
        List<BookReceive> bookReceives = bookReceiveMapper.selectBookReceiveByStu(bookReceiveSearch);
        return new PageInfo<>(bookReceives);
    }

    @Override
    public void deleteBookReceiveByIds(List<String> ids) {
        for (String id : ids) {
            bookReceiveMapper.deleteBookReceiveById(Integer.parseInt(id));
        }
    }

    @Override
    public PageInfo<BookReceive> selectBookApplyAll(Integer pageNum, Integer pageSize, BookReceiveSearch bookReceiveSearch, HttpServletRequest request) {
        Integer adminDeptId = (Integer) request.getSession().getAttribute("adminDeptId");
        PageHelper.startPage(pageNum, pageSize);
        List<BookReceive> bookReceives = bookReceiveMapper.selectBookReceiveAll(bookReceiveSearch, adminDeptId);
        return new PageInfo<>(bookReceives);
    }

    @Override
    public List<Statistics> selectCountLastSevenDays() {
        List<Statistics> statistics = bookReceiveMapper.selectCountLastSevenDays();
        for (Statistics statistic : statistics) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    //格式化规则
            Date date = statistic.getDate();         //获得你要处理的时间 Date型
            String strDate = sdf.format(date); //格式化成yyyy-MM-dd格式的时间字符串
            try {
                Date newDate = sdf.parse(strDate);
                java.sql.Date resultDate = new java.sql.Date(newDate.getTime());
                statistic.setDate(resultDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return statistics;
    }
}
