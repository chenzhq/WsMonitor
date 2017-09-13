/**
 * Created by chenzheqi on 2017/9/12.
 */

(function ($) {
    $.fn.problemTable = function (options) {
        var settings = $.extend({}, options);

        var problemsTable = this.DataTable({
            iDisplayLength: 10,
            order: [0, 'desc'],
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
            drawCallback: function () {
                $(this).removeClass('dataTable')
                popAlert($('.alert'), $('#alert_popup'))
                popAck($('.ack'), $('#ack_popup'))
                $("a.event_time").on("click", function () {
                    var event_id = $(this).attr("data-id");
                    eventDetailModal(event_id);
                });
                $("a.confirm").on("click", function () {
                    var event_id = $(this).attr("data-id");
                    getAcknowledgeModal(event_id);
                })
            },
            data: [],
            columns: [
                {
                    "data": "problem_time",
                    "width": "18%",
                    "title": "发生时间",
                    "orderable": false,
                    render: function (data, type, row) {
                        return '<a href="#" data-id="' + row.problem_eventid + '" class="event_time">' + row.problem_time + '</a>';
                    }
                },
                {
                    "data": "host_name",
                    "width": "10%",
                    "title": "设备",
                    "orderable": false,
                },
                {
                    "data": null,
                    "width": "25%",
                    "title": "问题",
                    "orderable": false,
                    render: function (data, type, row) {
                        return '<a href="problemdetail?trigger_id=' + row.trigger_id + '">' + row.description + '</a>';
                    }
                },
                {
                    "data": "priority_state",
                    "width": "7%",
                    "title": "级别",
                    "orderable": false,
                },
                {
                    "data": null,
                    "width": "9%",
                    "title": "状态",
                    "orderable": false,
                    render: function (data, type, row, meta) {
                        if (row.problem_state !== "问题") {
                            return '<div class="ui mini green label" data-tooltip="恢复时间：' + row.recover_time + '" >' + row.problem_state + '</div>';
                        } else {
                            return '<div class="ui mini red label" >' + row.problem_state + '</div>';
                        }
                    }
                },
                {
                    "data": "duration_string",
                    "width": "15%",
                    "title": "持续时间",
                    "orderable": false,
                },
                {
                    "data": null,
                    "width": "9%",
                    "title": "告警状态",
                    "orderable": false,
                    render: function (data, type, row) {
                        if (row.alert_state === '未告警') {
                            return row.alert_state;
                        } else {
                            return '<a href="#" data-id="' + row.problem_eventid + '" class="alert" >' + row.alert_state + '(' + row.alert_num + ') </a>';
                        }
                    }
                },
                {
                    "data": null,
                    "width": "7%",
                    "title": "确认",
                    "orderable": false,
                    render: function (data, type, row) {
                        var _class
                        if (row.acknowledged === '是') {
                            _class = 'confirm ack'
                        } else {
                            _class = 'confirm'
                        }
                        return '<a href="javascript:void(0)" data-id="' + row.problem_eventid + '" class="' + _class + '">' + row.acknowledged + '</a>';
                    }
                }
            ]
        });

        function popAlert($_target, $_popup) {
            if (!$_target instanceof jQuery || !$_popup instanceof jQuery) {
                console.error("参数必须是jquery对象")
                return
            }
            if ($_popup.children('table').length === 0) {
                $_popup.append('<table class="ui single line very basic compact table">' +
                    '<thead>' +
                    '<tr>' +
                    '<th class="center aligned">步骤</th>' +
                    '<th class="center aligned">时间</th>' +
                    '<th class="center aligned">接收者</th>' +
                    '<th class="center aligned">状态(重试)</th>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody>' +
                    '</tbody>' +
                    '<tfoot>' +
                    '<tr><th colspan="4"><i class="announcement red icon"></i><span class="ui red">问题告警</span>&nbsp;&nbsp;' +
                    '<i class="announcement green icon"></i>恢复通知</th></tr>' +
                    '</tfoot>' +
                    '</table>')
            }
            if (!$_popup.hasClass('ui popup')) {
                $_popup.addClass('ui popup')
            }
            $_target.popup({
                popup: $_popup,
                on: 'hover',
                hoverable: true,
                delay: {
                    show: 500
                },
                inline: false,
                position: 'top right',
                onShow: function (target) {

                    $_popup.find('tbody').html('')
                    $.ajax({
                        type: 'get',
                        url: 'alerts/get_alert',
                        dataType: 'json',
                        data: {event_id: $(target).attr('data-id')},
                        success: function (result) {
                            if (result.success) {
                                var body_html = []
                                var data = result.data
                                data.forEach(function (v, i) {
                                    var oneAlert = v || {
                                                alert_id: "0",
                                                alias: "BTW",
                                                esc_step: 0,
                                                last_time: "2018-00-00 00:00:00",
                                                recovery: false,
                                                retries: 0,
                                                sendto: "ok",
                                                status: "成功"
                                            },
                                        alert_type,
                                        alert_status
                                    alert_type = oneAlert.recovery ? '<i class="announcement green icon"></i>' : '<i class="announcement red icon"></i>'
                                    alert_status = oneAlert.status === '成功' ? '<i class="green checkmark icon"></i>' : '<i class="red close icon"></i>'
                                    body_html.push(
                                        '<tr>' +
                                        '<td >' + alert_type + oneAlert.esc_step + '</td>' +
                                        '<td class="center aligned">' + oneAlert.last_time + '</td>' +
                                        '<td>' + oneAlert.alias + '(' + oneAlert.sendto + ')' + '</td>' +
                                        '<td class="center aligned">' + alert_status + '(' + oneAlert.retries + ')' + '</td>' +
                                        '</tr>')
                                })
                                $_popup.find('tbody').html(body_html.join())
                            } else {

                            }
                        },
                        error: function (e) {

                        }
                    })
                }
            })
        }

        function popAck($_target, $_popup) {
            if (!$_target instanceof jQuery || !$_popup instanceof jQuery) {
                console.error("参数必须是jquery对象")
                return
            }
            if ($_popup.children('table').length === 0) {
                $_popup.append('<table class="ui single line very basic compact table">' +
                    '<thead>' +
                    '<tr>' +
                    '<th class="center aligned">时间</th>' +
                    '<th class="center aligned">用户</th>' +
                    '<th class="center aligned">消息</th>' +
                    '<th class="center aligned">动作</th>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody>' +
                    '</tbody>' +
                    '</table>')
            }
            if (!$_popup.hasClass('ui popup')) {
                $_popup.addClass('ui popup')
            }
            $_target.popup({
                popup: $_popup,
                on: 'hover',
                hoverable: true,
                delay: {
                    show: 500
                },
                inline: false,
                position: 'top right',
                onShow: function (target) {

                    $_popup.find('tbody').html('')
                    $.ajax({
                        type: 'get',
                        url: 'acknowledges/get_acknowledge',
                        dataType: 'json',
                        data: {event_id: $(target).attr('data-id')},
                        success: function (result) {
                            if (result.success) {
                                var body_html = []
                                var data = result.data
                                data.forEach(function (v) {
                                    var close_action = v.action === "0" ? '' : '关闭问题'
                                    body_html.push(
                                        '<tr>' +
                                        '<td>' + v.clock + '</td>' +
                                        '<td>' + v.alias + '</td>' +
                                        '<td>' + v.message + '</td>' +
                                        '<td>' + close_action + '</td>' +
                                        '</tr>')
                                })
                                $_popup.find('tbody').html(body_html.join())
                            } else {

                            }
                        },
                        error: function (e) {

                        }
                    })
                }
            })
        }

        //获取最近问题
        $.ajax({
            type: "get",
            url: '/problems/get_current',
            dataType: 'json',
            success: function (result) {
                if (result.success) {
                    problemsTable.rows.add(result.data).draw()
                } else {

                }
            },
            error: function (e) {

            }
        })
        return problemsTable
    }
})(jQuery)
