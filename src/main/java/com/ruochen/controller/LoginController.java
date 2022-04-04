package com.ruochen.controller;

import com.ruochen.domain.User;
import com.ruochen.service.UserService;
import com.ruochen.verifyCodeUtil.IVerifyCodeGen;
import com.ruochen.verifyCodeUtil.SimpleCharVerifyCodeGenImpl;
import com.ruochen.verifyCodeUtil.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登录页面跳转
     *
     * @return
     */
    @GetMapping("login")
    public String login() {
        return "login";
    }

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("异常处理");
        }
    }

    /**
     * 登录验证
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("loginIn")
    public String loginIn(HttpServletRequest request, Model model) {
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
            model.addAttribute("msg", "验证码不正确");
            return "login";
        } else {
            // 验证码正确则判断用户名和密码
            User user = userService.selectUserByUsernameAndPasswordAndRole(username, password, Integer.parseInt(type));
            if (user == null) {
                // 用户不存在
                model.addAttribute("msg", "用户名或密码错误");
                return "login";
            }
            session.setAttribute("user", user);
            session.setAttribute("type", type);
        }
        if (type.equals("0")) {
            return "index-admin";
        } else if (type.equals("1")) {
            return "index-student";
        } else {
            return "index-teacher";
        }
    }

    /**
     * 退出功能
     *
     * @param request
     * @return
     */
    @GetMapping("loginOut")
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();  // 注销
        return "login";
    }
}
