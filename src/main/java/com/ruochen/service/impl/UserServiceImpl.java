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
        // 登录成功
        return Constants.OK_CODE;
    }

    @Override
    public Integer register(User user, String captcha, HttpServletRequest request) {
        // 判断验证码是否正确（验证码已经放入session）
        HttpSession session = request.getSession();
        String realCode = (String) session.getAttribute("VerifyCode");
        if (!realCode.equalsIgnoreCase(captcha)) {
            // 验证码错误
            return Constants.CAPTCHA_ERROR;
        }
        User u = userMapper.selectUserByUsername(user.getUsername());
        if (u != null) {
            // 用户已存在
            return Constants.REGISTER_ERROR;
        }
        userMapper.addUser(user);
        // 注册成功
        return Constants.OK_CODE;
    }

    @Override
    public Integer updatePassword(User user, String oldPassword) {
        String _oldPassword = userMapper.selectPasswordByUsername(user.getUsername());
        if (!_oldPassword.equals(oldPassword)) {
            return Constants.PASSWORD_ERROR;
        } else {
            userMapper.updatePassword(user.getUsername(), user.getPassword());
            return Constants.OK_CODE;
        }
    }

    @Override
    public Integer selectAllByRole(int role) {
        return userMapper.selectAllByRole(role);
    }
}
