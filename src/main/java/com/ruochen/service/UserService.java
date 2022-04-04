package com.ruochen.service;

import com.ruochen.domain.User;

public interface UserService {
    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    User selectUserByUsernameAndPasswordAndRole(String username, String password, Integer type);

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
}
