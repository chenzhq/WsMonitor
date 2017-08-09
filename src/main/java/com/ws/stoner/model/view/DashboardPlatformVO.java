package com.ws.stoner.model.view;

/**
 * Created by pc on 2017/6/19.
 */
public class DashboardPlatformVO {

    private String platformId;
    private Float availability;
    private String name;
    private Integer warningNum; //警告主机数量
    private Integer highNum; //严重主机数量
    private String state;
    private Integer allNum;

    public String getPlatformId() {
        return platformId;
    }

    public DashboardPlatformVO setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public Float getAvailability() {
        return availability;
    }

    public DashboardPlatformVO setAvailability(Float availability) {
        this.availability = availability;
        return this;
    }

    public String getName() {
        return name;
    }

    public DashboardPlatformVO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "DashboardPlatformVO{" +
                "platformId='" + platformId + '\'' +
                ", availability=" + availability +
                ", name='" + name + '\'' +
                ", warningNum=" + warningNum +
                ", hightNum=" + highNum +
                ", state='" + state + '\'' +
                ", allNum=" + allNum +
                '}';
    }

    public Integer getWarningNum() {
        return warningNum;
    }

    public DashboardPlatformVO setWarningNum(Integer warningNum) {
        this.warningNum = warningNum;
        return this;
    }

    public Integer getHighNum() {
        return highNum;
    }

    public DashboardPlatformVO setHighNum(Integer highNum) {
        this.highNum = highNum;
        return this;
    }

    public String getState() {
        return state;
    }

    public DashboardPlatformVO setState(String state) {
        this.state = state;
        return this;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public DashboardPlatformVO setAllNum(Integer allNum) {
        this.allNum = allNum;
        return this;
    }
}
