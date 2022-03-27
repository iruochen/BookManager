package com.ruochen.mapper;

import com.ruochen.domain.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TeacherMapper {
    /**
     * 查询所有教师信息
     *
     * @param teacher
     * @return
     */
    List<Teacher> selectTeacherAll(Teacher teacher);

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
}
