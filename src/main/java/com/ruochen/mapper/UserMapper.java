package com.ruochen.mapper;

import com.ruochen.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface UserMapper {
    /**
     * 插入用户信息
     *
     * @param user
     */
    @Insert("insert into user (username, password, role) values (#{username}, #{password}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addUser(User user);
}
