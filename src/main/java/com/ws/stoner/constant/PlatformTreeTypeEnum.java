package com.ws.stoner.constant;

/**
 * Created by pc on 2017/8/10.
 */
public enum PlatformTreeTypeEnum {

    PLATFORM("platform","业务平台"),
    CLUSTER("cluster","集群"),
    HOST("host","设备");

    PlatformTreeTypeEnum(String code,String name) {
        this.code = code;
        this.name = name;
    }
    public String name;

    public String code;

    public String getName() {
        return name;
    }


    public PlatformTreeTypeEnum setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public PlatformTreeTypeEnum setCode(String code) {
        this.code = code;
        return this;
    }
}
