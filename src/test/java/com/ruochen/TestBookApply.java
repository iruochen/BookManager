package com.ruochen;

import com.ruochen.domain.BookApply;
import com.ruochen.domain.BookApplySearch;
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
        bookApplySearch.setCurrentTeacherId(11);
        List<BookApply> bookApplies = bookApplyMapper.selectBookApplyByTea(bookApplySearch);
        System.out.println(bookApplies);
    }
}
