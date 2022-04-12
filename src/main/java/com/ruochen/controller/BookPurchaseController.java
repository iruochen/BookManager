package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.BookApply;
import com.ruochen.domain.BookApplySearch;
import com.ruochen.service.BookApplyService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookPurchaseController {

    @Autowired
    private BookApplyService bookApplyService;

    /**
     * 教材采购需求首页
     *
     * @return
     */
    @GetMapping("bookPurchaseDemand")
    public String bookPurchaseDemand() {
        return "purchase/bookPurchaseDemand";
    }

    /**
     * 查询所有已通过的申请记录
     *
     * @param pageNum
     * @param pageSize
     * @param bookApplySearch
     * @return
     */
    @RequestMapping("selectBookPurchaseAll")
    @ResponseBody
    public DataInfo selectBookPurchaseAll(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, BookApplySearch bookApplySearch) {
        PageInfo<BookApply> pageInfo = bookApplyService.selectBookPurchaseAll(pageNum, pageSize, bookApplySearch);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }
}
