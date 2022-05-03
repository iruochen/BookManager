package com.ruochen;

import com.ruochen.domain.BookApply;
import com.ruochen.domain.BookApplySearch;
import com.ruochen.dto.BookScore;
import com.ruochen.mapper.BookApplyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestBookApply {

    @Autowired
    private BookApplyMapper bookApplyMapper;

    /**
     * 测试查询当前教师教材申请记录
     */
    @Test
    public void testSelectBookApplyAllByTea() {
        BookApplySearch bookApplySearch = new BookApplySearch();
        bookApplySearch.setCurrentTeacherId(16);
        List<BookApply> bookApplies = bookApplyMapper.selectBookApplyByTea(bookApplySearch);
        for (BookApply bookApply : bookApplies) {
            System.out.println(bookApply);
        }
    }


    /**
     * 测试根据ID 更新教材申请状态
     */
    @Test
    public void testUpdateBookApplyById() {
        bookApplyMapper.updateBookApplyStatusById(1, 0);
    }

    /**
     * 测试查询所有教材申请记录
     */
    @Test
    public void testSelectBookApplyAll() {
        BookApplySearch bookApplySearch = new BookApplySearch();
        /*
        List<BookApply> bookApplies = bookApplyMapper.selectBookApplyAll(bookApplySearch);
        System.out.println(bookApplies);
        */
        List<BookApply> bookApplies = bookApplyMapper.selectBookApplyAll(bookApplySearch, 3);
        for (BookApply bookApply : bookApplies) {
            System.out.println(bookApply);
        }
    }

    /**
     * 测试分组查询教材申请记录
     */
    @Test
    public void testSelectBookApplyGroupByBookId() {
        BookApplySearch bookApplySearch = new BookApplySearch();
        List<BookApply> bookApplies = bookApplyMapper.selectBookApplyGroupByBookId(bookApplySearch);
        for (BookApply bookApply : bookApplies) {
            System.out.println(bookApply);
        }
    }

    /**
     * 测试获取教材评分均值
     */
    @Test
    public void testSelectBookScoreAvg() {
        List<BookScore> bookScores = bookApplyMapper.selectBookScoreAvg();
        for (BookScore bookScore : bookScores) {
            System.out.println(bookScore);
        }
    }
}
