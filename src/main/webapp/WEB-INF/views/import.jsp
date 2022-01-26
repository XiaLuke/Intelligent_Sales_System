<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/14
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
</head>
<body>
<!-- 注意:上传需要加enctype -->
<form method="post" action="/import/empxlsx" enctype="multipart/form-data">
    <input class="easyui-filebox" name="empFile" data-options="prompt:'选择一个excel文件..'" style="width:80%">
    <button class="easyui-linkbutton">导入</button>
</form>
</body>
</html>
