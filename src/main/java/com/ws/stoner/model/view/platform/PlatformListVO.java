package com.ws.stoner.model.view.platform;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zkf on 2017/8/8.
 */
public class PlatformListVO {

    @JSONField(name = "platform_id")
    private String platformId;
    @JSONField(name = "platform_name")
    private String platformName;
    private String state;
    private Float health;
    @JSONField(name = "high_num")
    private int highNum;
    @JSONField(name = "warning_num")
    private int warningNum;
    @JSONField(name = "all_num")
    private int allNum;

    public PlatformListVO(String platformId, String platformName, String state, Float health, int warningNum,  int highNum,  int allNum) {
        this.platformId = platformId;
        this.platformName = platformName;
        this.state = state;
        this.health = health;
        this.highNum = highNum;
        this.warningNum = warningNum;
        this.allNum = allNum;
    }

    public String getPlatformId() {
        return platformId;
    }

    public PlatformListVO setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public String getPlatformName() {
        return platformName;
    }

    public PlatformListVO setPlatformName(String platformName) {
        this.platformName = platformName;
        return this;
    }

    public String getState() {
        return state;
    }

    public PlatformListVO setState(String state) {
        this.state = state;
        return this;
    }

    public Float getHealth() {
        return health;
    }

    public PlatformListVO setHealth(Float health) {
        this.health = health;
        return this;
    }

    public int getHighNum() {
        return highNum;
    }

    public PlatformListVO setHighNum(int highNum) {
        this.highNum = highNum;
        return this;
    }

    public int getWarningNum() {
        return warningNum;
    }

    public PlatformListVO setWarningNum(int warningNum) {
        this.warningNum = warningNum;
        return this;
    }

    public int getAllNum() {
        return allNum;
    }

    public PlatformListVO setAllNum(int allNum) {
        this.allNum = allNum;
        return this;
    }

    @Override
    public String toString() {
        return "PlatformListVO{" +
                "platformId='" + platformId + '\'' +
                ", platformName='" + platformName + '\'' +
                ", state='" + state + '\'' +
                ", health='" + health + '\'' +
                ", highNum=" + highNum +
                ", warningNum=" + warningNum +
                ", allNum=" + allNum +
                '}';
    }
}
