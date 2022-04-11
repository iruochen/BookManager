package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;
import com.ruochen.domain.BookApply;
import com.ruochen.domain.BookApplySearch;
import com.ruochen.service.BookApplyService;
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
public class BookApplyController {

    @Autowired
    private BookApplyService bookApplyService;

    @Autowired
    private BookService bookService;

    /**
     * 申请教材首页
     *
     * @return
     */
    @GetMapping("applyIndex")
    public String applyIndex() {
        return "bookApply/applyIndex";
    }

    /**
     * 教材申请页面跳转
     *
     * @param id
     * @return
     */
    @GetMapping("applyBookById")
    public String applyBookById(Integer id, Model model) {
        Book book = bookService.selectBookById(id);
        model.addAttribute("book", book);
        return "bookApply/bookApply";
    }

    /**
     * 教材申请提交
     *
     * @param book
     * @param bookApply
     * @return
     */
    @RequestMapping("applyBookSubmit")
    @ResponseBody
    public DataInfo applyBookSubmit(Book book, BookApply bookApply, HttpServletRequest request) {
        bookApplyService.addBookApply(book, bookApply, request);
        return DataInfo.ok();
    }

    /**
     * 教材申请记录页面跳转
     *
     * @return
     */
    @GetMapping("bookApplyRecordIndex")
    public String bookApplyRecordIndex() {
        return "bookApply/bookApplyRecordIndex";
    }

    /**
     * 教材全部申请记录（采购需求）
     *
     * @return
     */
    @GetMapping("bookApplyRecordAll")
    public String bookApplyRecordAllIndex() {
        return "bookApply/bookApplyRecordAll";
    }

    /**
     * 查询登录教师的教材申请记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookApplySearch
     * @return
     */
    @RequestMapping("selectBookApplyAllByTea")
    @ResponseBody
    public DataInfo selectBookApplyAllByTea(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, BookApplySearch bookApplySearch, HttpServletRequest request) {
        PageInfo<BookApply> pageInfo = bookApplyService.selectBookApplyByTea(pageNum, pageSize, bookApplySearch, request);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 查询所有教材申请记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookApplySearch
     * @return
     */
    @RequestMapping("selectBookApplyAll")
    @ResponseBody
    public DataInfo selectBookApplyAll(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, BookApplySearch bookApplySearch) {
        PageInfo<BookApply> pageInfo = bookApplyService.selectBookApplyAll(pageNum, pageSize, bookApplySearch);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据ID 更改教材申请记录状态
     *
     * @param ids
     * @param status
     * @return
     */
    @RequestMapping("updateBookApplyStatus")
    @ResponseBody
    public DataInfo bookApplyRecordSubmit(String ids, Integer status) {
        List<String> list = Arrays.asList(ids.split(","));
        bookApplyService.updateBookApplyStatusByIds(list, status);
        return DataInfo.ok(status);
    }
}
