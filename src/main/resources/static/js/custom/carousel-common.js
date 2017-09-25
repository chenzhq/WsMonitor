/**
 * Created by zkf on 2017/9/21.
 *
 */

//渲染 下拉框 通用方法  data 为数组字符串的方法
function getSelectByString($_target,$_default, data) {
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
    $_default.dropdown('set text',data[data.length-1]);
    $_default.dropdown('set value',data[data.length-1]);
}

//渲染 下拉框 通用方法  data 为数组对象的方法
function getSelectByObject($_target,$_default, data) {
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
    $_default.dropdown('set text',data[data.length-1].name);
    $_default.dropdown('set value',data[data.length-1].type);
}
//后期整理还可以继续优化，将图表单独拿出来先初始化，后面根据赋值修改图形数据
function getGraphOption(graph_type,graph_vo) {
//        console.log("type",graph_type);
//        console.log("graph_vo",graph_vo);
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
                    type: graph_type,
                    data: graph_vo.data,
                    color: [],
                }]
            };
    }
    return option;
}

