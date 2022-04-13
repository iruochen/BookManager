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
        <title>采购记录</title>
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
                        出版社：
                        <div class="layui-inline">
                            <input class="layui-input" name="bookPress" id="bookPress" autocomplete="off">
                        </div>
                        <button class="layui-btn" data-type="reload">搜索</button>
                    </div>
                </div>

                <%--
                <script type="text/html" id="toolbarDemo">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="purchaseByIds">
                            采购
                        </button>
                    </div>
                </script>
                --%>

                <!--表单，查询出的数据在这里显示-->
                <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

                <script type="text/html" id="currentTableBar">
                    <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="purchase">采购</a>
                </script>

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

                table.render({
                    elem: '#currentTableId',
                    url: '${pageContext.request.contextPath}/selectBookNeedPurchaseAll',  // 查询数据
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
                            field: 'bookAuthor',
                            templet: '<div>{{d.book.bookAuthor}}</div>',
                            title: '作者',
                            align: 'center'
                        },
                        {
                            field: 'bookPress',
                            templet: '<div>{{d.book.bookPress}}</div>',
                            title: '出版社',
                            align: 'center'
                        },
                        {
                            field: 'bookPrice',
                            templet: '<div>{{d.book.bookPrice}}</div>',
                            title: '价格',
                            align: 'center'
                        },
                        {
                            field: 'bookNum',
                            templet: '<div>{{d.book.bookNum}}</div>',
                            title: '库存',
                            align: 'center'
                        },
                        {field: 'needPurchaseCount', title: '采购数量', align: "center"},
                        {field: 'priceCount', title: '采购总值', align: "center"},
                        {title: '操作', toolbar: '#currentTableBar', align: "center"}
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
                        var bookPress = $('#bookPress').val();
                        // 执行重载
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
                    var bookId = data.book.bookId;
                    var price = data.priceCount;
                    var count = data.needPurchaseCount;
                    console.log(bookId, price, count)
                    if (obj.event === 'purchase') {  // 监听提交操作
                        layer.confirm('确定是否采购', function (index) {
                            bookPurchase(bookId, price, count, index);
                            layer.close(index);
                        })
                    }
                });

                //监听表格复选框选择
                table.on('checkbox(currentTableFilter)', function (obj) {
                    // console.log(obj)
                });

                /**
                 * 获取选中记录的教材id信息
                 */
                function getCheckBookId(data) {
                    var arr = new Array();
                    for (var i = 0; i < data.length; i++) {
                        arr.push(data[i].bookId);
                    }
                    // 拼接id,变成一个字符串
                    return arr.join(",");
                }


                /**
                 * 更新记录状态功能
                 */
                function bookPurchase(bookId, price, count, index) {
                    //向后台发送请求
                    $.ajax({
                        url: "bookPurchase",
                        type: "POST",
                        data: {
                            bookId: bookId,
                            price: price,
                            count: count
                        },
                        success: function (result) {
                            if (result.code == 0) {
                                layer.msg('采购成功', {
                                    icon: 6,
                                    time: 500
                                }, function () {
                                    parent.window.location.reload();
                                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(iframeIndex);
                                });
                            } else {
                                layer.msg("采购失败");
                            }
                        }
                    })
                }

                /**
                 * toolbar栏监听事件
                 */
                table.on('toolbar(currentTableFilter)', function (obj) {
                    if (obj.event === 'purchaseById') {
                        // 获取选中的记录信息
                        var checkStatus = table.checkStatus(obj.config.id);
                        var data = checkStatus.data;
                        if (data.length == 0) {  // 如果没有选中信息
                            layer.msg("请选择要采购的记录信息");
                        } else {
                            //获取记录信息的id集合,拼接的ids
                            var bookIds = getCheckBookId(data);
                            layer.confirm('确定是否采购', function (index) {
                                // 调用采购功能
                                bookPurchase(bookIds, index);
                                layer.close(index);
                            });
                        }
                    }
                });


            });
        </script>

    </body>
</html>