package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.*;
import com.ruochen.mapper.BookApplyMapper;
import com.ruochen.service.BookApplyService;
import com.ruochen.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookApplyServiceImpl implements BookApplyService {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private BookApplyMapper bookApplyMapper;

    @Override
    public void addBookApply(Book book, BookApply bookApply, HttpServletRequest request) {
        Integer bId = book.getId();
        // 根据userId 查询当前教师ID
        User user = (User) request.getSession().getAttribute("user");
        Integer tId = teacherService.selectTeacherByUserId(user.getId()).getId();
        bookApply.setbId(bId);
        bookApply.settId(tId);
        bookApplyMapper.addBookApply(bookApply);
    }

    @Override
    public PageInfo<BookApply> selectBookApplyByTea(Integer pageNum, Integer pageSize, BookApplySearch bookApplySearch, HttpServletRequest request) {
        // 根据userId 查询当前教师ID
        User user = (User) request.getSession().getAttribute("user");
        Integer currentTeacherId = teacherService.selectTeacherByUserId(user.getId()).getId();
        bookApplySearch.setCurrentTeacherId(currentTeacherId);
        PageHelper.startPage(pageNum, pageSize);
        List<BookApply> bookApplies = bookApplyMapper.selectBookApplyByTea(bookApplySearch);
        return new PageInfo<>(bookApplies);
    }

    @Override
    public void updateBookApplyStatusByIds(List<String> ids, Integer status) {
        for (String id : ids) {
            bookApplyMapper.updateBookApplyStatusById(Integer.parseInt(id), status);
        }
    }

    @Override
    public PageInfo<BookApply> selectBookApplyAll(Integer pageNum, Integer pageSize, BookApplySearch bookApplySearch) {
        PageHelper.startPage(pageNum, pageSize);
        List<BookApply> bookApplies = bookApplyMapper.selectBookApplyAll(bookApplySearch);
        return new PageInfo<>(bookApplies);
    }

    @Override
    public PageInfo<BookApply> selectBookNeedPurchaseAll(Integer pageNum, Integer pageSize, BookApplySearch bookApplySearch) {
        PageHelper.startPage(pageNum, pageSize);
        List<BookApply> bookApplies = bookApplyMapper.selectBookApplyGroupByBookId(bookApplySearch);
        for (BookApply bookApply : bookApplies) {
            // 教材价格
            double bookPrice = bookApply.getBook().getBookPrice();
            // 教材库存数量
            Integer bookNum = bookApply.getBook().getBookNum();
            // 申请总数
            Integer applyCount = bookApply.getApplyCount();
            // 需要采购的数量
            int needCount = applyCount - bookNum;
            bookApply.setNeedPurchaseCount(needCount);
            // 采购总值
            bookApply.setPriceCount(needCount * bookPrice);
        }
        return new PageInfo<>(bookApplies);
    }

    @Override
    public List<Statistics> selectCountLastSevenDays() {
        List<Statistics> statistics = bookApplyMapper.selectCountLastSevenDays();
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
