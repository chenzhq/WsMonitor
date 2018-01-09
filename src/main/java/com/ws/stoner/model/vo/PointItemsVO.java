package com.ws.stoner.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.stoner.model.vo.item.ItemVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class PointItemsVO {

    private String id;
    private String name;
    private String state;
    @JSONField(name = "details")
    private List<ItemVO> items;

    public PointItemsVO() {
    }

    public PointItemsVO(String id, String name, String state, List<ItemVO> items) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.items = items;
    }

    @Override
    public String toString() {
        return "PointItemsVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", items=" + items +
                '}';
    }

    public String getId() {

        return id;
    }

    public PointItemsVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PointItemsVO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ItemVO> getItems() {
        return items;
    }

    public PointItemsVO setItems(List<ItemVO> items) {
        this.items = items;
        return this;
    }

    public String getState() {
        return state;
    }

    public PointItemsVO setState(String state) {
        this.state = state;
        return this;
    }
}
