/**
 * Created by chenzheqi on 2017/10/18.
 */
/**
 * 创建静态表格
 * @param options   rows cols   行数和列数
 *                  modalId     弹出框modal的ID
 *                  editMode    是否为编辑状态
 *                  values      如果是编辑状态，默认文本数组，从左至右，由上到下
 */
function genTableBlock(options) {
    const settings = $.extend({
        rows: 1,
        cols: 1,
        modalId: '',
        editMode: false,
        values: [],
        onApprove: (result) => {}
    }, options);

    const modal = document.getElementById(settings.modalId);

    if (settings.editMode) {
        if (settings.values.length !== settings.rows * settings.cols) {
            console.error('单元格数量与数据长度不对应');
            return;
        }
    }

    console.log(settings)
    if (!modal.innerHTML || modal.innerHTML.length === 0) {
        modal.innerHTML = genModal(settings.rows, settings.cols, settings.values, settings.editMode);
    } else {
        let editedTable = document.getElementById('edited-table');
        editedTable.innerHTML = genTableHtml(settings.rows, settings.cols, settings.values, settings.editMode);
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
                settings.onApprove(result);
                console.log('result',result);

            }
        }
    ).modal('show');
}


function genModal(row, col, values, edited) {
    let tableHtml = '<div id="edited-table">' + genTableHtml(row, col, values, edited) + '</div>';
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

function genTableHtml(row, col, values, edited) {
    let body = '';
    for (let i = 0; i < row; ++i) {
        body += '<tr>';
        console.log(values);
        if (values && values.length !== 0) {
            if (!edited) {
                for (let j = 0; j < col; ++j) {
                    body += '<td>' +
                        values[i * col + j] +
                        '</td>';
                }
            } else {
                for (let j = 0; j < col; ++j) {
                    body += '<td>' +
                        '<div id="cell-' + i + '-' + j + '" class="ui large fluid transparent input" style="margin-bottom: 0">' +
                        '  <input placeholder="..." ' + 'value="' + values[i * col + j] + '">' +
                        '</div>' +
                        '</td>';
                }
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