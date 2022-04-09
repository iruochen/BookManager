package com.ruochen.controller;

import com.ruochen.domain.Student;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;
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
            return "404";
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
}
