/**
 * Created by chenzheqi on 2017/10/18.
 */
/**
 * 创建静态表格
 * @param options   rows cols 行数和列数
 *                  modalId 弹出框modal的ID
 */
function genTableBlock(options) {
    const settings = $.extend({
        rows: 1,
        cols: 1,
        modalId: '',
    }, options);

    const modal = document.getElementById(settings.modalId);

    if (!modal.innerHTML) {
        modal.innerHTML = genModal(settings.rows, settings.cols);
    } else {
        let editedTable = document.getElementById('edited-table');
        editedTable.innerHTML = genTableHtml(settings.rows, settings.cols);
    }

    let $modal = $(modal);

    $modal.modal({
            onApprove: () => {
                let inputs = $modal.find('input');
                let values = [];
                for (let i = 0, length = inputs.length; i < length; ++i) {
                    values[i] = inputs[i].value;
                }
                // result是table的html结构
                let result = genTableHtml(settings.rows, settings.cols, values);
                console.log('result',result);
                // TODO: 补充其它逻辑
                //ajax，存储表格数据
                var config_info = {
                    block_name: $('#chart_name').val(),
                    block_type: 'chart',
                    graph_type: $('#charttype_select').dropdown('get value'),
                    contents: result
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
                            //添加 config_info 和 block_info 到page_vo 对象中
                            page_vo.config_data.push(config_info);

                            page_vo.block_data.push(block_info);

                            //默认  静态表格 block大小
                            addBlockWidget(block_default_size[5].size_x,block_default_size[5].size_y);

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
    ).modal('show');
}


function genModal(row, col) {
    let tableHtml = '<div id="edited-table">' + genTableHtml(row, col) + '</div>';
    let header_section = '<div class="header">' +
        '填写表格' +
        '</div>';
    let action_section = '<div class="actions">' +
        '    <div class="ui positive right labeled icon button">' +
        '      确定' +
        '      <i class="checkmark icon"></i>' +
        '    </div>' +
        '    <div class="ui black deny button">' +
        '      取消' +
        '    </div>';
    return header_section + tableHtml + action_section;
}

function genTableHtml(row, col, values) {
    let body = '';
    for (let i = 0; i < row; ++i) {
        body += '<tr>';
        if (values) {
            for (let j = 0; j < col; ++j) {
                body += '<td>' +
                    values[i * col + j] +
                    '</td>';
            }
        } else {
            for (let j = 0; j < col; ++j) {
                body += '<td>' +
                    '<div id="cell-' + i + '-' + j + '" class="ui large fluid transparent input" style="margin-bottom: 0">' +
                    '  <input placeholder="...">' +
                    '</div>' +
                    '</td>';
            }
        }
        body += '</tr>';
    }
    return "<table class=\"ui celled striped table\" style=\" width: 98%;margin: 0 auto; \"><tbody>" + body +
        "</tbody></table>";
}