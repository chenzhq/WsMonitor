/**
 * Created by yanms on 2017/8/15.
 */
$(function(){
    //modal点击监控点按钮 进入监控点详情
    $('#pointButton').on('click', '.button',function() {
        var point_id = this.id.substring(1);
        getItemsDetail(point_id);
    });
})

function getItemsDetail(point_id) {

    $('.tab_points.menu .item').tab('change tab', 'Summary');
    $("#rowData").empty();
    $("#rowData").append("<table id='tableData' class='ui compact celled table center aligned'> </table>");
    $("#pointDetailArea").text(point_id);
    tabSummary(point_id);
    $('#pointDetailModal').modal('show');
    $('.tab_points.menu .item')
        .tab({
            'onFirstLoad': function (path) {
                if (path === 'Report') {

                    tabReport( $('.top.attached.label').attr("data-id") ,40);
                }
                if (path === 'Data') {
                    var point_id = $("#pointDetailArea").text();
                    var item_id = $("#pointDetailFirstItem").text() ;
                    var tableData = $("#tableData").DataTable({
                        "orderFixed": [ 0, 'desc' ],
                        "paging": false,
                        "columnDefs": [ {
                            "targets": [ 3 ],
                            "visible": false } ] ,
                        columns: [
                            //item_id
                            {
                                "data": "last_time",
                                "width": "30%",
                                "title": "最新时间",
                            },
                            {
                                "data": null,
                                "width": "10%",
                                "title": "状态",
                                "orderable": false,
                                render: function (data, type, row, meta) {
                                    var Color = ['green', 'red', 'yellow', '#d2d2d2', '#2F4056'];
                                    if (row.state == "正常") {
                                        var myColor = Color[0]
                                    }
                                    if (row.state == "严重") {
                                        var myColor = Color[1]
                                    }
                                    if (row.state == "警告") {
                                        var myColor = Color[2]
                                    }
                                    return '<i class="'+ myColor + ' square icon"></i>';
                                }
                            },
                            {
                                "data": "value",
                                "width": "30%",
                                "title": "值",
                                "orderable":false,
                            },
                            {
                                "data": "state",
                                "width": "30%",
                                "title": "状态",
                                "orderable":false,
                            },
                        ],
                    });
                    //加载数据
                    if('' !== item_id) {
                        $('#data-dimmer').addClass('active');
                        $.ajax({
                            type: 'get',
                            url: '/pointdetail/get_datas?item_id='+item_id+'&time=40',
                            dataType: 'json',
                            success: function (result) {
                                if(result.success) {
                                    var table_data = result.data;
                                    tableData.clear().rows.add(table_data).draw();
                                    $('#data-dimmer').removeClass('active');

                                }
                                else {
                                    errorMsg_no_data(result.message);
                                }
                            },
                            error: function () {
                                errorMsg_no_connect('');
                            }
                        })
                    }else {
                        tableData.clear();
                    }

                    $('#itemdata_select').dropdown({

                    });

                    dropdownitemsTab(point_id,tableData);


                    $('.dropdown.stateTab').dropdown({
                        useLabels: false,
                        onChange: function(value, text, $selectedItem) {
                            if (text == '全部')
                            {
                                tableData.search('').draw();
                            }else {
                                tableData.search(text).draw();
                            }
                        }
                    });

                    $('.ui.buttons.Time.Data .button').on('click', function() {
                        $('.buttons.Time.Data .button').removeClass('active');
                        $(this).addClass('active');
                        var item_id = $('#itemdata_select').dropdown('get value');
                        if(item_id !== '') {
                            var url=  "/pointdetail/get_datas?item_id="+item_id+"&time="+this.value;
                            $('#data-dimmer').addClass('active');
                            $.ajax({
                                type: "get",
                                url: url,
                                dataType: 'json',
                                success: function (result) {
                                    if (result.success) {
                                        tableData.clear().rows.add(result.data).draw()
                                        $('#data-dimmer').removeClass('active');
                                    } else {

                                    }
                                },
                                error: function (e) {

                                }
                            })
                        }else {
                            $('#data-dimmer').removeClass('active');
                        }
                    })


                }
            }
        });
}

//获取监控点详情 概述 标签页数据
function tabSummary(point_id) {
    $.ajax({
        type: "get",
        url: "/pointdetail?point_id="+point_id,
        data: {},
        dataType: "json",
        success: function (result) {
            if(result.success) {
                var data=result.data;
                //将第一个item赋值给弹出框
                if(data.item_datas.length != 0) {
                    $("#pointDetailFirstItem").text(data.item_datas[0].item_id);
                }else {
                    $("#pointDetailFirstItem").text('');
                }
                var Color = ['green', 'red', 'yellow', '#d2d2d2', '#2F4056'];
                if (data.state == "正常"){ var myColor = Color[0] }
                if (data.state == "严重"){ var myColor = Color[1] }
                if (data.state == "警告"){ var myColor = Color[2] }
                $('#labelPoints').html("<div class='ui "+myColor+" massive top right attached ribbon label' data-id='"+data.point_id+"'>"+data.point_name+"</div>")
                var str = "";
                for(var i=0;i<data.item_datas.length;i++)
                {
                    var mytrColor = '';
                    if (data.item_datas[i].with_triggers == true)
                    {
                        if (data.item_datas[i].state == "正常"){ mytrColor = Color[0] }
                        if (data.item_datas[i].state == "严重"){ mytrColor = Color[1] }
                        if (data.item_datas[i].state == "警告"){ mytrColor = Color[2] }
                    }
                    else {
                    }
                    str +=" <tr id='"+data.item_datas[i].item_id+"' class='"+mytrColor+" tr' ><td class='left aligned'>"
                        + data.item_datas[i].item_name +"</td><td class='left aligned' title='"+data.item_datas[i].value+"'>"
                        + data.item_datas[i].value +"</td><td>"
                        + data.item_datas[i].warning_point +"</td><td>"
                        + data.item_datas[i].high_point +"</td><td>"
                        + data.item_datas[i].last_time +"</td></tr> ";
                }
                $("#tableSummary").html(str);
            }
            else {
                errorMsg_no_data("监控点 Tab");
            }
        },
        error: function () {
            errorMsg_no_connect("监控点 Tab");
        }
    });
}

//获取监控点详情 图形报告 标签页数据
function tabReport(point_id,days) {
    $('#chart-dimmer').addClass('active');
    var option = {
        title: {
            text: ""
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
            left: '3%',
            right: '3%',
            bottom: '0%',
            top:'5%',
            containLabel: true
        },
        xAxis: {
            data: {}
        },
        yAxis: {
            max:''
        },
        series: [{
            name: '',
            type: '',
            data: {},
            color: [],
            areaStyle:{}
        }]
    };
    $.ajax({
        type: "get",
        url: "/pointdetail/get_graphs?point_id=" + point_id + "&time="+days,
        dataType: "json",
        success: function (result) {
            if(result.success) {
                var data = result.data;
                var str = '';
                $("#chartReport").empty();
                for (var i=0; i<data.length; i++){
                    var id="pi"+data[i].item_id;
                    var Color = ['#5FB878', '#DB2828', '#F7B824', '#d2d2d2', '#2F4056'];
                    if (data[i].state == "正常"){ var myColor = Color[0] }
                    if (data[i].state == "严重"){ var myColor = Color[1] }
                    if (data[i].state == "警告"){ var myColor = Color[2] }
                    if(data[i].units == "%"){
                        option.yAxis.max = 100;
                    }else {
                        option.yAxis.max = null;
                    }
                    var title = "";
                    var name = "";
                    if(data[i].units == "") {
                        title = data[i].item_name;
                    }else {
                        title = data[i].item_name + "(" + data[i].units +")";
                    }
                    if(strlen(title) > 30)
                    {
                        name = "<div class='fourteen wide column no-padding' data-tooltip='"+title+"'>"+
                            "<h3 class='text-hidden'>&nbsp&nbsp&nbsp"+title+"</h3></div>"+
                            "<div class='two wide column no-padding right aligned'>"+
                            "<i class='zoom icon' id='z" + data[i].item_id + "p" + point_id + "' onclick='zoom_graph(this.id)'></i></div>";
                    }else {
                        name = "<div class='fourteen wide column no-padding'>"+
                            "<h3 class='text-hidden'>&nbsp&nbsp&nbsp"+title+"</h3></div>"+
                            "<div class='two wide column no-padding right aligned'>"+
                            "<i class='zoom icon' id='z" + data[i].item_id + "p" + point_id + "' onclick='zoom_graph(this.id)'></i></div>";
                    }
                    str ="<div class='column'><div class='ui grid'>"+ name+
                    "<div class='sixteen wide column no-padding'>"+
                    "<div id='"+id+"' class='chart-style'>"+id+"</div></div></div></div>";
                    $("#chartReport").append(str);
                    option.xAxis.data = data[i].data_time;
                    option.series[0].name = data[i].graph_name;
                    option.series[0].type = data[i].graph_type;
                    option.series[0].data = data[i].data;
                    option.series[0].color[0] = myColor;
                    echarts.init(document.getElementById(id)).setOption(option);
                }

                $('#typeReport').find('.button').on('click', function() {

                    var type = this.value;

                    $('#typeReport').find('.button').removeClass('active');
                    $(this).addClass('active');

                    if(type === 'area') {
                        $('#chartReport').find('.chart-style').each(function(i,value){

                            var graph_chart = echarts.init(document.getElementById(this.id));
                            var _option = graph_chart.getOption();

                            _option.series[0].areaStyle = {normal: {}};
                            _option.series[0].type = 'line';
                            graph_chart.setOption(_option);


                        })
                    }else {
                        $('#chartReport').find('.chart-style').each(function(i,value){

                            var graph_chart = echarts.init(document.getElementById(this.id));
                            var _option = graph_chart.getOption();
                            _option.series[0].areaStyle = '';
                            _option.series[0].type = type;
                            graph_chart.setOption(_option);
                        })
                    }
                })

                $('#chart-dimmer').removeClass('active');
            }
            else {
                errorMsg_no_data("监控点 Tab");
            }
        },
        error: function () {
            errorMsg_no_connect("监控点 Tab");
        }
    });
}

$('.ui.buttons.Time.Report .button').on('click', function() {
    $('.buttons.Time.Report .button').removeClass('active');
    $(this).addClass('active');
    tabReport($('.top.attached.label').attr("data-id") ,this.value);
})


//时序数据中 加载监控项下拉框数据
function dropdownitemsTab(point_id,tableData){
    $.ajax({
        type: "get",
        url: "/hostgraphs/get_items?point_id=" + point_id,
        dataType: "json",
        success: function (result) {
            if(result.success) {
                var data=result.data;
                var str = '';
                if(data.length !== 0) {

                    for(var i=0;i<data.length;i++) {
                        str +="<div class='item' data-value='"+data[i].item_id+"'>"+data[i].item_name+"</div> ";
                    }
                    $("#itemdata_menu").html(str);
                    $("#itemdata_select").dropdown('set value',data[0].item_id);
                    $("#itemdata_select").dropdown('set text',data[0].item_name.substr(0,25));
                }else {
                    $("#itemdata_menu").html(str);
                    $("#itemdata_select").dropdown('clear');

                }
                //绑定item下拉框 onchange 事件
                $('#itemdata_select').dropdown({

                    onChange: function(value, text, $selectedItem) {
                        var time_value = $('.Time.Data').find('.active').val();
                        $('#data-dimmer').addClass('active');
                        var url=  "/pointdetail/get_datas?item_id=" + value + "&time=" + time_value;
                        //tableData.ajax.url(url).load();
                        if(value !== '') {
                            $.ajax({
                                type: "get",
                                url: url,
                                dataType: 'json',
                                success: function (result) {
                                    if (result.success) {
                                        tableData.clear().rows.add(result.data).draw()
                                        $('#data-dimmer').removeClass('active');
                                        $("#itemdata_select").dropdown('set text',text.substr(0,25));

                                    } else {

                                    }
                                },
                                error: function (e) {
                                    errorMsg_no_connect("监控项 Dropdown");
                                }
                            })
                        }else {
                            tableData.clear();
                            $('#data-dimmer').removeClass('active');
                        }

                        $('.dropdown.stateTab').prop('selectedIndex', 0);
                    }
                });

            }
            else {
                errorMsg_no_data("监控项 Dropdown");
            }
        },
        error: function () {
            errorMsg_no_connect("监控项 Dropdown");
        }
    });
};

//设备详情中 添加图形功能 加载 监控点 下拉框数据
function dropdownPoints(host_id){
    $.ajax({
        type: "get",
        url: "/hostgraphs/get_points",
        data: {
            host_id: host_id
        },
        dataType: "json",
        success: function (result) {
            if(result.success) {
                var data=result.data;
                var str = "";
                for(var i=0;i<data.length;i++)
                {
                    str +=" <option value='"+data[i].point_id+"'  >"+data[i].point_name+"</option> ";
                }
                $(".dropdown.points option").html(str);
            }
            else {
                errorMsg_no_data("监控点 Dropdown");
            }
        },
        error: function () {
            errorMsg_no_connect("监控点 Dropdown");
        }
    });
};

//设备详情中 添加图形功能 加载 监控项 下拉框数据
function dropdownItems(value){
    $.ajax({
        type: "get",
        url: "/hostgraphs/get_items",
        data: {
            point_id: value
        },
        dataType: "json",
        success: function (result) {
            if(result.success) {
                var data=result.data;
                var str = "";
                for(var i=0;i<data.length;i++)
                {
                    if(data[i].value_type == "0" || data[i].value_type == "3" || data[i].value_type == "%") {
                        str +=" <option value='"+data[i].value_type+"_"+data[i].item_id+"'>"+data[i].item_name+"</option> ";
                    }else {
                        str +=" <option disabled='disabled' value='"+data[i].value_type+"_"+data[i].item_id+"'>"+data[i].item_name+"</option> ";
                    }

                }
                $(".dropdown.items option").html(str);
            }
            else {
                errorMsg_no_data("监控项 Dropdown");
            }
        },
        error: function () {
            errorMsg_no_connect("监控项 Dropdown");
        }
    });
};

//设备详情中 添加图形功能 加载 图形类型 下拉框数据
function dropdownType(value){
    $.ajax({
        type: "get",
        url: "/hostgraphs/get_graphs",
        data: {
            value_type: value
        },
        dataType: "json",
        success: function (result) {
            if(result.success) {
                var data=result.data;
                var str = "";
                for(var i=0;i<data.length;i++)
                {
                    str +=" <option value='"+data[i].graph_type+"' >"+data[i].graph_name+"</option> ";
                }
                $(".dropdown.type option").html(str);
            }
            else {
                errorMsg_no_data("图表类型 Dropdown");
            }
        },
        error: function () {
            errorMsg_no_connect("图表类型 Dropdown");
        }
    });
};