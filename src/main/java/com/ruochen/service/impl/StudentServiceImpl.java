package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Student;
import com.ruochen.domain.User;
import com.ruochen.mapper.StudentMapper;
import com.ruochen.mapper.UserMapper;
import com.ruochen.service.StudentService;
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
    public void addStudent(Student student, User user) {
        userMapper.addUser(user);
        student.setUserId(user.getId());
        studentMapper.addStudent(student);
    }
}
