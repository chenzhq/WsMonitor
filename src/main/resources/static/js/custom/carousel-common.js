/**
 * Created by zkf on 2017/9/21.
 *
 */

//定义echart图表
var Color = ['#5FB878', '#F7B824', '#f11111','#d2d2d2', '#2F4056'];

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

//定义问题列表
$.fn.carProTable = function () {
    var problemTable = this.DataTable({
        "info": false,
        "searching": false,
        "lengthChange": false,
        "paging":false,
        createdRow: function (row, rowData) {
            $('td', row).eq(1).addClass('left aligned');
            $('td', row).eq(2).addClass('left aligned');
            if (rowData.problem_state === '问题') {
                if (rowData.priority_state === '警告') {
                    row.classList.add("warning")
                } else if (rowData.priority_state === '严重') {
                    row.classList.add("negative")
                } else {
                }
            }
        },
        data: [],
        columns: [
            {
                "data": "problem_time",
                "width": "25%",
                "title": "发生时间",
                "orderable": false,

            },
            {
                "data": "host_name",
                "width": "10%",
                "title": "设备",
                "orderable": false,
            },
            {
                "data": "description",
                "width": "20%",
                "title": "问题",
                "orderable": false
            },
            {
                "data": "priority_state",
                "width": "10%",
                "title": "级别",
                "orderable": false,
            },
            {
                "data": "problem_state",
                "width": "10%",
                "title": "状态",
                "orderable": false,
            },
            {
                "data": "duration_string",
                "width": "15%",
                "title": "持续时间",
                "orderable": false,
            },
            {
                "data": "alert_state",
                "width": "10%",
                "title": "告警状态",
                "orderable": false,
            },
            {
                "data": "acknowledged",
                "width": "10%",
                "title": "确认",
                "orderable": false,
            }
        ]
    });
    return problemTable;

}

//定义 gridster 全局变量
var gridster;

//绘制页面
function drawPage($_gridster,page_vo) {
    gridster = $_gridster.gridster({
        //widget_selector: 'li',
        widget_margins: [50, 50],                       //margin大小
        widget_base_dimensions: [100, 100],             //网格粒度
        avoid_overlapped_widgets: true,  //不允许widgets加载的时候重叠
        max_cols: 15,                             //最多创建多少列，null表示没有限制
        max_rows: 10,                             //最多创建多少横，null表示没有限制
        min_cols: 1,                                //至少创建多少列
        min_rows: 1,                               //至少创建多少横
        resize: {
            enabled: true,
            max_size: [4, 4],
            min_size: [1, 1]
        }
    }).data('gridster');
    gridster.disable();
    var layout_arr = page_vo.layout_data;
    var config_arr = page_vo.config_data;
    var block_arr = page_vo.block_data;

    //清空
    gridster.remove_all_widgets();
    $_gridster.html('');

    //画 页 page
    $.each(layout_arr, function (i,value) {
        // 画 项 block
        drawBlock(gridster,layout_arr[i],config_arr[i],block_arr[i],i);
    });

}

/*
 * gridster 初始化对象
 * layout_info 单个布局展示项
 * config_info 单个配置展示项
 * block_info 单个渲染数据展示项
 * i 表示页面中第几个展示项
 *
 * */
function drawBlock(gridster,layout_info,config_info,block_info,i) {
    gridster.add_widget('<li>', layout_info.size_x, layout_info.size_y, layout_info.col, layout_info.row);
    //根据配置画图
    if(config_info.block_type === 'view') {
        //视图
        if(config_info.graph_type === 'statepie') {
            //状态视图
            $('.gridster > ul > li').eq(i).append(
                '<div style="width: 430px;height: 150px;" >' +
                '<div style="width: 100%;height: 10%;"></div>' +
                '<div style="float:left;width: 45%;height: 80%;" id="host_pie'+ i +'"></div >' +
                '<div style="float:left;width: 45%;height: 80%;" id="point_pie'+ i +'"></div>' +
                '</div>');

            var host_chart = echarts.init(document.getElementById('host_pie'+ i));

            var point_chart = echarts.init(document.getElementById('point_pie'+ i));

            getViewStatepie(host_chart,point_chart,block_info);

        }else if(config_info.graph_type === 'problems') {
            //问题视图
            $('.gridster > ul > li').eq(i).append('<table id="problems_'+ i +'"></table>');

            var problemTable = $('#problems_' + i).carProTable();

            problemTable.rows.add(block_info.problems).draw();

        }else if(config_info.graph_type === 'appletree') {
            //苹果树 待完成
        }else {

        }
    }else if(config_info.block_type === 'graph') {
        //图表
        var graph_vo = block_info;
        //渲染图形
        $('.gridster > ul > li').eq(i).append(
            '<div style="height: 100%;width: 100%" id="graph_'+ i +'">图表数据</div>');

        var graph_chart = echarts.init(document.getElementById('graph_' + i));//待补充

        var option = getGraphOption(config_info.graph_type,graph_vo);

        graph_chart.setOption(option);

    }else if(config_info.block_type === 'chart') {
        //控件
        if(config_info.graph_type === 'clock') {
            //钟表
            if(config_info.contents === 'clock-clock') {

                $('.gridster > ul > li').eq(i).append('<div id="clock_' + i +'">时钟数据</div>');
                //时钟显示
                AnalogClock('clock_' + i, new AnalogClockOption(200, "black", "white"));

            }else if(config_info.contents === 'number-clock') {
                //待完成
            }

        }else if (config_info.graph_type === 'table') {
            //表格
            $('.gridster > ul > li').eq(i).append('<div id="table_' + i +'">表格数据</div>');

            $('#table_' + i).append(block_info.html_contents);// 待完成

        }
    }else {

    }
}

/**
 * $_gridster ul对象
 * page_vo
 * index 第几个页面
 */

function drawDataPage($_gridster,page_vo,index) {
    gridster = $_gridster.gridster({
        widget_selector: 'li',
        widget_margins: [50, 50],                       //margin大小
        widget_base_dimensions: [100, 100],             //网格粒度
        avoid_overlapped_widgets: true,  //不允许widgets加载的时候重叠
        max_cols: 15,                             //最多创建多少列，null表示没有限制
        max_rows: 10,                             //最多创建多少横，null表示没有限制
        min_cols: 1,                                //至少创建多少列
        min_rows: 1,                               //至少创建多少横
        resize: {
            enabled: true,
            max_size: [4, 4],
            min_size: [1, 1]
        }
    }).data('gridster');
    //console.log('gridster',gridster);
    gridster.disable();
    var config_arr = page_vo.config_data;
    var block_arr = page_vo.block_data;

    //画 页 page
    $.each(config_arr, function (i,value) {
        // 画 项 block

        drawDataBlock($_gridster.children('li').eq(i),config_arr[i],block_arr[i],index,i);
    });
}

/**
 *
 * @param $_block 块对象
 * @param config_info
 * @param block_info
 * @param index  表示那一页
 * @param i  表示那一块
 */
function drawDataBlock($_block,config_info,block_info,index,i) {
    $_block.html('');
    //根据配置画图
    if(config_info.block_type === 'view') {
        //视图
        if(config_info.graph_type === 'statepie') {
            //状态视图
            $_block.append(
                '<div style="width: 430px;height: 150px;" >' +
                '<div style="width: 100%;height: 10%;"></div>' +
                '<div style="float:left;width: 45%;height: 80%;" id="host_pie'+ index + '_' + i +'"></div >' +
                '<div style="float:left;width: 45%;height: 80%;" id="point_pie'+ index + '_' + i +'"></div>' +
                '</div>');

            var host_chart = echarts.init(document.getElementById('host_pie'+ index + '_' + i));

            var point_chart = echarts.init(document.getElementById('point_pie'+ index + '_' + i));

            getViewStatepie(host_chart,point_chart,block_info);

        }else if(config_info.graph_type === 'problems') {
            //问题视图
            $_block.append('<table id="problems_'+ index + '_' + i +'"></table>');

            var problemTable = $('#problems_' + index + '_' + i).carProTable();

            problemTable.rows.add(block_info.problems).draw();

        }else if(config_info.graph_type === 'appletree') {
            //苹果树 待完成
        }else {

        }
    }else if(config_info.block_type === 'graph') {
        //图表
        var graph_vo = block_info;
        //渲染图形
        $_block.append(
            '<div style="height: 100%;width: 100%" id="graph_'+ index + '_' + i +'">图表数据</div>');

        var graph_chart = echarts.init(document.getElementById('graph_' + index + '_' + i));//待补充

        var option = getGraphOption(config_info.graph_type,graph_vo);

        graph_chart.setOption(option);

    }else if(config_info.block_type === 'chart') {
        //控件
        if(config_info.graph_type === 'clock') {
            //钟表
            if(config_info.contents === 'clock-clock') {

                $_block.append('<div id="clock_' + index + '_' + i +'">时钟数据</div>');
                //时钟显示
                AnalogClock('clock_' + index + '_' + i, new AnalogClockOption(200, "black", "white"));

            }else if(config_info.contents === 'number-clock') {
                //待完成
            }

        }else if (config_info.graph_type === 'table') {
            //表格
            $_block.append('<div id="table_' + index + '_' + i +'">表格数据</div>');

            $('#table_' + index + '_' + i).append(block_info.html_contents);// 待完成

        }
    }else {

    }
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

//绘制状态统计视图
function getViewStatepie(host_chart,point_chart,data) {
    //console.log("state-data",data);
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

//渲染 下拉框 通用方法  data 为数组对象的方法
function getButtonByObject($_target, data) {
    if (!$_target instanceof jQuery) {
        console.error("参数对象必须是jquery对象")
    }
    var str = '';
    for(var i=0;i<data.length;i++)
    {
        str +="<button id='"+ data[i].type +"' class='ui "+ data[i].type +" button' >"+data[i].name+"</button> ";
    }
    $_target.html(str);
}


