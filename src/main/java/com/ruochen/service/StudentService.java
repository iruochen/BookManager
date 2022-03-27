package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Student;

public interface StudentService {
    /**
     * 查询所有学生信息
     *
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @param student  约束条件
     * @return
     */
    PageInfo<Student> selectStudentAll(Integer pageNum, Integer pageSize, Student student);
}
