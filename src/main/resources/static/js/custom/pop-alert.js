/**
 * Created by chenzheqi on 2017/9/7.
 * 必须在jquery 和 semantic 的js文件之后引入
 */

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
