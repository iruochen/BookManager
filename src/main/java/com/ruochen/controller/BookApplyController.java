package com.ruochen.controller;

import com.ruochen.service.BookApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookApplyController {

    @Autowired
    private BookApplyService bookApplyService;

    /**
     * 申请教材首页
     *
     * @return
     */
    @GetMapping("applyIndex")
    public String applyIndex() {
        return "apply/applyIndex";
    }
}
