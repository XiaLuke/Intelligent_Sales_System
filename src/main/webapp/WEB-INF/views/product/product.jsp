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
    <script src="/js/model/product.js"></script>
    <script type="text/javascript" src="/easyui/plugin/tooltip/jeasyui.extensions.base.tooltip.js"></script>
</head>
<body>

<%--
    pagination:分页条支持
--%>
<table id="productGrid" class="easyui-datagrid"
       data-options="url:'/product/page',fitColumns:true,singleSelect:true,fit:true,pagination:true,toolbar:'#gridTools',onLoadSuccess:loadSuccess">
    <thead>
    <tr>
        <th width="20" field="name">名称</th>
        <th width="20" field="color" data-options="formatter:formatColor">颜色</th>
        <th width="20" field="smallpic" data-options="formatter:formatImg">图片</th>
        <th width="20" field="costprice">成本价</th>
        <th width="20" field="saleprice">销售价</th>
        <th width="20" field="types" data-options="formatter:formatObj">类型</th>
        <th width="20" field="unit" data-options="formatter:formatObj">单位</th>
        <th width="20" field="brand" data-options="formatter:formatObj">品牌</th>
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
    <form id="editForm" method="post" enctype="multipart/form-data">
        <input id="productId" type="hidden" name="id"/>
        <table cellpadding="5">
            <tr>
                <td>名称:</td>
                <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>颜色:</td>
                <td><input class="easyui-validatebox" type="color" name="color"></input></td>
            </tr>
            <tr>
                <td>成本价:</td>
                <td><input class="easyui-validatebox" type="text" name="costprice"></input></td>
            </tr>
            <tr>
                <td>销售价:</td>
                <td><input class="easyui-validatebox" type="text" name="saleprice"></input></td>
            </tr>
            <tr>
                <td>产品图片:</td>
                <td>
                    <input name="fileImage" class="easyui-filebox" style="width:100%">
                </td>
            </tr>
            <tr>
                <td>单位:</td>
                <td>
                    <input  class="easyui-combobox" name="unit.id"
                            data-options="valueField:'id',textField:'name',panelHeight:'auto',url:'/util/findAllUnit'">
                </td>
            </tr>
            <tr>
                <td>品牌:</td>
                <td>
                    <input  class="easyui-combobox" name="brand.id"
                            data-options="valueField:'id',textField:'name',panelHeight:'auto',url:'/util/findAllBrand'">
                </td>
            </tr>
            <tr>
                <td>类型:</td>
                <td>

                    <input  class="easyui-combobox" name="types.id"
                            data-options="groupField:'group',valueField:'id',textField:'name',panelHeight:'auto',url:'/util/findChildren'">
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