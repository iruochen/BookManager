package com.ruochen.controller;

import com.ruochen.domain.Department;
import com.ruochen.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取所有院系信息
     *
     * @return
     */
    @RequestMapping("selectDeptAll")
    @ResponseBody
    public List<Department> selectDeptAll() {
        return departmentService.selectDeptAll();
    }
}
