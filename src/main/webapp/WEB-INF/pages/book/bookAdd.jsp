<%--
  Created by IntelliJ IDEA.
  User: ruochen
  Date: 2022/3/24
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>添加教材</title>
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
            <div class="layui-form-item">
                <label class="layui-form-label required">教材编号</label>
                <div class="layui-input-block">
                    <input type="text" name="id" lay-verify="required" lay-reqtext="教材编号不能为空" placeholder="请输入教材编号"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">教材名称</label>
                <div class="layui-input-block">
                    <input type="text" name="bookName" lay-verify="required" lay-reqtext="教材名称不能为空"
                           placeholder="请输入教材名称"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">教材作者</label>
                <div class="layui-input-block">
                    <input type="text" name="bookAuthor" lay-verify="required" class="layui-input" autocomplete="off">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">教材出版社</label>
                <div class="layui-input-block">
                    <input type="text" name="bookPress" lay-verify="required" class="layui-input" autocomplete="off">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">教材价格</label>
                <div class="layui-input-block">
                    <input type="number" name="bookPrice" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">教材数量</label>
                <div class="layui-input-block">
                    <input type="number" name="bookPrice" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">图像上传：</label>
                <div class="layui-input-block">
                    <img id="upload_img" src="${pageContext.request.contextPath}/images/bg.jpg" width="100"
                         height="100">
                    <button type="button" class="layui-btn" id="uploadImg">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                </div>
            </div>
            <%--上传图片将路径防放于此--%>
            <input type="hidden" id="mainImage" name="mainImage" required value="default_img.png" class="layui-input">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">立即提交</button>
                    <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
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

                var uploadInst = upload.render({
                    elem: '#uploadImg'  // 绑定元素
                    , url: 'upload'  // 后台上传接口
                    , before: function (obj) {
                        layer.msg('上传中', {icon: 16, time: 0, shade: 0.01});
                    }
                    , done: function (res, index, upload) {
                        layer.closeAll('loading');
                        console.log(res);
                        // status=1代表上传成功
                        if (res.code == 0) {
                            layer.msg("上传成功", {icon: 1});
                            // do something （比如将res返回的图片链接保存到表单的隐藏域）
                            $('#upload_img').attr('src', 'https://book-manager-1301954372.cos.ap-nanjing.myqcloud.com/img/' + res.data);
                            $('#mainImage').val(res.data);
                        }

                        //获取当前触发上传的元素，一般用于 elem 绑定 class 的情况，注意：此乃 layui 2.1.0 新增
                        var item = this.item;
                        //文件保存失败
                        //do something
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
                    //向后台发送数据提交添加
                    $.ajax({
                        url: "addBookSubmit",
                        type: "POST",
                        data: datas,
                        success: function (result) {
                            if (result.code == 0) {//如果成功
                                layer.msg('添加成功', {
                                    icon: 6,
                                    time: 500
                                }, function () {
                                    parent.window.location.reload();
                                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(iframeIndex);
                                })
                            } else {
                                layer.msg("添加失败");
                            }
                        }
                    })
                    return false;
                });
            });
        </script>
    </body>
</html>
