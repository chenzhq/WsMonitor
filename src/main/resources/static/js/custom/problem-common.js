/**
 * Created by zkf on 2017/9/7.
 */


//事件详情弹出框
function eventDetailModal(event_id) {
    //事件详情细节部分
    var eventDetailStr = getEventDetailContent(event_id);
    $("#eventDetailModal").html(eventDetailStr);
    //确认记录部分
    var acknowledgeStr = getAcknowledgeContent(event_id);
    $("#acknowledgeModal").html(acknowledgeStr);
    //告警详情部分
    var alertStr = getAlertDetailContent(event_id);
    $("#alertDetailModal").html(alertStr);


    //显示弹出框
    $('.ui.modal.detail').modal('show');
}

//获取确认记录提示框内容 str
function getAcknowledgeContent(event_id) {
    var str = "";
    $.ajax({
        async: false,
        type: "get",
        url: "/acknowledges/get_acknowledge?event_id=" + event_id,
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            if (result.success) {
                var data = result.data;
                for(var i=0;i<data.length;i++)
                {
                    var action = "";
                    if(data[i].action == '0'){
                        action = '未确认';
                    }
                    else if(data[i].action == '1'){
                        action = '已确认';
                    }
                    str += "<tr><td>"
                        +data[i].clock+"</td><td>"
                        +data[i].alias+"</td><td class='left aligned'>"
                        +data[i].message+"</td><td>"
                        +action+"</td></tr>"
                }
            } else {
                errorMsg_no_data("确认记录 Popup");
            }
        },
        error: function () {
            errorMsg_no_connect("确认记录 Popup");
        }
    })
    return str;
}

//获取已告警详细信息内容 str
function getAlertDetailContent(event_id){
    var str = "";
    $.ajax({
        async: false,
        type: "get",
        url: "/alerts/get_detail?event_id=" + event_id,
        dataType: "json",
        success: function (result) {
            if (result.success) {
                var color;
                var data = result.data;
                console.log('alert data',data);
                for(var i=0;i<data.length;i++)
                {
                    if(data[i].recovery) {
                        color = '#BBFFBB';
                    }else {
                        color = '#FF9797';
                    }
                    str += "<tr style='background-color: "+ color +"' ><td>"
                        +data[i].esc_step+"</td><td>"
                        +data[i].last_time+"</td><td>"
                        +data[i].sendto+"</td><td>"
                        +data[i].alert_type+"</td><td>"
                        +data[i].subject+"</td><td>"
                        +data[i].message+"</td><td>"
                        +data[i].status+"("+data[i].retries+")</td></tr>";
                }
            } else {
                errorMsg_no_data("已告警 Popup");
            }
        },
        error: function () {
            errorMsg_no_connect("已告警 Popup");
        }
    })
    return str;
}

//获取事件详细信息内容 str
function getEventDetailContent(event_id) {
    var str = "";
    //事件详情细节部分
    $.ajax({
        async: false,
        type: "get",
        url: "/event/get_detail?event_id=" + event_id,
        dataType: "json",
        success: function (result) {
            if (result.success) {

                var event_data = result.data;
                //标题赋值
                $('#event_title').text(event_data.trigger_name);
                if(event_data.closed){
                    var closed = '已关闭';
                }else{
                    var closed = '未关闭';
                }
                str += "<tr><td>所属主机</td><td>"
                    +event_data.host_name+"</td></tr><tr><td>监控点</td><td>"
                    +event_data.point_name+"</td></tr><tr><td>监控项</td><td>"
                    +event_data.item_name+"</td></tr><tr><td>阀值</td><td>"
                    +event_data.threshold+"</td></tr><tr><td>触发器描述</td><td  title='"+event_data.trigger_name+"'>"
                    +event_data.trigger_name+"</td></tr><tr><td>发生时间</td><td>"
                    +event_data.problem_time+"</td></tr><tr><td>恢复时间</td><td>"
                    +event_data.recovery_time+"</td></tr><tr><td>手动关闭(操作人)</td><td>"
                    +closed+"("+event_data.closed_user+")</td></tr>";
            } else {
                errorMsg_no_data("细节列表 modal");
            }
        },
        error: function () {
            errorMsg_no_connect("细节列表 modal");
        }
    });
    return str;
}

//获取确认操作modal框
function getAcknowledgeModal(event_id) {
    //modal框 数据初始化
    $("#acknowledge_message").val("");
    $("#text_field").removeClass("error");
    $("#text_checkbox").removeClass("error");
    $("#acknowledge_checkbox").attr("checked",false);
    //用户是否可以关闭问题
    $.ajax({
        type: "get",
        url: "/acknowledge/is_closed?event_id=" + event_id,
        dataType: "json",
        success: function (result) {
            if (result.success) {
                $("#acknowledge_eventid").val(event_id);
                var data = result.data;
                if (!data.checkbox_enable){
                    $("#acknowledge_checkbox").addClass("disabled");
                    $("#text_checkbox").attr("data-tooltip",data.disable_message);
                    //$("#label_checkbox").html(data.disable_message);
                }else {
                    $("#text_checkbox").attr("data-tooltip","问题可关闭");
                    //$("#label_checkbox").html("问题可关闭")
                }
                //确认历史记录数据
                var str = '';
                var history_data = data. acknowledge_history;
                for(var i=0;i<history_data.length;i++)
                {
                    if(history_data[i].action == '0'){
                        var action = '';
                    }
                    else if(history_data[i].action == '1'){
                        var action = '已关闭问题';
                    }
                    str += "<tr><td>"
                        +history_data[i].clock+"</td><td>"
                        +history_data[i].alias+"</td><td class='left aligned'>"
                        +history_data[i].message+"</td><td>"
                        +action+"</td></tr>"
                }
                $("#acknowledge_data").html(str);
            } else {
                errorMsg_no_data("用户是否可关闭问题 modal");
            }
        },
        error: function () {
            errorMsg_no_connect("用户是否可关闭问题 modal");
        }
    })
    $(".ui.modal.confirm").modal("show");
    //绑定点击确定提交事件
    $("#submit_bnt").on("click",function(){
        $('.ui.modal.confirm').modal({
            /*onShow: function () {
                if($("#acknowledge_message").val()=="") {
                    $("#submit_bnt").addClass("disabled ");
                }else {
                    $("#submit_bnt").removeClass("disabled ");
                }
            },*/
            onApprove: function () {
                //验证是否为空
                if($("#acknowledge_message").val()=="")
                {
                    $("#submit_bnt").addClass("error");
                    return false;
                }else {
                    var action = "";
                    if($("#acknowledge_checkbox").attr("checked")) {
                        action = "1";
                    }else {
                        action = "0";
                    }
                    var dataInfo = {
                        eventId: $("#acknowledge_eventid").val(),
                        message:$("#acknowledge_message").val(),
                        action:action
                    };

                    $.ajax({
                        type: "post",
                        url: "/acknowledge/acknowledge_event",
                        data:JSON.stringify(dataInfo),
                        dataType: "json",
                        contentType:'application/json;charset=UTF-8',
                        success: function (result) {
                            if (result.success) {
                                var data = result.data;
                            } else {
                                errorMsg_no_data("一次关闭问题的确认 modal");
                            }
                        },
                        error: function () {
                            errorMsg_no_connect("一次关闭问题的确认 modal");
                        }
                    });
                }
            }
        }).modal('setting', 'closable', false).modal('show');
    });
}
