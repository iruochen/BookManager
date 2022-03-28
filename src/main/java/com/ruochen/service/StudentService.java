package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Student;
import com.ruochen.domain.User;

import java.util.List;

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

    /**
     * 添加学生信息
     *
     * @param student
     * @param user
     */
    void addStudent(Student student, User user);

    /**
     * 根据ID 查询学生信息
     *
     * @param id
     * @return
     */
    Student selectStudentById(Integer id);

    /**
     * 修改学生信息
     *
     * @param student
     * @param user
     */
    void updateStudent(Student student, User user);


    /**
     * 根据ID 删除学生
     *
     * @param ids
     */
    void deleteStudentByIds(List<String> ids);
}
