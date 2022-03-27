package com.ruochen.service;

import com.ruochen.domain.Department;

import java.util.List;

public interface DepartmentService {
    /**
     * 查询所有院系信息
     *
     * @return
     */
    List<Department> selectDeptAll();
}
