package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.util.List;

/**
 * Created by chen on 2017/4/1.
 */
public class UserDO {
    @JSONField(name = "userid")
    private String userId;
    private String alias;
    private String name;
    private String surname;
    private String url;
    @JSONField(name = "autologin")
    private String autoLogin;
    @JSONField(name = "autologout")
    private String autoLogout;
    private String lang;
    private String refresh;
    private int type;
    private String theme;

    @JSONField(name = "attempt_failed")
    private int attemptFailed;
    @JSONField(name = "attempt_ip")
    private String attemptIp;
    @JSONField(name = "attempt_clock")
    private Instant attemptClock;

    @JSONField(name = "rows_per_page")
    private int rowsPerPage;

    @JSONField(name = "gui_access")
    private String guiAccess;
    @JSONField(name = "debug_mode")
    private String debugMode;
    @JSONField(name = "users_status")
    private String usersStatus;

    private List<MediaDO> medias;
    @JSONField(name = "mediatypes")
    private List<MediaTypeDO> mediaTypes;
    @JSONField(name = "usrgrps")
    private List<UserGroupDO> usrGrps;

    @Override
    public String toString() {
        return "UserDO{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                '}'+'\n';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Instant getAttemptClock() {
        return attemptClock;
    }

    public void setAttemptClock(int attemptClock) {
        this.attemptClock = Instant.ofEpochSecond(attemptClock);
    }

    public int getAttemptFailed() {
        return attemptFailed;
    }

    public void setAttemptFailed(int attemptFailed) {
        this.attemptFailed = attemptFailed;
    }

    public String getAttemptIp() {
        return attemptIp;
    }

    public void setAttemptIp(String attemptIp) {
        this.attemptIp = attemptIp;
    }

    public String getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(String autoLogin) {
        this.autoLogin = autoLogin;
    }

    public String getAutoLogout() {
        return autoLogout;
    }

    public void setAutoLogout(String autoLogout) {
        this.autoLogout = autoLogout;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGuiAccess() {
        return guiAccess;
    }

    public void setGuiAccess(String guiAccess) {
        this.guiAccess = guiAccess;
    }

    public String getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(String debugMode) {
        this.debugMode = debugMode;
    }

    public String getUsersStatus() {
        return usersStatus;
    }

    public void setUsersStatus(String usersStatus) {
        this.usersStatus = usersStatus;
    }

    public List<MediaDO> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaDO> medias) {
        this.medias = medias;
    }

    public List<MediaTypeDO> getMediatypes() {
        return mediaTypes;
    }

    public void setMediatypes(List<MediaTypeDO> mediatypes) {
        this.mediaTypes = mediatypes;
    }

    public List<UserGroupDO> getUsrgrps() {
        return usrGrps;
    }

    public void setUsrgrps(List<UserGroupDO> usrgrps) {
        this.usrGrps = usrgrps;
    }
}
