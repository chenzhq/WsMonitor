package com.ws.stoner.model.view;

/**
 * Created by pc on 2017/6/19.
 */
public class DashboardPlatformVO {

    private String platformId;
    private Integer availability;
    private String name;
    private Integer problemNum; //问题主机数量
    private String state;
    private Integer allNum;

    @Override
    public String toString() {
        return "DashboardPlatformVO{" +
                "platformId='" + platformId + '\'' +
                ", availability=" + availability +
                ", name='" + name + '\'' +
                ", problemNum=" + problemNum +
                ", state='" + state + '\'' +
                ", allNum=" + allNum +
                '}';
    }

    public String getPlatformId() {
        return platformId;
    }

    public DashboardPlatformVO setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public Integer getAvailability() {
        return availability;
    }

    public DashboardPlatformVO setAvailability(Integer availability) {
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

    public Integer getProblemNum() {
        return problemNum;
    }

    public DashboardPlatformVO setProblemNum(Integer problemNum) {
        this.problemNum = problemNum;
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
