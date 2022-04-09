package com.ruochen.service.impl;

import com.ruochen.domain.Admin;
import com.ruochen.mapper.AdminMapper;
import com.ruochen.service.AdminService;
import com.ruochen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Integer adminSetting(Admin admin, String oldAdminId, Integer userId) {
        Admin _admin = adminMapper.selectAdminByAdminId(admin.getAdminId());
        if (admin.getId() == null) {
            // 添加
            if (null != _admin) {
                // 工号已存在
                return Constants.STUDENT_EXIST_CODE;
            } else {
                admin.setUserId(userId);
                adminMapper.addAdmin(admin);
                return Constants.OK_CODE;
            }
        }
        // 更新
        if (!admin.getAdminId().equals(oldAdminId) && null != _admin) {
            // 工号已存在
            return Constants.STUDENT_EXIST_CODE;
        }
        adminMapper.updateAdmin(admin);
        return Constants.OK_CODE;
    }

    @Override
    public Admin selectAdminByUserId(Integer userId) {
        return adminMapper.selectAdminByUserId(userId);
    }
}
