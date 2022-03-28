package com.ruochen;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;
import com.ruochen.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestTeacher {
    @Autowired
    private TeacherService teacherService;


    /**
     * 测试查询所有教师信息
     */
    @Test
    public void testSelectTeacherAll() {
        Teacher teacher = new Teacher();
        // teacher.setTeaName("教");
        // teacher.setDeptId(2);
        PageInfo<Teacher> pageInfo = teacherService.selectTeacherAll(1, 10, teacher);
        for (Teacher tea : pageInfo.getList()) {
            System.out.println(tea);
        }
    }

    /**
     * 测试添加教师信息
     */
    @Test
    public void testAddTeacher() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setRole(2);
        Teacher teacher = new Teacher();
        teacher.setTeaId("1111");
        teacher.setTeaName("测试");
        teacher.setTeaSex("女");
        teacher.setDeptId(2);
        teacherService.addTeacher(teacher, user);
    }

    /**
     * 测试根据ID查找教师
     */
    @Test
    public void testSelectTeacherById() {
        Teacher teacher = teacherService.selectTeacherById(2);
        System.out.println(teacher);
    }

    /**
     * 测试修改教师信息
     */
    @Test
    public void testUpdateTeacher() {
        User user = new User();
        user.setId(7);
        user.setUsername("updateTeacher");
        user.setPassword("updatePwd");
        Teacher teacher = new Teacher();
        teacher.setId(2);
        teacher.setUserId(2);
        teacher.setTeaId("0000");
        teacher.setTeaName("更新教师");
        teacher.setTeaSex("男");
        teacher.setDeptId(3);
        teacherService.updateTeacher(teacher, user);
    }

    /**
     * 测试删除教师
     */
    @Test
    public void testDeleteTeacher() {
        List<String> ids = new ArrayList<>();
        ids.add("2");
        ids.add("3");
        teacherService.deleteTeacherByIds(ids);
    }
}
