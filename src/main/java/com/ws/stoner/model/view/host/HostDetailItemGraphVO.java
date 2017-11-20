package com.ws.stoner.model.view.host;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/8/2.
 */
public class HostDetailItemGraphVO {
    @JSONField(name = "item_id")
    private String itemId;
    @JSONField(name = "item_name")
    private String itemName;
    @JSONField(name = "point_id")
    private String pointId;
    @JSONField(name = "point_name")
    private String pointName;
    @JSONField(name = "graph_name")
    private String graphName;
    @JSONField(name = "graph_type")
    private String graphType;
    @JSONField(name = "graph_value")
    private String graphValue;
    @JSONField(name = "value_type")
    private String valueType;

    public String getItemId() {
        return itemId;
    }

    public HostDetailItemGraphVO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getPointId() {
        return pointId;
    }

    public HostDetailItemGraphVO setPointId(String pointId) {
        this.pointId = pointId;
        return this;
    }

    public String getGraphName() {
        return graphName;
    }

    public HostDetailItemGraphVO setGraphName(String graphName) {
        this.graphName = graphName;
        return this;
    }

    public String getGraphType() {
        return graphType;
    }

    public HostDetailItemGraphVO setGraphType(String graphType) {
        this.graphType = graphType;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public HostDetailItemGraphVO setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public HostDetailItemGraphVO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getPointName() {
        return pointName;
    }

    public HostDetailItemGraphVO setPointName(String pointName) {
        this.pointName = pointName;
        return this;
    }

    public String getGraphValue() {
        return graphValue;
    }

    public HostDetailItemGraphVO setGraphValue(String graphValue) {
        this.graphValue = graphValue;
        return this;
    }

    @Override
    public String toString() {
        return "HostDetailItemGraphVO{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", pointId='" + pointId + '\'' +
                ", pointName='" + pointName + '\'' +
                ", graphName='" + graphName + '\'' +
                ", graphType='" + graphType + '\'' +
                ", graphValue='" + graphValue + '\'' +
                ", valueType='" + valueType + '\'' +
                '}';
    }
}
