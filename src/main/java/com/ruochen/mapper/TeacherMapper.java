package com.ruochen.mapper;

import com.ruochen.domain.Teacher;

import java.util.List;

public interface TeacherMapper {
    /**
     * 查询所有教师信息
     *
     * @param teacher
     * @return
     */
    List<Teacher> selectTeacherAll(Teacher teacher);
}
