<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>地区</title>
    <script src="${pageContext.request.contextPath}/statics/echarts/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/statics/echarts/china.js"></script>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>
    <script type="text/javascript">

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $(function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/user/userDistribution',
                type: 'post', //静态资源
                //data:'',
                datatype: 'json',
                success: function (d) {
                    console.log(d);
                    // 指定图表的配置项和数据
                    option = {
                        title: {
                            text: '持名法洲用户分布地图',
                            //  subtext: '持名法洲用户分布',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',

                            data: ['男', '女']
                        },
                        visualMap: {
                            min: 0,
                            max: 2500,
                            left: 'left',
                            top: 'bottom',
                            text: ['高', '低'],           // 文本，默认为数值文本
                            calculable: true
                        },
                        toolbox: {
                            show: true,
                            orient: 'vertical',
                            left: 'right',
                            top: 'center',
                            feature: {
                                mark: {show: true},
                                dataView: {show: true, readOnly: false},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        series: [
                            {
                                name: '男',
                                type: 'map',
                                mapType: 'china',
                                roam: false,
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data: d.male
                            }
                            , {
                                name: '女',
                                type: 'map',
                                mapType: 'china',
                                roam: false,
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data:d.girl
                            }
                        ]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            })
        })
        var goEasy = new GoEasy({
            appkey:"BC-e61b09f5c5dd45648d84b4d694e8f51a"
        })
        goEasy.subscribe({
            channel:"test",
            onMessage:function (message) {
                alert("Channel:"+message.channel+"content"+message.connect());
            }
        })
    </script>
</head>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>

<br><br><br><br>
</body>
</html>
