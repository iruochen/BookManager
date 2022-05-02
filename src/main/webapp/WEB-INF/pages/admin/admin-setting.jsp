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
        <title>个人信息</title>
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
                    <%--用来判断进行添加还是更新操作--%>
                    <input type="hidden" name="id" value="${admin.id}">
                    <input type="hidden" name="oldAdminId" value="${admin.adminId}">
                    <div class="layui-form-item">
                        <label class="layui-form-label required">工号</label>
                        <div class="layui-input-block">
                            <input type="text" name="adminId" lay-verify="required|number|adminid" lay-reqtext="工号不能为空"
                                   placeholder="请输入工号" value="${admin.adminId}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label required">姓名</label>
                        <div class="layui-input-block">
                            <input type="text" name="adminName" lay-verify="required" lay-reqtext="姓名不能为空"
                                   placeholder="请输入姓名" value="${admin.adminName}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label required">性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="sex" value="男"
                                   title="男"  ${"男" eq admin.sex?"checked='checked'":''} />
                            <input type="radio" name="sex" value="女"
                                   title="女"  ${"女" eq admin.sex?"checked='checked'":''} />
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label required">院系</label>
                        <div class="layui-input-block">
                            <select name="deptId" id="deptId" lay-verify="required">
                                <option value="${admin.deptId}">请选择</option>
                            </select>
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
                    adminid: [
                        /^\d{1,9}$/,
                        '工号输入错误，请重新输入'
                    ]
                })

                // 动态获取院系的数据
                $.get("selectDeptAll", {}, function (data) {
                    // 获取院系类型的值
                    var deptId = $('#deptId')[0].value;
                    var list = data;
                    var select = document.getElementById("deptId");
                    if (list != null || list.size() > 0) {
                        for (var c in list) {
                            var option = document.createElement("option");
                            option.setAttribute("value", list[c].id);
                            option.innerText = list[c].deptName;
                            select.appendChild(option);
                            // 如果类型和循环到的类型iD一致，选中
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
                        url: "adminSettingSubmit",
                        type: "POST",
                        data: datas,
                        success: function (result) {
                            if (result.code == 0) {//如果成功
                                layer.msg('保存成功', {
                                    icon: 6,
                                    time: 500
                                }, function () {
                                    // 防止第一次添加错误
                                    window.location.reload();
                                })
                            } else if (result.code == -1) {
                                layer.msg("工号已存在");
                            } else {
                                layer.msg("保存失败");
                            }
                        }
                    })
                    return false;
                });
            });
        </script>
    </body>
</html>