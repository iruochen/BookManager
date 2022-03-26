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
        <title>修改教材</title>
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
                    <input type="text" name="bookId" lay-verify="required" value="${book.bookId}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">教材名称</label>
                <div class="layui-input-block">
                    <input type="text" name="bookName" lay-verify="required" value="${book.bookName}"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">教材作者</label>
                <div class="layui-input-block">
                    <input type="text" name="bookAuthor" value="${book.bookAuthor}" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">教材出版社</label>
                <div class="layui-input-block">
                    <input type="text" name="bookPress" value="${book.bookPress}" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">教材价格</label>
                <div class="layui-input-block">
                    <input type="number" name="bookPrice" lay-verify="required" value="${book.bookPrice}"
                           class="layui-input">
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
                    <button type="button" class="layui-btn" id="uploadImg">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                </div>
            </div>
            <%--上传图片将路径防放于此--%>
            <input type="hidden" id="mainImage" name="bookImgUrl" required value="${book.bookImgUrl}"
                   class="layui-input">

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认修改</button>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
        <script>
            layui.use(['form', 'upload'], function () {
                var form = layui.form,
                    layer = layui.layer,
                    upload = layui.upload,
                    $ = layui.$;

                // 上传图片
                var uploadInst = upload.render({
                    elem: '#uploadImg'  // 绑定元素
                    , url: 'upload'  // 后台上传接口
                    , before: function (obj) {
                        layer.msg('上传中', {icon: 16, time: 0, shade: 0.01});
                    }
                    , done: function (res, index, upload) {
                        layer.closeAll('loading');
                        // code = 0 代表上传成功
                        if (res.code == 0) {
                            layer.msg("上传成功", {icon: 1});
                            // do something （比如将res返回的图片链接保存到表单的隐藏域）
                            $('#upload_img').attr('src', res.data);
                            $('#mainImage').val(res.data);
                        }

                        // 获取当前触发上传的元素，一般用于 elem 绑定 class 的情况，注意：此乃 layui 2.1.0 新增
                        var item = this.item;
                        // 文件保存失败
                        // do something
                    }
                    , error: function () {
                        layer.closeAll('loading');
                        //请求异常回调
                        layer.msg("上传失败，请重试", {icon: 2});
                    }
                    , size: 2048 //文件大小限制
                    , number: 1 //文件数量限制
                    , accept: 'images' //文件类型限制
                    , acceptMime: 'image/jpg, image/png' //选择窗口属性
                });

                //监听提交
                form.on('submit(saveBtn)', function (data) {
                    var datas = data.field;//form单中的数据信息
                    // 向后台发送数据提交添加
                    $.ajax({
                        url: "updateBookSubmit",
                        type: "POST",
                        data: datas,
                        // contentType: 'application/json',
                        // data: JSON.stringify(datas),
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
