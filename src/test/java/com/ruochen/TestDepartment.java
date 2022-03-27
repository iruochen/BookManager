package com.ruochen;

import com.ruochen.domain.Department;
import com.ruochen.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestDepartment {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testSelectDeptAll() {
        List<Department> deptList = departmentService.selectDeptAll();
        for (Department department : deptList) {
            System.out.println(department);
        }
    }
}
