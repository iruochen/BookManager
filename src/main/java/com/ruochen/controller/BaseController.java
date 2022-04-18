package com.ruochen.controller;

import com.ruochen.domain.User;
import com.ruochen.mapper.BookMapper;
import com.ruochen.service.BookService;
import com.ruochen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


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

    /**
     * welcome
     *
     * @return
     */
    @GetMapping("welcome")
    public String welcome(Model model) {
        Integer adminCount = userService.selectAllByRole(0);
        Integer stuCount = userService.selectAllByRole(1);
        Integer teaCount = userService.selectAllByRole(2);
        Integer bookCount = bookService.selectCount();
        model.addAttribute("adminCount", adminCount);
        model.addAttribute("stuCount", stuCount);
        model.addAttribute("teaCount", teaCount);
        model.addAttribute("bookCount", bookCount);
        return "welcome";
    }
}
