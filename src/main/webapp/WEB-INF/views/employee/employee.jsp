<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/5
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <%--引入相应的css与js--%>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script src="/js/model/employee.js"></script>
</head>
<body>

<%--
    pagination:分页条支持
--%>
<table id="employeeGrid" class="easyui-datagrid"
       data-options="url:'/employee/page',fitColumns:true,singleSelect:true,fit:true,pagination:true,toolbar:'#gridTools'">
    <thead>
    <tr>
        <th data-options="field:'id',width:100">编码</th>
        <th data-options="field:'headImage',width:100,formatter:formatImage">头像</th>
        <th data-options="field:'username',width:100">名称</th>
        <th data-options="field:'password',width:100">密码</th>
        <th data-options="field:'email',width:100">邮件</th>
        <th data-options="field:'age',width:100">年龄</th>
        <th data-options="field:'department',width:100,formatter:formatDept">部门</th>
    </tr>
    </thead>
</table>
<%--grid顶部工具栏--%>
<div id="gridTools" style="padding:5px;height:auto">
    <%--功能条--%>
    <div style="margin-bottom:5px">
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <shiro:hasPermission name="employee:delete">
            <a href="#" data-method="del" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </shiro:hasPermission>
    </div>
     <%--查询条--%>
    <form id="searchForm" action="/employee/download">
        用户名: <input name="username" class="easyui-textbox" style="width:80px">
        邮件: <input name="email" class="easyui-textbox" style="width:80px">
        部门:
        <input  name="departmentId" class="easyui-combobox"   panelHeight="auto"
               data-options="valueField:'id',textField:'name',url:'/util/depts'" />
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <button type="submit" class="easyui-linkbutton" iconCls="icon-search">导出</button>
    </form>
</div>

<%--添加与修改的表单对话框--%>
<div id="editDialog" class="easyui-dialog" title="功能编辑" style="width:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="editForm" method="post">
        <input id="employeeId" type="hidden" name="id" />
        <table cellpadding="5">
            <tr>
                <td>用户名:</td>
                <td><input class="easyui-validatebox" type="text" name="username"
                           data-options="required:true,validType:'checkName'"></input></td>
            </tr>
            <tr data-edit="true">
                <td>密码:</td>
                <td><input id="password" class="easyui-validatebox" type="password" name="password" data-options="required:true"></input></td>
            </tr>
            <tr data-edit="true">
                <td>确定密码:</td>
                <td><input class="easyui-validatebox" type="password" name="confirmPassword"
                           validType="equals['password','id']"
                           data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>邮件:</td>
                <td><input class="easyui-validatebox" type="text" name="email" data-options="required:true,validType:'email'"></input></td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><input class="easyui-validatebox" type="text" name="age" data-options="validType:'integerRange[18,80]'"></input></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td>
                    <input  name="department.id" class="easyui-combobox"   panelHeight="auto"
                            data-options="valueField:'id',textField:'name',url:'/util/depts',required:true" />
                </td>
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
