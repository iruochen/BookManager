package com.ruochen.controller;

import com.ruochen.domain.Admin;
import com.ruochen.domain.Student;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;
import com.ruochen.service.AdminService;
import com.ruochen.service.StudentService;
import com.ruochen.service.TeacherService;
import com.ruochen.service.UserService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;

    /**
     * 用户个人信息页面跳转
     *
     * @return
     */
    @GetMapping("userSetting")
    public String userSetting(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole() == 0) {
            // 管理员
            Admin admin =  adminService.selectAdminByUserId(user.getId());
            model.addAttribute("admin", admin);
            return "admin/admin-setting";
        } else if (user.getRole() == 1) {
            // 学生
            Student student = studentService.selectStudentByUserId(user.getId());
            model.addAttribute("student", student);
            return "student/student-setting";
        } else if (user.getRole() == 2) {
            // 教师
            Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
            model.addAttribute("teacher", teacher);
            return "teacher/teacher-setting";
        }
        return "404";
    }

    /**
     * 学生个人信息修改
     *
     * @param student
     * @param oldStuId
     * @param request
     * @return
     */
    @RequestMapping("studentSettingSubmit")
    @ResponseBody
    public DataInfo studentSettingSubmit(Student student, String oldStuId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer code = studentService.studentSetting(student, oldStuId, user.getId());
        return DataInfo.ok(code);
    }

    /**
     * 教师个人信息修改
     *
     * @param teacher
     * @param oldTeaId
     * @param request
     * @return
     */
    @RequestMapping("teacherSettingSubmit")
    @ResponseBody
    public DataInfo teacherSettingSubmit(Teacher teacher, String oldTeaId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer code = teacherService.teacherSetting(teacher, oldTeaId, user.getId());
        return DataInfo.ok(code);
    }

    /**
     * 管理员个人信息修改
     *
     * @param admin
     * @param oldAdminId
     * @param request
     * @return
     */
    @RequestMapping("adminSettingSubmit")
    @ResponseBody
    public DataInfo adminSettingSubmit(Admin admin, String oldAdminId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Integer code = adminService.adminSetting(admin, oldAdminId, user.getId());
        return DataInfo.ok(code);
    }

    /**
     * 修改密码页面跳转
     *
     * @return
     */
    @RequestMapping("updatePassword")
    public String updatePassword(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "update-password";
    }

    /**
     * 修改密码
     *
     * @param user
     * @param oldPassword
     * @return
     */
    @RequestMapping("updatePasswordSubmit")
    @ResponseBody
    public DataInfo updatePasswordSubmit(User user, String oldPassword) {
        Integer code = userService.updatePassword(user, oldPassword);
        return DataInfo.ok(code);
    }
}
