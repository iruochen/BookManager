package com.ruochen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Admin;
import com.ruochen.domain.User;
import com.ruochen.mapper.AdminMapper;
import com.ruochen.mapper.UserMapper;
import com.ruochen.service.AdminService;
import com.ruochen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

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

    @Override
    public PageInfo<Admin> selectAdminAllExcludeCurrent(Integer pageNum, Integer pageSize, Admin admin, Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        // 当前管理员ID
        Admin adminCurrent = adminMapper.selectAdminByUserId(userId);
        if (adminCurrent == null) {
            // 当前user未与admin关联
            admin.setId(0);
        } else {
            admin.setId(adminCurrent.getId());
        }
        List<Admin> admins = adminMapper.selectAdminAllExcludeCurrent(admin);
        return new PageInfo<>(admins);
    }

    @Override
    public Admin selectAdminById(Integer id) {
        return adminMapper.selectAdminById(id);
    }

    @Override
    public Integer updateAdmin(Admin admin, User user, String oldAdminId, String oldUsername) {
        if (!user.getUsername().equals(oldUsername) && null != userMapper.selectUserByUsername(user.getUsername())) {
            // 用户名已存在
            return Constants.USER_EXIST_CODE;
        }
        if (!admin.getAdminId().equals(oldAdminId) && null != adminMapper.selectAdminByAdminId(admin.getAdminId())) {
            // 工号已存在
            return Constants.ADMIN_EXIST_CODE;
        }
        user.setId(admin.getUserId());
        userMapper.updateUser(user);
        adminMapper.updateAdmin(admin);
        return Constants.OK_CODE;
    }
}
