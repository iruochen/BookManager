<%--
  Created by IntelliJ IDEA.
  User: ruochen
  Date: 2022/4/9
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>更新密码</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.6.3/css/layui.css" media="all">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
        <style>
            .layui-form-item {
                width: auto;
                padding-right: 10px;
                line-height: 38px;
            }
        </style>
    </head>
    <body>
        <div class="layuimini-container">
            <div class="layuimini-main">
                <div class="layui-form layuimini-form">

                    <div class="layui-form-item">
                        <label class="layui-form-label">用户名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="username" value="${user.username}" readonly="readonly"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label required">旧密码</label>
                        <div class="layui-input-inline">
                            <input type="password" name="oldPassword" lay-reqtext="密码不能为空"
                                   lay-verify="required|pwd" class="layui-input"
                                   placeholder="请输入旧密码">
                        </div>
                    </div>
                    <div class="layui-form-item layuimini-form">
                        <label class="layui-form-label required">旧密码</label>
                        <div class="layui-input-inline">
                            <input type="password" name="password" lay-reqtext="密码不能为空"
                                   lay-verify="required|pwd" class="layui-input"
                                   placeholder="请输入旧密码">
                        </div>
                        <div>
                            <span class="bind-password icon icon-4"></span>
                        </div>
                    </div>
                    <div class="layui-form-item layuimini-form">
                        <label class="layui-form-label required">确认密码</label>
                        <div class="layui-input-inline">
                            <input type="password" name="pwdConfirm" lay-reqtext="密码不能为空"
                                   lay-verify="required|pwd" class="layui-input"
                                   placeholder="请输入确认密码">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
                        </div>
                    </div>
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
                    pwd: [
                        /^[\S]{6,12}$/,
                        '密码必须6到12位，且不能出现空格'
                    ]
                })

                // 监听提交
                form.on('submit(saveBtn)', function (data) {
                    var datas = data.field;//form单中的数据信息
                    if (datas.password !== datas.pwdConfirm) {
                        layer.msg("两次输入密码不一致，请重新输入")
                    } else {
                        // 向后台发送数据提交添加
                        $.ajax({
                            url: "updatePasswordSubmit",
                            type: "POST",
                            data: datas,
                            success: function (result) {
                                if (result.code == 0) {//如果成功
                                    layer.msg('修改成功', {
                                        icon: 6,
                                        time: 500
                                    }, function () {
                                        window.location.reload();
                                    })
                                } else if (result.code == -1) {
                                    layer.msg("旧密码输入错误");
                                } else {
                                    layer.msg("修改失败");
                                }
                            }
                        })
                    }
                    return false;
                });
            });
        </script>
    </body>
</html>