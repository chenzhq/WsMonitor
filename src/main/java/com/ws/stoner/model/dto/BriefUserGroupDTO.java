package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zkf on 2017/8/29.
 */
public class BriefUserGroupDTO {

    @JSONField(name = "usrgrpid")
    private String usrgrpId;
    private String name;

    private List<BriefPermissionDTO> rights;

    public static final String[] PROPERTY_NAMES = {"usrgrpid", "name","expression"};

    @Override
    public String toString() {
        return "BriefUserGroupDTO{" +
                "usrgrpId='" + usrgrpId + '\'' +
                ", name='" + name + '\'' +
                ", rights=" + rights +
                '}';
    }

    public String getUsrgrpId() {
        return usrgrpId;
    }

    public BriefUserGroupDTO setUsrgrpId(String usrgrpId) {
        this.usrgrpId = usrgrpId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefUserGroupDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<BriefPermissionDTO> getRights() {
        return rights;
    }

    public BriefUserGroupDTO setRights(List<BriefPermissionDTO> rights) {
        this.rights = rights;
        return this;
    }
}
