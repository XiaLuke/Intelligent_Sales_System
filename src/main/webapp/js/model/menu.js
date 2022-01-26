$(function () {

    //1.获取常用的一些组件
    var menuGrid = $("#menuGrid");
    var searchForm = $("#searchForm");
    var editDialog = $("#editDialog");
    var editForm = $("#editForm");

    //2.绑定相应的事件
    $("*[data-method]").on("click",function(){
        var methodName = $(this).data("method");
        window.itsource[methodName]();
    })


    itsource={
        //添加
        add(){
            //让密码框失效且隐藏起来
            $("*[data-edit]").show();
            $("*[data-edit] input").validatebox("enable");
            //1.清空form中的数据
            editForm.form("clear");
            //2.打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
        },
        //修改
        update(){
            //1.获取到选中的那一行数据
            let row = menuGrid.datagrid("getSelected");
            //2.如果没有选中，给出提示，后面的代码就不再执行了
            if(!row){
                $.messager.alert('警告','没选中，改个鬼啊！',"warning");
                return ;
            }
            //清空form中的数据
            editForm.form("clear");
            //让密码框失效且隐藏起来
            $("*[data-edit]").hide();
            $("*[data-edit] input").validatebox("disable");
	    /*
            //把结果进行回显
            if(row.department){
                row["department.id"] = row.department.id;
            }
	    */
            editForm.form("load",row);
            //打开弹出框(居中)
            editDialog.dialog("center").dialog("open");

        },
        //保存功能
        save(){
            var url = "/menu/save";
            //获到id的值
            var menuId = $("#menuId").val();
            if(menuId){
                url = "/menu/update?cmd=_update";
            }
            //easyui的form提交
            editForm.form('submit', {
                //提交的路径
                url:url,
                //提交之前的操作
                onSubmit: function(){
                    // 做一些检查
                    // 返回false可以阻止提交;
                    return $(this).form('validate');
                },
                //data : {success:true/false,msg:xxx} -> 字符串
                success:function(data){
                   var result = JSON.parse(data);
                   if(result.success){
                       menuGrid.datagrid("reload");
                   }else{
                       $.messager.alert('错误',`失败了，打我啊！ 原因是:${result.msg}`,"error");
                   }
                   //关闭弹出框
                    itsource.closeDialog();
                }
            });
        },
        //删除
        del(){
            //1.获取到选中的那一行数据
            let row = menuGrid.datagrid("getSelected");
            //2.如果没有选中，给出提示，后面的代码就不再执行了
            if(!row){
                $.messager.alert('警告','没选中，删个鬼啊！',"warning");
                return ;
            }
            //3.如果选中,确定是否要执行删除
            $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                if (r){
                    //4.如果确定删除,把id传到后台,后台删除数据
                    $.get("/menu/delete",{id:row.id},function (result) {
                        //5.后台会返回 {success:true/false,msg:xxx}
                        //6.后台返回true:刷新数据  / 后台返回false:提示错误信息
                        if(result.success){
                            menuGrid.datagrid("reload");
                        }else{
                            $.messager.alert('错误',`失败了，打我啊！ 原因是:${result.msg}`,"error");
                        }
                    })

                }
            });

        },
        //查询
        search(){
            //serializeObject:拿到一个form中的所有数据,封装成json对象
            var params = searchForm.serializeObject();
            menuGrid.datagrid("load",params);
        },
        //关闭窗口
        closeDialog(){
            editDialog.dialog("close");
        }
    }

})