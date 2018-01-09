package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.stat.StateVO;

/**
 * Created by zhongkf on 2017/12/8
 */
public class PointVO {
    private String id;
    private String name;
    private String state;
    private HostVO host;
    private StateVO stat;

    public PointVO(String id, String name, String state, HostVO host, StateVO stat) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.host = host;
        this.stat = stat;
    }

    public PointVO() {

    }

    @Override
    public String toString() {
        return "PointVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", host=" + host +
                ", stat=" + stat +
                '}';
    }

    public String getId() {
        return id;
    }

    public PointVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PointVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public PointVO setState(String state) {
        this.state = state;
        return this;
    }

    public HostVO getHost() {
        return host;
    }

    public PointVO setHost(HostVO host) {
        this.host = host;
        return this;
    }

    public StateVO getStat() {
        return stat;
    }

    public PointVO setStat(StateVO stat) {
        this.stat = stat;
        return this;
    }
}
