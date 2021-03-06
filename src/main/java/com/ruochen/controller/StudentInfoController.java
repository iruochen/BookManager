package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Student;
import com.ruochen.domain.User;
import com.ruochen.service.StudentService;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

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
    public DataInfo studentAll(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize, Student student, HttpServletRequest request) {
        PageInfo<Student> pageInfo = studentService.selectStudentAll(pageNum, pageSize, student, request);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 学生添加页面跳转
     *
     * @return
     */
    @GetMapping("studentAdd")
    public String studentAdd(Model model, HttpServletRequest request) {
        Integer adminDeptId = (Integer) request.getSession().getAttribute("adminDeptId");
        model.addAttribute("adminDeptId", adminDeptId);
        return "student/studentAdd";
    }

    /**
     * 添加学生
     *
     * @param student
     * @param user
     * @return
     */
    @RequestMapping("addStudentSubmit")
    @ResponseBody
    public DataInfo addStudentSubmit(Student student, User user) {
        Integer code = studentService.addStudent(student, user);
        return DataInfo.ok(code);
    }

    /**
     * 根据ID 查询学生信息
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("selectStudentById")
    public String selectStudentById(Integer id, Model model) {
        Student student = studentService.selectStudentById(id);
        model.addAttribute("student", student);
        return "student/updateStudent";
    }

    /**
     * 修改学生信息
     *
     * @param student
     * @param user
     * @param oldStuId
     * @param oldUsername
     * @return
     */
    @RequestMapping("updateStudentSubmit")
    @ResponseBody
    public DataInfo updateStudentSubmit(Student student, User user, String oldStuId, String oldUsername) {
        Integer code = studentService.updateStudent(student, user, oldStuId, oldUsername);
        return DataInfo.ok(code);
    }

    /**
     * 根据ID 删除学生
     *
     * @param ids
     * @return
     */
    @RequestMapping("deleteStudent")
    @ResponseBody
    public DataInfo deleteStudentByIds(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        studentService.deleteStudentByIds(list);
        return DataInfo.ok();
    }
}
