<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2020/3/9
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/main.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <jsp:include page="/static/common/top.jsp"></jsp:include>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="tree">
                <jsp:include page="/static/common/menu.jsp"></jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">新增用户数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form role="form" id="addUserForm" method="post" action="${APP_PATH}/user/addUser.do">
                        <div class="form-group">
                            <label for="loginacct">登陆账号</label>
                            <input type="text" class="form-control" id="loginacct" name="loginacct"
                                   placeholder="请输入登陆账号">
                        </div>
                        <div class="form-group">
                            <label for="userpswd">用户密码</label>
                            <input type="password" class="form-control" id="userpswd" name="userpswd"
                                   placeholder="请输入用户密码">
                        </div>
                        <div class="form-group">
                            <label for="username">用户名称</label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱地址</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="button" id="addUser" class="btn btn-success"><i
                                class="glyphicon glyphicon-plus"></i> 新增
                        </button>
                        <button type="button" id="reset" class="btn btn-danger"><i
                                class="glyphicon glyphicon-refresh"></i> 重置
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<script src="${APP_PATH}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script src="${APP_PATH}/static/js/check.js"></script>
<script src="${APP_PATH}/static/js/menu.js"></script>
<script src="${APP_PATH}/static/layer/layer.js"></script>
<script src="${APP_PATH}/static/js/reset.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
        /**
         * 对页面展开闭合控制
         **/
        showMenu();

        /***
         * 当页面加载完成时触发校验函数
         */
        var indexLoad = "";
        $("#addUser").click(function () {
            if (checkEmail() && checkloginaccte()&& checkuserpswd()) {
                $.ajax({
                    type: "post",
                    url: "${APP_PATH}/user/addUser.do",
                    dataType: "json",
                    data: $("#addUserForm").serialize(),
                    beforeSend: function () {
                        indexLoad = layer.load(2, {time: 10 * 1000});
                        return true;
                    },
                    complete: function () {
                        layer.close(indexLoad);
                    },
                    success: function (result) {
                        //处理返回
                        if (result.success) {
                            layer.msg("添加成功", {time: 3000, icon: 6, shift: 6});
                            window.location.href = "${APP_PATH}/user/toUser.html"
                        } else {
                            layer.msg("添加失败", {time: 3000, icon: 5, shift: 6});
                        }
                    }
                });
            }
            return false;
        });
        //失去焦点 调用对应的检查方法
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

