package com.ws.stoner.model.query;

/**
 * Created by zkf on 2017/8/30.
 */
public class CalendarFormQuery {

    private String hostId;
    private String problemNum;
    private Integer priority;
    private Integer acknowledge;
    private boolean isProblem;

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

    public boolean isProblem() {
        return isProblem;
    }

    public CalendarFormQuery setProblem(boolean problem) {
        isProblem = problem;
        return this;
    }
}
