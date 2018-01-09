package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.item.ItemVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/18
 */
public class HostItemsVO {

    private String id;
    private String name;
    private String state;
    private String type;
    private List<ItemVO> details;

    public String getId() {
        return id;
    }

    public HostItemsVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostItemsVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostItemsVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public HostItemsVO setType(String type) {
        this.type = type;
        return this;
    }

    public List<ItemVO> getDetails() {
        return details;
    }

    public HostItemsVO setDetails(List<ItemVO> details) {
        this.details = details;
        return this;
    }

    @Override
    public String toString() {
        return "HostItemsVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", details=" + details +
                '}';
    }

    public HostItemsVO(String id, String name, String state, String type, List<ItemVO> details) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.type = type;
        this.details = details;
    }

    public HostItemsVO() {

    }
}
