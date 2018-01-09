package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.ack.AckVO;
import com.ws.stoner.model.vo.alert.AlertVO;
import com.ws.stoner.model.vo.item.ItemVO;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/3
 */
public class EventVO {

    private String id;
    private String triggerId;
    private String description;
    private String state;
    private String time;
    private String comments;
    private String priority;
    private String recoveryEventId;
    private String recoveryTime;

    private HostVO host;
    private PointVO point;
    private ItemVO item;
    private List<AlertVO> alerts;
    private List<AckVO> acks;

    public EventVO() {
    }

    @Override
    public String toString() {
        return "EventVO{" +
                "id='" + id + '\'' +
                ", triggerId='" + triggerId + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", time='" + time + '\'' +
                ", comments='" + comments + '\'' +
                ", priority='" + priority + '\'' +
                ", recoveryEventId='" + recoveryEventId + '\'' +
                ", recoveryTime='" + recoveryTime + '\'' +
                ", host=" + host +
                ", point=" + point +
                ", item=" + item +
                ", alerts=" + alerts +
                ", acks=" + acks +
                '}';
    }

    public String getId() {
        return id;
    }

    public EventVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getTriggerId() {
        return triggerId;
    }

    public EventVO setTriggerId(String triggerId) {
        this.triggerId = triggerId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getState() {
        return state;
    }

    public EventVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getTime() {
        return time;
    }

    public EventVO setTime(String time) {
        this.time = time;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public EventVO setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public EventVO setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public String getRecoveryEventId() {
        return recoveryEventId;
    }

    public EventVO setRecoveryEventId(String recoveryEventId) {
        this.recoveryEventId = recoveryEventId;
        return this;
    }

    public String getRecoveryTime() {
        return recoveryTime;
    }

    public EventVO setRecoveryTime(String recoveryTime) {
        this.recoveryTime = recoveryTime;
        return this;
    }

    public HostVO getHost() {
        return host;
    }

    public EventVO setHost(HostVO host) {
        this.host = host;
        return this;
    }

    public PointVO getPoint() {
        return point;
    }

    public EventVO setPoint(PointVO point) {
        this.point = point;
        return this;
    }

    public ItemVO getItem() {
        return item;
    }

    public EventVO setItem(ItemVO item) {
        this.item = item;
        return this;
    }

    public List<AlertVO> getAlerts() {
        return alerts;
    }

    public EventVO setAlerts(List<AlertVO> alerts) {
        this.alerts = alerts;
        return this;
    }

    public List<AckVO> getAcks() {
        return acks;
    }

    public EventVO setAcks(List<AckVO> acks) {
        this.acks = acks;
        return this;
    }
}
