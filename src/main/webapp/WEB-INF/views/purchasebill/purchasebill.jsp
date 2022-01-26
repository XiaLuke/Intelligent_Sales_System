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
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.getColumnInfo.js"></script>
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.editors.js"></script>
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.edit.cellEdit.js"></script>
    <script src="/js/model/purchasebill.js"></script>

    <style type="text/css">
        .bodyCls{
            min-height: 100px;
        }
    </style>
</head>
<body>

<%--
    pagination:分页条支持
--%>
<table id="purchasebillGrid" class="easyui-datagrid"
       data-options="url:'/purchasebill/page',fitColumns:true,singleSelect:true,fit:true,pagination:true,toolbar:'#gridTools'">
    <thead>
    <tr>
        <th data-options="field:'vdate',width:100">交易时间</th>
        <th data-options="field:'totalNum',width:100">总数量</th>
        <th data-options="field:'totalAmount',width:100">总金额</th>
        <th data-options="field:'status',width:100,formatter:formatStatus">状态</th>
        <th data-options="field:'supplier',width:100,formatter:formatObj">供应商</th>
        <th data-options="field:'auditor',width:100,formatter:formatObj">审核人</th>
        <th data-options="field:'inputUser',width:100,formatter:formatObj">录入人</th>
        <th data-options="field:'buyer',width:100,formatter:formatObj">采购员</th>
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
        日期: <input name="beginDate" class="easyui-datebox" style="width:150px">
        - &nbsp;<input name="endDate" class="easyui-datebox" style="width:150px">
        状态: <select class="easyui-combobox" name="status" panelHeight="auto" style="width:100px;">
        <option value="0">待审</option>
        <option value="1">已审</option>
        <option value="-1">作废</option>
    </select>
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>

<%--添加与修改的表单对话框--%>
<div id="editDialog" class="easyui-dialog" title="功能编辑" style="width:820px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="editForm" method="post">
        <input id="purchasebillId" type="hidden" name="id"/>
        <table cellpadding="5">
            <tr>
                <td>交易时间:</td>
                <td><input class="easyui-datebox" type="text" name="vdate"
                           data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>供应商:</td>
                <td> <input  name="supplier.id" class="easyui-combobox"   panelHeight="auto"
                             data-options="valueField:'id',textField:'name',url:'/util/findAllSupplier'" /></td>
            </tr>
            <tr>
                <td>采购员:</td>
                <td><input  name="buyer.id" class="easyui-combobox"
                            data-options="valueField:'id',textField:'username',url:'/util/findBuyer'" /></td>
            </tr>
        </table>
        <%--明细数据--%>
        <table id="itemsGrid"></table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-method="save">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-method="closeDialog">关闭</a>
    </div>
</div>

<div id="itemsTools">
    <a href="javascript:void(0)" id="btnInsert" data-options="iconCls:'icon-add',plain:true" class="easyui-linkbutton">增加</a>
    <a href="javascript:void(0)" id="btnRemove" data-options="iconCls:'icon-remove',plain:true"  class="easyui-linkbutton">删除</a>
</div>

</body>
</html>