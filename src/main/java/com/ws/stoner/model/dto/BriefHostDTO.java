package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by chenzheqi on 2017/6/13.
 */
public class BriefHostDTO {
    private String ip;
    @JSONField(name = "hostid")
    private String hostId;
    private String host;
    private String name;
    private String state;
    private String type;
    private Integer problemNum;//主机下监控点问题数


    public static final String[] PROPERTY_NAMES = {"hostid", "host","name"};

    public String getIp() {
        return ip;
    }

    public BriefHostDTO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public BriefHostDTO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getHost() {
        return host;
    }

    public BriefHostDTO setHost(String host) {
        this.host = host;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefHostDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public BriefHostDTO setState(String state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public BriefHostDTO setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getProblemNum() {
        return problemNum;
    }

    public BriefHostDTO setProblemNum(Integer problemNum) {
        this.problemNum = problemNum;
        return this;
    }

    public static String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }


    @Override
    public String toString() {
        return "BriefHostDTO{" +
                "ip='" + ip + '\'' +
                ", hostId='" + hostId + '\'' +
                ", host='" + host + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", problemNum=" + problemNum +
                '}';
    }
}
