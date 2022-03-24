package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;
import com.ruochen.service.BookService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 教材管理首页
     *
     * @return
     */
    @GetMapping("bookIndex")
    public String bookIndex() {
        return "book/bookIndex";
    }

    /**
     * 获取教材信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("bookAll")
    @ResponseBody
    public DataInfo bookIndex(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize) {
        PageInfo<Book> pageInfo = bookService.selectBooksAll(pageNum, pageSize);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }
}
