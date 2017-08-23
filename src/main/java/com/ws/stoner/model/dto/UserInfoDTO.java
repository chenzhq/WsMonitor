package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.bean.UserDO;

import java.time.Instant;

/**
 * Created by chenzheqi on 2017/5/27.
 */
public class UserInfoDTO {
    @JSONField(name = "userid")
    private String userId;
    @JSONField(name = "alias")
    private String name;
    private int type;
    private String userIp;

    private String attempIp;
    private Instant attempClock;
    private int attempFailed;

    public static final String[] PROPERTY_NAMES = {"userid", "alias","type"};

    public UserInfoDTO() {
    }

    public UserInfoDTO(UserDO result) {
        this.userId = result.getUserId();
        this.name = result.getAlias();
        this.type = result.getType();
        this.attempIp = result.getAttemptIp();
        this.attempClock = result.getAttemptClock();
        this.attempFailed = result.getAttemptFailed();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getUserIp() {
        return userIp;
    }

    public String getAttempIp() {
        return attempIp;
    }

    public Instant getAttempClock() {
        return attempClock;
    }

    public int getAttempFailed() {
        return attempFailed;
    }
}
