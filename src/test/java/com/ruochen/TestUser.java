package com.ruochen;

import com.ruochen.domain.User;
import com.ruochen.mapper.UserMapper;
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

    /**
     * 测试根据用户名查询用户
     */
    @Test
    public void testSelectUserByUsername() {
        User user = userMapper.selectUserByUsername("ad");
        System.out.println(user);
    }
}
