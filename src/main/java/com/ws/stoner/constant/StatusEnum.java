package com.ws.stoner.constant;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public enum StatusEnum {
    WARNING("警告"),
    HIGHT("严重"),
    OK("正常");

    StatusEnum(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public StatusEnum setName(String name) {
        this.name = name;
        return this;
    }
}
