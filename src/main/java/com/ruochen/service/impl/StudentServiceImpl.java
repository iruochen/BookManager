package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Student;
import com.ruochen.domain.User;
import com.ruochen.mapper.StudentMapper;
import com.ruochen.mapper.UserMapper;
import com.ruochen.service.StudentService;
import com.ruochen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Student> selectStudentAll(Integer pageNum, Integer pageSize, Student student) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentMapper.selectStudentAll(student);
        return new PageInfo<>(students);
    }

    @Override
    public Integer addStudent(Student student, User user) {
        if (null != userMapper.selectUserByUsername(user.getUsername())) {
            // 用户名已存在
            return Constants.USER_EXIST_CODE;
        }
        if (null != studentMapper.selectStudentByStuId(student.getStuId())) {
            // 学号已存在
            return Constants.STUDENT_EXIST_CODE;
        }
        userMapper.addUser(user);
        student.setUserId(user.getId());
        studentMapper.addStudent(student);
        return Constants.OK_CODE;
    }

    @Override
    public Student selectStudentById(Integer id) {
        return studentMapper.selectStudentById(id);
    }

    @Override
    public Integer updateStudent(Student student, User user, String oldStuId, String oldUsername) {
        if (!user.getUsername().equals(oldUsername) && null != userMapper.selectUserByUsername(user.getUsername())) {
            // 用户名已存在
            return Constants.USER_EXIST_CODE;
        }
        if (!student.getStuId().equals(oldStuId) && null != studentMapper.selectStudentByStuId(student.getStuId())) {
            // 学号已存在
            return Constants.STUDENT_EXIST_CODE;
        }
        user.setId(student.getUserId());
        userMapper.updateUser(user);
        studentMapper.updateStudent(student);
        return Constants.OK_CODE;
    }

    @Override
    public void deleteStudentByIds(List<String> ids) {
        for (String id : ids) {
            String userId = studentMapper.selectStudentUserIdById(Integer.parseInt(id));
            // 删除学生信息
            studentMapper.deleteStudentById(Integer.parseInt(id));
            // 删除关联用户
            userMapper.deleteUserById(Integer.parseInt(userId));
        }
    }

    @Override
    public Student selectStudentByStuId(String stuId) {
        return null;
    }

    @Override
    public Student selectStudentByUserId(Integer userId) {
        return studentMapper.selectStudentByUserId(userId);
    }

    @Override
    public Integer studentSetting(Student student, String oldStuId, Integer userId) {
        if (student.getId() == null) {
            // 添加
            student.setUserId(userId);
            studentMapper.addStudent(student);
            return Constants.OK_CODE;
        }
        // 更新
        if (!student.getStuId().equals(oldStuId) && null != studentMapper.selectStudentByStuId(student.getStuId())) {
            // 学号已存在
            return Constants.STUDENT_EXIST_CODE;
        }
        studentMapper.updateStudent(student);
        return Constants.OK_CODE;
    }
}
