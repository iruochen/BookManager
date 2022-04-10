<%--
  Created by IntelliJ IDEA.
  User: ruochen
  Date: 2022/3/24
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>教材管理</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.6.3/css/layui.css" media="all">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
        <script src="${pageContext.request.contextPath}/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
        <style>
            /*重写表格高度，图片显示*/
            .layui-table-cell {
                text-align: center;
                height: auto;
                white-space: normal;
            }
        </style>
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
                        出版社：
                        <div class="layui-inline">
                            <input class="layui-input" name="bookPress" id="bookPress" autocomplete="off">
                        </div>
                        <button class="layui-btn" data-type="reload">搜索</button>
                    </div>
                </div>

                <!--表单，查询出的数据在这里显示-->
                <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

                <script type="text/html" id="currentTableBar">
                    <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="apply">申请</a>
                </script>

            </div>
        </div>

        <%--自定义temlet，表格中显示图片--%>
        <script type="text/html" id="imgtmp">
            <img src="{{d.bookImgUrl}}" style="width: 100px;height: 100px"/>
        </script>

        <script>
            layui.use(['form', 'table'], function () {
                var $ = layui.jquery,
                    form = layui.form,
                    table = layui.table;

                function hoverOpenImg() {
                    var img_show = null; // tips提示
                    $('td img').hover(function () {
                        var kd = $(this).width();
                        kd1 = kd * 3;          //图片放大倍数
                        kd2 = kd * 3 + 30;       //图片放大倍数
                        var img = "<img class='img_msg' src='" + $(this).attr('src') + "' style='width:" + kd1 + "px;' />";
                        img_show = layer.tips(img, this, {
                            tips: [2, 'rgba(41,41,41,.5)']
                            , area: [kd2 + 'px']
                        });
                    }, function () {
                        layer.close(img_show);
                    });
                    $('td img').attr('style', 'max-width:70px;display:block!important');
                }

                table.render({
                    elem: '#currentTableId',
                    url: '${pageContext.request.contextPath}/bookAll',
                    limits: [8, 16, 24, 32, 40, 48],
                    limit: 8,  // 每页10条
                    page: true,  // 开启分页
                    skin: 'line',  // 行边框风格
                    even: true,  // 开启隔行背景
                    id: 'testReload',
                    toolbar: '#toolbarDemo',
                    defaultToolbar: ['filter', 'exports', 'print', {
                        title: '提示',
                        layEvent: 'LAYTABLE_TIPS',
                        icon: 'layui-icon-tips'
                    }],
                    cols: [[
                        {type: "checkbox"},
                        {field: 'bookId', title: '教材编号'},
                        {field: 'bookName', title: '教材名称'},
                        {field: 'bookAuthor', title: '作者'},
                        {field: 'bookPress', title: '出版社'},
                        {field: 'bookPrice', title: '价格'},
                        {field: 'bookNum', title: '数量'},
                        {field: 'bookImgUrl', title: '图片', templet: '#imgtmp'},
                        {title: '操作', toolbar: '#currentTableBar', align: "center"}
                    ]],
                    request: {
                        pageName: "page",
                        limitName: "size"
                    },
                    done: function (res, curr, count) {
                        hoverOpenImg();//显示大图
                        $('table tr').on('click', function () {
                            $('table tr').css('background', '');
                        });
                    }
                });

                var $ = layui.$, active = {
                    reload: function () {
                        var bookId = $('#bookId').val();
                        var bookName = $('#bookName').val();
                        var bookPress = $('#bookPress').val();
                        //执行重载
                        table.reload('testReload', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                bookId: bookId,
                                bookName: bookName,
                                bookPress: bookPress
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
                    if (obj.event === 'apply') {  // 监听申请操作
                        var index = layer.open({
                            title: '申请教材',
                            type: 2,
                            shade: 0.2,
                            maxmin: true,
                            shadeClose: true,
                            area: ['100%', '100%'],
                            content: '${pageContext.request.contextPath}/applyBookById?id=' + data.id,
                        });
                        $(window).on("resize", function () {
                            layer.full(index);
                        });
                    }
                });
            });
        </script>

    </body>
</html>