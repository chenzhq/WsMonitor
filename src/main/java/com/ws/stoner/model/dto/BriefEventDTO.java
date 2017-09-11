package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by pc on 2017/8/22.
 */
public class BriefEventDTO {

    @JSONField(name = "eventid")
    private String eventId;
    private Integer source;
    private Integer object;
    @JSONField(name = "objectid")
    private String objectId;
    private Integer acknowledged;
    private LocalDateTime clock;
    private Integer value;
    @JSONField(name = "r_eventid")
    private String rEventid;
    @JSONField(name = "userid")
    private String userId;

    private BriefTriggerDTO relatedObject;

    private List<BriefHostDTO> hosts;
    private List<BriefAcknowledgeDTO> acknowledges ;
    private List<BriefAlertDTO> alerts;

    public static final String[] PROPERTY_NAMES = {"eventid", "source","object","objectid","acknowledged","clock","value","r_eventid","userid"};

    public String getEventId() {
        return eventId;
    }

    public BriefEventDTO setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public Integer getSource() {
        return source;
    }

    public BriefEventDTO setSource(Integer source) {
        this.source = source;
        return this;
    }

    public Integer getObject() {
        return object;
    }

    public BriefEventDTO setObject(Integer object) {
        this.object = object;
        return this;
    }

    public String getObjectId() {
        return objectId;
    }

    public BriefEventDTO setObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public Integer getAcknowledged() {
        return acknowledged;
    }

    public BriefEventDTO setAcknowledged(Integer acknowledged) {
        this.acknowledged = acknowledged;
        return this;
    }

    public LocalDateTime getClock() {
        return clock;
    }

    public BriefEventDTO setClock(int time) {
        Instant instant = Instant.ofEpochSecond(time);
        this.clock = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public BriefEventDTO setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getrEventid() {
        return rEventid;
    }

    public BriefEventDTO setrEventid(String rEventid) {
        this.rEventid = rEventid;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public BriefEventDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public List<BriefAcknowledgeDTO> getAcknowledges() {
        return acknowledges;
    }

    public BriefEventDTO setAcknowledges(List<BriefAcknowledgeDTO> acknowledges) {
        this.acknowledges = acknowledges;
        return this;
    }

    public List<BriefHostDTO> getHosts() {
        return hosts;
    }

    public BriefEventDTO setHosts(List<BriefHostDTO> hosts) {
        this.hosts = hosts;
        return this;
    }

    public BriefTriggerDTO getRelatedObject() {
        return relatedObject;
    }

    public BriefEventDTO setRelatedObject(BriefTriggerDTO relatedObject) {
        this.relatedObject = relatedObject;
        return this;
    }

    public List<BriefAlertDTO> getAlerts() {
        return alerts;
    }

    public BriefEventDTO setAlerts(List<BriefAlertDTO> alerts) {
        this.alerts = alerts;
        return this;
    }

    @Override
    public String toString() {
        return "BriefEventDTO{" +
                "eventId='" + eventId + '\'' +
                ", source=" + source +
                ", object=" + object +
                ", objectId='" + objectId + '\'' +
                ", acknowledged=" + acknowledged +
                ", clock=" + clock +
                ", value=" + value +
                ", rEventid='" + rEventid + '\'' +
                ", userId='" + userId + '\'' +
                ", relatedObject=" + relatedObject +
                ", hosts=" + hosts +
                ", acknowledges=" + acknowledges +
                ", alerts=" + alerts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BriefEventDTO eventDTO = (BriefEventDTO) o;

        if (eventId != null ? !eventId.equals(eventDTO.eventId) : eventDTO.eventId != null) return false;
        if (source != null ? !source.equals(eventDTO.source) : eventDTO.source != null) return false;
        if (object != null ? !object.equals(eventDTO.object) : eventDTO.object != null) return false;
        if (objectId != null ? !objectId.equals(eventDTO.objectId) : eventDTO.objectId != null) return false;
        if (acknowledged != null ? !acknowledged.equals(eventDTO.acknowledged) : eventDTO.acknowledged != null)
            return false;
        if (clock != null ? !clock.equals(eventDTO.clock) : eventDTO.clock != null) return false;
        if (value != null ? !value.equals(eventDTO.value) : eventDTO.value != null) return false;
        if (rEventid != null ? !rEventid.equals(eventDTO.rEventid) : eventDTO.rEventid != null) return false;
        if (userId != null ? !userId.equals(eventDTO.userId) : eventDTO.userId != null) return false;
        if (relatedObject != null ? !relatedObject.equals(eventDTO.relatedObject) : eventDTO.relatedObject != null)
            return false;
        if (hosts != null ? !hosts.equals(eventDTO.hosts) : eventDTO.hosts != null) return false;
        if (acknowledges != null ? !acknowledges.equals(eventDTO.acknowledges) : eventDTO.acknowledges != null)
            return false;
        return alerts != null ? alerts.equals(eventDTO.alerts) : eventDTO.alerts == null;
    }

    @Override
    public int hashCode() {
        int result = eventId != null ? eventId.hashCode() : 0;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (object != null ? object.hashCode() : 0);
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + (acknowledged != null ? acknowledged.hashCode() : 0);
        result = 31 * result + (clock != null ? clock.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (rEventid != null ? rEventid.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (relatedObject != null ? relatedObject.hashCode() : 0);
        result = 31 * result + (hosts != null ? hosts.hashCode() : 0);
        result = 31 * result + (acknowledges != null ? acknowledges.hashCode() : 0);
        result = 31 * result + (alerts != null ? alerts.hashCode() : 0);
        return result;
    }
}
