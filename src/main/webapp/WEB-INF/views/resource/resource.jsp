<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/5
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--引入相应的css与js--%>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script src="/js/model/resource.js"></script>
</head>
<body>

<%--
    pagination:分页条支持
--%>
<table id="resourceGrid" class="easyui-datagrid"
       data-options="url:'/resource/page',fitColumns:true,singleSelect:true,fit:true,pagination:true,toolbar:'#gridTools'">
    <thead>
    <tr>
                 <th data-options="field:'name',width:100">name</th>
                 <th data-options="field:'url',width:100">url</th>
                 <th data-options="field:'descs',width:100">descs</th>
            </tr>
    </thead>
</table>
<%--grid顶部工具栏--%>
<div id="gridTools" style="padding:5px;height:auto">
    <%--功能条--%>
    <div style="margin-bottom:5px">
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" data-method="del" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
     <%--查询条--%>
    <form id="searchForm">
        名称: <input name="name" class="easyui-textbox" style="width:80px">
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>

<%--添加与修改的表单对话框--%>
<div id="editDialog" class="easyui-dialog" title="功能编辑" style="width:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="editForm" method="post">
        <input id="resourceId" type="hidden" name="id" />
        <table cellpadding="5">
                         <tr>
                <td>name:</td>
                <td><input class="easyui-validatebox" type="text" name="name"
                           data-options="required:true"></input></td>
            </tr>
                        <tr>
                <td>url:</td>
                <td><input class="easyui-validatebox" type="text" name="url"
                           data-options="required:true"></input></td>
            </tr>
                        <tr>
                <td>descs:</td>
                <td><input class="easyui-validatebox" type="text" name="descs"
                           data-options="required:true"></input></td>
            </tr>
                    </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-method="save">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-method="closeDialog">关闭</a>
    </div>
</div>

</body>
</html>