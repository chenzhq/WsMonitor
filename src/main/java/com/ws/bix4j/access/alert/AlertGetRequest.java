package com.ws.bix4j.access.alert;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.List;

/**
 * Created by pc on 2017/8/22.
 */
public class AlertGetRequest extends ZRequest<AlertGetRequest.Params> {

    public AlertGetRequest() {
        this.setMethod("alert.get");
    }

    private AlertGetRequest.Params params = new AlertGetRequest.Params();
    @Override
    public AlertGetRequest.Params getParams() {
        return params;
    }

    public class Params extends GetRequestCommonParam {


        @JSONField(name = "alertids")
        private List<String> alertIds;
        @JSONField(name = "actionids")
        private List<String> actionIds;
        @JSONField(name = "eventids")
        private List<String> eventIds;
        @JSONField(name = "groupids")
        private List<String> groupIds;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        @JSONField(name = "mediatypeids")
        private List<String> mediatypeIds;
        @JSONField(name = "objectids")
        private List<String> objectIds;
        @JSONField(name = "userids")
        private List<String> userIds;

        private Integer eventobject;
        private Integer eventsource;

        @JSONField(name = "time_from")
        private String timeFrom;
        @JSONField(name = "time_till")
        private String timeTill;


        /**
         * select参数 string类型的可能值为 "extend" "count"
         * list类型的值是返回对象的属性列表
         */

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectHosts;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectMediatypes;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectUsers;

        public List<String> getAlertIds() {
            return alertIds;
        }

        public Params setAlertIds(List<String> alertIds) {
            this.alertIds = alertIds;
            return this;
        }

        public List<String> getActionIds() {
            return actionIds;
        }

        public Params setActionIds(List<String> actionIds) {
            this.actionIds = actionIds;
            return this;
        }

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

        public List<String> getMediatypeIds() {
            return mediatypeIds;
        }

        public Params setMediatypeIds(List<String> mediatypeIds) {
            this.mediatypeIds = mediatypeIds;
            return this;
        }

        public List<String> getObjectIds() {
            return objectIds;
        }

        public Params setObjectIds(List<String> objectIds) {
            this.objectIds = objectIds;
            return this;
        }

        public List<String> getUserIds() {
            return userIds;
        }

        public Params setUserIds(List<String> userIds) {
            this.userIds = userIds;
            return this;
        }

        public Integer getEventobject() {
            return eventobject;
        }

        public Params setEventobject(Integer eventobject) {
            this.eventobject = eventobject;
            return this;
        }

        public Integer getEventsource() {
            return eventsource;
        }

        public Params setEventsource(Integer eventsource) {
            this.eventsource = eventsource;
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

        public String[] getSelectHosts() {
            return selectHosts;
        }

        public Params setSelectHosts(String[] selectHosts) {
            this.selectHosts = selectHosts;
            return this;
        }

        public String[] getSelectMediatypes() {
            return selectMediatypes;
        }

        public Params setSelectMediatypes(String[] selectMediatypes) {
            this.selectMediatypes = selectMediatypes;
            return this;
        }

        public String[] getSelectUsers() {
            return selectUsers;
        }

        public Params setSelectUsers(String[] selectUsers) {
            this.selectUsers = selectUsers;
            return this;
        }
    }
}
