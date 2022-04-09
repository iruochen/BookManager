package com.ruochen.controller;

import com.ruochen.domain.User;
import com.ruochen.service.UserService;
import com.ruochen.utils.DataInfo;
import com.ruochen.verifyCodeUtil.IVerifyCodeGen;
import com.ruochen.verifyCodeUtil.SimpleCharVerifyCodeGenImpl;
import com.ruochen.verifyCodeUtil.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String login(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (null != user) {
            // 已登录
            return "index";
        } else {
            // 未登录
            return "login";
        }
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
     * 登录
     *
     * @param user
     * @param captcha
     * @param request
     * @return
     */
    @RequestMapping("loginIn")
    @ResponseBody
    public DataInfo loginIn(User user, String captcha, HttpServletRequest request) {
        Integer code = userService.login(user, captcha, request);
        return DataInfo.ok(code);
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
