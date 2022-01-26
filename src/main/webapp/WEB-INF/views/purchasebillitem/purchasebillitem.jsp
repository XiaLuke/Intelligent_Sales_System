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
    <%--引入group-view的插件--%>
    <script src="/easyui/plugin/datagrid-groupview.js"></script>
    <%--引入Highcharts支持的JS文件--%>
    <script src="/easyui/plugin/Highcharts/code/highcharts.js"></script>
    <script src="/easyui/plugin/Highcharts/code/highcharts-3d.js"></script>
    <script src="/easyui/plugin/Highcharts/code/modules/exporting.js"></script>
    <script src="/easyui/plugin/Highcharts/code/modules/export-data.js"></script>

    <script src="/js/model/purchasebillitem.js"></script>
</head>
<body>

<table id="purchasebillitemGrid">
</table>
<%--grid顶部工具栏--%>
<div id="gridTools" style="padding:5px;height:auto">
     <%--查询条--%>
    <form id="searchForm">
        日期: <input name="beginDate" class="easyui-datebox" style="width:150px">
        - &nbsp;<input name="endDate" class="easyui-datebox" style="width:150px">
        状态: <select class="easyui-combobox" name="status" panelHeight="auto" style="width:100px;">
                <option value="0">待审</option>
                <option value="1">已审</option>
                <option value="-1">作废</option>
            </select>
        <select class="easyui-combobox" name="groupBy" panelHeight="auto" style="width:100px;">
                <option value="0">供应商</option>
                <option value="1">采购员</option>
                <option value="2">月份</option>
            </select>
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a href="#" data-method="chart3d" class="easyui-linkbutton" iconCls="icon-search">3D</a>
        <a href="#" data-method="chart2d" class="easyui-linkbutton" iconCls="icon-search">2D</a>
    </form>
</div>

<%--显示咱们的图表的弹出框--%>
<div id="chartDialog" class="easyui-dialog" title="功能编辑" style="width:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div id="container" style="height: 400px"></div>
</div>


</body>
</html>