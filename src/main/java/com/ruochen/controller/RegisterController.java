package com.ruochen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    /**
     * 注册页面跳转
     *
     * @return
     */
    @GetMapping("register")
    public String register() {
        return "register";
    }
}
