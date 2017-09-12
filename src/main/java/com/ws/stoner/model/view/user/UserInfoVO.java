package com.ws.stoner.model.view.user;

import com.ws.bix4j.access.user.UserLoginResponse;

import java.time.Instant;

/**
 * Created by chenzheqi on 2017/4/28.
 */
public class UserInfoVO {
    private String loginName;
    private String userName;
    private String currentIp;
    private String attemptIp;
    private Instant attemptClock;
    private int attemptFaild;
    private String auth;
    private String userType;

    public String getAuth() {
        return auth;
    }

    public UserInfoVO setAuth(String auth) {
        this.auth = auth;
        return this;
    }

    public UserInfoVO(UserLoginResponse.Result result) {
        loginName = result.getAlias();
        userName = result.getName();
        currentIp = result.getUserIp();
        attemptIp = result.getAttemptIp();
        attemptClock = result.getAttemptClock();
        attemptFaild = result.getAttemptFailed();
        auth = result.getSessionId();
    }

    public String getLoginName() {

        return loginName;
    }

    public UserInfoVO setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfoVO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getCurrentIp() {
        return currentIp;
    }

    public UserInfoVO setCurrentIp(String currentIp) {
        this.currentIp = currentIp;
        return this;
    }

    public String getAttemptIp() {
        return attemptIp;
    }

    public UserInfoVO setAttemptIp(String attemptIp) {
        this.attemptIp = attemptIp;
        return this;
    }

    public Instant getAttemptClock() {
        return attemptClock;
    }

    public UserInfoVO setAttemptClock(Instant attemptClock) {
        this.attemptClock = attemptClock;
        return this;
    }

    public int getAttemptFaild() {
        return attemptFaild;
    }

    public UserInfoVO setAttemptFaild(int attemptFaild) {
        this.attemptFaild = attemptFaild;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public UserInfoVO setUserType(String userType) {
        this.userType = userType;
        return this;
    }

}
