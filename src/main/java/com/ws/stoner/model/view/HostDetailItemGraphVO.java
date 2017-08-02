package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/8/2.
 */
public class HostDetailItemGraphVO {
    @JSONField(name = "item_id")
    private String itemId;
    @JSONField(name = "point_id")
    private String pointId;
    @JSONField(name = "graph_name")
    private String graphName;
    @JSONField(name = "graph_type")
    private String graphType;
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

    @Override
    public String toString() {
        return "HostDetailItemGraphVO{" +
                "itemId='" + itemId + '\'' +
                ", pointId='" + pointId + '\'' +
                ", graphName='" + graphName + '\'' +
                ", graphType='" + graphType + '\'' +
                ", valueType='" + valueType + '\'' +
                '}';
    }
}
