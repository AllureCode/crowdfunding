<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2020/3/2
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/login.css">
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/login.css">

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index_1.html" style="font-size:32px;">爱上筹-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>
<%--<div class="container">--%>
    <div class="login-box">
    <form class="form-signin" role="form" id="loginForm" method="post" action="${APP_PATH}/dologin/login.do">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" id="loginacct" name="loginacct" class="form-control"  placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" id="userpswd" name="userpswd" class="form-control"  placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select class="form-control" id="IDtype" name="IDtype" >
                <option value="member">会员</option>
                <option value="user">管理</option>
            </select>
        </div>
<%--        <div class="form-group">--%>
<%--            <label   class="col-sm-3 control-label">验证码</label>--%>
<%--            <div class="col-sm-4">--%>
<%--                <input type="text" class="form-control" id="inputaccount" placeholder="请输入验证码">--%>
<%--            </div>--%>
<%--            <div class="col-sm-4">--%>
<%--                <img id="loginform:vCode" src="${APP_PATH}/validatecode.jsp"  οnclick="javascript:document.getElementById('loginform:vCode'). src='validatecode.jsp?'+Math.random();" />--%>
<%--            </div>--%>
<%--        </div>--%>

        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="${APP_PATH}/reg.html">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" id="dologin" > 登录</a>
    </form>
</div>

<script src="${APP_PATH}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/static/js/check.js"></script>
<script src="${APP_PATH}/static/layer/layer.js"></script>
<script>
    //验证码
    function loadImage(){
        $("#randImage").src("${APP_PATH}/image.jsp?"+Math.random());
    }
    $(function () {
        $("#dologin").click(function () {
            if (checkloginaccte() && checkuserpswd()){
                $.post("${APP_PATH}/dologin/login.do",$("#loginForm").serialize(),function (result) {
                    /**
                     * 当返回的结果为true时触发的逻辑
                     */
                    if (result.success) {
                        var type = $(":selected").val();
                        if (type == 'member') {
                            window.location.href = "${APP_PATH}/index_1.html";
                        } else {
                            window.location.href = "${APP_PATH}/main.html";
                        }
                    }else{
                        layer.msg("用户名或密码错误登陆失败",{time:3000,icon:5,shift:6},function () {

                        });
                    }
                },"json");
            }
            return false;
        });
        //失去焦点 调用对应的检查方法
        $("#loginacct").blur(function () {
            checkloginaccte();
            }
        );
        $("#userpswd").blur(function () {
                checkuserpswd();
            }
        );
    });
</script>
</body>
</html>
