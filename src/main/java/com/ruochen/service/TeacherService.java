package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;

public interface TeacherService {
    /**
     * 查询所有教师信息
     *
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @param teacher  约束条件
     * @return pageInfo
     */
    PageInfo<Teacher> selectTeacherAll(Integer pageNum, Integer pageSize, Teacher teacher);

    /**
     * 添加教师
     *
     * @param teacher
     */
    void addTeacher(Teacher teacher, User user);

    /**
     * 根据ID查询教师信息
     *
     * @param id
     * @return
     */
    Teacher selectTeacherById(Integer id);

    /**
     * 修改教师信息
     *
     * @param teacher
     * @param user
     */
    void updateTeacher(Teacher teacher, User user);
}
