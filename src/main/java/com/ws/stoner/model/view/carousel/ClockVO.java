package com.ws.stoner.model.view.carousel;


/**
 * Created by zkf on 2017/9/14.
 */
public class ClockVO extends BlockVO {

    //格式：yyyy-MM-dd HH:mm:ss
    private String time;

    public ClockVO() {
    }

    public ClockVO(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public ClockVO setTime(String time) {
        this.time = time;
        return this;
    }
}
