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

//打印 【object Object】
function writeObj(obj){
    var description = "";
    for(var i in obj){
        var property=obj[i];
        description+=i+" = "+property+"\n";
    }
    console.log(description);
}

//时间格式化
Date.prototype.Format = function (fmt) { //author: zkf
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}