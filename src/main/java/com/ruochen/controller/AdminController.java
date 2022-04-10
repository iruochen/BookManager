package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Admin;
import com.ruochen.domain.User;
import com.ruochen.service.AdminService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /**
     * 根据ID 查询管理员信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("selectAdminById")
    public String selectAdminById(Integer id, Model model) {
        Admin admin = adminService.selectAdminById(id);
        model.addAttribute("admin", admin);
        return "admin/updateAdmin";
    }

    /**
     * 修改管理员信息
     *
     * @param admin
     * @param user
     * @param oldAdminId
     * @param oldUsername
     * @return
     */
    @RequestMapping("updateAdminSubmit")
    @ResponseBody
    public DataInfo updateAdminSubmit(Admin admin, User user, String oldAdminId, String oldUsername) {
        Integer code = adminService.updateAdmin(admin, user, oldAdminId, oldUsername);
        return DataInfo.ok(code);
    }
}
