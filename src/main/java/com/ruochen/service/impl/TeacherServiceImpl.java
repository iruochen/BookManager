package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;
import com.ruochen.mapper.TeacherMapper;
import com.ruochen.mapper.UserMapper;
import com.ruochen.service.TeacherService;
import com.ruochen.utils.Constants;
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
    public Integer addTeacher(Teacher teacher, User user) {
        if (null != userMapper.selectUserByUsername(user.getUsername())) {
            // 用户名已存在
            return Constants.USER_EXIST_CODE;
        }
        if (null != teacherMapper.selectTeacherByTeaId(teacher.getTeaId())) {
            // 工号已存在
            return Constants.TEACHER_EXIST_CODE;
        }
        userMapper.addUser(user);
        teacher.setUserId(user.getId());
        teacherMapper.addTeacher(teacher);
        return Constants.OK_CODE;
    }

    @Override
    public Teacher selectTeacherById(Integer id) {
        return teacherMapper.selectTeacherById(id);
    }

    @Override
    public Integer updateTeacher(Teacher teacher, User user, String oldTeaId, String oldUsername) {
        if (!user.getUsername().equals(oldUsername) && null != userMapper.selectUserByUsername(user.getUsername())) {
            // 用户名已存在
            return Constants.USER_EXIST_CODE;
        }
        if (!teacher.getTeaId().equals(oldTeaId) && null != teacherMapper.selectTeacherByTeaId(teacher.getTeaId())) {
            // 工号已存在
            return Constants.TEACHER_EXIST_CODE;
        }
        user.setId(teacher.getUserId());
        userMapper.updateUser(user);
        teacherMapper.updateTeacher(teacher);
        return Constants.OK_CODE;
    }

    @Override
    public void deleteTeacherByIds(List<String> ids) {
        for (String id : ids) {
            String userId = teacherMapper.selectTeacherUserIdById(Integer.parseInt(id));
            // 删除教师信息
            teacherMapper.deleteTeacherById(Integer.parseInt(id));
            // 删除关联用户信息
            userMapper.deleteUserById(Integer.parseInt(userId));
        }
    }

    @Override
    public Teacher selectTeacherByUserId(Integer id) {
        return teacherMapper.selectTeacherByUserId(id);
    }

    @Override
    public Integer teacherSetting(Teacher teacher, String oldTeaId, Integer userId) {
        Teacher tea = teacherMapper.selectTeacherByTeaId(teacher.getTeaId());
        if (teacher.getId() == null) {
            // 添加
            if (null != tea) {
                return Constants.STUDENT_EXIST_CODE;
            } else {
                teacher.setUserId(userId);
                teacherMapper.addTeacher(teacher);
                return Constants.OK_CODE;
            }
        }
        // 更新
        if (!teacher.getTeaId().equals(oldTeaId) && null != tea) {
            // 工号已存在
            return Constants.STUDENT_EXIST_CODE;
        }
        teacherMapper.updateTeacher(teacher);
        return Constants.OK_CODE;
    }
}
