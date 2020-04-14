<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2020/3/2
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">
<head>
    <meta charset="GB18030">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index_1.html" style="font-size:32px;">爱上筹-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form class="form-signin" id="regForm" method="post" action="${APP_PATH}/register/reg.do" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户注册</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="loginacct" name="loginacct" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="userpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" name="email" class="form-control" id="email" placeholder="请输入邮箱地址" style="margin-top:10px;">
            <span class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" name="username" class="form-control" id="username" placeholder="请输入会员名称" style="margin-top:10px;">
            <span class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select class="form-control" id="usertype" name="usertype" >
                <option>企业</option>
                <option>个人</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="login.html">我有账号</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" id="register" > 注册</a>
    </form>
</div>
<script src="${APP_PATH}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/static/js/check.js"></script>
<script>
    /***
     * 注册逻辑的处理
     * 采用异步提交的注册方式
     */
    $(function () {
        $("#register").click(function () {
            if (checkuserpswd() && checkloginaccte() && checkEmail()){
                $.post("${APP_PATH}/register/reg.do",$("#regForm").serialize(),function (result) {
                    if (result.success){
                        alert("注册成功");
                        window.location.href="${APP_PATH}/index_1.html";
                    }else{
                        alert("注册失败");
                    }
                },"json");
            }else{
                alert("注册信息有误,请检查注册信息");
            }
        });
        /**
         * 失去焦点触发
         */
        $("#loginacct").blur(function () {
            checkloginaccte();
        })
        $("#userpswd").blur(function () {
            checkuserpswd();
        })
        $("#email").blur(function () {
            checkEmail();
        })

    });
</script>
</body>
</html>