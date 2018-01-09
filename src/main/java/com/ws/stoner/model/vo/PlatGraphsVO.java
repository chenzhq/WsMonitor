package com.ws.stoner.model.vo;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class PlatGraphsVO {
    private String id;
    private String name;
    private String state;
    private Float health;
    private List<GraphVO> graphs;

    public PlatGraphsVO() {
    }

    public PlatGraphsVO(String id, String name, String state, Float health, List<GraphVO> graphs) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.health = health;
        this.graphs = graphs;
    }

    @Override
    public String toString() {
        return "PlatGraphsVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", health=" + health +
                ", graphs=" + graphs +
                '}';
    }

    public String getId() {
        return id;
    }

    public PlatGraphsVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlatGraphsVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public PlatGraphsVO setState(String state) {
        this.state = state;
        return this;
    }

    public Float getHealth() {
        return health;
    }

    public PlatGraphsVO setHealth(Float health) {
        this.health = health;
        return this;
    }

    public List<GraphVO> getGraphs() {
        return graphs;
    }

    public PlatGraphsVO setGraphs(List<GraphVO> graphs) {
        this.graphs = graphs;
        return this;
    }
}
