package com.ws.stoner.constant;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public enum StatusEnum {
    WARNING("警告", 1,"yellow","warning"),
    HIGH("严重", 2,"red","high"),
    OK("正常", 0,"green","ok"),
    INFO("信息",3,"gray","info");

    public String name;
    public int code;
    public String color;
    public String text;

    StatusEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    StatusEnum(String name, int code, String color) {
        this.name = name;
        this.code = code;
        this.color = color;
    }

    StatusEnum(String name, int code, String color, String text) {
        this.name = name;
        this.code = code;
        this.color = color;
        this.text = text;
    }

    StatusEnum(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public StatusEnum setName(String name) {
        this.name = name;
        return this;
    }
}
