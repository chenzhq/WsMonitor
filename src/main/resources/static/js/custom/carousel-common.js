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
    // legend: {
    //     orient: 'vertical',
    //     x: 'right',
    //     y: 'center',
    //     data:['正常','警告','严重'],
    // },
    grid: {
        left:'5%',
        right:'5%',
    },
    series: [
        {
            name: '',
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
            data:  [],
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
    // legend: {
    //     orient: 'vertical',
    //     x: 'right',
    //     y: 'center',
    //     data:['正常','警告','严重'],
    // },
    grid: {
        left:'15%',
        right:'15%',
    },
    series: [
        {
            name: '',
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
            data:  [ ],
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
//用于设置自适应的展示项
var graph_resize = [];

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
            min_size: [1, 1],
            start: function(e, ui, $widget) {

            },
            resize: function(e, ui, $widget) {

            },
            stop: function(e, ui, $widget) {

                // 自适应
                var chart_id = $widget.find('.block_content').children().eq(0).attr('id');
                if('graph' === chart_id.split('_')[0]) {

                    $(window).trigger("resize");

                }else if('problems' === chart_id.split('_')[0]) {


                }

            }
        },

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

        $_block.append($('#block_temp').html());

        // 画 项 block  0表示第 0 页，这里不关心第几页，所以可随意取值
        drawBlock($_block,config_arr[i],block_arr[i],0,i);
    });

    window.onresize = function(){
        $.each(graph_resize,function( index,value){
            value.resize();
        });
    }

}

function addBlockWidget(size_x,size_y) {

    //添加config_info 保存至 page_vo.config_data

    var last_block = page_vo.config_data.length;
    var config_info = page_vo.config_data[page_vo.config_data.length-1];
    var block_info = page_vo.block_data[page_vo.block_data.length-1];


    var $_block = gridster.add_widget('<li >', size_x, size_y);

    $_block.append($('#block_temp').html());


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

    // block 标题
    $_block.find('.block_title').text(config_info.block_name);

    //根据配置画图
    $_block.find('.block_content').html('');
    if(config_info.block_type === 'view') {
        //视图
        if(config_info.graph_type === 'statepie') {
            //状态视图

            //block 内容
            $_block.find('.block_content').append(
                    '<div style="float:left;width: 240px;height: 200px;" id="host_pie' + index + '_' + i +'"></div >' +
                    '<div style="float:left;width: 240px;height: 200px;" id="point_pie' + index + '_' + i +'"></div>');

            var host_chart = echarts.init(document.getElementById('host_pie' + index + '_' + i));

            var point_chart = echarts.init(document.getElementById('point_pie' + index + '_' + i));

            getViewStatepie(host_chart,point_chart,block_info);

        }else if(config_info.graph_type === 'problems') {
            //问题视图

            // block 内容
            $_block.find('.block_content').append('<table width="100%" id="problems_' + index + '_' + i +'"></table>');

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
            '<div style="height: 100%;width: 100%;margin-top: 20px" id="graph_' + index + '_'+ i +'">图表数据</div>');

        var graph_chart = echarts.init(document.getElementById('graph_' + index + '_' + i));//待补充

        var option = getGraphOption(config_info.graph_type,graph_vo);

        graph_chart.setOption(option);

        graph_resize.push(graph_chart);
    }else if(config_info.block_type === 'chart') {
        //控件
        if(config_info.graph_type === 'clock') {
            //钟表
            if(config_info.contents === 'clock-clock') {

                $_block.find('.block_content').append('<div style = "margin: 20px 20px" id="clock_' + index + '_' + i +'"></div>');
                //时钟显示
                AnalogClock('clock_' + index + '_' + i, new AnalogClockOption(200, "black", "white"));

            }else if(config_info.contents === 'number-clock') {
                $_block.find('.block_content').append(
                    ' <div style = "margin-top: 20px" class="clock-num">'+
                        '<div class="display">'+
                            '<div class="date"></div>'+
                            '<div class="digits"></div>'+
                        '</div>'+
                    '</div>');
                showNumClock($_block);
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

    //添加块
    $('#addblock_button').on("dblclick",function(){
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

    //添加点击事件
    //编辑
    $_block.find('.block_edit i.edit.icon').on('click',function(){

        //alert('我是block' + i +'的编辑 按钮');
        var type = $_block.find('.block_content').children().eq(0).attr('id').split('_')[0];

        var old_config_info = page_vo.config_data[i];

        if('host' === type || 'problems' === type || 'appletree' === type) {
            //视图类型

            $('#point_view_modal').modal({
                onShow:function(){
                    $.ajax({
                        type: "get",
                        async:false,
                        url: "/viewtype/get_list",
                        dataType: "json",
                        success: function (result) {
                            if(result.success) {
                                var data=result.data;
                                getSelectByObject($('#viewtype_menu'),$('#viewtype_select'),data);
                            }
                            else {
                                errorMsg_no_data(result.message);
                            }
                        },
                        error: function () {
                            errorMsg_no_connect("视图类型下拉框 Dropdown");
                        }
                    })
                },
                onApprove : function() {
                    var block_name  = $('#view_name').val();
                    if('' === block_name) {
                        alert('展示项名称不能为空');
                        return false;
                    }
                    var config_info = {
                        block_name: block_name,
                        block_type: 'view',
                        graph_type: $('#viewtype_select').dropdown('get value'),
                        contents: $('#viewname_select').dropdown('get text')
                    }
                    $.ajax({
                        type: "get",
                        url: "/carousel/get_block",
                        data:config_info,
                        dataType: "json",
                        contentType:'application/json;charset=UTF-8',
                        success: function (result) {
                            if (result.success) {
                                //清空表单
                                $('#view_name').val('');
                                $('#viewtype_select').dropdown('clear');
                                $('#viewname_select').dropdown('clear');
                                var block_info = result.data;
                                //更新 config_info 到page_vo 对象中
                                page_vo.config_data[i] = config_info;

                                $_block.children().first().nextAll().remove();

                                $_block.append($('#block_temp').html());

                                // 画 项 block  0 表示第 0 页，这里不关心第几页，所以可随意取值
                                drawBlock($_block,config_info,block_info,0,i);

                                //添加编辑按钮
                                addBlockiCon($_block,i);

                            } else {
                                errorMsg_no_connect("获取展示项数据失败");
                            }
                        },
                        error: function () {
                            errorMsg_no_connect("获取展示项数据失败");
                        }
                    });
                }
            }).modal('show')

            //赋值
            $('#view_name').val(old_config_info.block_name);
            if('statepie' === old_config_info.graph_type ) {

                $('#viewtype_select').dropdown('set text','状态统计');
                $('#viewtype_select').dropdown('set value','statepie');

            }else if('problems' === old_config_info.graph_type) {

                $('#viewtype_select').dropdown('set text','问题视图');
                $('#viewtype_select').dropdown('set value','problems');

            }else if('appletree' === old_config_info.graph_type) {

                $('#viewtype_select').dropdown('set text','苹果树');
                $('#viewtype_select').dropdown('set value','appletree');

            }else {

            }

            $('#viewname_select').dropdown('set text',old_config_info.contents);

        }else if('graph' === type) {

            $('#point_graph_modal').modal({
                onShow:function(){
                    //获取设备选择树
                    var host_id ;
                    var $host_tree = $('#host_tree');

                    $host_tree.genHostTree({
                        multi: false,
                        onChange:function(e,data){

                            host_id = data.selected[0];
                            pointsMenu(host_id);
                        }
                    })
                },
                onApprove : function() {
                    var block_name  = $('#graph_name').val();
                    if('' === block_name) {
                        alert('展示项名称不能为空');
                        return false;
                    }
                    var config_info = {
                        block_name: block_name,
                        block_type: 'graph',
                        graph_type: $('#typeMenu .active').attr('data-type'),
                        contents: $('#itemsMenu .active').attr('id')
                    }
                    $.ajax({
                        type: "get",
                        url: "/carousel/get_block",
                        data:config_info,
                        dataType: "json",
                        contentType:'application/json;charset=UTF-8',
                        success: function (result) {
                            if (result.success) {
                                //清空表单
                                $('#graph_name').val('');
                                //清空选择树 待完成
                                $('#pointsMenu').html('');
                                $('#itemsMenu').html('');
                                $('#typeMenu').html('');

                                var block_info = result.data;
                                //更新 config_info 到page_vo 对象中
                                page_vo.config_data[i] = config_info;

                                $_block.children().first().nextAll().remove();

                                $_block.append($('#block_temp').html());

                                // 画 项 block  0 表示第 0 页，这里不关心第几页，所以可随意取值
                                drawBlock($_block,config_info,block_info,0,i);

                                //添加编辑按钮
                                addBlockiCon($_block,i);

                            } else {
                                errorMsg_no_connect("获取展示项数据失败");
                            }
                        },
                        error: function () {
                            errorMsg_no_connect("获取展示项数据失败");
                        }
                    });
                }
            }).modal('show')
            //赋值
            $('#graph_name').val(old_config_info.block_name);
            //设备选择树赋值 待完成

        }else if('clock' === type || 'table' === type ) {

            $('#point_chart_modal').modal({
                onShow:function(){
                    $.ajax({
                        type: "get",
                        async:false,
                        url: "/charttype/get_list",
                        dataType: "json",
                        success: function (result) {
                            if(result.success) {
                                var data=result.data;
                                getSelectByObject($('#charttype_menu'),$('#charttype_select'),data);
                            }
                            else {
                                errorMsg_no_data(result.message);
                            }
                        },
                        error: function () {
                            errorMsg_no_connect("chart类型下拉框 Dropdown");
                        }
                    })
                },
                onApprove : function() {

                    var block_name  = $('#chart_name').val();
                    if('' === block_name) {
                        alert('展示项名称不能为空');
                        return false;
                    }
                    if($('#charttype_select').dropdown('get value')=='table')
                    {

                        $('#table_thead_modal').modal({
                            onShow: function () {
                                var reg = /^[1-9]\d*$/;
                                if(!(reg.test($('#row_table').val()) && reg.test($('#column_table').val())) ) {
                                    alert('行列数必须为正整数！');
                                }
                                var row = $('#row_table').val();
                                var columnd = $('#column_table').val();
                                var str = '';
                                for(var i=0;i<columnd;i++) {
                                    var j=i+1;
                                    str += "<div class='ui label no-padding'> " +
                                        "<div class='detail'>第"+j+"列</div> " +
                                        "<input type='text' class='th_name' style='width: 180px'></div> " +
                                        "<div class='ui label no-padding'> " +
                                        "<input type='text' value ='180' class='th_width' style='width: 80px;'>"+
                                        "<div class='detail'>PX</div></div>";
                                }
                                $('#table_thead_content').html(str);
                            }
                        }).modal('show');

                        $('#table_thead_modal .button').on('click',function(){
                            $('#table_tbody_modal').modal({
                                onShow: function () {
                                    var str = 0;
                                    var name = [];
                                    var width = [];
                                    var fields = [];//thead详情
                                    var clientsdata=[];//tbody详情
                                    var row = $('#row_table').val();
                                    var columnd = $('#column_table').val();
                                    $("#table_thead_content input.th_name").each(function () {
                                        name.push($(this).val());
                                    })
                                    $("#table_thead_content input.th_width").each(function () {
                                        var reg = /^[1-9]\d*$/;
                                        var v=$(this).val();
                                        if(!reg.test(v)){
                                            str += 180;
                                            width.push(180);
                                        }
                                        else {
                                            str += parseInt(v);
                                            width.push(parseInt(v));
                                        }
                                    })
                                    for(var i=0;i<columnd;i++)
                                    {
                                        fields.push({
                                            name: name[i],
                                            type: 'text',
                                            width: width[i],
                                        });
                                    }
                                    fields.push({
                                        type: 'control'
                                    });
                                    custom_edittable(fields,str);
                                    for(var j=0;j<row;j++)
                                    {
                                        var jsonStr='{';
                                        for(var i=0;i<columnd;i++) {
                                            jsonStr+='"'+name[i]+'":"'+""+'",';
                                        }
                                        jsonStr+='},'
                                        clientsdata.push(jsonStr);
                                    }
                                    //定义edittable功能和数据
                                    var db = {

                                        loadData: function(filter) {
                                        },

                                        insertItem: function(insertingClient) {
                                            this.clients.push(insertingClient);
                                        },

                                        updateItem: function(updatingClient) { },

                                        deleteItem: function(deletingClient) {
                                            var clientIndex = $.inArray(deletingClient, this.clients);
                                            this.clients.splice(clientIndex, 1);
                                        }

                                    };
                                    window.db = db;
                                    db.clients =clientsdata;
                                },
                                onApprove : function() {
                                    //ajax，存储表格数据，并在页面展示只读表格
                                    var config_info = {
                                        block_name: $('#chart_name').val(),
                                        block_type: 'chart',
                                        graph_type: $('#charttype_select').dropdown('get value'),
                                        contents: $('#jsGrid').prop("outerHTML")
                                    }
                                    $.ajax({
                                        type: "get",
                                        url: "/carousel/get_block",
                                        data: config_info,
                                        dataType: "json",
                                        contentType: 'application/json;charset=UTF-8',
                                        success: function (result) {
                                            if (result.success) {

                                                //清空表单
                                                $('#chart_name').val('');
                                                $('#charttype_select').dropdown('clear');
                                                $('#chartname_select').dropdown('clear');

                                                //$('#table_thead_content').html('');
                                                //$('#jsGrid').html('');

                                                var block_info = result.data;
                                                //更新 config_info 到page_vo 对象中
                                                page_vo.config_data[i] = config_info;

                                                $_block.children().first().nextAll().remove();

                                                $_block.append($('#block_temp').html());

                                                // 画 项 block  0 表示第 0 页，这里不关心第几页，所以可随意取值
                                                drawBlock($_block,config_info,block_info,0,i);

                                                //添加编辑按钮
                                                addBlockiCon($_block,i);
                                                return true;
                                            } else {
                                                errorMsg_no_connect("获取展示项数据失败");
                                            }
                                        },
                                        error: function () {
                                            errorMsg_no_connect("获取展示项数据失败");
                                        }
                                    });
                                }
                            }).modal('show')
                        })
                        return false;

                    }else if($('#charttype_select').dropdown('get value')=='clock') {
                        var config_info = {
                            block_name: block_name,
                            block_type: 'chart',
                            graph_type: $('#charttype_select').dropdown('get value'),
                            contents: $('#chartname_select').dropdown('get value')
                        }
                        $.ajax({
                            type: "get",
                            url: "/carousel/get_block",
                            data: config_info,
                            dataType: "json",
                            contentType: 'application/json;charset=UTF-8',
                            success: function (result) {
                                if (result.success) {

                                    //清空表单
                                    $('#chart_name').val('');
                                    $('#charttype_select').dropdown('clear');
                                    $('#chartname_select').dropdown('clear');

                                    var block_info = result.data;
                                    //更新 config_info 到page_vo 对象中
                                    page_vo.config_data[i] = config_info;

                                    $_block.children().first().nextAll().remove();

                                    $_block.append($('#block_temp').html());

                                    // 画 项 block  0 表示第 0 页，这里不关心第几页，所以可随意取值
                                    drawBlock($_block,config_info,block_info,0,i);
                                    //添加编辑按钮
                                    addBlockiCon($_block,i);
                                    return true;
                                } else {
                                    errorMsg_no_connect("获取展示项数据失败");
                                }
                            },
                            error: function () {
                                errorMsg_no_connect("获取展示项数据失败");
                            }
                        });
                    }
                }
            }).modal('show')
            //赋值
            $('#chart_name').val(old_config_info.block_name);

            if('clock' === old_config_info.graph_type) {

                $('#charttype_select').dropdown('set text','钟表');
                $('#charttype_select').dropdown('set value','clock');

                if('clock-clock' === old_config_info.contents) {

                    $('#chartname_select').dropdown('set text','时钟样式');
                    $('#chartname_select').dropdown('set value','clock-clock');

                }else if('number-clock' === old_config_info.contents) {

                    $('#chartname_select').dropdown('set text','数字样式');
                    $('#chartname_select').dropdown('set value','number-clock');

                }else {

                }


            }else if('table' === old_config_info.graph_type) {

                $('#charttype_select').dropdown('set text','表格');
                $('#charttype_select').dropdown('set value','table');

            }else {

            }

        }else if('table' === type) {



        }

    });

    //刷新
    $_block.find('.block_edit i.refresh.icon').on('click',function(){

        var dimmer = $_block.find('.dimmer').html();

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

                    // 画 项 block  0 表示第 0 页，这里不关心第几页，所以可随意取值
                    drawBlock($_block,config_info,block_info,0,i);

                    $_block.find('.dimmer').removeClass('active');

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

        if(confirm("确定删除该展示项")){
            //点击确定后操作
            //alert('我是block' + i +'的删除 按钮');

            gridster.remove_widget($_block);

            return true;
        }else {
            return false;
        }


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

    var option ;
    var ymax ;
    if(graph_vo.units === '%'){
        ymax = 100;
    }else {
        ymax = null;
    }
    //图形值
    switch (graph_type) {
        case 'gauge':
            option = {
                title: {
                    },
                tooltip : {
                    formatter: '{a} <br/>{b} : {c}%'
                },
                grid: {
                    left: '5%',
                    right: '5%',
                    bottom: '1%',
                    height: '90%',
                    width: '90%',
                    containLabel: true
                },
                series: [
                    {
                        type: 'gauge',
                        min:0,
                        max:100,
                        splitNumber: 5,
                        axisLine: {            // 坐标轴线
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: [[0.6, '#5FB878'],[0.8, '#F7B824'],[1, '#FF5722']],
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
                        data: [{value: graph_vo.data[graph_vo.data.length-1].toFixed(2), name: ''}]
                    }
                ]
            };
            break;
        case 'area':
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
                    left: '5%',
                    right: '5%',
                    bottom: '1%',
                    height: '90%',
                    width: '90%',
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
                    left: '5%',
                    right: '5%',
                    bottom: '1%',
                    height: '90%',
                    width: '90%',
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

function showNumClock($_block) {
    var clock = $_block.find('.clock-num');
    //定义数字数组0-9
    var digit_to_name = ['zero','one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine'];
    //定义星期
    var weekday = ['周日','周一','周二','周三','周四','周五','周六'];

    var digits = {};

    //定义时分秒位置
    var positions = [
        'h1', 'h2', ':', 'm1', 'm2', ':', 's1', 's2'
    ];

    //构建数字时钟的时分秒

    var digit_holder = clock.find('.digits');
    console.log('digit_holder',clock);

    $.each(positions, function(){

        if(this == ':'){
            digit_holder.append('<div class="dots">');
        }
        else{

            var pos = $('<div>');

            for(var i=1; i<8; i++){
                pos.append('<span class="d' + i + '">');
            }

            digits[this] = pos;

            digit_holder.append(pos);
        }

    });


    // 让时钟跑起来
    (function update_time(){

        //调用moment.js来格式化时间
        var now = moment().format("HHmmss");

        digits.h1.attr('class', digit_to_name[now[0]]);
        digits.h2.attr('class', digit_to_name[now[1]]);
        digits.m1.attr('class', digit_to_name[now[2]]);
        digits.m2.attr('class', digit_to_name[now[3]]);
        digits.s1.attr('class', digit_to_name[now[4]]);
        digits.s2.attr('class', digit_to_name[now[5]]);

        var date = moment().format("YYYY年MM月DD日");
        var week = weekday[moment().format('d')];
        $(".date").html(date + ' ' + week);


        // 每秒钟运行一次
        setTimeout(update_time, 1000);

    })();
}

