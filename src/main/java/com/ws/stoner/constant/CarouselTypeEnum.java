package com.ws.stoner.constant;

/**
 * Created by zkf on 2017/9/14.
 */
public enum CarouselTypeEnum {

    VIEW("视图","view"),
    GRAPH("监控项图表","graph"),
    CHART("控件","chart");

    public String name;
    public String type;

    CarouselTypeEnum(String name,String type) {
        this.name = name;
        this.type = type;
    }
}
