package com.ws.stoner.model.view;


/**
 * Created by pc on 2017/7/13.
 */
public class HostDetailVO {

    private String hostId;
    private String hostName;
    private String state;
    private String type;
    private String ip;
    private String description;

    public String getHostId() {
        return hostId;
    }

    public HostDetailVO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public HostDetailVO setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostDetailVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public HostDetailVO setType(String type) {
        this.type = type;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public HostDetailVO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public HostDetailVO setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String
    toString() {
        return "HostDetailVO{" +
                "hostId='" + hostId + '\'' +
                ", hostName='" + hostName + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", ip='" + ip + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
