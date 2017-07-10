package com.ws.stoner.constant;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public enum StatusEnum {
    WARNING("警告", 1),
    HIGH("严重", 2),
    OK("正常", 0);

    StatusEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    StatusEnum(String name) {
        this.name = name;
    }
    private String name;
    public int code;

    public String getName() {
        return name;
    }

    public StatusEnum setName(String name) {
        this.name = name;
        return this;
    }
}
