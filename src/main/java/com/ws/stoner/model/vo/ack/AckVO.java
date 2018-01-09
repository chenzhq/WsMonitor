package com.ws.stoner.model.vo.ack;

/**
 * Created by zhongkf on 2018/1/3
 */
public class AckVO {

    private String userId;
    private String userName;
    private String time;
    private String message;
    private String actions;

    public AckVO() {
    }

    public AckVO(String userId, String userName, String time, String message, String actions) {
        this.userId = userId;
        this.userName = userName;
        this.time = time;
        this.message = message;
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "AckVO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                ", message='" + message + '\'' +
                ", actions='" + actions + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public AckVO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public AckVO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getTime() {
        return time;
    }

    public AckVO setTime(String time) {
        this.time = time;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AckVO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getActions() {
        return actions;
    }

    public AckVO setActions(String actions) {
        this.actions = actions;
        return this;
    }
}
