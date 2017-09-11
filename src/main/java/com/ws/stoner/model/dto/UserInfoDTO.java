package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.bean.UserDO;

import java.time.Instant;
import java.util.List;

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

    private List<BriefUserGroupDTO> usrgrps;

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

    public UserInfoDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public UserInfoDTO setType(int type) {
        this.type = type;
        return this;
    }

    public UserInfoDTO setUserIp(String userIp) {
        this.userIp = userIp;
        return this;
    }

    public UserInfoDTO setAttempIp(String attempIp) {
        this.attempIp = attempIp;
        return this;
    }

    public UserInfoDTO setAttempClock(Instant attempClock) {
        this.attempClock = attempClock;
        return this;
    }

    public UserInfoDTO setAttempFailed(int attempFailed) {
        this.attempFailed = attempFailed;
        return this;
    }

    public List<BriefUserGroupDTO> getUsrgrps() {
        return usrgrps;
    }

    public UserInfoDTO setUsrgrps(List<BriefUserGroupDTO> usrgrps) {
        this.usrgrps = usrgrps;
        return this;
    }
}
