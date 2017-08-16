/**
 * Created by yanms on 2017/8/15.
 */
//告警消息
function errorMsg_no_connect(title) {
    Lobibox.notify("error", {
        size: "normal",
        rounded: false,
        delayIndicator: true,
        msg: "连接服务器失败！",
        icon: "warning icon",
        title: title
    });
}
function errorMsg_no_data(title) {
    Lobibox.notify("error", {
        size: "normal",
        rounded: false,
        delayIndicator: true,
        msg: "解析数据失败！",
        icon: "warning icon",
        title: title
    });
}
//
function strlen(str){
    var len = 0;
    for (var i=0; i<str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
            len++;
        }
        else {
            len+=2;
        }
    }
    return len;
}