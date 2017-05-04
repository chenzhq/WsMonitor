package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Date 2017/4/5
 * @Author chen
 */
public class MediaDO {
    @JSONField(name = "mediaid")
    private String mediaId;
    private int active;
    @JSONField(name = "mediatypeid")
    private String mediaTypeId;
    private String period;
    @JSONField(name = "sendto ")
    private String sendTo;
    private int severity;
    @JSONField(name = "userid")
    private String userId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getMediaTypeId() {
        return mediaTypeId;
    }

    public void setMediaTypeId(String mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
