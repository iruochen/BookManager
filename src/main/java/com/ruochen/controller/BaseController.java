package com.ruochen.controller;

import com.ruochen.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    /**
     * 首次加载
     *
     * @return
     */
    @RequestMapping("/")
    public String load(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (null != user) {
            // 已登录
            return "index";
        } else {
            // 未登录
            return "login";
        }
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
