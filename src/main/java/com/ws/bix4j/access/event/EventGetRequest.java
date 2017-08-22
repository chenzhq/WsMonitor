package com.ws.bix4j.access.event;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.List;


/**
 * Created by zkf on 2017/8/22.
 */
public class EventGetRequest extends ZRequest<EventGetRequest.Params> {

    public EventGetRequest() {
        this.setMethod("event.get");
    }

    private EventGetRequest.Params params = new EventGetRequest.Params();
    @Override
    public EventGetRequest.Params getParams() {
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

        private Integer source;
        private Integer object;
        private boolean acknowledged;
        private List<Integer> severities;
        private Object tags;

        @JSONField(name = "eventid_from")
        private String eventidFrom;
        @JSONField(name = "eventid_till")
        private String eventidTill;

        @JSONField(name = "time_from")
        private String timeFrom;
        @JSONField(name = "time_till")
        private String timeTill;

        private List<Integer> value;

        /**
         * select参数 string类型的可能值为 "extend" "count"
         * list类型的值是返回对象的属性列表
         */

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectHosts;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectRelatedObject;

        @JSONField(name = "select_alerts",serializeUsing = SelectParamSerializer.class)
        private String[] selectAlerts;

        @JSONField(name = "select_acknowledges",serializeUsing = SelectParamSerializer.class)
        private String[] selectAcknowledges;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectTags;

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

        public Integer getSource() {
            return source;
        }

        public Params setSource(Integer source) {
            this.source = source;
            return this;
        }

        public Integer getObject() {
            return object;
        }

        public Params setObject(Integer object) {
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

        public List<Integer> getSeverities() {
            return severities;
        }

        public Params setSeverities(List<Integer> severities) {
            this.severities = severities;
            return this;
        }

        public Object getTags() {
            return tags;
        }

        public Params setTags(Object tags) {
            this.tags = tags;
            return this;
        }

        public String getEventidFrom() {
            return eventidFrom;
        }

        public Params setEventidFrom(String eventidFrom) {
            this.eventidFrom = eventidFrom;
            return this;
        }

        public String getEventidTill() {
            return eventidTill;
        }

        public Params setEventidTill(String eventidTill) {
            this.eventidTill = eventidTill;
            return this;
        }

        public String getTimeFrom() {
            return timeFrom;
        }

        public Params setTimeFrom(String timeFrom) {
            this.timeFrom = timeFrom;
            return this;
        }

        public String getTimeTill() {
            return timeTill;
        }

        public Params setTimeTill(String timeTill) {
            this.timeTill = timeTill;
            return this;
        }

        public List<Integer> getValue() {
            return value;
        }

        public Params setValue(List<Integer> value) {
            this.value = value;
            return this;
        }

        public String[] getSelectHosts() {
            return selectHosts;
        }

        public Params setSelectHosts(String[] selectHosts) {
            this.selectHosts = selectHosts;
            return this;
        }

        public String[] getSelectRelatedObject() {
            return selectRelatedObject;
        }

        public Params setSelectRelatedObject(String[] selectRelatedObject) {
            this.selectRelatedObject = selectRelatedObject;
            return this;
        }

        public String[] getSelectAlerts() {
            return selectAlerts;
        }

        public Params setSelectAlerts(String[] selectAlerts) {
            this.selectAlerts = selectAlerts;
            return this;
        }

        public String[] getSelectAcknowledges() {
            return selectAcknowledges;
        }

        public Params setSelectAcknowledges(String[] selectAcknowledges) {
            this.selectAcknowledges = selectAcknowledges;
            return this;
        }

        public String[] getSelectTags() {
            return selectTags;
        }

        public Params setSelectTags(String[] selectTags) {
            this.selectTags = selectTags;
            return this;
        }
    }
}
