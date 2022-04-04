package com.ruochen.controller;

import com.ruochen.domain.User;
import com.ruochen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String registerSubmit(HttpServletRequest request, Model model) {
        // 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 验证码
        String code = request.getParameter("captcha");

        // 用户类型
        String type = request.getParameter("type");

        // 回显
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("type", type);
        model.addAttribute("code", code);

        // 判断验证码是否正确（验证码已经放入session）
        HttpSession session = request.getSession();
        String realCode = (String) session.getAttribute("VerifyCode");
        if (!realCode.toLowerCase().equals(code.toLowerCase())) {
            // 验证码错误
            model.addAttribute("msg", "验证码不正确");
            return "register";
        }
        // 验证码正确
        // 查看用户名是否重复
        User user = userService.selectUserByUsername(username);
        if (user != null) {
            model.addAttribute("msg", "用户名已存在");
            return "register";
        } else {
            // 添加用户
            userService.addUser(username, password, Integer.parseInt(type));
            return "redirect:login";
        }
    }
}
