package com.ruochen.service.impl;

import com.ruochen.domain.User;
import com.ruochen.mapper.UserMapper;
import com.ruochen.service.UserService;
import com.ruochen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(String username, String password, Integer type) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(type);
        userMapper.addUser(user);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public Integer login(User user, String captcha, HttpServletRequest request) {
        // 判断验证码是否正确（验证码已经放入session）
        HttpSession session = request.getSession();
        String realCode = (String) session.getAttribute("VerifyCode");
        if (!realCode.equalsIgnoreCase(captcha)) {
            // 验证码错误
            return Constants.CAPTCHA_ERROR;
        }
        User u = userMapper.selectUserByUser(user);
        if (u == null) {
            // 用户不存在
            return Constants.LOGIN_ERROR;
        }
        session.setAttribute("user", u);
        return Constants.OK_CODE;
    }
}
