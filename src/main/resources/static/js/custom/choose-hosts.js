/**
 * Created by chenzheqi on 2017/9/1.
 */

function genHostsTree($tree_div, callback) {
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
                var valid_children = ["group-ok", "group-high", "group-warning", "host-ok", "host-high", "host-warning"]
                if ($tree_div.jstree(true)) {
                    $tree_div.jstree(true).destroy()
                }
                $tree_div.jstree({
                    core: {
                        data: dst
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
                    plugins: ["checkbox", "types"]
                })
                //请求成功后，执行回调函数
                callback()
            } else {
                console.error(res.message)
            }
        },
        error: function (e) {
            console.error(e)
        }
    })
}
