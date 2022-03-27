package com.ruochen.service.impl;

import com.ruochen.domain.Department;
import com.ruochen.mapper.DepartmentMapper;
import com.ruochen.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> selectDeptAll() {
        return departmentMapper.selectDeptAll();
    }
}
