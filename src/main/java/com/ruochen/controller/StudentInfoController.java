package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Student;
import com.ruochen.service.StudentService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentInfoController {

    @Autowired
    private StudentService studentService;

    /**
     * 学生管理首页
     *
     * @return
     */
    @GetMapping("studentIndex")
    public String studentManageIndex() {
        return "student/studentIndex";
    }

    /**
     * 获取所有学生信息
     *
     * @param pageNum
     * @param pageSize
     * @param student
     * @return
     */
    @RequestMapping("selectStudentAll")
    @ResponseBody
    public DataInfo studentAll(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, Student student) {
        PageInfo<Student> pageInfo = studentService.selectStudentAll(pageNum, pageSize, student);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }
}