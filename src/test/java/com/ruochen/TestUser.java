package com.ruochen;

import com.ruochen.domain.User;
import com.ruochen.mapper.UserMapper;
import com.ruochen.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestUser {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * 测试根据用户名查询用户
     */
    @Test
    public void testSelectUserByUsername() {
        User user = userMapper.selectUserByUsername("ad");
        System.out.println(user);
    }

    /**
     * 测试查询用户
     */
    @Test
    public void testSelectUserByUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setRole(1);
        User u = userMapper.selectUserByUser(user);
        System.out.println(u);
    }

    /**
     * 测试根据role查询用户总数
     */
    @Test
    public void testSelectAllByRole() {
        Integer count = userService.selectAllByRole(0);
        System.out.println(count);
    }
}
