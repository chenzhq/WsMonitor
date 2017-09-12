package com.ws.stoner.model.view.platform;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Arrays;

/**
 * Created by zkf on 2017/8/11.
 */
public class PlatformGraphVO {
    @JSONField(name = "platform_id")
    private String platformId;
    @JSONField(name = "host_id")
    private String hostId;
    @JSONField(name = "item_id")
    private String itemId;
    @JSONField(name = "item_name")
    private String itemName;
    @JSONField(name = "graph_name")
    private String graphName;
    @JSONField(name = "graph_type")
    private String graphType;
    private String state;
    @JSONField(name = "data_time")
    private String[] dataTime;
    private Float[] datas;
    private String units;

    public PlatformGraphVO(String platformId, String hostId, String itemId, String itemName, String graphName, String graphType, String state, String[] dataTime, Float[] datas, String units) {
        this.platformId = platformId;
        this.hostId = hostId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.graphName = graphName;
        this.graphType = graphType;
        this.state = state;
        this.dataTime = dataTime;
        this.datas = datas;
        this.units = units;
    }

    public PlatformGraphVO() {

    }

    @Override
    public String toString() {
        return "PlatformGraphVO{" +
                "platformId='" + platformId + '\'' +
                ", hostId='" + hostId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", graphName='" + graphName + '\'' +
                ", graphType='" + graphType + '\'' +
                ", state='" + state + '\'' +
                ", dataTime=" + Arrays.toString(dataTime) +
                ", datas=" + Arrays.toString(datas) +
                ", units='" + units + '\'' +
                '}';
    }

    public String getPlatformId() {
        return platformId;
    }

    public PlatformGraphVO setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public PlatformGraphVO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public PlatformGraphVO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public PlatformGraphVO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getGraphName() {
        return graphName;
    }

    public PlatformGraphVO setGraphName(String graphName) {
        this.graphName = graphName;
        return this;
    }

    public String getGraphType() {
        return graphType;
    }

    public PlatformGraphVO setGraphType(String graphType) {
        this.graphType = graphType;
        return this;
    }

    public String getState() {
        return state;
    }

    public PlatformGraphVO setState(String state) {
        this.state = state;
        return this;
    }

    public String[] getDataTime() {
        return dataTime;
    }

    public PlatformGraphVO setDataTime(String[] dataTime) {
        this.dataTime = dataTime;
        return this;
    }

    public Float[] getDatas() {
        return datas;
    }

    public PlatformGraphVO setDatas(Float[] datas) {
        this.datas = datas;
        return this;
    }

    public String getUnits() {
        return units;
    }

    public PlatformGraphVO setUnits(String units) {
        this.units = units;
        return this;
    }
}
