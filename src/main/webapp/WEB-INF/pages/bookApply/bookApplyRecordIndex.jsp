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
        <title>申请记录</title>
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
                        申请时间：
                        <div class="layui-inline">
                            <input class="layui-input" name="time" id="time" autocomplete="off">
                        </div>
                        申请状态：
                        <div class="layui-inline">
                            <select id="status" name="status" lay-verify="required">
                                <option value="">请选择</option>
                                <option value="0">已提交</option>
                                <option value="-1">已撤销</option>
                                <option value="1">已通过</option>
                                <option value="2">已拒绝</option>
                            </select>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn" data-type="reload">搜索</button>
                        </div>
                    </div>
                </div>

                <%--
                <script type="text/html" id="toolbarDemo">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除
                        </button>
                    </div>
                </script>
                --%>

                <!--表单，查询出的数据在这里显示-->
                <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

                <script type="text/html" id="currentTableBar">
                    {{#  if(d.status == -1){ }}
                    <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="submit">提交</a>
                    {{#  } }}

                    {{#  if(d.status == 0){ }}
                    <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="revoke">撤销</a>
                    {{#  } }}
                </script>

            </div>
        </div>

        <%--自定义状态显示模板--%>
        <script type="text/html" id="statusTmp">
            {{#  if(d.status == 0){ }}
            <span class="layui-badge layui-bg-blue">已提交</span>
            {{#  } }}

            {{#  if(d.status == -1){ }}
            <span class="layui-badge layui-bg-gray">已撤销</span>
            {{#  } }}

            {{#  if(d.status == 1){ }}
            <span class="layui-badge layui-bg-green">已通过</span>
            {{#  } }}

            {{#  if(d.status == 2){ }}
            <span class="layui-badge">已拒绝</span>
            {{#  } }}

            {{#  if(d.status == 3){ }}
            <span class="layui-badge layui-bg-orange">已采购</span>
            {{#  } }}
        </script>

        <script>
            layui.use(['form', 'table', 'laydate'], function () {
                var $ = layui.jquery,
                    form = layui.form,
                    table = layui.table,
                    rate = layui.rate,
                    laydate = layui.laydate;

                //日期
                laydate.render({
                    elem: '#time',
                    trigger: 'click'
                });

                table.render({
                    elem: '#currentTableId',
                    url: '${pageContext.request.contextPath}/selectBookApplyAllByTea',//查询数据
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
                            field: 'teaId',
                            templet: '<div>{{d.teacher.teaId}}</div>',
                            title: '教工号',
                            align: 'center'
                        },
                        {
                            field: 'teaName',
                            templet: '<div>{{d.teacher.teaName}}</div>',
                            title: '申请人',
                            align: 'center'
                        },
                        {
                            field: 'time',
                            templet: '<div>{{layui.util.toDateString(d.time, "yyyy年MM月dd日")}}</div>',
                            title: '申请时间',
                            align: "center"
                        },
                        {field: 'count', title: '申请数量', align: "center"},
                        {field: 'status', title: '申请状态', templet: '#statusTmp', align: "center"},
                        {title: '操作', toolbar: '#currentTableBar', align: "center"},
                        {
                            field: 'score',
                            title: '评分',
                            align: "center",
                            templet: function (d) {
                                return '<div id="score' + d.id + '" style="margin-top: -10px"></div>';
                            }
                        },
                    ]],
                    request: {
                        pageName: "page",
                        limitName: "size"
                    },
                    done: function (res) {
                        var data = res.data;
                        for (var item in data) {
                            rate.render({
                                elem: '#score' + data[item].id,
                                text: true,
                                // 三目运算符解决有评分时不能进行评分
                                readonly: (data[item].score == null) ? false : true,
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
                                choose: function (value) {
                                    var str = this.elem.selector;
                                    var id = str.charAt(str.length - 1);
                                    // 发送 ajax 请求
                                    $.ajax({
                                        async: false,
                                        type: "GET",
                                        url: 'addScore?applyId=' + id + '&score=' + value,
                                        success: function () {
                                            console.log("success");
                                        }
                                    })
                                    rate.render({
                                        elem: str,
                                        text: true,
                                        value: value,
                                        readonly: true,
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
                            });
                        }
                    }
                });

                var $ = layui.$, active = {
                    reload: function () {
                        var bookId = $('#bookId').val();
                        var bookName = $('#bookName').val();
                        var time = $('#time').val();
                        var status = $('#status').val();
                        //执行重载
                        table.reload('testReload', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                bookId: bookId,
                                bookName: bookName,
                                time: time,
                                status: status
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
                    if (obj.event === 'submit') {  // 监听提交操作
                        layer.confirm('确定是否提交', function (index) {
                            // 调用提交功能
                            updateBookApplyStatusByIds(data.id, 0, index);
                            layer.close(index);
                        })
                    } else if (obj.event === 'revoke') {  // 监听撤销操作
                        layer.confirm('确定是否撤销', function (index) {
                            // 调用撤销功能
                            updateBookApplyStatusByIds(data.id, -1, index);
                            layer.close(index);
                        });
                    } else if (obj.event === 'comment') {  // 评价

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
                 * 更新记录状态功能
                 */
                function updateBookApplyStatusByIds(ids, status, index) {
                    //向后台发送请求
                    $.ajax({
                        url: "updateBookApplyStatus",
                        type: "POST",
                        data: {
                            ids: ids,
                            status: status
                        },
                        success: function (result) {
                            if (result.code == 0) {
                                layer.msg('提交成功', {
                                    icon: 6,
                                    time: 500
                                }, function () {
                                    parent.window.location.reload();
                                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(iframeIndex);
                                });
                            } else if (result.code == -1) {
                                layer.msg('撤销成功', {
                                    icon: 6,
                                    time: 500
                                }, function () {
                                    parent.window.location.reload();
                                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(iframeIndex);
                                });
                            } else {
                                layer.msg("操作失败");
                            }
                        }
                    })
                }

                /**
                 * toolbar栏监听事件
                 */
                table.on('toolbar(currentTableFilter)', function (obj) {
                    if (obj.event === 'add') {  // 监听添加操作
                        var index = layer.open({
                            title: '添加学生',
                            type: 2,
                            shade: 0.2,
                            maxmin: true,
                            shadeClose: true,
                            area: ['100%', '100%'],
                            content: '${pageContext.request.contextPath}/studentAdd',
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