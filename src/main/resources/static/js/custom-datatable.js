
//datatable page semantic datatable settings
(function (window, document, undefined) {
    var factory = function ($, DataTable) {
        "use strict";

        /* 初始化时设置DataTable默认属性*/
        $.extend(true, DataTable.defaults, {
            dom:// 不显示搜索框
                "<'sixteen wide column'tr>" +
                "<'left aligned five wide column'i><'right aligned eleven wide column'p>",
            renderer: 'semantic',
            order: [0, 'asc'],// 以升序排列此列
            "pagingType": "full_numbers_icon",
            "iDisplayLength" : 12,// 每页显示的记录数
            "language": {
                "sLengthMenu" : "每页显示 _MENU_ 条记录",
                "sZeroRecords" : "对不起，没有匹配的数据",
                "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                "sInfoEmpty" : "没有匹配的数据",
                "sInfoFiltered" : "(原 _MAX_ 条记录)",
                "sProcessing" : "正在加载中...",
                "sSearch" : "全文搜索：",
                "oPaginate" : {
                    "sFirst" : "首页",
                    "sPrevious" : " 上一页 ",
                    "sNext" : " 下一页 ",
                    "sLast" : " 末页 "
                }
            },
        });

        $.extend(DataTable.ext.pager, {
            full_numbers_icon: DataTable.ext.pager.full_numbers
        });

        //错误信息提示
        $.fn.dataTable.ext.errMode = function (s, h, m) {
            if (h == 1) {
                Lobibox.notify("error", {
                    size: "normal",
                    rounded: false,
                    delayIndicator: true,
                    msg: "连接服务器失败！",
                    icon: "warning icon",
                    title: "业务系统 Table",
                });
            } else if (h == 7) {
                Lobibox.notify("error", {
                    size: "normal",
                    rounded: false,
                    delayIndicator: true,
                    /*msg: "返回数据错误！",*/
                    msg: "连接服务器失败！",
                    icon: "warning icon",
                    title: "业务系统 Table",
                });
            }
        };

        /* Default class modification */
        $.extend(DataTable.ext.classes, {
            sWrapper: "ui grid dataTables_wrapper ",
            sFilterInput: "",
            sLengthSelect: ""
        });

        /* 分页按钮渲染 */
        DataTable.ext.renderer.pageButton.semantic = function (settings, host, idx, buttons, page, pages) {
            var api = new DataTable.Api(settings);
            var classes = settings.oClasses;
            var lang = settings.oLanguage.oPaginate;
            var btnDisplay, btnClass, btnIcon, counter = 0;
            var addIcons = ((!api.init().pagingType ? '' : api.init().pagingType.toLowerCase()).indexOf('icon') !== -1);

            var attach = function (container, buttons) {
                var i, ien, node, button;
                var clickHandler = function (e) {
                    e.preventDefault();
                    if (!$(e.currentTarget).hasClass('disabled')) {
                        api.page(e.data.action).draw('page');
                    }
                };

                for (i = 0, ien = buttons.length ; i < ien ; i++) {
                    button = buttons[i];

                    if ($.isArray(button)) {
                        attach(container, button);
                    }
                    else {
                        btnDisplay = '';
                        btnClass = '';
                        btnIcon = '';
                        switch (button) {
                            case 'ellipsis':
                                btnDisplay = (addIcons ? '<i class="mini ellipsis horizontal icon"></i>' : '&hellip;');
                                btnClass = 'disabled';
                                break;

                            case 'first':
                                btnIcon = (addIcons ? '<i class="angle single left icon"></i>' : '');
                                btnDisplay = btnIcon + lang.sFirst;
                                btnClass = button + (page > 0 ?
                                    '' : ' disabled');
                                break;

                            case 'previous':
                                btnIcon = (addIcons ? '<i class="angle double left icon"></i>' : '');
                                btnDisplay = btnIcon + lang.sPrevious;
                                btnClass = button + (page > 0 ?
                                    '' : ' disabled');
                                break;

                            case 'next':
                                btnIcon = (addIcons ? '<i class="angle double right icon"></i>' : '');
                                btnDisplay = lang.sNext + btnIcon;
                                btnClass = button + (page < pages - 1 ?
                                    '' : ' disabled');
                                break;

                            case 'last':
                                btnIcon = (addIcons ? '<i class="angle single right icon"></i>' : '');
                                btnDisplay = lang.sLast + btnIcon;
                                btnClass = button + (page < pages - 1 ?
                                    '' : ' disabled');
                                break;

                            default:
                                btnDisplay = button + 1;
                                btnClass = page === button ?
                                    'active' : '';
                                break;
                        }

                        if (btnDisplay) {
                            node = $('<a>', {
                                'class': classes.sPageButton + ' ' + btnClass + ' item ',
                                'id': idx === 0 && typeof button === 'string' ?
                                    settings.sTableId + '_' + button :
                                    null
                            }).html(btnDisplay).appendTo(container);

                            settings.oApi._fnBindAction(
                                node, { action: button }, clickHandler
                            );

                            counter++;
                        }
                    }
                }
            };

            // IE9 throws an 'unknown error' if document.activeElement is used
            // inside an iframe or frame.
            var activeEl;

            try {
                // Because this approach is destroying and recreating the paging
                // elements, focus is lost on the select button which is bad for
                // accessibility. So we want to restore focus once the draw has
                // completed
                activeEl = $(host).find(document.activeElement).data('dt-idx');
            }
            catch (e) { }

            attach(
                $(host).empty().html('<div class="ui stackable small pagination menu" />').children('div'),
                buttons
            );

            if (activeEl) {
                $(host).find('[data-dt-idx=' + activeEl + ']').focus();
            }
        };
    }; // /factory

    // Define as an AMD module if possible
    if (typeof define === 'function' && define.amd) {
        define(['jquery', 'datatables'], factory);
    }
    else if (typeof exports === 'object') {
        // Node/CommonJS
        factory(require('jquery'), require('datatables'));
    }
    else if (jQuery) {
        // Otherwise simply initialise as normal, stopping multiple evaluation
        factory(jQuery, jQuery.fn.dataTable);
    }
})(window, document);

/*$(document).ready(function () {
    var dtable = $('.table').DataTable({
        "pagingType": "full_numbers_icon",
        order: [0, 'desc'],
          responsive: true
    });
});*/

