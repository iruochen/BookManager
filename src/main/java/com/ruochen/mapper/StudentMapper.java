package com.ruochen.mapper;

import com.ruochen.domain.Student;
import org.apache.ibatis.annotations.*;

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

    /**
     * 根据ID 查询学生
     *
     * @param id
     * @return
     */
    Student selectStudentById(Integer id);

    /**
     * 更新学生信息
     *
     * @param student
     */
    @Update("update student set stu_id = #{stuId}, stu_name = #{stuName}, stu_sex = #{stuSex}, major = #{stuMajor}, class = #{stuClass}, dept_id = #{deptId} where id = #{id};")
    void updateStudent(Student student);

    /**
     * 根据ID 查询 userId
     *
     * @param id
     * @return
     */
    @Select("select user_id from student where id = #{id};")
    String selectStudentUserIdById(Integer id);

    /**
     * 根据ID 删除学生
     *
     * @param id
     */
    @Delete("delete from student where id = #{id}")
    void deleteStudentById(Integer id);

    /**
     * 根据学号查询学生
     *
     * @param stuId
     * @return
     */
    Student selectStudentByStuId(String stuId);

    /**
     * 根据用户ID查询学生
     *
     * @param userId
     * @return
     */
    Student selectStudentByUserId(Integer userId);
}
