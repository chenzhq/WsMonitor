package com.ws.bix4j.access.problem;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.ZRequest;

import java.time.Instant;
import java.util.List;

/**
 * Created by chenzheqi on 2017/5/22.
 * tags查询条件 暂时未实现 需要额外设计一个类
 * selectAcknowledges selectTags未实现
 */
public class ProblemGetRequest extends ZRequest {
    private Params params = new Params();

    public ProblemGetRequest() {
        this.setMethod("problem.get");
    }

    public ProblemGetRequest setParams(Params params) {
        this.params = params;
        return this;
    }

    @Override
    public Params getParams() {
        return params;
    }

    public class Params extends GetRequestCommonParam {
        @JSONField(name = "eventids")
        private List<String> eventIds;
        @JSONField(name = "groupids")
        private List<String> groupIds;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        @JSONField(name = "objectids")
        private List<String> objectIds;
        @JSONField(name = "applicationids")
        private List<String> applicationIds;

        private int source;
        private int object;
        private boolean acknowledged;
        private List<Integer> serverities;
        //for tags

        private String recent;
        @JSONField(name = "eventid_from")
        private String eventIdFrom;
        @JSONField(name = "eventid_till")
        private String eventIdTill;
        @JSONField(name = "time_from")
        private Instant timeFrom;
        @JSONField(name = "time_till")
        private Instant timeTill;

        public List<String> getEventIds() {
            return eventIds;
        }

        public Params setEventIds(List<String> eventIds) {
            this.eventIds = eventIds;
            return this;
        }

        public List<String> getGroupIds() {
            return groupIds;
        }

        public Params setGroupIds(List<String> groupIds) {
            this.groupIds = groupIds;
            return this;
        }

        public List<String> getHostIds() {
            return hostIds;
        }

        public Params setHostIds(List<String> hostIds) {
            this.hostIds = hostIds;
            return this;
        }

        public List<String> getObjectIds() {
            return objectIds;
        }

        public Params setObjectIds(List<String> objectIds) {
            this.objectIds = objectIds;
            return this;
        }

        public List<String> getApplicationIds() {
            return applicationIds;
        }

        public Params setApplicationIds(List<String> applicationIds) {
            this.applicationIds = applicationIds;
            return this;
        }

        public int getSource() {
            return source;
        }

        public Params setSource(int source) {
            this.source = source;
            return this;
        }

        public int getObject() {
            return object;
        }

        public Params setObject(int object) {
            this.object = object;
            return this;
        }

        public boolean isAcknowledged() {
            return acknowledged;
        }

        public Params setAcknowledged(boolean acknowledged) {
            this.acknowledged = acknowledged;
            return this;
        }

        public List<Integer> getServerities() {
            return serverities;
        }

        public Params setServerities(List<Integer> serverities) {
            this.serverities = serverities;
            return this;
        }

        public String getRecent() {
            return recent;
        }

        public Params setRecent(String recent) {
            this.recent = recent;
            return this;
        }

        public String getEventIdFrom() {
            return eventIdFrom;
        }

        public Params setEventIdFrom(String eventIdFrom) {
            this.eventIdFrom = eventIdFrom;
            return this;
        }

        public String getEventIdTill() {
            return eventIdTill;
        }

        public Params setEventIdTill(String eventIdTill) {
            this.eventIdTill = eventIdTill;
            return this;
        }

        public Instant getTimeFrom() {
            return timeFrom;
        }

        public Params setTimeFrom(Instant timeFrom) {
            this.timeFrom = timeFrom;
            return this;
        }

        public Instant getTimeTill() {
            return timeTill;
        }

        public Params setTimeTill(Instant timeTill) {
            this.timeTill = timeTill;
            return this;
        }
    }
}
