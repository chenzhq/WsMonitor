package com.ws.stoner.constant;

/**
 * Created by zkf on 2017/9/14.
 */
public enum ChartTypeEnum {
    TABLE("表格","table"),
    CLOCK("钟表","clock");

    public String name;
    public String type;

    ChartTypeEnum(String name,String type) {
        this.name = name;
        this.type = type;
    }
}
