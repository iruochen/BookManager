package com.ruochen.service;

import com.ruochen.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 登录
     *
     * @param user
     * @return
     */
    Integer login(User user, String captcha, HttpServletRequest request);

    /**
     * 注册
     *
     * @param user
     * @param captcha
     * @param request
     * @return
     */
    Integer register(User user, String captcha, HttpServletRequest request);

    /**
     * 修改密码
     *
     * @param user
     * @param oldPassword
     * @return
     */
    Integer updatePassword(User user, String oldPassword);

    /**
     * 根据role查询用户总数
     *
     * @param role
     * @return
     */
    Integer selectAllByRole(int role);
}
