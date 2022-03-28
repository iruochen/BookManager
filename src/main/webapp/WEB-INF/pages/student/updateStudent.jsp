<%--
  Created by IntelliJ IDEA.
  User: ruochen
  Date: 2022/3/27
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>修改学生</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.6.3/css/layui.css" media="all">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
        <style>
            body {
                background-color: #ffffff;
            }
        </style>
    </head>
    <body>
        <div class="layui-form layuimini-form">
            <input type="hidden" name="id" value="${student.id}">
            <input type="hidden" name="userId" value="${student.userId}">
            <input type="hidden" name="oldStuId" value="${student.stuId}">
            <input type="hidden" name="oldUsername" value="${student.user.username}">
            <div class="layui-form-item">
                <label class="layui-form-label required">学号</label>
                <div class="layui-input-block">
                    <input type="text" name="stuId" lay-verify="required|number|stuid" value="${student.stuId}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">学生姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="stuName" lay-verify="required" value="${student.stuName}"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="stuSex" value="男"
                           title="男"  ${"男" eq student.stuSex?"checked='checked'":''} />
                    <input type="radio" name="stuSex" value="女"
                           title="女"  ${"女" eq student.stuSex?"checked='checked'":''} />
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">院系</label>
                <div class="layui-input-block">
                    <select name="deptId" id="deptId" lay-verify="required">
                        <option value="${student.deptId}">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">专业</label>
                <div class="layui-input-block">
                    <input type="text" name="stuMajor" lay-verify="required" value="${student.stuMajor}"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">班级</label>
                <div class="layui-input-block">
                    <input type="text" name="stuClass" lay-verify="required|number|stuclass" value="${student.stuClass}"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" lay-verify="required|username" value="${student.user.username}"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">密码</label>
                <div class="layui-input-block">
                    <input type="text" name="password" lay-verify="required|pwd" value="${student.user.password}"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认修改</button>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
        <script>
            layui.use(['form'], function () {
                var form = layui.form,
                    layer = layui.layer,
                    $ = layui.$;

                // 自定义表单验证
                form.verify({
                    username: [
                        /[A-Za-z0-9_\-\u4e00-\u9fa5]+/,
                        '用户不符合规范，请重新输入'
                    ],
                    pwd: [
                        /^[\S]{6,12}$/,
                        '密码必须6到12位，且不能出现空格'
                    ],
                    stuid: [
                        /^\d{9}$/,
                        '学号输入错误，请重新输入'
                    ],
                    stuclass: [
                        /^\d{7}$/,
                        '班级输入错误，请重新输入'
                    ]
                })

                // 动态获取院系的数据
                $.get("selectDeptAll", {}, function (data) {
                    //获取图书类型的值
                    var deptId = $('#deptId')[0].value;
                    var list = data;
                    var select = document.getElementById("deptId");
                    if (list != null || list.size() > 0) {
                        for (var c in list) {
                            var option = document.createElement("option");
                            option.setAttribute("value", list[c].id);
                            option.innerText = list[c].deptName;
                            select.appendChild(option);
                            //如果类型和循环到的类型iD一致，选中
                            if (list[c].id == deptId) {
                                option.setAttribute("selected", "selected");
                                layui.form.render('select');
                            }
                        }
                    }
                    form.render('select');
                }, "json")

                // 监听提交
                form.on('submit(saveBtn)', function (data) {
                    var datas = data.field;//form单中的数据信息
                    //向后台发送数据提交添加
                    $.ajax({
                        url: "updateStudentSubmit",
                        type: "POST",
                        data: datas,
                        success: function (result) {
                            if (result.code == 0) {//如果成功
                                layer.msg('修改成功', {
                                    icon: 6,
                                    time: 500
                                }, function () {
                                    parent.window.location.reload();
                                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(iframeIndex);
                                })
                            } else if (result.code == -2) {
                                layer.msg("用户名已存在");
                            } else if (result.code == -1) {
                                layer.msg("学号已存在");
                            } else {
                                layer.msg("修改失败");
                            }
                        }
                    })
                    return false;
                });
            });
        </script>
    </body>
</html>
