package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;
import com.ruochen.service.TeacherService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherInfoController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 教师管理首页
     *
     * @return
     */
    @GetMapping("teacherIndex")
    public String teacherManageIndex() {
        return "teacher/teacherIndex";
    }

    /**
     * 获取所有教师信息
     *
     * @param pageNum
     * @param pageSize
     * @param teacher
     * @return
     */
    @RequestMapping("selectTeacherAll")
    @ResponseBody
    public DataInfo teacherAll(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, Teacher teacher) {
        PageInfo<Teacher> pageInfo = teacherService.selectTeacherAll(pageNum, pageSize, teacher);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 教师添加页面跳转
     *
     * @return
     */
    @GetMapping("teacherAdd")
    public String teacherAdd() {
        return "teacher/teacherAdd";
    }


    /**
     * 添加教师
     *
     * @param teacher
     * @param user
     * @return
     */
    @RequestMapping("addTeacherSubmit")
    @ResponseBody
    public DataInfo addTeacherSubmit(Teacher teacher, User user) {
        teacherService.addTeacher(teacher, user);
        return DataInfo.ok();
    }
}
