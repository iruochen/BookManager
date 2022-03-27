package com.ruochen;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Student;
import com.ruochen.domain.User;
import com.ruochen.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestStudent {
    @Autowired
    private StudentService studentService;


    /**
     * 测试查询所有学生信息
     */
    @Test
    public void testSelectTeacherAll() {
        Student student = new Student();
        student.setStuName("学");
        student.setStuId("6");
        PageInfo<Student> pageInfo = studentService.selectStudentAll(1, 1, student);
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
        user.setUsername("testStu");
        user.setPassword("123456");
        user.setRole(1);
        Student student = new Student();
        student.setStuId("1111");
        student.setStuName("测试");
        student.setStuSex("男");
        student.setDeptId(5);
        student.setStuMajor("计科");
        student.setStuClass("1820544");
        studentService.addStudent(student, user);
    }

}
