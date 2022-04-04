package com.ruochen.service.impl;

import com.ruochen.domain.User;
import com.ruochen.mapper.UserMapper;
import com.ruochen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByUsernameAndPasswordAndRole(String username, String password, Integer type) {
        return userMapper.selectUserByUsernameAndPassword(username, password, type);
    }

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
}
