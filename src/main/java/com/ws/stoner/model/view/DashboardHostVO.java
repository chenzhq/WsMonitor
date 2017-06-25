package com.ws.stoner.model.view;


/**
 * Created by pc on 2017/6/19.
 */
public class DashboardHostVO {

    private String ip;
    private String hostId;
    private String name;
    private Integer allNum;
    private String state;
    private String type;
    private Integer problemNum;//主机下监控点问题数

    @Override
    public String toString() {
        return "DashboardHostVO{" +
                "ip='" + ip + '\'' +
                ", hostId='" + hostId + '\'' +
                ", name='" + name + '\'' +
                ", allNum=" + allNum +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", problemNum=" + problemNum +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public DashboardHostVO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public DashboardHostVO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getName() {
        return name;
    }

    public DashboardHostVO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public DashboardHostVO setAllNum(Integer allNum) {
        this.allNum = allNum;
        return this;
    }

    public String getState() {
        return state;
    }

    public DashboardHostVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public DashboardHostVO setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getProblemNum() {
        return problemNum;
    }

    public DashboardHostVO setProblemNum(Integer problemNum) {
        this.problemNum = problemNum;
        return this;
    }
}
