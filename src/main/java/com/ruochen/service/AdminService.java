package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Admin;
import com.ruochen.domain.User;

public interface AdminService {
    /**
     * 管理员个人信息修改
     *
     * @param admin
     * @param oldAdminId
     * @param userId
     * @return
     */
    Integer adminSetting(Admin admin, String oldAdminId, Integer userId);

    /**
     * 根据用户ID 查询管理员
     *
     * @param userId
     * @return
     */
    Admin selectAdminByUserId(Integer userId);

    /**
     * 查询所有管理员信息
     *
     * @param pageNum
     * @param pageSize
     * @param admin
     * @param userId
     * @return
     */
    PageInfo<Admin> selectAdminAllExcludeCurrent(Integer pageNum, Integer pageSize, Admin admin, Integer userId);

    /**
     * 根据ID 查询管理员信息
     *
     * @param id
     * @return
     */
    Admin selectAdminById(Integer id);

    /**
     * 更新管理员信息
     *
     * @param admin
     * @param user
     * @param oldAdminId
     * @param oldUsername
     * @return
     */
    Integer updateAdmin(Admin admin, User user, String oldAdminId, String oldUsername);
}
