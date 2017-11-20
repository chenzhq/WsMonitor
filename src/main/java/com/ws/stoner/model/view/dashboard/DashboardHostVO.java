package com.ws.stoner.model.view.dashboard;


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
    private Integer warningNum;//主机下监控点警告数
    private Integer highNum;//严重数


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

    public Integer getWarningNum() {
        return warningNum;
    }

    public DashboardHostVO setWarningNum(Integer warningNum) {
        this.warningNum = warningNum;
        return this;
    }

    public Integer getHighNum() {
        return highNum;
    }

    public DashboardHostVO setHighNum(Integer highNum) {
        this.highNum = highNum;
        return this;
    }

    @Override
    public String toString() {
        return "DashboardHostVO{" +
                "ip='" + ip + '\'' +
                ", hostId='" + hostId + '\'' +
                ", name='" + name + '\'' +
                ", allNum=" + allNum +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", warningNum=" + warningNum +
                ", hightNum=" + highNum +
                '}';
    }
}
