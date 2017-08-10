package com.ws.stoner.constant;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public enum StatusEnum {
    WARNING("警告", 1,"#F7B824"),
    HIGH("严重", 2,"#DB2828"),
    OK("正常", 0,"#5FB878");

    StatusEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    StatusEnum(String name, int code, String color) {
        this.name = name;
        this.code = code;
        this.color = color;
    }

    StatusEnum(String name) {
        this.name = name;
    }
    public String name;
    public int code;
    public String color;

    public String getName() {
        return name;
    }

    public StatusEnum setName(String name) {
        this.name = name;
        return this;
    }
}
