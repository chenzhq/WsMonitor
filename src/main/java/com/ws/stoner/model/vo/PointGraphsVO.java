package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.trigger.TriggerVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class PointGraphsVO {

    private String id;
    private String name;
    private String state;

    private List<GraphVO> graphs;
    private TriggerVO trigger;

    public PointGraphsVO() {
    }

    public String getId() {
        return id;
    }

    public PointGraphsVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PointGraphsVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public PointGraphsVO setState(String state) {
        this.state = state;
        return this;
    }

    public List<GraphVO> getGraphs() {
        return graphs;
    }

    public PointGraphsVO setGraphs(List<GraphVO> graphs) {
        this.graphs = graphs;
        return this;
    }

    public TriggerVO getTrigger() {
        return trigger;
    }

    public PointGraphsVO setTrigger(TriggerVO trigger) {
        this.trigger = trigger;
        return this;
    }

    @Override
    public String toString() {
        return "PointGraphsVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", graphs=" + graphs +
                ", trigger=" + trigger +
                '}';
    }

    public PointGraphsVO(String id, String name, String state, List<GraphVO> graphs, TriggerVO trigger) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.graphs = graphs;
        this.trigger = trigger;
    }
}
