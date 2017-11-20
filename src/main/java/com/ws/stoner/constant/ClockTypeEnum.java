package com.ws.stoner.constant;

/**
 * Created by zkf on 2017/9/14.
 */
public enum ClockTypeEnum {

    NUMBER("数字","clock-number"),
    CLOCK("时钟","clock-clock");

    public String name;
    public String type;

    ClockTypeEnum(String name,String type) {
        this.name = name;
        this.type = type;
    }
}
