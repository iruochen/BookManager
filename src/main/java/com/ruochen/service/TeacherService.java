package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Teacher;

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
}
