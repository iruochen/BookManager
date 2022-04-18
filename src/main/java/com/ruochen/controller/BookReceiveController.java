package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.*;
import com.ruochen.service.BookReceiveService;
import com.ruochen.service.BookService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 教材领取记录页面跳转
     *
     * @return
     */
    @RequestMapping("bookReceiveRecordIndex")
    public String bookReceiveRecordIndex() {
        return "bookReceive/bookReceiveRecordIndex";
    }

    /**
     * 查询登录学生的教材领取记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookReceiveSearch
     * @return
     */
    @RequestMapping("selectBookReceiveAllByStu")
    @ResponseBody
    public DataInfo selectBookReceiveAllByStu(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, BookReceiveSearch bookReceiveSearch, HttpServletRequest request) {
        PageInfo<BookReceive> pageInfo = bookReceiveService.selectBookReceiveByStu(pageNum, pageSize, bookReceiveSearch, request);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 删除教材领取记录
     *
     * @param ids
     * @return
     */
    @RequestMapping("deleteBookReceive")
    @ResponseBody
    public DataInfo deleteBookReceive(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        bookReceiveService.deleteBookReceiveByIds(list);
        return DataInfo.ok();
    }

    /**
     * 教材全部领取记录
     *
     * @return
     */
    @GetMapping("bookReceiveRecordAll")
    public String bookReceiveRecordAllIndex() {
        return "bookReceive/bookReceiveRecordAll";
    }

    /**
     * 查询所有教材领取记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookReceiveSearch
     * @return
     */
    @RequestMapping("selectBookReceiveAll")
    @ResponseBody
    public DataInfo selectBookReceiveAll(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, BookReceiveSearch bookReceiveSearch) {
        PageInfo<BookReceive> pageInfo = bookReceiveService.selectBookApplyAll(pageNum, pageSize, bookReceiveSearch);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }
}
