package com.ruochen;

import com.ruochen.domain.Statistics;
import com.ruochen.mapper.BookPurchaseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestStatistics {

    @Autowired
    private BookPurchaseMapper bookPurchaseMapper;

    /**
     * 测试查询进七天的采购数据统计
     */
    @Test
    public void testSelectAllLastSevenDays() {
        List<Statistics> statistics = bookPurchaseMapper.selectCountLastSevenDays();
        for (Statistics statistic : statistics) {
            System.out.println(statistic);
        }
    }
}
