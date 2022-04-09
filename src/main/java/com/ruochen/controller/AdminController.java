package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Admin;
import com.ruochen.domain.Student;
import com.ruochen.service.AdminService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员管理首页
     *
     * @return
     */
    @GetMapping("adminIndex")
    public String adminManageIndex() {
        return "admin/adminIndex";
    }

    @RequestMapping("selectAdminAll")
    @ResponseBody
    public DataInfo adminAll(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, Admin admin) {
        PageInfo<Admin> pageInfo = adminService.selectAdminAll(pageNum, pageSize, admin);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }
}
