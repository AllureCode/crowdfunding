<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2020/3/12
  Time: 7:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
            cursor:pointer;
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
                <li><a href="${APP_PATH}/user/toUser.html">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline">
                        <div class="form-group">
                            <label for="unDistributed">未分配角色列表</label><br>
                            <select id="unDistributed" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">

                               <c:forEach items="${leftRole}" var="role" >
                                   <option value="${role.id}">${role.name}</option>
                               </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="rightDistributed" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="leftDistributed" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="distributed">已分配角色列表</label><br>
                            <select id="distributed" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">
                                <c:forEach items="${rightRole}" var="role" >
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
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
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
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
<script src="${APP_PATH}/static/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });

    /**
     * 当点击右边按钮
     */
    var idsArray=[]; var indexLoad="";
    $("#rightDistributed").click(function () {
        var unDistributed = $("#unDistributed option:selected");
        $("#distributed").append(unDistributed);
        $.each(unDistributed,function (i,n) {
            idsArray.push(n.value);
        })
        // var ids=idsArray.join(",");
        $.ajax({
            type:'post',
            url: '${APP_PATH}/user/saveRolePower.do',
            dataType:'json',
            data:{'id':"${param.id}",'idsArray':idsArray},
            beforeSend: function () {
                layer.msg("正在分配角色", {time: 6000});
                indexLoad = layer.load(2, {time: 10 * 1000});
                return true;
            },
            complete: function () {
                layer.close(indexLoad);
            },
            success: function (result) {
                //处理返回
                if (result.success) {
                    layer.msg("分配成功", {time: 3000, icon: 6, shift: 6});
                } else {
                    layer.msg("分配失败", {time: 3000, icon: 5, shift: 6});
                }
            }
        });
    });

    /**
     * 当点击左边按钮
     */
    $("#leftDistributed").click(function () {
        var distributed = $("#distributed option:selected");
        $("#unDistributed").append(distributed);
        $.each(distributed,function (i,n) {
            idsArray.push(n.value);
        })
        $.ajax({
            type:'post',
            url: '${APP_PATH}/user/deleteRolePower.do',
            dataType:'json',
            data:{'id':"${param.id}",'idsArray':idsArray},
            beforeSend: function () {
                layer.msg("正在收回角色", {time: 6000});
                indexLoad = layer.load(2, {time: 10 * 1000});
                return true;
            },
            complete: function () {
                layer.close(indexLoad);
            },
            success: function (result) {
                //处理返回
                if (result.success) {
                    layer.msg("收回角色成功", {time: 3000, icon: 6, shift: 6});
                } else {
                    layer.msg("收回角色失败", {time: 3000, icon: 5, shift: 6});
                }
            }
        });

    });

</script>
</body>
</html>