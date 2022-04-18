<%--
  Created by IntelliJ IDEA.
  User: ruochen
  Date: 2022/4/18
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>主页</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.6.3/css/layui.css" media="all">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome-4.7.0/css/font-awesome.min.css"
              media="all">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
    </head>
    <style>
        .layui-top-box {
            padding: 40px 20px 20px 20px;
            color: #fff
        }

        .panel {
            margin-bottom: 17px;
            background-color: #fff;
            border: 1px solid transparent;
            border-radius: 3px;
            -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 1px rgba(0, 0, 0, .05)
        }

        .panel-body {
            padding: 15px
        }

        .panel-title {
            margin-top: 0;
            margin-bottom: 0;
            font-size: 14px;
            color: inherit
        }

        .label {
            display: inline;
            padding: .2em .6em .3em;
            font-size: 75%;
            font-weight: 700;
            line-height: 1;
            color: #fff;
            text-align: center;
            white-space: nowrap;
            vertical-align: baseline;
            border-radius: .25em;
            margin-top: .3em;
        }

        .layui-red {
            color: red
        }

        .main_btn > p {
            height: 40px;
        }
    </style>
    <body>
        <div class="layuimini-container">
            <div class="layuimini-main layui-top-box">
                <div class="layui-row layui-col-space10">

                    <div class="layui-col-md3">
                        <div class="col-xs-6 col-md-3">
                            <div class="panel layui-bg-cyan">
                                <div class="panel-body">
                                    <div class="panel-title">
                                        <span class="label pull-right layui-bg-blue">实时</span>
                                        <h5>教材统计</h5>
                                    </div>
                                    <div class="panel-content">
                                        <h1 class="no-margins">${bookCount}</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="layui-col-md3">
                        <div class="col-xs-6 col-md-3">
                            <div class="panel layui-bg-blue">
                                <div class="panel-body">
                                    <div class="panel-title">
                                        <span class="label pull-right layui-bg-cyan">实时</span>
                                        <h5>管理员统计</h5>
                                    </div>
                                    <div class="panel-content">
                                        <h1 class="no-margins">${adminCount}</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="layui-col-md3">
                        <div class="col-xs-6 col-md-3">
                            <div class="panel layui-bg-green">
                                <div class="panel-body">
                                    <div class="panel-title">
                                        <span class="label pull-right layui-bg-orange">实时</span>
                                        <h5>教师统计</h5>
                                    </div>
                                    <div class="panel-content">
                                        <h1 class="no-margins">${teaCount}</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md3">
                        <div class="col-xs-6 col-md-3">
                            <div class="panel layui-bg-orange">
                                <div class="panel-body">
                                    <div class="panel-title">
                                        <span class="label pull-right layui-bg-green">实时</span>
                                        <h5>学生统计</h5>
                                    </div>
                                    <div class="panel-content">
                                        <h1 class="no-margins">${stuCount}</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-box">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-md12">
                        <blockquote class="layui-elem-quote main_btn">
                            <p>本系统基于 SSM + LAYUI MINI 实现</p>
                            <p>找到我：
                                <a href="Mailto:wangrui@ruochen.email"> <img
                                        src="https://img.shields.io/badge/mail-%230077B5.svg?style=for-the-badge&logo=mail&logoColor=white"
                                        height="25px" alt="mail"/> </a>
                                <a href="https://iruochen.net" target="_blank"> <img
                                        src="https://img.shields.io/badge/ruochen's blog-FF5722?style=for-the-badge&logo=ruochen's blog&logoColor=white"
                                        height="25px" alt="blog"> </a>
                            </p>
                            <p>可以给我的 GitHub 加个 Star 支持一下</p>
                            <p>GitHub地址：
                                <a href="https://github.com/iruochen/BookManager" target="_blank"> <img
                                        src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"
                                        height="25px" alt="blog"> </a>
                            </p>
                        </blockquote>
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
    </body>
</html>
