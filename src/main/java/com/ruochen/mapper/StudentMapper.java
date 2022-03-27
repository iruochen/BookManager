package com.ruochen.mapper;

import com.ruochen.domain.Student;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface StudentMapper {
    /**
     * 查询所有学生信息
     *
     * @param student
     * @return
     */
    List<Student> selectStudentAll(Student student);

    /**
     * 添加学生信息
     *
     * @param student
     */
    @Insert("insert into student (id, user_id, stu_id, stu_name, stu_sex, dept_id, major, class) VALUES (null, #{userId}, #{stuId}, #{stuName}, #{stuSex}, #{deptId}, #{stuMajor}, #{stuClass})")
    void addStudent(Student student);
}
