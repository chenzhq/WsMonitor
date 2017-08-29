package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zkf on 2017/8/28.
 */
public class ProblemDetailVO {

    @JSONField(name = "trigger_id")
    private String triggerId;
    @JSONField(name = "host_name")
    private String hostName;
    @JSONField(name = "point_name")
    private String pointName;
    @JSONField(name = "item_name")
    private String itemName;

    private String threshold;
    @JSONField(name = "trigger_name")
    private String triggerName;
    private String level;
    private Integer state;

    public ProblemDetailVO() {
    }

    public ProblemDetailVO(String triggerId, String hostName, String pointName, String itemName, String threshold, String triggerName, String level, Integer state) {
        this.triggerId = triggerId;
        this.hostName = hostName;
        this.pointName = pointName;
        this.itemName = itemName;
        this.threshold = threshold;
        this.triggerName = triggerName;
        this.level = level;
        this.state = state;
    }

    public String getTriggerId() {
        return triggerId;
    }

    public ProblemDetailVO setTriggerId(String triggerId) {
        this.triggerId = triggerId;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public ProblemDetailVO setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public String getPointName() {
        return pointName;
    }

    public ProblemDetailVO setPointName(String pointName) {
        this.pointName = pointName;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public ProblemDetailVO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getThreshold() {
        return threshold;
    }

    public ProblemDetailVO setThreshold(String threshold) {
        this.threshold = threshold;
        return this;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public ProblemDetailVO setTriggerName(String triggerName) {
        this.triggerName = triggerName;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public ProblemDetailVO setLevel(String level) {
        this.level = level;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public ProblemDetailVO setState(Integer state) {
        this.state = state;
        return this;
    }

    @Override
    public String toString() {
        return "ProblemDetailVO{" +
                "triggerId='" + triggerId + '\'' +
                ", hostName='" + hostName + '\'' +
                ", pointName='" + pointName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", threshold='" + threshold + '\'' +
                ", triggerName='" + triggerName + '\'' +
                ", level='" + level + '\'' +
                ", state=" + state +
                '}';
    }
}
