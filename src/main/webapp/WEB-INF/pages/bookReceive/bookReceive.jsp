<%--
  Created by IntelliJ IDEA.
  User: ruochen
  Date: 2022/3/26
  Time: 10:30
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
        <title>领取教材</title>
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
            <input type="hidden" name="id" value="${book.id}">
            <div class="layui-form-item">
                <label class="layui-form-label required">教材编号</label>
                <div class="layui-input-block">
                    <input type="text" name="bookId" value="${book.bookId}" class="layui-input" readonly="readonly">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">教材名称</label>
                <div class="layui-input-block">
                    <input type="text" name="bookName" value="${book.bookName}"
                           class="layui-input" readonly="readonly">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">教材作者</label>
                <div class="layui-input-block">
                    <input type="text" name="bookAuthor" value="${book.bookAuthor}" class="layui-input"
                           readonly="readonly">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">教材出版社</label>
                <div class="layui-input-block">
                    <input type="text" name="bookPress" value="${book.bookPress}" class="layui-input"
                           readonly="readonly">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">教材价格</label>
                <div class="layui-input-block">
                    <input type="number" name="bookPrice" value="${book.bookPrice}"
                           class="layui-input" readonly="readonly">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">教材数量</label>
                <div class="layui-input-block">
                    <input type="number" name="bookNum" value="${book.bookNum}" class="layui-input" readonly="readonly">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">教材图片：</label>
                <div class="layui-input-block">
                    <img id="upload_img" src="${book.bookImgUrl}" width="100"
                         height="100">
                </div>
            </div>
            <%--教材图片--%>
            <input type="hidden" id="mainImage" name="bookImgUrl" required value="${book.bookImgUrl}"
                   class="layui-input">

            <div class="layui-form-item">
                <label class="layui-form-label required">领取数量</label>
                <div class="layui-input-block">
                    <input type="number" name="count" class="layui-input" lay-verify="required|number"
                           placeholder="请输入领取数量">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">领取时间</label>
                <div class="layui-input-block">
                    <input type="text" name="time" id="date" lay-verify="date" autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认领取</button>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
        <script>
            layui.use(['form', 'upload', 'laydate'], function () {
                var form = layui.form,
                    layer = layui.layer,
                    laydate = layui.laydate,
                    $ = layui.$;

                //日期
                laydate.render({
                    elem: '#date',
                    trigger: 'click'
                });

                //监听提交
                form.on('submit(saveBtn)', function (data) {
                    var datas = data.field;//form单中的数据信息
                    // 向后台发送数据提交添加
                    $.ajax({
                        url: "receiveBookSubmit",
                        type: "POST",
                        data: datas,
                        success: function (result) {
                            if (result.code == 0) {//如果成功
                                layer.msg('领取成功', {
                                    icon: 6,
                                    time: 500
                                }, function () {
                                    parent.window.location.reload();
                                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(iframeIndex);
                                })
                            } else if (result.code == -1) {
                                layer.msg("教材库存数量不足");
                            } else {
                                layer.msg("领取失败");
                            }
                        }
                    })
                    return false;
                });
            });
        </script>
    </body>
</html>
