//对权限进行相应的格式化
function permsFormat(val) {
    var permsStr = "";
    for(let o of val){
        permsStr += o.name +" ";
    }
    return permsStr;
}

$(function () {

    //1.获取常用的一些组件
    var roleGrid = $("#roleGrid");
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
            //清空左边grid的数据  loadData:加载本地数据，旧的行将被移除
            userPermissionGrid.datagrid("loadData",[]);
            //2.打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
        },
        //修改
        update(){
            //1.获取到选中的那一行数据
            let row = roleGrid.datagrid("getSelected");
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
            //回显左边grid的数据  loadData:加载本地数据，旧的行将被移除
            //获取当前选中的行的所有权限
            //这里必需要拷备一个数组(不然会直接修改原数据)
            var permissions = [...row.permissions];
            userPermissionGrid.datagrid("loadData",permissions);
        },
        //保存功能
        save(){
            var url = "/role/save";
            //获到id的值
            var roleId = $("#roleId").val();
            if(roleId){
                url = "/role/update?cmd=_update";
            }
            //easyui的form提交
            editForm.form('submit', {
                //提交的路径
                url:url,
                //提交之前的操作
                // param这个参数加的属性都会向后台提示
                onSubmit: function(param){
                    /**
                     * 后台的权限是： List<Permission> permissions
                     * 我们这里传参:permissions[0].id=? ,permissions[1].id=?,...
                     * @type {string}
                     */
                    //1.拿到role(左边的grid)中的所有权限
                    var rows = userPermissionGrid.datagrid("getRows");
                    //2.遍历它,拼接出相应的结构
                   for(var i=0;i<rows.length;i++){
                        var row = rows[i];
                        // param.permissions is undefined
                       param[`permissions[${i}].id`] = row.id;
                   }
                    // 返回false可以阻止提交;
                    return $(this).form('validate');
                },
                //data : {success:true/false,msg:xxx} -> 字符串
                success:function(data){
                   var result = JSON.parse(data);
                   if(result.success){
                       roleGrid.datagrid("reload");
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
            let row = roleGrid.datagrid("getSelected");
            //2.如果没有选中，给出提示，后面的代码就不再执行了
            if(!row){
                $.messager.alert('警告','没选中，删个鬼啊！',"warning");
                return ;
            }
            //3.如果选中,确定是否要执行删除
            $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                if (r){
                    //4.如果确定删除,把id传到后台,后台删除数据
                    $.get("/role/delete",{id:row.id},function (result) {
                        //5.后台会返回 {success:true/false,msg:xxx}
                        //6.后台返回true:刷新数据  / 后台返回false:提示错误信息
                        if(result.success){
                            roleGrid.datagrid("reload");
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
            roleGrid.datagrid("load",params);
        },
        //关闭窗口
        closeDialog(){
            editDialog.dialog("close");
        },
        //添加权限 index:点的是哪一行  row:点击这一行的数据
        addPermission(index, row){
            //1.获取左边grid的所有数据
            var rows = userPermissionGrid.datagrid("getRows");
            //2.循环所有的行,如果和传过来的row一致,就不执行后面代码了
            for(let o of rows){
                if(o.id == row.id){
                    $.messager.show({
                        title:'提示',
                        msg:'贪心变成贫...',
                        timeout:1000,
                        showType:'slide',
                        style:{
                            right:'',
                            top:document.body.scrollTop+document.documentElement.scrollTop,
                            bottom:''
                        }
                    });
                    return;
                }
            }
            userPermissionGrid.datagrid("appendRow",row);
        },
        //删除权限
        removePermission(index, row){
            userPermissionGrid.datagrid("deleteRow",index);
        }
    }

    //单独获取两个rid
    var userPermissionGrid= $('#userPermissionGrid');
    var allPermissionGrid= $('#allPermissionGrid');

    //创建当前角色对应的权限（左）grid
    userPermissionGrid.datagrid({
        fit:true,
        // url:'datagrid_data.json',
        fitColumns:true,
        singleSelect:true,
        columns:[[
            {field:'name',title:'名称',width:100},
            {field:'sn',title:'权限',width:100},
            {field:'url',title:'资源',width:100}
        ]],
        onDblClickRow:itsource.removePermission
    });

    //创建权限的(右)grid
    allPermissionGrid.datagrid({
        fit:true,
        url:'/permission/page',
        fitColumns:true,
        singleSelect:true,
        pagination:true,
        columns:[[
            {field:'name',title:'名称',width:100},
            {field:'sn',title:'权限',width:100},
            {field:'url',title:'资源',width:100}
        ]],
        onDblClickRow:itsource.addPermission
    });
})