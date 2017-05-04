package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;

/**
 * Created by chenzheqi on 2017/5/3.
 */
public class EventDO {
    @JSONField(name = "eventid")
    private String eventId;
    private int source;
    private int object;
    @JSONField(name = "objectid")
    private String objectId;
    private int acknowledged;
    private Instant clock;
    private int ns;
    private int value;
    @JSONField(name = "r_eventid")
    private String  rEventId;
    @JSONField(name = "c_eventid")
    private String cEventId;
    @JSONField(name = "correlationid")
    private String correlationId;
    @JSONField(name = "userid")
    private String userId;
}
