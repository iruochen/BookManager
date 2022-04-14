package com.ruochen;

import com.ruochen.domain.BookPurchase;
import com.ruochen.domain.BookPurchaseSearch;
import com.ruochen.mapper.BookPurchaseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestBookPurchase {

    @Autowired
    private BookPurchaseMapper bookPurchaseMapper;

    /**
     * 测试查询所有采购记录
     */
    @Test
    public void testBookPurchaseAll() {
        BookPurchaseSearch bookPurchaseSearch = new BookPurchaseSearch();
        List<BookPurchase> bookPurchases = bookPurchaseMapper.selectBookPurchaseAll(bookPurchaseSearch);
        for (BookPurchase bookPurchase : bookPurchases) {
            System.out.println(bookPurchase);
        }
    }
}
