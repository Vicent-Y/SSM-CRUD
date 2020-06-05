<%--
  Created by IntelliJ IDEA.
  User: YJ
  Date: 2020/5/27
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--写了跳转信息，即打开项目后，跳转到/emps请求下--%>
<jsp:forward page="/emps"></jsp:forward>



<html>
<head>
    <title>Title</title>

    <%--引入jquerry--%>
    <script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>
    <%--引入样式--%>
    <link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <%--引入js插件--%>
    <script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
        <button class="btn btn-success">按钮</button>
        <button class="btn btn-success">按钮</button>
        <button class="btn btn-success">按钮</button>
</body>
</html>
