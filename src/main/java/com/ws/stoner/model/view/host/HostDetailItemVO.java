package com.ws.stoner.model.view.host;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

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
    private String units;
    @JSONField(name = "graph_name")
    private String graphName;
    @JSONField(name = "graph_type")
    private String graphType;
    private String state;
    private boolean flag;
    @JSONField(name = "value_type")
    private String valueType;
    @JSONField(name = "warning_point",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String warningPoint;
    @JSONField(name = "high_point" ,serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String highPoint;
    @JSONField(name = "with_triggers")
    private boolean withTriggers;

    @Override
    public String toString() {
        return "HostDetailItemVO{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", dataTime=" + Arrays.toString(dataTime) +
                ", data=" + Arrays.toString(data) +
                ", units='" + units + '\'' +
                ", graphName='" + graphName + '\'' +
                ", graphType='" + graphType + '\'' +
                ", state='" + state + '\'' +
                ", flag=" + flag +
                ", valueType=" + valueType +
                ", warningPoint='" + warningPoint + '\'' +
                ", highPoint='" + highPoint + '\'' +
                ", withTriggers=" + withTriggers +
                '}';
    }

    public String getValueType() {
        return valueType;
    }

    public HostDetailItemVO setValueType(String valueType) {
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

    public String getUnits() {
        return units;
    }

    public HostDetailItemVO setUnits(String units) {
        this.units = units;
        return this;
    }

    public String getWarningPoint() {
        return warningPoint;
    }

    public HostDetailItemVO setWarningPoint(String warningPoint) {
        this.warningPoint = warningPoint;
        return this;
    }

    public String getHighPoint() {
        return highPoint;
    }

    public HostDetailItemVO setHighPoint(String highPoint) {
        this.highPoint = highPoint;
        return this;
    }

    public boolean isWithTriggers() {
        return withTriggers;
    }

    public HostDetailItemVO setWithTriggers(boolean withTriggers) {
        this.withTriggers = withTriggers;
        return this;
    }
}