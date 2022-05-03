package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.BookApply;
import com.ruochen.domain.BookApplySearch;
import com.ruochen.domain.BookPurchase;
import com.ruochen.domain.BookPurchaseSearch;
import com.ruochen.service.BookApplyService;
import com.ruochen.service.BookPurchaseService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;

@Controller
public class BookPurchaseController {

    @Autowired
    private BookApplyService bookApplyService;

    @Autowired
    private BookPurchaseService bookPurchaseService;

    /**
     * 教材采购需求首页
     *
     * @return
     */
    @GetMapping("bookPurchaseDemand")
    public String bookPurchaseDemand() {
        return "bookPurchase/bookPurchaseDemand";
    }

    /**
     * 查询所有已通过的申请记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookApplySearch
     * @return
     */
    @RequestMapping("selectBookNeedPurchaseAll")
    @ResponseBody
    public DataInfo selectBookNeedPurchaseAll(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, BookApplySearch bookApplySearch, HttpServletRequest request) {
        PageInfo<BookApply> pageInfo = bookApplyService.selectBookNeedPurchaseAll(pageNum, pageSize, bookApplySearch, request);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 教材采购
     *
     * @return
     */
    @RequestMapping("bookPurchase")
    @ResponseBody
    public DataInfo bookPurchase(String bookId, BookPurchase bookPurchase, HttpServletRequest request) {
        bookPurchaseService.bookPurchase(bookId, bookPurchase, request);
        return DataInfo.ok();
    }

    /**
     * 教材采购记录页面跳转
     *
     * @return
     */
    @GetMapping("bookPurchaseIndex")
    public String bookPurchaseIndex() {
        return "bookPurchase/bookPurchaseRecordAll";
    }

    /**
     * 查询所有采购记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookPurchaseSearch
     * @return
     */
    @RequestMapping("selectBookPurchaseAll")
    @ResponseBody
    public DataInfo selectBookPurchaseAll(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, BookPurchaseSearch bookPurchaseSearch) {
        PageInfo<BookPurchase> pageInfo = bookPurchaseService.selectBookPurchaseAll(pageNum, pageSize, bookPurchaseSearch);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 删除采购记录
     *
     * @param ids
     * @return
     */
    @RequestMapping("deleteBookPurchase")
    @ResponseBody
    public DataInfo deleteBookPurchase(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        bookPurchaseService.deleteBookPurchaseByIds(list);
        return DataInfo.ok();
    }
}
