package com.ruochen.service;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Admin;
import com.ruochen.domain.Student;

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
     * @return
     */
    PageInfo<Admin> selectAdminAll(Integer pageNum, Integer pageSize, Admin admin);
}
