package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by chenzheqi on 2017/6/13.
 */
public class BriefHostDTO {
    private String ip;
    @JSONField(name = "hostid")
    private String hostId;
    @JSONField(name = "host")
    private String name;
    private String state;
    private String type;


    public static final String[] PROPERTY_NAMES = {"hostid", "host", "name"};



}
