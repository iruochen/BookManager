package com.ruochen;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Teacher;
import com.ruochen.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

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
        teacher.setTeaName("教");
        teacher.setDeptId(2);
        PageInfo<Teacher> pageInfo = teacherService.selectTeacherAll(1, 1, teacher);
        for (Teacher tea : pageInfo.getList()) {
            System.out.println(tea);
        }
    }

}
