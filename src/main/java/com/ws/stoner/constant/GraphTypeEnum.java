package com.ws.stoner.constant;

/**
 * Created by pc on 2017/7/18.
 */
public enum GraphTypeEnum {

    LINE("折线图","line"),
    BAR("柱状图","bar"),
    AREA("面积图","area"),
    GAUGE("仪表盘","gauge");

    GraphTypeEnum(String name,String type) {
        this.name = name;
        this.type = type;
    }

    public String name;
    public String type;


}
