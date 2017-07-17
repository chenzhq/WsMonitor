package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by zkf on 2017/7/12.
 */
public class HostDetailItemVO {

    @JSONField(name = "item_id")
    private String itemId;
    @JSONField(name = "item_name")
    private String itemName;
    @JSONField(name = "data_time")
    private String[] dataTime;
    private Float[] data;
    @JSONField(name = "graph_name")
    private String graphName;
    @JSONField(name = "graph_type")
    private String graphType;
    private String state;
    private boolean flag;
    @JSONField(name = "value_type")
    private Integer valueType;

    @Override
    public String toString() {
        return "HostDetailItemVO{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", dataTime=" + Arrays.toString(dataTime) +
                ", data=" + Arrays.toString(data) +
                ", graphName='" + graphName + '\'' +
                ", graphType='" + graphType + '\'' +
                ", state='" + state + '\'' +
                ", flag=" + flag +
                ", valueType=" + valueType +
                '}';
    }

    public Integer getValueType() {
        return valueType;
    }

    public HostDetailItemVO setValueType(Integer valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public HostDetailItemVO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public HostDetailItemVO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String[] getDataTime() {
        return dataTime;
    }

    public HostDetailItemVO setDataTime(String[] dataTime) {
        this.dataTime = dataTime;
        return this;
    }

    public Float[] getData() {
        return data;
    }

    public HostDetailItemVO setData(Float[] data) {
        this.data = data;
        return this;
    }

    public String getGraphName() {
        return graphName;
    }

    public HostDetailItemVO setGraphName(String graphName) {
        this.graphName = graphName;
        return this;
    }

    public String getGraphType() {
        return graphType;
    }

    public HostDetailItemVO setGraphType(String graphType) {
        this.graphType = graphType;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostDetailItemVO setState(String state) {
        this.state = state;
        return this;
    }

    public boolean isFlag() {
        return flag;
    }

    public HostDetailItemVO setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }
}
