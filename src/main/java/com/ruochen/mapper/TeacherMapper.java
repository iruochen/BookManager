package com.ruochen.mapper;

import com.ruochen.domain.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TeacherMapper {
    /**
     * 查询所有教师信息
     *
     * @param teacher
     * @param adminDeptId
     * @return
     */
    List<Teacher> selectTeacherAll(@Param("teacher") Teacher teacher, @Param("adminDeptId") Integer adminDeptId);

    /**
     * 添加教师
     *
     * @param teacher
     */
    @Insert("insert into teacher (id, user_id, tea_id, tea_name, tea_sex, dept_id) VALUES (null, #{userId}, #{teaId}, #{teaName}, #{teaSex}, #{deptId})")
    void addTeacher(Teacher teacher);

    /**
     * 根据ID查找教师
     *
     * @param id
     * @return
     */
    Teacher selectTeacherById(Integer id);

    /**
     * 修改教师信息
     *
     * @param teacher
     */
    @Update("update teacher set tea_id = #{teaId}, tea_name = #{teaName}, tea_sex = #{teaSex}, dept_id = #{deptId} where id = #{id}")
    void updateTeacher(Teacher teacher);

    /**
     * 根据ID 查询 userId
     *
     * @param id
     * @return
     */
    @Select("select user_id from teacher where id = #{id};")
    String selectTeacherUserIdById(Integer id);

    /**
     * 根据ID 删除教师
     *
     * @param id
     */
    @Delete("delete from teacher where id = #{id}")
    void deleteTeacherById(Integer id);

    /**
     * 根据工号查询教师
     *
     * @param teaId
     * @return
     */
    Teacher selectTeacherByTeaId(String teaId);

    /**
     * 根据用户ID查询教师
     *
     * @param userId
     * @return
     */
    Teacher selectTeacherByUserId(Integer userId);
}
