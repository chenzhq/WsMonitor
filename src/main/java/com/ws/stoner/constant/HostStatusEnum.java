package com.ws.stoner.constant;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public enum HostStatusEnum {
    PROBLEM("问题"),
    MAINTENANCE("维护"),
    UNKNOWN("未知"),
    NORMAL("正常");

    HostStatusEnum(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public HostStatusEnum setName(String name) {
        this.name = name;
        return this;
    }
}
