<%--
  Created by IntelliJ IDEA.
  User: ruochen
  Date: 2022/4/19
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>统计</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.6.3/css/layui.css" media="all">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome-4.7.0/css/font-awesome.min.css"
              media="all">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
        <style>
            .layui-card {
                border: 1px solid #f2f2f2;
                border-radius: 5px;
            }

            .icon {
                margin-right: 10px;
                color: #1aa094;
            }

            .layuimini-qiuck-module a i {
                display: inline-block;
                width: 100%;
                height: 60px;
                line-height: 60px;
                text-align: center;
                border-radius: 2px;
                font-size: 30px;
                background-color: #F8F8F8;
                color: #333;
                transition: all .3s;
                -webkit-transition: all .3s;
            }

            .layuimini-qiuck-module a cite {
                position: relative;
                top: 2px;
                display: block;
                color: #666;
                text-overflow: ellipsis;
                overflow: hidden;
                white-space: nowrap;
                font-size: 14px;
            }

            .main_btn > p {
                height: 40px;
            }

        </style>
    </head>
    <body>
        <div class="layuimini-container">
            <div class="layuimini-main">
                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md8">
                        <div class="layui-col-md12">
                            <div class="layui-card">
                                <div class="layui-card-header"><i class="fa fa-line-chart icon"></i>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.role == 0}">
                                            近七日采购统计
                                        </c:when>
                                        <c:when test="${sessionScope.user.role == 1}">
                                            近七日领取统计
                                        </c:when>
                                        <c:when test="${sessionScope.user.role == 2}">
                                            近七日申请统计
                                        </c:when>
                                    </c:choose>
                                </div>
                                <div class="layui-card-body">
                                    <div id="echarts-records" style="width: 100%;min-height:500px"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
        <script>
            layui.use(['layer', 'miniTab', 'echarts'], function () {
                var $ = layui.jquery,
                    layer = layui.layer,
                    miniTab = layui.miniTab,
                    echarts = layui.echarts;

                miniTab.listen();

                /**
                 * 报表功能
                 */
                var echartsRecords = echarts.init(document.getElementById('echarts-records'), 'walden');
                var optionRecords = {
                    xAxis: {
                        type: 'category',
                        data: [
                            <c:forEach items="${statistics}" var="statistic">
                            "${statistic.date}",
                            </c:forEach>
                        ]
                    },
                    yAxis: {
                        type: 'value',
                        minInterval: 1
                    },
                    series: [
                        {
                            data: [
                                <c:forEach items="${statistics}" var="statistic">
                                ${statistic.count},
                                </c:forEach>
                            ],
                            type: 'line',
                            smooth: false
                        }
                    ]
                };
                echartsRecords.setOption(optionRecords);

                // echarts 窗口缩放自适应
                window.onresize = function () {
                    echartsRecords.resize();
                }

            });
        </script>
    </body>
</html>
