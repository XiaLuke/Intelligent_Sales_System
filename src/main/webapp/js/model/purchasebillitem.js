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

    var purchasebillitemGrid = $('#purchasebillitemGrid');
    var searchForm = $('#searchForm');
    var chartDialog = $('#chartDialog');

    purchasebillitemGrid.datagrid({
        rownumbers:true, // 行号
        remoteSort:false, //远程排序
        nowrap:false,
        fit:true,
        fitColumns:true,
        toolbar:"#gridTools",
        url:'/purchasebillitem/findItems', //获取数据的路径
        //所有的列的字段 【需要修改成我们自己的数据:编号，采购员，供应商....】
        columns:[[
            {field:'id',title:'编号',width:100},
            {field:'supplierName',title:'供应商',width:100},
            {field:'buyerName',title:'采购员',width:100},
            {field:'productName',title:'产品',width:100},
            {field:'productTypeName',title:'产品类型',width:100},
            {field:'vdate',title:'日期',width:100},
            {field:'price',title:'单价',width:100},
            {field:'num',title:'数量',width:100},
            {field:'amount',title:'小计',width:100},
            {field:'status',title:'状态',formatter:formatStatus,width:100}
        ]],
        //分组字段
        groupField:'groupField',
        view: groupview, //支持分组视图功能
        //分组格式化
        // value:就是当前groupField分组的值
        // rows:当前这一组的所有行
        groupFormatter:function(value, rows){
            var totalNum = 0;
            var totalAmount = 0;
            for(let r of rows){
                totalNum += r.num;
                totalAmount += r.amount;
            }
            //成都供应商 - 10 条数据 共34商品 总金额:344
            return `${value} - ${rows.length}条数据 <span style="color: #00ee00">共${totalNum}个商品</span>
                    <span  style="color: deeppink">总金额:${totalAmount}</span>`;
}
});



$("*[data-method]").on("click",function(){
        var methodName = $(this).data("method");
        window.itsource[methodName]();
    })
    itsource={
        //查询
        search(){
            //serializeObject:拿到一个form中的所有数据,封装成json对象
            var params = searchForm.serializeObject();
            purchasebillitemGrid.datagrid("load",params);
        },
        //查询3D图片
        chart3d(){
            //打开咱们的弹出框
            chartDialog.dialog("center").dialog("open");
            //获取到表单中的所有值
            var params = searchForm.serializeObject();
            //请求的时候把值传到后台
            $.post("/purchasebillitem/findCharts",params,function(result){
                //展示图表
                Highcharts.chart('container', {
                    chart: {
                        type: 'pie',
                        options3d: {
                            enabled: true,
                            alpha: 45, //倾斜度
                            beta: 0
                        }
                    },
                    title: {
                        text: '浏览器2014年占比'
                    },
                    //鼠标移上去后显示的数据
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            //是否自己可以选择
                            allowPointSelect: true,
                            //鼠标指上来后的样式
                            cursor: 'pointer',
                            depth: 35, //深度
                            dataLabels: {
                                enabled: true,
                                format: '{point.name}'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: '浏览器占比',
                        data: result
                    }]
                });
            })
        },
        //查询3D图片
        chart2d(){
            //打开咱们的弹出框
            chartDialog.dialog("center").dialog("open");
            //获取到表单中的所有值
            var params = searchForm.serializeObject();
            //请求的时候把值传到后台
            $.post("/purchasebillitem/findCharts",params,function(result){
                Highcharts.chart('container', {
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie'
                    },
                    title: {
                        text: 'Browser market shares in January, 2018'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                }
                            }
                        }
                    },
                    series: [{
                        name: 'Brands',
                        colorByPoint: true,
                        data: result
                    }]
                });
            })
        }
    }

})