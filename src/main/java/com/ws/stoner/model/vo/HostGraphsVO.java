package com.ws.stoner.model.vo;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class HostGraphsVO {

    private String id;
    private String name;
    private String type;
    private String state;
    private List<GraphVO> graphs;

    public HostGraphsVO() {
    }

    public HostGraphsVO(String id, String name, String type, String state, List<GraphVO> graphs) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.state = state;
        this.graphs = graphs;
    }

    @Override
    public String toString() {
        return "HostGraphsVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", graphs=" + graphs +
                '}';
    }

    public String getId() {

        return id;
    }

    public HostGraphsVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostGraphsVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public HostGraphsVO setType(String type) {
        this.type = type;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostGraphsVO setState(String state) {
        this.state = state;
        return this;
    }

    public List<GraphVO> getGraphs() {
        return graphs;
    }

    public HostGraphsVO setGraphs(List<GraphVO> graphs) {
        this.graphs = graphs;
        return this;
    }
}
