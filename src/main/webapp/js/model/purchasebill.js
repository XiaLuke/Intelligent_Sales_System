//对象的格式化
function formatObj(v){
    return v? v.name || v.username :"";
}
//状态的格式化
function formatStatus(v){
    if(v==-1){
        return `<span style="color: grey"><s>删除</s></span>`
    }else if(v==1){
        return `<span style="color: green">已审</span>`
    }else if(v==0){
        return `<span style="color: red">待审</span>`
    }
}

$(function () {

    //1.获取常用的一些组件
    var purchasebillGrid = $("#purchasebillGrid");
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
            //清空grid中的数据
            dg.datagrid("loadData",[]);
            //2.打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
        },
        //修改
        update(){
            //1.获取到选中的那一行数据
            let row = purchasebillGrid.datagrid("getSelected");
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
            //把结果进行回显
            // 供应商
            if(row.supplier){
                row["supplier.id"] = row.supplier.id;
            }
            // 采购员
            if(row.buyer){
                row["buyer.id"] = row.buyer.id;
            }
            editForm.form("load",row);
            //打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
            //拿到这行数据中的items(明细)
            var items = [...row.items];
            dg.datagrid("loadData",items);
        },
        //保存功能
        save(){
            var url = "/purchasebill/save";
            //获到id的值
            var purchasebillId = $("#purchasebillId").val();
            if(purchasebillId){
                url = "/purchasebill/update?cmd=_update";
            }
            //easyui的form提交
            editForm.form('submit', {
                //提交的路径
                url:url,
                //提交之前的操作
                //param里面的数据就会传到后台
                onSubmit: function(param){
                    //1.获取dg到所有的值
                    var rows = dg.datagrid("getRows");
                    //2.遍历所有的行
                    for(var i=0;i<rows.length;i++){
                        var row = rows[i];
                        //3.把值放到params中去
                        param[`items[${i}].product.id`] = row.product.id;
                        param[`items[${i}].num`] = row.num;
                        param[`items[${i}].price`] = row.price;
                        param[`items[${i}].descs`] = row.descs;
                    }
                    // 做一些检查
                    // 返回false可以阻止提交;
                    return $(this).form('validate');
                },
                //data : {success:true/false,msg:xxx} -> 字符串
                success:function(data){
                   var result = JSON.parse(data);
                   if(result.success){
                       purchasebillGrid.datagrid("reload");
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
            let row = purchasebillGrid.datagrid("getSelected");
            //2.如果没有选中，给出提示，后面的代码就不再执行了
            if(!row){
                $.messager.alert('警告','没选中，删个鬼啊！',"warning");
                return ;
            }
            //3.如果选中,确定是否要执行删除
            $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                if (r){
                    //4.如果确定删除,把id传到后台,后台删除数据
                    $.get("/purchasebill/delete",{id:row.id},function (result) {
                        //5.后台会返回 {success:true/false,msg:xxx}
                        //6.后台返回true:刷新数据  / 后台返回false:提示错误信息
                        if(result.success){
                            purchasebillGrid.datagrid("reload");
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
            purchasebillGrid.datagrid("load",params);
        },
        //关闭窗口
        closeDialog(){
            editDialog.dialog("close");
        }
    }

    //这段代码要写在最后面
    /**
     * dg: 明细的grid组件
     * defaultRow:默认的行数据(表头)
     *insertPosition:插入数据的位置 (bottom:下面)
     */
    var dg = $("#itemsGrid"),
        defaultRow = { id: "", product: "", productColor: "", productImg: "", num: 0, price: 0, amount: 0,descs:"" },
        insertPosition = "bottom";
    //明细的grid组件的初始化设置
    var dgInit = function () {
        //datagrid的列数据
        var getColumns = function () {
            var result = [];

            var normal = [
                {
                    field: 'product', title: '商品', width: 180,
                    editor: {
                        type: "combobox",
                        options: {
                            valueField:'id',
                            textField:'name',
                            panelHeight:"auto",
                            url:'/util/findProducts',
                            required: true
                        }
                    },
                    formatter(v){
                        return v?v.name:"";
                    }
                },
                {
                    field: 'productColor', title: '颜色', width: 80,
                    formatter(v,r,i){
                        if(r && r.product){
                            return `<div style='width: 20px;height: 20px;background-color:${r.product.color}'></div>`;
                        }
                    }
                },
                {
                    field: 'productImg', title: '图片', width: 100,
                    formatter(v,r,i){
                        if(r && r.product){
                            return  `<img src='${r.product.smallpic}' alt='没有图片' />`;
                        }
                    }
                },
                {
                    field: 'num', title: '数量', width: 100,
                    editor: {
                        type: "numberbox",
                        options: {
                            precision:2,
                            required: true
                        }
                    }
                },
                {
                    field: 'price', title: '价格', width: 100,
                    editor: {
                        type: "numberbox",
                        options: {
                            precision:2,
                            required: true
                        }
                    }
                },
                {
                    field: 'amount', title: '小计', width: 100,
                    formatter(v,r,i){
                        if(r && r.num && r.price){
                            return (r.num * r.price).toFixed(2);
                        }
                        return 0;
                    }
                },
                {
                    field: 'descs', title: '备注', width: 100,
                    editor: {
                        type: "text"
                    }
                }
            ];
            result.push(normal);

            return result;
        };
        //准备datagrid组件中的属性
        var options = {
            idField: "ID", //id的字段(唯一的)
            rownumbers: true, // 行号
            fitColumns: true, //列的自适应
            fit: true, //自适应咱们的父窗口
            border: true, //是否显示边框
            singleSelect: true,
            columns: getColumns(),
            toolbar:"#itemsTools",
            bodyCls:"bodyCls",
            //表示开启单元格编辑功能
            enableCellEdit: true
        };
        //创建datagrid组件
        dg.datagrid(options);
    };

    //拿到插入的那一行数据的索引
    var getInsertRowIndex = function () {
        return insertPosition == "top" ? 0 : dg.datagrid("getRows").length;
    }

    //定义了一个变量,这个变量也是一个方法
    //button(按键)Bind(绑定)Event(事件)
    var buttonBindEvent = function () {
        //添加一行数据
        $("#btnInsert").click(function () {
            var targetIndex = getInsertRowIndex(), targetRow = $.extend({}, defaultRow, { ID: $.util.guid() });
            dg.datagrid("insertRow", { index: targetIndex, row: targetRow });
            dg.datagrid("editCell", { index: 0, field: "Code" });
        });

        //删除一行数据
        $("#btnRemove").click(function () {
            //1.获取到选中的行(这一行的数据)
            var row = dg.datagrid("getSelected");
            if(row){
                //2.这里要的是这一行的行号
                var index = dg.datagrid("getRowIndex",row);
                //3.把这一行数据删除掉
                dg.datagrid("deleteRow",index);
            }
        });

        /*
        $("#btnSave").click(function () {
            var rows = dg.datagrid("getRows"), len = rows.length;
            for (var i = 0; i < len; i++) {
                dg.datagrid("endEdit", i);
            }
        });
        */
    };

    //把grid初始化与事务绑定完成
    dgInit(); buttonBindEvent();

})