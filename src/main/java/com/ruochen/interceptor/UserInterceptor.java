package com.ruochen.interceptor;

import com.ruochen.domain.Admin;
import com.ruochen.domain.Student;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;
import com.ruochen.service.AdminService;
import com.ruochen.service.StudentService;
import com.ruochen.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
        Student student = studentService.selectStudentByUserId(user.getId());
        Admin admin = adminService.selectAdminByUserId(user.getId());

        if ((null != teacher) || (null != student) || (null != admin)) {
            // 个人信息已完善
            return true;
        } else {
            // 个人信息未完善
            request.getRequestDispatcher("/userSetting").forward(request, response);
            return false;
        }
    }
}
