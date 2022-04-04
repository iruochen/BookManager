<%--
  Created by IntelliJ IDEA.
  User: ruochen
  Date: 2022/4/4
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>error</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    </head>
    <!-- body -->
    <body class="main-layout template4">

        <!-- header -->
        <div class="header">
            <div class=" container">
                <div class="row">
                    <div class="col-md-12">
                        <a class="logo"><img src="${pageContext.request.contextPath}/images/404-logo.png" alt="#"/></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- end header -->
        <!-- mobile_section -->
        <div class="mobile_section">
            <div class="container">
                <div class="row d_flex">
                    <div class="col-md-5">
                        <div class="demo_box text_align_left margino">
                            <strong>Oops</strong>
                            <h2>404</h2>
                            <span>Error </span>
                            <h1>Page not found</h1>
                            <a class="read_more" href="${pageContext.request.contextPath}/index">Back to home</a>
                        </div>
                    </div>
                    <div class="col-md-7">
                        <div class="demo_box text_align_center margino">
                            <figure><img src="${pageContext.request.contextPath}/images/404.png" alt="#"/></figure>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end mobile_section -->
        <!--  footer -->
        <footer>
            <div class="footer">
                <div class="copyright yellowbg">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6 offset-md-3">
                                <p>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- end footer -->
        <!-- Javascript files-->
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/custom.js"></script>
    </body>
</html>
