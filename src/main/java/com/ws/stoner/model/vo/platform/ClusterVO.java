package com.ws.stoner.model.vo.platform;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class ClusterVO {
    private String id;
    private String name;
    private String state;
    private String type;
    private List<String> children;

    public ClusterVO() {
    }

    public ClusterVO(String id, String name, String state, String type, List<String> children) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.type = type;
        this.children = children;
    }

    @Override
    public String toString() {
        return "ClusterVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", children=" + children +
                '}';
    }

    public String getId() {
        return id;
    }

    public ClusterVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClusterVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public ClusterVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public ClusterVO setType(String type) {
        this.type = type;
        return this;
    }

    public List<String> getChildren() {
        return children;
    }

    public ClusterVO setChildren(List<String> children) {
        this.children = children;
        return this;
    }
}
