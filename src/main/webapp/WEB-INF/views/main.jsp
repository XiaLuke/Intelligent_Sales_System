<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/5
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script>
        $(function () {
            var mainTab = $("#mainTab");
            $('#menuTree').tree({
                url:'/util/findMenusByUser',
                onClick: function(node){
                   //console.debug(node)
                    //获取相应路径
                    var nodeUrl = node.url;
                    if(!nodeUrl){
                       //如果没有路径，就不需要打开新页面
                        return;
                     }
                    //拿到菜单名称
                    var nodeTitle = node.text;
                    //判断这个菜单名称卡是否已经存在
                    if(mainTab.tabs("exists",nodeTitle)){
                        //如果存在，选中这个菜单
                        mainTab.tabs("select",nodeTitle)
                        return;
                    }
                    // 添加一个新的选项卡
                    mainTab.tabs('add',{
                        //选项卡名称
                        title:nodeTitle,
                        //是否可以关闭
                        closable:true,
                        //当前选项卡的内容 嵌入一个iframe
                        content:'<iframe src="'+node.url+'" width="100%" height="100%" frameborder="0">'
                    });
                }

            });
        })
    </script>
</head>
<body class="easyui-layout">
    <div data-options="region:'north'" style="height:100px;">
        <h1>易销宝</h1>
        <%--
            shiro:principal：显示主体名称
            property:属性
        --%>
        <div style="text-align: right;padding-right:20px;">欢迎您,<shiro:principal property="username" />  <a href="/logout">登出</a></div>
    </div>
    <div data-options="region:'west',title:'菜单',split:true" style="width:230px;">
        <ul id="menuTree"></ul>
    </div>
    <div id="mainTab"  class="easyui-tabs"  data-options="region:'center'">
        <div title="首页" style="padding:20px;display:none;">
            欢迎来到易销宝操作系统！！！ 给钱！！
        </div>
    </div>
</body>
</html>
