package com.ruochen;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Student;
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

}
