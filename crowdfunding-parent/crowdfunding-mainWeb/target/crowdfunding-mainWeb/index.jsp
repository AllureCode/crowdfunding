<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2020/2/18
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"  %>
<html>
<head>
    <title>Title</title>
    <!--用于转发到主页面-->
<%--    <meta http-equiv="refresh" content="0;URL=${APP_PATH}/index_1.html" >--%>
</head>
<body>
<!--用于转发到主页面-->
<%--<jsp:forward page="${APP_PATH}/index.html"></jsp:forward>--%>
<%--<a href="${APP_PATH}/test.do">test</a>--%>
<%
    response.sendRedirect("index_1.html");
%>
</body>
</html>
