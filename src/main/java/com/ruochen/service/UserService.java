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
}
