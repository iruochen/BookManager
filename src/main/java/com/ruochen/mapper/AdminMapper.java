package com.ruochen.mapper;

import com.ruochen.domain.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminMapper {

    /**
     * 查询所有管理员
     *
     * @return
     */
    List<Admin> selectAdminAllExcludeCurrent(Admin admin);

    /**
     * 添加管理员
     *
     * @param admin
     */
    @Insert("insert into admin (id, user_id, admin_id, admin_name, sex) values (null, #{userId}, #{adminId}, #{adminName}, #{sex});")
    void addAdmin(Admin admin);

    /**
     * 根据ID 查询管理员
     *
     * @param id
     * @return
     */
    Admin selectAdminById(Integer id);

    /**
     * 根据用户ID 查询管理员
     *
     * @param userId
     * @return
     */
    Admin selectAdminByUserId(Integer userId);

    /**
     * 根据管理员ID 查询管理员
     *
     * @param adminId
     * @return
     */
    Admin selectAdminByAdminId(String adminId);

    /**
     * 更新管理员信息
     *
     * @param admin
     */
    @Update("update admin set admin_id = #{adminId}, admin_name = #{adminName}, sex = #{sex} where id = #{id};")
    void updateAdmin(Admin admin);

    /**
     * 根据ID 查询userId
     *
     * @param id
     * @return
     */
    @Select("select user_id from admin where id = #{id}")
    String selectAdminUserIdById(int id);

    /**
     * 根据ID 删除管理员
     *
     * @param id
     */
    @Delete("delete from admin where id = #{id}")
    void deleteAdminById(int id);
}
