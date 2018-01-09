package com.ws.stoner.constant;

/**
 * Created by zkf on 2017/6/29.
 */
public enum HostTreeTypeEnum {
    GROUP("组","group"),
    HOST("设备","host"),
    POINT("监控点","point");

    private String name;
    public String text;

    HostTreeTypeEnum(String name) {
        this.name = name;
    }

    HostTreeTypeEnum(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public HostTreeTypeEnum setText(String text) {
        this.text = text;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostTreeTypeEnum setName(String name) {
        this.name = name;
        return this;
    }
}
