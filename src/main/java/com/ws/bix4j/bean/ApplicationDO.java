package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/2.
 */
public class ApplicationDO {
    @JSONField(name = "applicationid")
    private String applicationId;
    @JSONField(name = "hostid")
    private String hostId;
    private String name;
    /**
     * 应用集来源 <br>
     * 0 普通应用集；4 自动发现应用集
     */
    private int flag;
    /**
     * 父模版应用集的ids
     */
    @JSONField(name = "templateids")
    private List<String> templateIds;

    public ApplicationDO setApplicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public ApplicationDO setFlag(int flag) {
        this.flag = flag;
        return this;
    }

    public ApplicationDO setTemplateIds(List<String> templateIds) {
        this.templateIds = templateIds;
        return this;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getHostId() {
        return hostId;
    }

    public ApplicationDO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ApplicationDO setName(String name) {
        this.name = name;
        return this;
    }

    public int getFlag() {
        return flag;
    }

    public List<String> getTemplateIds() {
        return templateIds;
    }



}
