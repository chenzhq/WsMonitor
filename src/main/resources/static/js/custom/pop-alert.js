/**
 * Created by chenzheqi on 2017/9/7.
 * 必须在jquery 和 semantic 的js文件之后引入
 */

function popAlert($_target, $_popup) {
    if (!$_target instanceof jQuery || !$_popup instanceof jQuery) {
        console.error("参数必须是jquery对象")
    }
    if ($_popup.children('table').length === 0) {
        $_popup.append('<table class="ui single line very basic table">' +
            '<thead>' +
            '<tr>' +
            '<th class="center aligned">步骤</th>' +
            '<th class="center aligned">时间</th>' +
            '<th class="center aligned">接受者</th>' +
            '<th class="center aligned">状态</th>' +
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
                        data.forEach(function (v) {
                            body_html.push(
                                '<tr>' +
                                '<td>' + v.esc_step + '</td>' +
                                '<td>' + v.last_time + '</td>' +
                                '<td>' + v.alias + '(' + v.sendto + ')' + '</td>' +
                                '<td>' + v.status + '(' + v.retries + ')' + '</td>' +
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
