package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Admin;
import com.ruochen.domain.User;
import com.ruochen.service.AdminService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 获取所有管理员信息
     *
     * @param pageNum
     * @param pageSize
     * @param admin
     * @param request
     * @return
     */
    @RequestMapping("selectAdminAll")
    @ResponseBody
    public DataInfo adminAll(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, Admin admin, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        PageInfo<Admin> pageInfo = adminService.selectAdminAllExcludeCurrent(pageNum, pageSize, admin, user.getId());
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }
}
