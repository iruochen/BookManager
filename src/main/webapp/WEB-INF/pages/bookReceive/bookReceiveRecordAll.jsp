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
        <title>领取记录</title>
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
                        教材编号：
                        <div class="layui-inline">
                            <input class="layui-input" name="bookId" id="bookId" autocomplete="off">
                        </div>
                        教材名称：
                        <div class="layui-inline">
                            <input class="layui-input" name="bookName" id="bookName" autocomplete="off">
                        </div>
                        学号：
                        <div class="layui-inline">
                            <input class="layui-input" name="stuId" id="stuId" autocomplete="off">
                        </div>
                        学生：
                        <div class="layui-inline">
                            <input class="layui-input" name="stuName" id="stuName" autocomplete="off">
                        </div>
                        院系：
                        <div class="layui-inline">
                            <select id="deptId" name="deptId" lay-verify="required">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        领取时间：
                        <div class="layui-inline">
                            <input class="layui-input" name="time" id="time" autocomplete="off">
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn" data-type="reload">搜索</button>
                        </div>
                    </div>
                </div>

                <!--表单，查询出的数据在这里显示-->
                <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

            </div>
        </div>

        <script>
            layui.use(['form', 'table', 'laydate'], function () {
                var $ = layui.jquery,
                    form = layui.form,
                    table = layui.table,
                    laydate = layui.laydate;

                //日期
                laydate.render({
                    elem: '#time',
                    trigger: 'click'
                });

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
                    url: '${pageContext.request.contextPath}/selectBookReceiveAll',  // 查询数据
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
                        // {type: "checkbox"},
                        {
                            field: 'bookId',
                            templet: '<div>{{d.book.bookId}}</div>',
                            title: '教材编号',
                            align: 'center'
                        },
                        {
                            field: 'bookName',
                            templet: '<div>{{d.book.bookName}}</div>',
                            title: '教材名称',
                            align: 'center'
                        },
                        {
                            field: 'stuId',
                            templet: '<div>{{d.student.stuId}}</div>',
                            title: '学号',
                            align: 'center'
                        },
                        {
                            field: 'stuName',
                            templet: '<div>{{d.student.stuName}}</div>',
                            title: '领取人',
                            align: 'center'
                        },
                        {
                            field: 'time',
                            templet: '<div>{{layui.util.toDateString(d.time, "yyyy年MM月dd日")}}</div>',
                            title: '申请时间',
                            align: "center"
                        },
                        {
                            field: 'deptName',
                            templet: '<div>{{d.student.department.deptName}}</div>',
                            title: '院系',
                            align: 'center'
                        },
                        {field: 'count', title: '领取数量', align: "center"},
                        // {title: '操作', toolbar: '#currentTableBar', align: "center"}
                    ]],
                    request: {
                        pageName: "page",
                        limitName: "size"
                    },
                });

                var $ = layui.$, active = {
                    reload: function () {
                        var bookId = $('#bookId').val();
                        var bookName = $('#bookName').val();
                        var stuId = $('#stuId').val();
                        var stuName = $('#stuName').val();
                        var deptId = $('#deptId').val();
                        var time = $('#time').val();
                        // 执行重载
                        table.reload('testReload', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                bookId: bookId,
                                bookName: bookName,
                                stuId: stuId,
                                stuName: stuName,
                                deptId: deptId,
                                time: time,
                            }
                        }, 'data');
                    }
                };

                $('.demoTable .layui-btn').on('click', function () {
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
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
                }

            });
        </script>

    </body>
</html>