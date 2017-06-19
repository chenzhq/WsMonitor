package com.ws.stoner.model.view;

/**
 * Created by zkf on 2017/6/19.
 */
public class DashboardPointVO {

    private String pointId;
    private String name;
    private String state;
    private String hostId;
    private String hostName;

    @Override
    public String toString() {
        return "DashboardPointVO{" +
                "pointId='" + pointId + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", hostId='" + hostId + '\'' +
                ", hostName='" + hostName + '\'' +
                '}';
    }

    public String getPointId() {
        return pointId;
    }

    public DashboardPointVO setPointId(String pointId) {
        this.pointId = pointId;
        return this;
    }

    public String getName() {
        return name;
    }

    public DashboardPointVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public DashboardPointVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public DashboardPointVO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public DashboardPointVO setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }
}
