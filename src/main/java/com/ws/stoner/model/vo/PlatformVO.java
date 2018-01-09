package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.stat.StateVO;

/**
 * Created by zhongkf on 2017/12/7
 */
public class PlatformVO {

    private String id;
    private String name;
    private Float health;
    private String state;
    private StateVO stat;

    public PlatformVO() {
    }

    public PlatformVO(String id, String name, Float health, String state, StateVO stat) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.state = state;
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "PlatformVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", state='" + state + '\'' +
                ", stat=" + stat +
                '}';
    }

    public String getId() {
        return id;
    }

    public PlatformVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlatformVO setName(String name) {
        this.name = name;
        return this;
    }

    public Float getHealth() {
        return health;
    }

    public PlatformVO setHealth(Float health) {
        this.health = health;
        return this;
    }

    public String getState() {
        return state;
    }

    public PlatformVO setState(String state) {
        this.state = state;
        return this;
    }

    public StateVO getStat() {
        return stat;
    }

    public PlatformVO setStat(StateVO stat) {
        this.stat = stat;
        return this;
    }
}
