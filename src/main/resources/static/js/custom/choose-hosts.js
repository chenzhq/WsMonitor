/**
 * Created by chenzheqi on 2017/9/1.
 */

function genHostsTree() {
    var $tree = arguments[0]
    if (!$tree instanceof jQuery) {
        console.error("第一个参数必须是jquery对象")
    }
    if (Object.prototype.toString.call(arguments[1]) !== '[object Function]') {
        var checked_host = arguments[1],
            callback = arguments[2]
    } else {
        callback = arguments[1]
    }
    $.ajax({
        type: "get",
        url: "/ov/group/select_host",
        dataType: "json",
        success: function (res) {
            if (res.success) {
                var src = res.data;
                // console.log('原始res.data', src)
                var dst = src.map(function (v, i, a) {
                    if (v["type"] === 'group') {
                        v["state"] = {
                            opened: true
                        }
                    }
                    v["type"] = v["type"] + '-' + v["status"]
                    return v
                })
                // console.log('修改后的res.data', dst)
                if (!$tree.jstree(true)) {
                    var valid_children = ["group-ok", "group-high", "group-warning", "host-ok", "host-high", "host-warning"]
                    $tree.jstree({
                        core: {
                            data: dst,
                            check_callback: true
                        },
                        types: {
                            "#": {
                                valid_children: valid_children
                            },
                            "group-ok": {
                                valid_children: valid_children,
                                icon: "green folder icon"
                            },
                            "group-high": {
                                valid_children: valid_children,
                                icon: "red folder icon"
                            },
                            "group-warning": {
                                valid_children: valid_children,
                                icon: "yellow folder icon"
                            },
                            "host-ok": {
                                valid_children: [],
                                icon: "green desktop icon"
                            },
                            "host-warning": {
                                valid_children: [],
                                icon: "yellow desktop icon"
                            },
                            "host-high": {
                                valid_children: [],
                                icon: "red desktop icon"
                            }
                        },
                        checkbox: {
                            tie_selection: true
                        },
                        plugins: ["checkbox", "types"]
                    })
                }
                if (checked_host) {
                    $tree.jstree(true).uncheck_all()
                    $tree.jstree(true).check_node(checked_host)
                }
                //请求成功后，执行回调函数
                if (callback) callback()
            } else {
                console.error(res.message)
            }
        },
        error: function (e) {
            console.error(e)
        }
    })
}
