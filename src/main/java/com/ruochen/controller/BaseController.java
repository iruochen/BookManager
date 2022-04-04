package com.ruochen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    /**
     * 管理员登录首页
     *
     * @return
     */
    @GetMapping("index-admin")
    public String index() {
        return "index-admin";
    }

    /**
     * 教师登录首页
     *
     * @return
     */
    @GetMapping("index-teacher")
    public String teacherIndex() {
        return "index-teacher";
    }
}
