/**
 * Created by yanms on 2017/11/17.
 * 监控点详情
 */
//点击监控项图形放大
function zoom_graph(id)
{
    var item_id = id.substring(1);
    var days = 30;
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
        dataZoom: [
            {
                show: true,
                start: 0,
                end: 100
            }
        ],
        legend: {
            data:[]
        },
        grid: {
            left: '3%',
            right: '3%',
            bottom: '20%',
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
        url: "/pointdetail/get_datas?item_id=" + item_id + "&time=" + days,
        dataType: "json",
        success: function (result) {
            if (result.success) {
                var data = result.data;
                var str = '';
                var str_table = '';
                var title = '';
                var name = '';
                var item_time = [];
                var item_data = [];
                $("#itemZoom").empty();
                $("#itemHistory").empty();
                if(data.length > 0) {
                    var zoomId = "zoom" + item_id;
                    var Color = ['#5FB878', '#DB2828', '#F7B824', '#d2d2d2', '#2F4056'];
                    if (data[data.length - 1].state == "正常") {
                        var myColor = Color[0]
                    }
                    if (data[data.length - 1].state == "严重") {
                        var myColor = Color[1]
                    }
                    if (data[data.length - 1].state == "警告") {
                        var myColor = Color[2]
                    }
                    if (data[0].units == "%") {
                        option.yAxis.max = 100;
                    } else {
                        option.yAxis.max = null;
                    }
                    if (data[0].units == "") {
                        title = data[0].item_name;
                    } else {
                        title = data[0].item_name + "(" + data[0].units + ")";
                    }
                    if (strlen(title) > 100) {
                        name = "<div class='sixteen wide column no-padding' data-tooltip='" + title + "'>" +
                            "<h3 class='text-hidden'>&nbsp&nbsp&nbsp" + title + "</h3></div>";
                    } else {
                        name = "<div class='sixteen wide column no-padding'>" +
                            "<h3 class='text-hidden'>&nbsp&nbsp&nbsp" + title + "</h3></div>";
                    }
                    str = "<div class='column'><div class='ui grid'>" + name +
                        "<div class='sixteen wide column no-padding'>" +
                        "<div id='" + zoomId + "' class='chart-zoom'></div></div></div></div>";
                    $("#itemZoom").append(str);
                    for (var i = 0; i < data.length; i++) {
                        item_time.push(data[i].last_time);
                        item_data.push(data[i].value);
                        str_table += "<tr><td>" + data[i].last_time + "</td><td>" + data[i].value +"(" + data[i].units + ")" + "</td></tr>"
                    }
                    option.xAxis.data = item_time;
                    option.series[0].name = title;
                    option.series[0].type = 'line';
                    option.series[0].data = item_data;
                    option.series[0].color[0] = myColor;
                    echarts.init(document.getElementById(zoomId)).setOption(option);
                }else {
                    str_table = "<tr> <td colspan='2'>无数据</td> </tr>"
                }
                $("#itemHistory").append(str_table);
            }else {
                errorMsg_no_data("监控点 Tab");
            }
        },
        error: function () {
            errorMsg_no_connect("监控点 Tab");
        }
    });

    $('#pointDimmer').addClass('active');
    // initialize all modals
    $('.coupled.modal')
        .modal({
            allowMultiple: true
        })
    ;
    $('#zoomModal').modal('setting', 'closable', false).modal('show');
}
function zoom_close() {
    $('#pointDimmer').removeClass('active');
}