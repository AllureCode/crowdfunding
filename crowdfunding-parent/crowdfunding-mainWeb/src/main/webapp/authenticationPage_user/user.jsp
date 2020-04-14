<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2020/3/8
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/main.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }

        table tbody td:nth-child(even) {
            color: #C00;
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="queryText" class="form-control has-success" type="text"
                                       placeholder="请输入账号进行查询">
                            </div>
                        </div>
                                <button id="searchUser" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" id="deleteButton"  onclick="deleteUser();" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='${APP_PATH}/user/toAdd.html'"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="allCheckButton" type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                    </ul>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${APP_PATH}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script src="${APP_PATH}/static/layer/layer.js"></script>
<script src="${APP_PATH}/static/js/menu.js"></script>
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
         * 页面加载完成调用查询函数
         */
        queryUserPage(1);
        /**
         * 对页面展开闭合控制
         **/
        showMenu();
    });

    /**
     * 为了动态添加json返回后台的数据
     * 将json数据动态封装
     */
    var jsonObject = {
        "pageNo": 1,
        "pageSize": 10
    };

    /**
     * 发起异步请求查询会员用户信息
     */
    function queryUserPage(currentPage) {
        var loadIndex = -1;
        jsonObject.pageNo = currentPage; //将当前页数据传入json的pageNo中
        $.ajax({
            type: "post",
            data: jsonObject,
            url: "${APP_PATH}/user/searchUser.do",
            beforeSend: function () {
                loadIndex = layer.load(2, {time: 10 * 1000});
                return true;
            },
            dataType: "json",
            success: function (result) {
                layer.close(loadIndex);
                if (result.success) {
                    var data = result.userPage.date;
                    var content = "";
                    $.each(data, function (i, n) {
                        content += '<tr>';
                        content += '<td>' + (i + 1) + '</td>';
                        content += '<td><input id=" '+n.id+' " type="checkbox"></td>';
                        content += '<td>' + n.loginacct + '</td>';
                        content += '<td>' + n.username + '</td>';
                        content += '<td>' + n.email + '</td>';
                        content += '<td>';
                        content += ' <button type="button" onclick="window.location.href=\'${APP_PATH}/user/toAssignRole.html?id='+n.id+'\' "  class="btn btn-success btn-xs" ><i class=" glyphicon glyphicon-check"></i></button>';
                        content += ' <button type="button" onclick="window.location.href=\'${APP_PATH}/user/toEdit.html?id='+n.id+'\' "  class="btn btn-primary btn-xs" ><i class=" glyphicon glyphicon-pencil"></i></button>';
                        content += ' <button type="button"  onclick="deleteUser('+n.id+',\''+n.loginacct +'\');" class="btn btn-danger btn-xs" ><i class=" glyphicon glyphicon-remove"></i></button>';
                        content += '</td>';
                        content += '</tr>';
                    });
                    $("tbody").html(content);
                    var contentBar = "";
                    if ((result.userPage.pageNo) == 1) {
                        contentBar += '<li class="disabled"><a href="#">上一页</a></li>';
                    } else {
                        contentBar += '<li><a href="#" onclick="pageChange(' + (currentPage - 1) + ') " >上一页</a></li>';
                    }
                    for (var i = 1; i <= result.userPage.totalNo; i++) {
                        contentBar += '<li ';
                        if (result.userPage.pageNo == i) {
                            contentBar += ' class="active" ';
                        }
                        contentBar += '><a href="#" onclick="pageChange(' + i + ')">' + i + '</a></li>';
                    }
                    if ((result.userPage.pageNo) == (result.userPage.totalNo)) {
                        contentBar += '<li class="disabled"><a href="#">下一页</a></li>';
                    } else {
                        contentBar += '<li><a href="#" onclick="pageChange(' + (currentPage + 1) + ')">下一页</a></li>'
                    }
                    $(".pagination").html(contentBar);
                } else {
                    layer.msg("查询有误", {time: 1000, icon: 5, shift: 6});
                }
            }
        });
    }

    /**
     * 获取当前页进行查询
     */
    function pageChange(currentPage) {
        queryUserPage(currentPage);
    }

    /**
     * 模糊查询
     */
    $("#searchUser").click(function () {
        var queryText = $("#queryText").val();
        jsonObject.queryText = queryText;//将查询数据添加到json条件中
        queryUserPage(1);
    });

    /**
     * 删除所选的记录
     */
    var indexLoad="";   var ids="";
    function deleteUser(id){
        if($("tbody tr td input:checked ").length==0){
            layer.msg("至少选择一个用户进行删除！",{time:3000,icon:5,shift:6});
            return false;
        }
        if(id==null) {
            id = 0;
            var selectIds = [];
            var checkedBtns = $("tbody tr td input:checked ");
            $.each(checkedBtns, function (i, n) {
                selectIds.push(n.id);
            });
            ids = selectIds.join(",");
        }else{
            ids = 0;
        }
        layer.confirm("您确认要删除所选择的用户吗？", {icon: 3, title:'提示'}, function(cindex){
            //确认删除 发送ajax
            $.ajax({
                type:'post',
                url: '${APP_PATH}/user/deleteUser.do',
                dataType:'json',
                data:{'id':id,'ids':ids},
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
                        layer.msg("删除成功", {time: 3000, icon: 6, shift: 6});
                        window.location.href = "${APP_PATH}/user/toUser.html"
                    } else {
                        layer.msg("删除失败", {time: 3000, icon: 5, shift: 6});
                    }
                }
            });
            layer.close(cindex);
        }, function(cindex){
            layer.close(cindex);
        });
    }

    /**
     * 选择所有
     **/
    $("#allCheckButton").click(function () {
        var checkState = this.checked;
        $("tbody tr td input[type='checkbox'] ").prop("checked",checkState);
    });

    $("tbody .btn-success").click(function () {
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function () {
        window.location.href = "edit.html";
    });
</script>
</body>
</html>
