package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Date 2017/4/5
 * @Author chen
 */
public class UserGroupDO {

    public UserGroupDO(String usrGrpId) {
        this.usrGrpId = usrGrpId;
    }

    @JSONField(name = "usrgrpid")
    private String usrGrpId;
    private String name;
    @JSONField(name = "gui_access")
    private String guiAccess;
    @JSONField(name = "debug_mode")
    private String debugMode;
    @JSONField(name = "users_status")
    private String usersStatus;

    public String getUsrGrpId() {
        return usrGrpId;
    }

    public void setUsrGrpId(String usrGrpId) {
        this.usrGrpId = usrGrpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
