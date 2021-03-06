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

        <%--自定义temlet，表格中显示图片--%>
        <script type="text/html" id="imgtmp">
            <img src="{{d.bookImgUrl}}" style="width: 100px;height: 100px"/>
        </script>

        <script>
            layui.use(['form', 'table'], function () {
                var $ = layui.jquery,
                    form = layui.form,
                    rate = layui.rate,
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
                        {
                            field: 'score',
                            title: '评分',
                            align: "center",
                            width: 200,
                            templet: function (d) {
                                return '<div id="score' + d.id + '" style="margin-top: -10px"></div>';
                            }
                        },
                        {title: '操作', toolbar: '#currentTableBar', align: "center"}
                    ]],
                    request: {
                        pageName: "page",
                        limitName: "size"
                    },
                    done: function (res, curr, count) {
                        var data = res.data;
                        console.log(data);
                        for (var item in data) {
                            rate.render({
                                elem: '#score' + data[item].id,
                                text: true,
                                // 三目运算符解决有评分时不能进行评分
                                readonly: true,
                                value: data[item].score,
                                setText: function (value) { //自定义文本的回调
                                    var arrs = {
                                        '0': '',
                                        '1': '极差'
                                        , '2': '差'
                                        , '3': '中等'
                                        , '4': '好'
                                        , '5': '极好'
                                    };
                                    this.span.text(arrs[value]);
                                },
                            })
                        }
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
                    if (obj.event === 'update') {  // 监听修改操作
                        var index = layer.open({
                            title: '修改教材信息',
                            type: 2,
                            shade: 0.2,
                            maxmin: true,
                            shadeClose: true,
                            area: ['100%', '100%'],
                            content: '${pageContext.request.contextPath}/selectBookById?id=' + data.id,
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

                // 监听表格复选框选择
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
                    //拼接id,变成一个字符串
                    return arr.join(",");
                }


                /**
                 * 提交删除功能
                 */
                function deleteInfoByIds(ids, index) {
                    //向后台发送请求
                    $.ajax({
                        url: "deleteBook",
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
                    }).fail(function () {
                        layer.msg("删除失败", {
                            icon: 6,
                            time: 500
                        });
                    })
                }

                /**
                 * toolbar栏监听事件
                 */
                table.on('toolbar(currentTableFilter)', function (obj) {
                    if (obj.event === 'add') {  // 监听添加操作
                        var index = layer.open({
                            title: '添加教材',
                            type: 2,
                            shade: 0.2,
                            maxmin: true,
                            shadeClose: true,
                            area: ['100%', '100%'],
                            content: '${pageContext.request.contextPath}/bookAdd',
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