package com.ws.stoner.constant;

/**
 * Created by pc on 2017/8/10.
 */
public enum PlatformTreeTypeEnum {

    PLATFORM("业务平台"),
    CLUSTER("集群"),
    HOST("设备");

    PlatformTreeTypeEnum(String name) {
        this.name = name;
    }
    public String name;

    public String getName() {
        return name;
    }

    public PlatformTreeTypeEnum setName(String name) {
        this.name = name;
        return this;
    }
}
