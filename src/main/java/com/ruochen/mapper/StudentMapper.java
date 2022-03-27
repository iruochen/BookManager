package com.ruochen.mapper;

import com.ruochen.domain.Student;

import java.util.List;

public interface StudentMapper {
    /**
     * 查询所有学生信息
     *
     * @param student
     * @return
     */
    List<Student> selectStudentAll(Student student);
}
