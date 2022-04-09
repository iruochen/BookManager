package com.ruochen.service;

import com.ruochen.domain.Admin;

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
}
