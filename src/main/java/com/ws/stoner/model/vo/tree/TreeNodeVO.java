package com.ws.stoner.model.vo.tree;

/**
 * Created by zhongkf on 2017/12/8
 */
public class TreeNodeVO {
    private String id;
    private String name;
    private String type;
    private String state;
    private String parent;

    public TreeNodeVO() {
    }

    @Override
    public String toString() {
        return "HostNodeVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", parent='" + parent + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public TreeNodeVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TreeNodeVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public TreeNodeVO setType(String type) {
        this.type = type;
        return this;
    }

    public String getState() {
        return state;
    }

    public TreeNodeVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getParent() {
        return parent;
    }

    public TreeNodeVO setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public TreeNodeVO(String id, String name, String type, String state, String parent) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.state = state;
        this.parent = parent;
    }
}
