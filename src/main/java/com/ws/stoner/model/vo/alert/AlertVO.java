package com.ws.stoner.model.vo.alert;

/**
 * Created by zhongkf on 2018/1/3
 */
public class AlertVO {

    private int step;
    private String userName;
    private String time;
    private String sendTo;
    private String type;
    private String subject;
    private String message;
    private String status;
    private int retries;
    private boolean recovery;

    public AlertVO() {

    }

    public AlertVO(int step, String userName, String time, String sendTo, String type, String subject, String message, String status, int retries, boolean recovery) {
        this.step = step;
        this.userName = userName;
        this.time = time;
        this.sendTo = sendTo;
        this.type = type;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.retries = retries;
        this.recovery = recovery;
    }

    @Override
    public String toString() {
        return "AlertVO{" +
                "step=" + step +
                ", userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                ", sendTo='" + sendTo + '\'' +
                ", type='" + type + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", retries=" + retries +
                ", recovery=" + recovery +
                '}';
    }

    public int getStep() {
        return step;
    }

    public AlertVO setStep(int step) {
        this.step = step;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public AlertVO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getTime() {
        return time;
    }

    public AlertVO setTime(String time) {
        this.time = time;
        return this;
    }

    public String getSendTo() {
        return sendTo;
    }

    public AlertVO setSendTo(String sendTo) {
        this.sendTo = sendTo;
        return this;
    }

    public String getType() {
        return type;
    }

    public AlertVO setType(String type) {
        this.type = type;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public AlertVO setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AlertVO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public AlertVO setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getRetries() {
        return retries;
    }

    public AlertVO setRetries(int retries) {
        this.retries = retries;
        return this;
    }

    public boolean isRecovery() {
        return recovery;
    }

    public AlertVO setRecovery(boolean recovery) {
        this.recovery = recovery;
        return this;
    }
}
