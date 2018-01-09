package com.ws.stoner.model.vo.alert;

/**
 * Created by zkf on 2017/9/15.
 */
//告警简约对象
public class AlertStatVO {

    private String eventId;
    private String alertState;
    private Integer alertNum;

    public String getEventId() {
        return eventId;
    }

    public AlertStatVO setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getAlertState() {
        return alertState;
    }


    public AlertStatVO setAlertState(String alertState) {
        this.alertState = alertState;
        return this;
    }

    public Integer getAlertNum() {
        return alertNum;
    }

    public AlertStatVO setAlertNum(Integer alertNum) {
        this.alertNum = alertNum;
        return this;
    }


}
