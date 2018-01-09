package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.stat.StateVO;

/**
 * Created by zhongkf on 2017/12/7
 */
public class HostVO {
    private String id;
    private String name;
    private String type;
    private String state;
    private StateVO stat;

    public HostVO() {
    }

    public HostVO(String id, String name, String type, String state, StateVO stat) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.state = state;
        this.stat = stat;
    }

    public String getId() {
        return id;
    }

    public HostVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public HostVO setType(String type) {
        this.type = type;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostVO setState(String state) {
        this.state = state;
        return this;
    }

    public StateVO getStat() {
        return stat;
    }

    public HostVO setStat(StateVO stat) {
        this.stat = stat;
        return this;
    }

    @Override
    public String toString() {
        return "HostVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", stat=" + stat +
                '}';
    }
}
