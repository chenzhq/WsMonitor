package com.ws.stoner.constant;

/**
 * Created by zkf on 2017/6/29.
 */
public enum OverviewTypeEnum {
    GROUP("组","group"),
    HOST("设备","host"),
    POINT("监控点","point");

    private String name;
    public String text;

    OverviewTypeEnum(String name) {
        this.name = name;
    }

    OverviewTypeEnum(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public OverviewTypeEnum setText(String text) {
        this.text = text;
        return this;
    }

    public String getName() {
        return name;
    }

    public OverviewTypeEnum setName(String name) {
        this.name = name;
        return this;
    }
}
