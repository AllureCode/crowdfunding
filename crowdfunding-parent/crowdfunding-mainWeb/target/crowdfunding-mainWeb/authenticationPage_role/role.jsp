<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2020/3/12
  Time: 21:53
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
                    <h3 href="${APP_PATH}/role/toRole.html" class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="queryText" class="form-control has-success" type="text"
                                       placeholder="请输入名称查询">
                            </div>
                        </div>
                        <button id="searchRole" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='form.html'"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                             <tr >
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
<script src="${APP_PATH}/static/js/menu.js"></script>
<script src="${APP_PATH}/static/layer/layer.js"></script>
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
         * 页面加载完成进行异步加载查询角色
         */
        queryRolePage(1);
    });

    /**
     *对角色的查询
     * @type {string}
     */
    var loadIndex = "";
    var jsonObject = {
        "pageNo": 1,
        "pageSize": 10
    };

    function queryRolePage(currentPage) {
        jsonObject.pageNo = currentPage; //将当前页数据传入json的pageNo中
        $.ajax({
            type: "post",
            data: jsonObject,
            url: "${APP_PATH}/role/queryAllRoles.do",
            beforeSend: function () {
                loadIndex = layer.load(2, {time: 10 * 1000});
                return true;
            },
            dataType: "json",
            success: function (result) {
                layer.close(loadIndex);
                if (result.success) {
                    var data = result.rolePage.date;
                    var content = "";
                    $.each(data, function (i, n) {
                        content += '<tr>';
                        content += '<td>' + (i + 1) + '</td>';
                        content += '<td><input id=" ' + n.id + ' " type="checkbox"></td>';
                        content += '<td>' + n.name + '</td>';
                        content += '<td>';
                        content += ' <button type="button" onclick="window.location.href=\'${APP_PATH}/role/toAssignPermission.html?roleid=' + n.id + '\' "  class="btn btn-success btn-xs" ><i class=" glyphicon glyphicon-check"></i></button>';
                        content += ' <button type="button" onclick="window.location.href=\'${APP_PATH}/user/toEdit.html?id=' + n.id + '\' "  class="btn btn-primary btn-xs" ><i class=" glyphicon glyphicon-pencil"></i></button>';
                        content += ' <button type="button"  onclick="deleteUser(' + n.id + ',\'' + n.loginacct + '\');" class="btn btn-danger btn-xs" ><i class=" glyphicon glyphicon-remove"></i></button>';
                        content += '</td>';
                        content += '</tr>';
                    });
                    $("tbody").html(content);
                    /**
                     * 对翻页的控制
                     * @type {string}
                     */
                    var contentBar = "";
                    if (result.rolePage.pageNo == 1) {
                        contentBar += "<li class=\"disabled\"><a href=\"#\">上一页</a></li>";
                    } else {
                        contentBar += '<a href="#" onclick="pageChange(' + (currentPage - 1) +')">上一页</a></li>';
                    }
                    for (var i = 1; i <= result.rolePage.totalNo; i++) {
                        contentBar += '<li ';
                        if (result.rolePage.pageNo == i) {
                            contentBar += ' class="active" ';
                        }
                        contentBar += '><a href="#" onclick="pageChange(' + i + ')">' + i + '</a></li>';
                    }

                    if ((result.rolePage.pageNo) == (result.rolePage.totalNo)) {
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


    $("#searchRole").click(function () {
        var queryText = $("#queryText").val();
        jsonObject.queryText = queryText;
        queryRolePage(1);
    });

    function pageChange(currentPage) {
        queryRolePage(currentPage);
    }

    $("tbody .btn-success").click(function () {
        window.location.href = "assignPermission.html";
    });
</script>
</body>
</html>
