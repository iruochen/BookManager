package com.ruochen.mapper;

import com.ruochen.domain.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    /**
     * 插入用户信息
     *
     * @param user
     */
    @Insert("insert into user (username, password, role) values (#{username}, #{password}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addUser(User user);

    /**
     * 修改用户信息
     *
     * @param user
     */
    @Update("update user set username = #{username}, password = #{password} where id = #{id}")
    void updateUser(User user);

    /**
     * 根据ID 删除用户
     *
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteUserById(int id);

    /**
     * 根据用户名查询用户
     *
     * @param username
     */
    @Select("select * from user where username = #{username};")
    User selectUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @Select("select * from user u where u.username = #{username} and u.password = #{password} and u.role = ${type};")
    User selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password, @Param("type") Integer type);
}
