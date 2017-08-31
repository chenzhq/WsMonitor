package com.ws.stoner.model.query;

/**
 * Created by zkf on 2017/8/30.
 */
public class CalendarFormQuery {

    private String hostId;
    private String problemNum;
    private Integer priority;
    private Integer acknowledge;
    private String today;//当天

    public String getHostId() {
        return hostId;
    }

    public CalendarFormQuery setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getProblemNum() {
        return problemNum;
    }

    public CalendarFormQuery setProblemNum(String problemNum) {
        this.problemNum = problemNum;
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public CalendarFormQuery setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public Integer getAcknowledge() {
        return acknowledge;
    }

    public CalendarFormQuery setAcknowledge(Integer acknowledge) {
        this.acknowledge = acknowledge;
        return this;
    }

    public String getToday() {
        return today;
    }

    public CalendarFormQuery setToday(String today) {
        this.today = today;
        return this;
    }
}
