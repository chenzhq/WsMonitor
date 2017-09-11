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

    static public String getName(String type) {
        String name ;
        if("line".equals(type)) {
            name = "折线图";
        }else if("bar".equals(type)) {
            name = "柱状图";
        }else if("area".equals(type)) {
            name = "面积图";
        }else if("gauge".equals(type)) {
            name = "仪表盘";
        }else {
            name = null;
        }
        return name;
    }

    static public String getType(String name) {
        String type ;
        if("折线图".equals(name)) {
            type = "line";
        }else if("柱状图".equals(name)) {
            type = "bar";
        }else if("面积图".equals(name)) {
            type = "area";
        }else if("仪表盘".equals(name)) {
            type = "gauge";
        }else {
            type = null;
        }
        return type;
    }


}
