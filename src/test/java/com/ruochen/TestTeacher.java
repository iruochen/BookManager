package com.ruochen;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;
import com.ruochen.mapper.TeacherMapper;
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

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 测试查询所有教师信息
     */
    @Test
    public void testSelectTeacherAll() {
        Teacher teacher = new Teacher();
        // teacher.setTeaName("教");
        // teacher.setDeptId(2);
        /*
        PageInfo<Teacher> pageInfo = teacherService.selectTeacherAll(1, 10, teacher);
        for (Teacher tea : pageInfo.getList()) {
            System.out.println(tea);
        }
        */
    }

    /**
     * 测试添加教师信息
     */
    @Test
    public void testAddTeacher() {
        User user = new User();
        user.setUsername("adm");
        user.setPassword("test");
        user.setRole(2);
        Teacher teacher = new Teacher();
        teacher.setTeaId("1010");
        teacher.setTeaName("测试");
        teacher.setTeaSex("女");
        teacher.setDeptId(2);
        Integer code = teacherService.addTeacher(teacher, user);
        System.out.println(code);
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
        String oldTeaId = "1010";
        String oldUsername = "updateruochen";
        User user = new User();
        user.setUsername("update");
        user.setPassword("123");
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setUserId(3);
        teacher.setTeaId("1010");
        teacher.setTeaName("更新教师");
        teacher.setTeaSex("男");
        teacher.setDeptId(6);
        Integer code = teacherService.updateTeacher(teacher, user, oldTeaId, oldUsername);
        System.out.println(code);
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

    /**
     * 测试根据用户ID查询教师
     */
    @Test
    public void testSelectTeacherByUserId() {
        Teacher teacher = teacherMapper.selectTeacherByUserId(11);
        System.out.println(teacher);
    }
}
