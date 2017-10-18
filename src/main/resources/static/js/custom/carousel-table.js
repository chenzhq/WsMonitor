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
                let result = genTableHtml(rows, cols, values);
                // TODO: 补充其它逻辑
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
    return "<table class=\"ui celled striped table\"><tbody>" + body +
        "</tbody></table>";
}