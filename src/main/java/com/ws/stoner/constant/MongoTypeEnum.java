package com.ws.stoner.constant;

/**
 * Created by pc on 2017/6/29.
 */
public enum MongoTypeEnum {
    Group("组"),
    Point("监控点");

    MongoTypeEnum(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public MongoTypeEnum setName(String name) {
        this.name = name;
        return this;
    }
}
