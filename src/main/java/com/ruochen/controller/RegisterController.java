package com.ruochen.controller;

import com.ruochen.domain.User;
import com.ruochen.service.UserService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * 注册页面跳转
     *
     * @return
     */
    @GetMapping("register")
    public String register() {
        return "register";
    }

    /**
     * 注册验证
     *
     * @return
     */
    @RequestMapping("registerSubmit")
    @ResponseBody
    public DataInfo registerSubmit(User user, String captcha, HttpServletRequest request) {
        Integer code = userService.register(user, captcha, request);
        return DataInfo.ok(code);
    }
}
