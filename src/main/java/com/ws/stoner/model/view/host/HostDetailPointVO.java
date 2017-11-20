package com.ws.stoner.model.view.host;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by pc on 2017/7/13.
 */
public class HostDetailPointVO {

    @JSONField(name = "point_id")
    private String pointId;
    @JSONField(name = "point_name")
    private String name;
    private String state;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JSONField(name = "item_datas")
    private List<HostDetailPointItemVO> items;

    @Override
    public String toString() {
        return "HostDetailPointVO{" +
                "pointId='" + pointId + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", items=" + items +
                '}';
    }

    public List<HostDetailPointItemVO> getItems() {

        return items;
    }

    public HostDetailPointVO setItems(List<HostDetailPointItemVO> items) {
        this.items = items;
        return this;
    }

    public String getPointId() {
        return pointId;
    }

    public HostDetailPointVO setPointId(String pointId) {
        this.pointId = pointId;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostDetailPointVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostDetailPointVO setState(String state) {
        this.state = state;
        return this;
    }


}
