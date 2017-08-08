package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Arrays;

/**
 * Created by pc on 2017/8/8.
 */
public class PlatformBlockVO {
    @JSONField(name = "platform_id")
    private String platformId;
    @JSONField(name = "platform_name")
    private String platformName;
    private Float health;
    private String[] types;
    private Integer[][] datas;

    public String getPlatformId() {
        return platformId;
    }

    public PlatformBlockVO setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public String getPlatformName() {
        return platformName;
    }

    public PlatformBlockVO setPlatformName(String platformName) {
        this.platformName = platformName;
        return this;
    }

    public Float getHealth() {
        return health;
    }

    public PlatformBlockVO setHealth(Float health) {
        this.health = health;
        return this;
    }

    public String[] getTypes() {
        return types;
    }

    public PlatformBlockVO setTypes(String[] types) {
        this.types = types;
        return this;
    }

    public Integer[][] getDatas() {
        return datas;
    }

    public PlatformBlockVO setDatas(Integer[][] datas) {
        this.datas = datas;
        return this;
    }

    @Override
    public String toString() {
        return "PlatformBlockVO{" +
                "platformId='" + platformId + '\'' +
                ", platformName='" + platformName + '\'' +
                ", health=" + health +
                ", types=" + Arrays.toString(types) +
                ", datas=" + Arrays.deepToString(datas) +
                '}';
    }
}
