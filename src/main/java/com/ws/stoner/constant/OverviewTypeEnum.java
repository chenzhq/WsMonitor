package com.ws.stoner.constant;

/**
 * Created by pc on 2017/6/29.
 */
public enum OverviewTypeEnum {
    GROUP("组"),
    POINT("监控点");

    OverviewTypeEnum(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public OverviewTypeEnum setName(String name) {
        this.name = name;
        return this;
    }
}
