package com.ruochen.controller;

import com.ruochen.domain.Book;
import com.ruochen.domain.BookReceive;
import com.ruochen.service.BookReceiveService;
import com.ruochen.service.BookService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BookReceiveController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookReceiveService bookReceiveService;

    /**
     * 领取教材首页
     *
     * @return
     */
    @GetMapping("receiveIndex")
    public String receiveIndex() {
        return "bookReceive/bookReceiveIndex";
    }

    /**
     * 教材领取页面跳转
     *
     * @param id
     * @return
     */
    @GetMapping("receiveBookById")
    public String receiveBookById(Integer id, Model model) {
        Book book = bookService.selectBookById(id);
        model.addAttribute("book", book);
        return "bookReceive/bookReceive";
    }

    /**
     * 教材领取提交
     *
     * @param book
     * @param bookReceive
     * @return
     */
    @RequestMapping("receiveBookSubmit")
    @ResponseBody
    public DataInfo receiveBookSubmit(Book book, BookReceive bookReceive, HttpServletRequest request) {
        Integer code = bookReceiveService.addBookReceive(book, bookReceive, request);
        return DataInfo.ok(code);
    }
}
