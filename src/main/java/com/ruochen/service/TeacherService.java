package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;

import java.util.List;

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
    Integer addTeacher(Teacher teacher, User user);

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
     * @param oldTeaId
     * @param oldUsername
     */
    Integer updateTeacher(Teacher teacher, User user, String oldTeaId, String oldUsername);

    /**
     * 根据ID 删除教师
     *
     * @param ids
     */
    void deleteTeacherByIds(List<String> ids);

    /**
     * 根据用户ID 查询教师
     *
     * @param id
     * @return
     */
    Teacher selectTeacherByUserId(Integer id);

    /**
     * 教师个人信息设置
     *
     * @param teacher
     * @param oldTeaId
     * @param userId
     * @return
     */
    Integer teacherSetting(Teacher teacher, String oldTeaId, Integer userId);
}
