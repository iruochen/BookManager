<%--
  Created by IntelliJ IDEA.
  User: ruochen
  Date: 2022/3/27
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>管理员管理</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.6.3/css/layui.css" media="all">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
        <script src="${pageContext.request.contextPath}/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
    </head>
    <body>
        <div class="layuimini-container">
            <div class="layuimini-main">

                <div class="demoTable">
                    <div class="layui-form-item layui-form ">
                        工号：
                        <div class="layui-inline">
                            <input class="layui-input" name="adminId" id="adminId" autocomplete="off">
                        </div>
                        姓名：
                        <div class="layui-inline">
                            <input class="layui-input" name="adminName" id="adminName" autocomplete="off">
                        </div>
                        院系：
                        <div class="layui-inline">
                            <select id="deptId" name="deptId" lay-verify="required">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn" data-type="reload">搜索</button>
                        </div>
                    </div>
                </div>

                <script type="text/html" id="toolbarDemo">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加
                        </button>
                        <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除
                        </button>
                    </div>
                </script>

                <!--表单，查询出的数据在这里显示-->
                <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

                <script type="text/html" id="currentTableBar">
                    <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="update">修改</a>
                    <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
                </script>

            </div>
        </div>

        <script>
            layui.use(['form', 'table'], function () {
                var $ = layui.jquery,
                    form = layui.form,
                    table = layui.table;

                // 动态获取院系类型的数据，即下拉菜单，跳出院系类型
                $.get("selectDeptAll", {}, function (data) {
                    var list = data;
                    var select = document.getElementById("deptId");
                    if (list != null || list.size() > 0) {
                        for (var obj in list) {
                            var option = document.createElement("option");
                            option.setAttribute("value", list[obj].id);
                            option.innerText = list[obj].deptName;
                            select.appendChild(option);
                        }
                    }
                    form.render('select');
                }, "json")

                table.render({
                    elem: '#currentTableId',
                    url: '${pageContext.request.contextPath}/selectAdminAll',//查询数据
                    limits: [10, 15, 20, 25, 50, 100],
                    limit: 10,  <!--默认显示10条-->
                    page: true,
                    skin: 'line',
                    id: 'testReload',
                    toolbar: '#toolbarDemo',
                    defaultToolbar: ['filter', 'exports', 'print', {
                        title: '提示',
                        layEvent: 'LAYTABLE_TIPS',
                        icon: 'layui-icon-tips'
                    }],
                    cols: [[
                        {type: "checkbox"},
                        {field: 'adminId', title: '工号', align: "center"},
                        {field: 'adminName', title: '姓名', align: "center"},
                        {field: 'sex', title: '性别', align: "center"},
                        {
                            field: 'deptName',
                            templet: '<div>{{d.department.deptName}}</div>',
                            title: '院系',
                            align: 'center'
                        },
                        {
                            field: 'username',
                            templet: '<div>{{d.user.username}}</div>',
                            title: '用户名',
                            align: 'center'
                        },
                        {
                            field: 'password',
                            templet: '<div>{{d.user.password}}</div>',
                            title: '密码',
                            align: 'center'
                        },
                        {title: '操作', toolbar: '#currentTableBar', align: "center"}
                    ]],
                    request: {
                        pageName: "page",
                        limitName: "size"
                    },
                });

                var $ = layui.$, active = {
                    reload: function () {
                        var adminId = $('#adminId').val();
                        var adminName = $('#adminName').val();
                        var deptId = $('#deptId').val();
                        //执行重载
                        table.reload('testReload', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                adminId: adminId,
                                adminName: adminName,
                                deptId: deptId
                            }
                        }, 'data');
                    }
                };

                $('.demoTable .layui-btn').on('click', function () {
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
                });

                /**
                 * tool操作栏监听事件
                 */
                table.on('tool(currentTableFilter)', function (obj) {
                    var data = obj.data;
                    if (obj.event === 'update') {  // 监听修改操作
                        var index = layer.open({
                            title: '修改管理员信息',
                            type: 2,
                            shade: 0.2,
                            maxmin: true,
                            shadeClose: true,
                            area: ['100%', '100%'],
                            content: '${pageContext.request.contextPath}/selectAdminById?id=' + data.id,
                        });
                        $(window).on("resize", function () {
                            layer.full(index);
                        });
                    } else if (obj.event === 'delete') {  // 监听删除操作
                        layer.confirm('确定是否删除', function (index) {
                            //调用删除功能
                            deleteInfoByIds(data.id, index);
                            layer.close(index);
                        });
                    }
                });

                //监听表格复选框选择
                table.on('checkbox(currentTableFilter)', function (obj) {
                    // console.log(obj)
                });

                /**
                 * 获取选中记录的id信息
                 */
                function getCheckId(data) {
                    var arr = new Array();
                    for (var i = 0; i < data.length; i++) {
                        arr.push(data[i].id);
                    }
                    // 拼接id,变成一个字符串
                    return arr.join(",");
                };


                /**
                 * 提交删除功能
                 */
                function deleteInfoByIds(ids, index) {
                    //向后台发送请求
                    $.ajax({
                        url: "deleteAdmin",
                        type: "POST",
                        data: {ids: ids},
                        success: function (result) {
                            if (result.code == 0) {//如果成功
                                layer.msg('删除成功', {
                                    icon: 6,
                                    time: 500
                                }, function () {
                                    parent.window.location.reload();
                                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(iframeIndex);
                                });
                            } else {
                                layer.msg("删除失败");
                            }
                        }
                    })
                };

                /**
                 * toolbar栏监听事件
                 */
                table.on('toolbar(currentTableFilter)', function (obj) {
                    if (obj.event === 'add') {  // 监听添加操作
                        var index = layer.open({
                            title: '添加管理员',
                            type: 2,
                            shade: 0.2,
                            maxmin: true,
                            shadeClose: true,
                            area: ['100%', '100%'],
                            content: '${pageContext.request.contextPath}/adminAdd',
                        });
                        $(window).on("resize", function () {
                            layer.full(index);
                        });
                    } else if (obj.event === 'delete') {
                        /*
                          1、提示内容，必须删除大于0条
                          2、获取要删除记录的id信息
                          3、提交删除功能 ajax
                        */
                        //获取选中的记录信息
                        var checkStatus = table.checkStatus(obj.config.id);
                        var data = checkStatus.data;
                        if (data.length == 0) {//如果没有选中信息
                            layer.msg("请选择要删除的记录信息");
                        } else {
                            //获取记录信息的id集合,拼接的ids
                            var ids = getCheckId(data);
                            layer.confirm('确定是否删除', function (index) {
                                //调用删除功能
                                deleteInfoByIds(ids, index);
                                layer.close(index);
                            });
                        }
                    }
                });

            });
        </script>

    </body>
</html>