package com.ruochen;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Student;
import com.ruochen.domain.User;
import com.ruochen.mapper.StudentMapper;
import com.ruochen.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestStudent {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;


    /**
     * 测试查询所有学生信息
     */
    @Test
    public void testSelectTeacherAll() {
        Student student = new Student();
        // student.setStuName("学");
        // student.setStuId("6");
        PageInfo<Student> pageInfo = studentService.selectStudentAll(1, 10, student);
        for (Student stu : pageInfo.getList()) {
            System.out.println(stu);
        }
    }

    /**
     * 测试添加学生
     */
    @Test
    public void testAddStudent() {
        User user = new User();
        user.setUsername("testAdd");
        user.setPassword("123456");
        user.setRole(1);
        Student student = new Student();
        student.setStuId("2");
        student.setStuName("测试");
        student.setStuSex("男");
        student.setDeptId(5);
        student.setStuMajor("计科");
        student.setStuClass("1820544");
        Integer code = studentService.addStudent(student, user);
        System.out.println(code);
    }

    /**
     * 测试根据ID 查询学生信息
     */
    @Test
    public void testSelectStudentById() {
        Student student = studentService.selectStudentById(2);
        System.out.println(student);
    }

    /**
     * 测试更新学生信息
     */
    @Test
    public void testUpdateStudent() {
        String oldStuId = "666";
        String oldUsername = "stu";
        User user = new User();
        user.setUsername("student");
        user.setPassword("123");
        Student student = new Student();
        student.setId(1);
        student.setUserId(2);
        student.setStuId("6666");
        student.setStuName("student");
        student.setStuSex("男");
        student.setDeptId(3);
        student.setStuMajor("自动");
        student.setStuClass("1830333");
        Integer code = studentService.updateStudent(student, user, oldStuId, oldUsername);
        System.out.println(code);
    }

    /**
     * 测试删除学生信息
     */
    @Test
    public void testDeleteStudent() {
        List<String> ids = new ArrayList<>();
        ids.add("2");
        ids.add("3");
        studentService.deleteStudentByIds(ids);
    }

    /**
     * 测试根据学号查询学生
     */
    @Test
    public void testSelectStudentByStuId() {
        Student student = studentMapper.selectStudentByStuId("666");
        System.out.println(student);
    }

    /**
     * 测试根据用户ID查询学生
     */
    @Test
    public void testSelectStudentByUserId() {
        Student student = studentMapper.selectStudentByUserId(23);
        System.out.println(student);
    }
}
