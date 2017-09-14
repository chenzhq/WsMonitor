package com.ws.stoner.constant;

/**
 * Created by zkf on 2017/9/11.
 */
public enum ViewTypeEnum {

    STATEPIE("状态统计","statepie"),
    PROBLEMS("问题视图","problems"),
    APPLETREE("苹果树","appletree");

    public String name;
    public String type;

    ViewTypeEnum(String name,String type) {
        this.name = name;
        this.type = type;
    }

}
