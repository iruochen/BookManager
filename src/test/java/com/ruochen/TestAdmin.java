package com.ruochen;

import com.ruochen.domain.Admin;
import com.ruochen.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestAdmin {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 测试添加管理员
     */
    @Test
    public void testAddAdmin() {
        Admin admin = new Admin();
        admin.setAdminId("1010");
        admin.setAdminName("管理员");
        admin.setSex("男");
        admin.setUserId(1);
        adminMapper.addAdmin(admin);
    }

    /**
     * 测试根据用户ID 查询管理员
     */
    @Test
    public void testSelectAdminByUserId() {
        Admin admin = adminMapper.selectAdminByUserId(1);
        System.out.println(admin);
    }

    /**
     * 测试根据管理员ID 查询管理员
     */
    @Test
    public void testSelectAdminByAdminId() {
        Admin admin = adminMapper.selectAdminByAdminId("1010");
        System.out.println(admin);
    }

    /**
     * 测试通过ID 查询管理员
     */
    @Test
    public void testSelectAdminById() {
        Admin admin = adminMapper.selectAdminById(1);
        System.out.println(admin);
    }
}
