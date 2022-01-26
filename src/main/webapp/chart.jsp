<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/19
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/easyui/plugin/Highcharts/code/highcharts.js"></script>
    <script src="/easyui/plugin/Highcharts/code/highcharts-3d.js"></script>
    <script src="/easyui/plugin/Highcharts/code/modules/exporting.js"></script>
    <script src="/easyui/plugin/Highcharts/code/modules/export-data.js"></script>
</head>
<body>
<div id="container" style="height: 400px"></div>

<script type="text/javascript">

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
            data: [
                ['火狐', 45.0],
                ['IE', 26.8],
                {
                    name: '谷歌',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['苹果', 8.5],
                ['欧朋', 6.2],
                ['其它', 0.7]
            ]
        }]
    });
</script>
</body>
</html>
