package com.ruochen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    /**
     * 首次加载
     *
     * @return
     */
    @RequestMapping("/")
    public String load() {
        return "login";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    /**
     * 404
     *
     * @return
     */
    @RequestMapping("error")
    public String error() {
        return "404";
    }
}
