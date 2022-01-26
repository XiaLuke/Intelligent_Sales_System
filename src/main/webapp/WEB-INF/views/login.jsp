<%--
  Created by IntelliJ IDEA.
  User: zhaoyi
  Date: 2018/10/14
  Time: 下午4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>源码智销系统</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <script type="text/javascript">

        //判断当前页面是否是顶层页面
        if(top != window){
            //window.top.location.href = "/login";
            window.top.location.href = window.location.href;
        }

        $(document.documentElement).on("keyup", function(event) {
            //console.debug(event.keyCode);
            console.debug(event);

            var keyCode = event.keyCode;
            if (keyCode === 13) { // 捕获回车
                submitForm(); // 提交表单
            }
        });

        //提交form表单
        function submitForm() {
            $('#loginForm').form('submit', {
                url:"/login",
                onSubmit: function(){
                    return $(this).form('validate');
                },
                //注意:现在这个data是一个字符串:{success:true,msg:""}
                success:function(data){
                    var result = JSON.parse(data);
                    if(result.success){
                        //如果登录成功，跳转到主页面
                        window.location.href = "/main";
                    }else{
                        //如果登录失败，给出错误提示
                        $.messager.alert('错误',result.msg);
                    }
                }
            });
        }
    </script>
</head>
<body>


<div align="center" style="margin-top: 100px;">
    <div class="easyui-panel" title="智销系统用户登陆" style="width: 350px; height: 240px;">
        <form id="loginForm" class="easyui-form" method="post">
            <table align="center" style="margin-top: 15px;">
                <tr height="20">
                    <td>用户名:</td>
                </tr>
                <tr height="10">
                    <td><input name="username" class="easyui-validatebox" required="true" value="admin" /></td>
                </tr>
                <tr height="20">
                    <td>密&emsp;码:</td>
                </tr>
                <tr height="10">
                    <td><input name="password" type="password" class="easyui-validatebox" required="true" value="admin" /></td>
                </tr>
                <tr height="40">
                    <td align="center"><a href="javascript:;" class="easyui-linkbutton" onclick="submitForm();">登陆</a> <a
                            href="javascript:;" class="easyui-linkbutton" onclick="resetForm();">重置</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>