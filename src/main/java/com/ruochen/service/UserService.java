package com.ruochen.service;

import com.ruochen.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 添加用户
     *
     * @param username
     * @param password
     * @param type
     */
    void addUser(String username, String password, Integer type);

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    User selectUserByUsername(String username);

    /**
     * 登录
     *
     * @param user
     * @return
     */
    Integer login(User user, String captcha, HttpServletRequest request);
}
