package com.ruochen.interceptor;

import com.ruochen.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (null != user) {
            // 已登录
            return true;
        } else {
            // 未登录
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            return false;
        }
    }
}
