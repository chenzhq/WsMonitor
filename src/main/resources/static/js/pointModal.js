/**
 * Created by yanms on 2017/8/15.
 */
$(function(){
    //modal点击监控点按钮 进入监控点详情
    $('#pointButton').on('click', '.button',function() {
        var point_id = this.id.substring(1);
        //console.log("进入了点击监控点按钮事件：point_id："+this.id);
        $('.tab_points.menu .item').tab('change tab', 'Summary');
        $("#rowData").empty();
        $("#rowData").append("<table id='tableData' class='ui compact celled table center aligned'> </table>");
        $("#pointDetailArea").text(point_id);
        tabSummary(point_id);
        $('.large.modal').modal('show');
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
                            ajax: {
                                url: '/pointdetail/get_datas?item_id='+item_id+'&time=40',
                                dataSrc: 'data'
                            },
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
                        dropdownitemsTab(point_id,item_id);
                        $('.dropdown.stateTab')
                            .dropdown({
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
                        $('.dropdown.itemsTab')
                            .dropdown({
                                /*useLabels: false,*/
                                onChange: function(value, text, $selectedItem) {
                                    $('#data-dimmer').addClass('active');
                                    var url=  "/pointdetail/get_datas?item_id="+value+"&time=1";
                                    //tableData.ajax.url(url).load();
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
                                    $('.dropdown.stateTab').prop('selectedIndex', 0);
                                }
                            });
                        $('.ui.buttons.Time.Data .button').on('click', function() {
                            $('#data-dimmer').addClass('active');
                            $('.buttons.Time.Data .button').removeClass('active');
                            $(this).addClass('active');
                            var item_id = $('.dropdown.itemsTab').dropdown('get value');
                            var url=  "/pointdetail/get_datas?item_id="+item_id+"&time="+this.value;
                            //tableData.ajax.url(url).load();
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
                        })
                    }
                }
            });
    });
})

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
                    str +=" <tr id='"+data.item_datas[i].item_id+"' class='"+mytrColor+" tr' ><td>"
                        + data.item_datas[i].item_name +"</td><td>"
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
    var areaoption = {
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
            left: '2%',
            right: '10%',
            bottom: '0%',
            top:'20%',
            containLabel: true
        },
        xAxis: {
            data: {}
        },
        yAxis: {
        },
        series: [{
            name: '',
            type: '',
            areaStyle: {normal: {}},
            data: {},
            color: []
        }]
    };
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
            left: '2%',
            right: '10%',
            bottom: '0%',
            top:'20%',
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
                    str ="<div class='column' ><div id='"+id+"' class='chart-style' style='height:200px'>"+id+"</div></div></div>";
                    $("#chartReport").append(str);
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
                    if(data[i].units == "") {
                        title = data[i].item_name;
                    }else {
                        title = data[i].item_name + "(" + data[i].units +")";
                    }
                    option.title.text = title;
                    option.xAxis.data = data[i].data_time;
                    option.series[0].name = data[i].graph_name;
                    option.series[0].type = data[i].graph_type;
                    option.series[0].data = data[i].data;
                    option.series[0].color[0] = myColor;
                    //面积图存
                    areaoption.title.text = title;
                    areaoption.xAxis.data = data[i].data_time;
                    areaoption.series[0].name = data[i].graph_name;
                    areaoption.series[0].type = data[i].graph_type;
                    areaoption.series[0].data = data[i].data;
                    areaoption.series[0].color[0] = myColor;
                    echarts.init(document.getElementById(id)).setOption(option);
                }
                $('.ui.buttons.Type.Report .button').on('click', function() {

                    $('.buttons.Type.Report .button').removeClass('active');
                    $(this).addClass('active');
                    option.series[0].type = this.value;
                    //console.log('this',$(this));
                    if (this.value == "area")
                    {
                        $("div#chartReport .chart-style").each(function(){
                            echarts.init(document.getElementById(this.id)).setOption(areaoption);
                        })
                    }else {
                        option.series[0].areaStyle = "";
                        $("div#chartReport .chart-style").each(function(){
                            echarts.init(document.getElementById(this.id)).setOption(option);
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
function dropdownitemsTab(point_id,item_id){
    $.ajax({
        type: "get",
        url: "/hostgraphs/get_items?point_id=" + point_id,
        dataType: "json",
        success: function (result) {
            if(result.success) {
                var data=result.data;
                var str = '';
                for(var i=0;i<data.length;i++)
                {
                    str +="<option value='"+data[i].item_id+"'>"+data[i].item_name+"</option> ";
                }
                $(".dropdown.itemsTab select").html(str);
                $(".dropdown.itemsTab .text").html(data[0].item_name);
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