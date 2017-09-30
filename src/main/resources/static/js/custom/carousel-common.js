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

//绘制页面 轮播配置使用
function drawPage($_gridster,page_vo) {
    gridster = $_gridster.gridster({
        //widget_selector: 'li',
        widget_margins: [20, 20],                       //margin大小
        widget_base_dimensions: [50, 50],             //网格粒度
        avoid_overlapped_widgets: true,  //不允许widgets加载的时候重叠
        max_cols: 100,                             //最多创建多少列，null表示没有限制
        max_rows: 100,                             //最多创建多少横，null表示没有限制
        min_cols: 1,                                //至少创建多少列
        min_rows: 1,                               //至少创建多少横
        resize: {
            enabled: true,
            max_size: [20, 20],
            min_size: [1, 1]
        },
        //拖拽回调函数
        /*draggable:{
             handle: '',         //设置拖动控件
             //拖拽之前回调
             start: function(event, ui){

             },
             //拖拽同时回调
             drag:function(event, ui){

             },
             //拖拽停止回调
             stop: function(event, ui){

             }
         }*/
    }).data('gridster');
    gridster.disable();
    var layout_arr = page_vo.layout_data;
    var config_arr = page_vo.config_data;
    var block_arr = page_vo.block_data;

    //清空
    gridster.remove_all_widgets();
    $_gridster.html('');

    //画 页 page
    $.each(layout_arr, function (i,value) { // i 表示第几项

        gridster.add_widget('<li >', value.size_x, value.size_y, value.col, value.row);

        var $_block = $_gridster.children('li').eq(i);

        // 画 项 block  0表示第 0 页，这里不关心第几页，所以可随意取值
        drawBlock($_block,config_arr[i],block_arr[i],0,i);
    });

}

function addBlockWidget(size_x,size_y) {

    //添加config_info 保存至 page_vo.config_data
    console.log('view page_vo',page_vo);
    var last_block = page_vo.config_data.length;
    var config_info = page_vo.config_data[page_vo.config_data.length-1];
    var block_info = page_vo.block_data[page_vo.block_data.length-1];


    var $_block = gridster.add_widget('<li >', size_x, size_y);

    console.log('new block',$_block);

    drawBlock($_block,config_info,block_info,0,last_block);

    addBlockiCon($_block);
}

/**
 * 用于轮播
 * $_block 展示块 对象
 * config_info 单个配置展示项
 * block_info 单个渲染数据展示项
 * index 表示第几页
 * i 表示页面中第几个展示项
 *
 * */
function drawBlock($_block,config_info,block_info,index,i) {

    var temp_str = $('#block_temp').html();

    $_block.html(temp_str);

    // block 标题
    $_block.find('.block_title').text(config_info.block_name);

    //根据配置画图
    if(config_info.block_type === 'view') {
        //视图
        if(config_info.graph_type === 'statepie') {
            //状态视图

            //block 内容
            $_block.find('.block_content').append(
                    '<div style="float:left;width: 45%;height: 80%;" id="host_pie' + index + '_' + i +'"></div >' +
                    '<div style="float:left;width: 45%;height: 80%;" id="point_pie' + index + '_' + i +'"></div>');

            var host_chart = echarts.init(document.getElementById('host_pie' + index + '_' + i));

            var point_chart = echarts.init(document.getElementById('point_pie' + index + '_' + i));

            getViewStatepie(host_chart,point_chart,block_info);

        }else if(config_info.graph_type === 'problems') {
            //问题视图

            // block 内容
            $_block.find('.block_content').append('<table id="problems_' + index + '_' + i +'"></table>');

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
        $_block.find('.block_content').append(
            '<div style="height: 100%;width: 100%" id="graph_' + index + '_'+ i +'">图表数据</div>');

        var graph_chart = echarts.init(document.getElementById('graph_' + index + '_' + i));//待补充

        var option = getGraphOption(config_info.graph_type,graph_vo);

        graph_chart.setOption(option);

    }else if(config_info.block_type === 'chart') {
        //控件
        if(config_info.graph_type === 'clock') {
            //钟表
            if(config_info.contents === 'clock-clock') {

                $_block.find('.block_content').append('<div id="clock_' + index + '_' + i +'"></div>');
                //时钟显示
                AnalogClock('clock_' + index + '_' + i, new AnalogClockOption(200, "black", "white"));

            }else if(config_info.contents === 'number-clock') {
                //待完成
            }

        }else if (config_info.graph_type === 'table') {
            //表格
            $_block.find('.block_content').append('<div id="table_' + index + '_' + i +'"></div>');

            $('#table_' + index + '_' + i).append(block_info.html_contents);// 待完成

        }
    }else {

    }
}

//添加编辑块
function addBlockButton(gridster) {
    //str 为添加 button
    var str =
        '<button ' +
                'class="ui icon basic massive button " ' +
                'id="addblock_button" ' +
                'style="box-shadow:0px 0px;border:3px dashed darkgray;width: 140px;height: 140px">' +
            '<i class="add icon" style="font-size:400%;padding-top: 10px;padding-right:30px "></i>' +
        '</button>';

    var $_add_widget = gridster.add_widget('<li id="add_widget">', 2, 2);

    $_add_widget.append(str);

    //console.log('addblock_button',$('#addblock_button'));

    //添加块
    $('#addblock_button').on("click",function(){
        $('#point_modal').modal('setting', 'closable', false).modal('show');

    });
}

/**
 * 给所有 block 添加 编辑按钮
 * $_blocks 传入所有 li jquery 对象
 */
function addBlockiCons($_blocks) {

    $_blocks.each(function(i,value){

        addBlockiCon($(this),i);

    });
}

// 给指定一个 block 添加 编辑按钮  i 表示第几个 block
function addBlockiCon($_block,i) {

    $_block.find('.block_edit').append(
        '<i style="float:right" class="edit icon"></i>' +
        '<i style="float:right" class="delete icon"></i>' +
        '<i style="float:right" class="refresh icon"></i>'
    );

    console.log('$_block',$_block);

    //添加点击事件
    //编辑
    $_block.find('.block_edit i.edit.icon').on('click',function(){

        alert('我是block' + i +'的编辑 按钮');

    });

    //刷新
    $_block.find('.block_edit i.refresh.icon').on('click',function(){

        alert('我是block' + i +'的刷新 按钮');

        var refresh_str =
            '<div id="block-dimmer'+ i +'" class="ui inverted dimmer">'+
                '<div class="ui text loader" style="text-align:center">正在刷新</div>'+
            '</div>';
        $_block.find('#block_temp').append(refresh_str);
        console.log('block_temp',$_block.find('#block_temp'));
        $('#block-dimmer'+i).addClass('active');
        console.log('i and page_vo', i+ ' ' + page_vo);

        var config_info = page_vo.config_data[i];
        $.ajax({
            type: 'get',
            url: '/carousel/get_block',
            data:config_info,
            dataType: "json",
            contentType:'application/json;charset=UTF-8',
            success: function (result) {
                if(result.success) {

                    var block_info = result.data;


                    console.log('$_block and i',$_block + '  ' + i);

                    // 画 项 block  0 表示第 0 页，这里不关心第几页，所以可随意取值
                    drawBlock($_block,config_info,block_info,0,i);
                    $_block.find('.block_edit').append(
                        '<i style="float:right" class="edit icon"></i>' +
                        '<i style="float:right" class="delete icon"></i>' +
                        '<i style="float:right" class="refresh icon"></i>'
                    );

                    $('#block-dimmer'+i).removeClass('active');

                } else {
                    errorMsg_no_connect("轮播类型 Dropdown");
                }
            },
            error: function () {
                errorMsg_no_connect("轮播类型 Dropdown");
            }
        })
    });

    //删除
    $_block.find('.block_edit i.delete.icon').on('click',function(){

        alert('我是block' + i +'的删除 按钮');

    });

}

/**
 * 废弃的方法
 * @param $_block 块对象
 * @param config_info
 * @param block_info
 * @param index  表示那一页
 * @param i  表示那一块
 */
function drawDataBlock($_block,config_info,block_info,index,i) {

    var temp_str = $('#block_temp').html();

    $_block.html(temp_str);

    // block 标题
    $_block.find('.block_title').text(config_info.block_name);


    //根据配置画图
    if(config_info.block_type === 'view') {
        //视图
        if(config_info.graph_type === 'statepie') {
            //状态视图
            $_block.append(
                '<div style="width: 430px;height: 150px;" >' +
                '<div style="width: 100%;height: 10%;"></div>' +
                '<div style="float:left;width: 45%;height: 80%;" id="host_pie' + index + '_' + i +'"></div >' +
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
                    },
                tooltip : {
                    formatter: '{a} <br/>{b} : {c}%'
                },
                series: [
                    {
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


