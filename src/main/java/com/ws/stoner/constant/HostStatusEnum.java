package com.ws.stoner.constant;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public enum HostStatusEnum {
    WARNING("警告"),
    DANGER("危险"),
    MAINTENANCE("维护"),
    DISABLE("停用"),
    UNSUPPORT("不支持"),
    UNKNOWN("未知"),
    OK("正常");

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
