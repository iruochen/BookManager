package com.ruochen.mapper;

import com.ruochen.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

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
}
