package com.ruochen;

import com.ruochen.domain.BookReceive;
import com.ruochen.domain.BookReceiveSearch;
import com.ruochen.mapper.BookReceiveMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestBookReceive {

    @Autowired
    private BookReceiveMapper bookReceiveMapper;

    /**
     * 测试查询所有学生的教材领取记录
     */
    @Test
    public void testSelectBookReceiveAll() {
        BookReceiveSearch bookReceiveSearch = new BookReceiveSearch();
        List<BookReceive> bookReceives = bookReceiveMapper.selectBookReceiveAll(bookReceiveSearch);
        for (BookReceive bookReceive : bookReceives) {
            System.out.println(bookReceive);
        }
    }
}
