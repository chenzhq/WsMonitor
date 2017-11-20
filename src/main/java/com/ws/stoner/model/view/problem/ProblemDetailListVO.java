package com.ws.stoner.model.view.problem;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.stoner.utils.BaseUtils;
import java.time.LocalDateTime;

/**
 * Created by zkf on 2017/8/28.
 */
public class ProblemDetailListVO {

    @JSONField(name = "event_id")
    private String eventId;
    @JSONField(name = "recovery_eventid",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String recoveryEventid;
    @JSONField(name = "begin_time")
    private String beginTime;
    @JSONField(name = "end_time",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String endTime;
    private String status;
    @JSONField(name = "duration_string")
    private String durationString;
    private String acknowledged;
    @JSONField(name = "alert_state")
    private String alertState;
    @JSONField(name = "alert_num")
    private Integer alertNum;

    @Override
    public String toString() {
        return "ProblemDetailListVO{" +
                "eventId='" + eventId + '\'' +
                ", recoveryEventid='" + recoveryEventid + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                ", durationString='" + durationString + '\'' +
                ", acknowledged='" + acknowledged + '\'' +
                ", alertState='" + alertState + '\'' +
                ", alertNum=" + alertNum +
                '}';
    }

    public String getEventId() {
        return eventId;
    }

    public ProblemDetailListVO setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getRecoveryEventid() {
        return recoveryEventid;
    }

    public ProblemDetailListVO setRecoveryEventid(String recoveryEventid) {
        this.recoveryEventid = recoveryEventid;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public ProblemDetailListVO setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public ProblemDetailListVO setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ProblemDetailListVO setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDurationString() {
        return durationString;
    }

    public ProblemDetailListVO setDurationString(LocalDateTime beginTime, LocalDateTime endTime) {
        this.durationString = BaseUtils.getDurationStringByTime(beginTime, endTime);
        return this;
    }

    public String getAcknowledged() {
        return acknowledged;
    }

    public ProblemDetailListVO setAcknowledged(String acknowledged) {
        this.acknowledged = acknowledged;
        return this;
    }

    public String getAlertState() {
        return alertState;
    }

    public ProblemDetailListVO setAlertState(String alertState) {
        this.alertState = alertState;
        return this;
    }

    public Integer getAlertNum() {
        return alertNum;
    }

    public ProblemDetailListVO setAlertNum(Integer alertNum) {
        this.alertNum = alertNum;
        return this;
    }
}
