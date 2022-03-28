package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Teacher;
import com.ruochen.domain.User;
import com.ruochen.service.TeacherService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

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

    /**
     * 根据ID查找教师
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("selectTeacherById")
    public String selectTeacherById(Integer id, Model model) {
        Teacher teacher = teacherService.selectTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacher/updateTeacher";
    }

    /**
     * 修改教师信息
     *
     * @param teacher
     * @param user
     * @return
     */
    @RequestMapping("updateTeacherSubmit")
    @ResponseBody
    public DataInfo updateTeacherSubmit(Teacher teacher, User user) {
        teacherService.updateTeacher(teacher, user);
        return DataInfo.ok();
    }

    /**
     * 根据ID 删除教师
     *
     * @param ids
     * @return
     */
    @RequestMapping("deleteTeacher")
    @ResponseBody
    public DataInfo deleteTeacherByIds(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        teacherService.deleteTeacherByIds(list);
        return DataInfo.ok();
    }
}
