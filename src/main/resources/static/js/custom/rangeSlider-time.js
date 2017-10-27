/**
 * Created by zkf on 2017/10/27.
 */

(function ($) {
    $.fn.rangeSliderTime = function (options) {

        var settings = $.extend({
            onStart: (data,time_arr) => {},
            onFinish: (data,time_arr) => {}
        }, options);

        var now = new Date();
        var end_time = new Date(now);
        end_time.setMinutes(0);
        end_time.setSeconds(0);
        end_time.setMilliseconds(0);
        var begin_time = new Date(end_time);
        //3表示三个月
        if(end_time.getMonth() > 3) {
            begin_time.setMonth(end_time.getMonth() - 3);
        }else {
            begin_time.setFullYear(end_time.getFullYear() - 1);
            begin_time.setMonth(end_time.getMonth() + 9);
        }
        //一个月
        var select_time = new Date(end_time);
        if(end_time.getMonth() != 1) {
            select_time.setMonth(end_time.getMonth() - 1);
        }else {
            select_time.setFullYear(end_time.getFullYear() - 1);
            select_time.setMonth(12);
        }
        var select_format = select_time.Format("MM-dd hh:mm");
        var select_index = 0;

        var begin_second = begin_time.getTime();
        var end_second = end_time.getTime();

        var time_arr = [];
        var vo_arr = [];
        var s = begin_second;
        var index=0;
        while(s <= end_second) {
            var dura_date = new Date();
            dura_date.setTime(s);
            time_arr.push(dura_date.Format("yyyy-MM-dd hh:mm:ss"));
            vo_arr.push(dura_date.Format("MM-dd hh:mm"));
            if(select_format === dura_date.Format("MM-dd hh:mm")) {
                select_index = index;
            }
            s += 3600 * 1000;
            index++;
        }
        time_arr.push(now.Format("yyyy-MM-dd hh:mm:ss"));
        vo_arr.push(now.Format("MM-dd hh:mm"));

        this.ionRangeSlider({
            type: "double",
//			grid:true,
            from: select_index,
            //TODO 最好使用返回的问题数中的最大值
            to: vo_arr.length-1,
            values:vo_arr,
            onStart: (data) => {

                settings.onStart(data,time_arr);

            },
            onChange: (data) => {

            },
            onFinish: (data) => {

                settings.onFinish(data,time_arr);

            },
            onUpdate: (data) => {

            }
        });
        return this.data("ionRangeSlider");
    }
})(jQuery)
