package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;
import com.ruochen.mapper.TeacherMapper;
import com.ruochen.mapper.UserMapper;
import com.ruochen.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Teacher> selectTeacherAll(Integer pageNum, Integer pageSize, Teacher teacher) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teachers = teacherMapper.selectTeacherAll(teacher);
        return new PageInfo<>(teachers);
    }

    @Override
    public void addTeacher(Teacher teacher, User user) {
        userMapper.addUser(user);
        teacher.setUserId(user.getId());
        teacherMapper.addTeacher(teacher);
    }

    @Override
    public Teacher selectTeacherById(Integer id) {
        return teacherMapper.selectTeacherById(id);
    }

    @Override
    public void updateTeacher(Teacher teacher, User user) {
        user.setId(teacher.getUserId());
        userMapper.updateUser(user);
        teacherMapper.updateTeacher(teacher);
    }
}
