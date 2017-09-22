/**
 * Created by zkf on 2017/9/21.
 *
 */

//渲染 下拉框 通用方法  data 为数组字符串的方法
function getSelectByString($_target, data) {
    if (!$_target instanceof jQuery) {
        console.error("参数对象必须是jquery对象")
    }
    var str = '';
    for(var i=0;i<data.length;i++)
    {
        str +="<div class='item' data-value='"+data[i]+"'  >"+data[i]+"</div> ";
    }
    $_target.html(str);
    //默认选择第一个
    $_target.dropdown('set text',data[0]);
    $_target.dropdown('set value',data[0]);
}

//渲染 下拉框 通用方法  data 为数组对象的方法
function getSelectByObject($_target, data) {
    if (!$_target instanceof jQuery) {
        console.error("参数对象必须是jquery对象")
    }
    var str = '';
    for(var i=0;i<data.length;i++)
    {
        str +="<div class='item' data-value='"+data[i].type+"'  >"+data[i].name+"</div> ";
    }
    $_target.html(str);
    //默认选择第一个
    $_target.dropdown('set text',data[0].name);
    $_target.dropdown('set value',data[0].type);
}

function getSelectPoint(data) {
    var str = '';
    for(var i=0;i<data.length;i++)
    {
        str +="<div class='item' data-value='"+data[i].point_id+"'  >"+data[i].point_name+"</div> ";
    }
    $_target.html(str);
    //默认选择第一个
    $_target.dropdown('set text',data[0].point_name);
    $_target.dropdown('set value',data[0].point_id);
}

//展示页数据 渲染 page_vo 全局变量，在调用之前已经通过 API 获取到了 page_vo 的值
function darwPage() {
    var layout_arr = page_vo.layout_data;
    var config_arr = page_vo.config_data;
    var block_arr = page_vo.block_data;
    for(x in layout_arr) {
        //第一步： 确定布局
        //console.log('x',x);
        //dom 选择需要根据具体情况而定
        $('').gridster({
            widget_margins: [x.col, x.row ],
            widget_base_dimensions: [x.x_size, x.y_size]
        });
        //第二步： 根据配置画图
        if(config_arr[x].block_type === 'view') {
            //视图
            if(config_arr[x].graph_type === 'statepie') {
                //状态视图
                var host_chart = echarts.init(document.getElementById(''));//待补充
                var host_option={
                    title: {
                        text: '0',
                        subtext: '台设备',
                        x: 'center',
                        y: 'center',
                        itemGap: 1,
                        textStyle: {
                            color: '#01AAED',
                            fontFamily: '微软雅黑',
                            fontSize: 24,
                            fontWeight: 'bolder'
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'right',
                        y: 'center',
                        data:['正常','警告','严重'],
                    },
                    grid: {
                        left:'5%',
                        right:'5%',
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: ['40%', '65%'],
                            selectedMode: '',
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        formatter: function (param) {
                                            return param.name + param.value + "台" + ':\n' + Math.round(param.percent) + '%';
                                        },
                                        textStyle: {
                                            fontWeight: 'bolder'
                                        }
                                    }
                                },
                            },
                            data:  [
                                {
                                    "value": 2,
                                    "name": "正常,"
                                },
                                {
                                    "value": 7,
                                    "name": "警告"
                                }
                            ],
                            color: Color
                        }
                    ]
                };
                host_chart.setOption(host_option);
                var point_chart = echarts.init(document.getElementById(''));//待补充
                var point_option = {
                    title: {
                        text: '0',
                        subtext: '个监控点',
                        x: 'center',
                        y: 'center',
                        itemGap: 1,
                        textStyle: {
                            color: '#01AAED',
                            fontFamily: '微软雅黑',
                            fontSize: 24,
                            fontWeight: 'bolder'
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'right',
                        y: 'center',
                        data:['正常','警告','严重'],
                    },
                    grid: {
                        left:'15%',
                        right:'15%',
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: ['40%', '65%'],
                            selectedMode: '',
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        formatter: function (param) {
                                            return param.name + param.value + "个" + ':\n' + Math.round(param.percent) + '%';
                                        },
                                        textStyle: {
                                            fontWeight: 'bolder'
                                        }
                                    }
                                },
                            },
                            data:  [
                                {
                                    "value": 5,
                                    "name": "正常"
                                },
                                {
                                    "value": 5,
                                    "name": "警告"
                                }
                            ],
                            color: Color
                        }
                    ]
                }
                point_chart.setOption(point_option);
                getViewStatepie(host_chart,point_chart,block_arr[x]);

            }else if(config_arr[x].graph_type === 'problems') {
                //问题视图
                //待完成
            }else if(config_arr[x].graph_type === 'appletree') {
                //苹果树
            }else {

            }
        }else if(config_arr[x].block_type === 'graph') {
            //图表
            graph_vo = block_arr[x];
            //渲染图形
            var graph_chart = echarts.init(document.getElementById(''));//待补充
            var option = getGraphOption(config_arr[x].graph_type,graph_vo);
            graph_chart.setOption(option);
        }else if(config_arr[x].block_type === 'chart') {
            //控件
            if(config_arr[x].graph_type === 'clock') {
                //钟表
                $('').html(block_arr[x].time);//待完成
            }else if (config_arr[x].graph_type === 'table') {
                //表格
                $('').html(block_arr[x].html_contents);// 待完成
            }
        }else {

        }

    }

}
//后期整理还可以继续优化，将图表单独拿出来先初始化，后面根据赋值修改图形数据
function getGraphOption(graph_type,graph_vo) {
    var option ;
    var ymax ;
    if(graph_vo.units === '%'){
        ymax = 100;
    }else {
        ymax = null;
    }
    //图形值
    /*console.log("图形类型： " + graph_type);*/
    switch (graph_type) {
        case 'gauge':
            option = {
                title: {
                    text: graph_vo.block_name},
                tooltip : {
                    formatter: '{a} <br/>{b} : {c}%'
                },
                series: [
                    {
                        name: graph_vo.block_name,
                        type: 'gauge',
                        min:0,
                        max:100,
                        splitNumber: 5,
                        axisLine: {            // 坐标轴线
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: [colorGreen,colorYellow,colorRed],
                                width: 13
                            }
                        },
                        axisTick: {            // 坐标轴小标记
                            length: 18,        // 属性length控制线长
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: 'auto'
                            }
                        },
                        splitLine: {           // 分隔线
                            length: 22,         // 属性length控制线长
                            lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                                color: 'auto'
                            }
                        },
                        pointer: {
                            width:5
                        },
                        detail: {
                            formatter:'{value}%',
                            offsetCenter:[0,'60%'],
                            textStyle: {
                                fontWeight: 'bolder', fontSize: 16,color: '#333',
                            }},
                        data: [{value: graph_vo.data[graph_vo.data.length-1], name: ''}]
                    }
                ]
            };
            break;
        case 'area':
            /*console.log('进入到了area域：');*/
            option = {
                title: {
                    text: graph_vo.block_name
                },
                tooltip: {
                    trigger: 'axis',
                    position: function (pt) {
                        return [pt[0], '10%'];
                    }
                },
                legend: {
                    data:[]
                },
                grid: {
                    left: '2%',
                    right: '10%',
                    bottom: '0%',
                    top:'20%',
                    containLabel: true
                },
                xAxis: {
                    data: graph_vo.data_time
                },
                yAxis: {
                    max: ymax,
                },
                series: [{
                    name: graph_vo.block_name,
                    type: 'line',
                    data: graph_vo.data,
                    areaStyle: {
                        normal: { }
                    },
                    color: []
                }]
            };
            break;
        default:
            option = {
                title: {
                    text: graph_vo.block_name
                },
                tooltip: {
                    trigger: 'axis',
                    position: function (pt) {
                        return [pt[0], '10%'];
                    }
                },
                legend: {
                    data:[]
                },
                grid: {
                    left: '2%',
                    right: '10%',
                    bottom: '0%',
                    top:'20%',
                    containLabel: true
                },
                xAxis: {
                    data: graph_vo.data_time
                },
                yAxis: {
                    max: ymax,
                    splitLine:{show: false},
                },
                series: [{
                    name: graph_vo.block_name,
                    type: graph_vo.graph_type,
                    data: graph_vo.data,
                    color: [],
                }]
            };
    }
    return option;
}

function getViewStatepie(host_chart,point_chart,data) {
    console.log("state-data",data);
    var hostStateNum = data.hostStateNum;
    if (hostStateNum.stateNum) {
        host_option.title.text = hostStateNum.totalNum;
        host_option.series[0].data = hostStateNum.stateNum;
        // 重写指定图表的配置项和数据
        host_chart.setOption(host_option);
    }
    var pointStateNum = data.pointStateNum;
    if (pointStateNum.stateNum) {
        point_option.title.text = pointStateNum.totalNum;
        point_option.series[0].data = pointStateNum.stateNum;
        point_chart.setOption(point_option);
    }
}