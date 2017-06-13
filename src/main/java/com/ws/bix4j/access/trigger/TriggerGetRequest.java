package com.ws.bix4j.access.trigger;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.ZRequest;

import java.time.Instant;
import java.util.List;


/**
 * Created by pc on 2017/6/8.
 */
public class TriggerGetRequest extends ZRequest<TriggerGetRequest.Params>{

    public TriggerGetRequest() {
        this.setMethod("trigger.get");
    }

    private Params params = new Params();

    public Params getParams() {
        return params;
    }

    public TriggerGetRequest setParams(Params params) {
        this.params = params;
        return this;
    }

    public class Params extends GetRequestCommonParam{
        @JSONField(name = "triggerids")
        private List<String> triggerIds;
        @JSONField(name = "groupids")
        private List<String> groupIds;
        @JSONField(name = "templateids")
        private List<String> templateIds;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        @JSONField(name = "itemids")
        private List<String> itemIds;
        @JSONField(name = "applicationids")
        private List<String> applicationIds;
        private List<String> functions;
        private String group;
        private String host;
        private boolean inherited;
        private boolean templated;
        private boolean monitored;
        private boolean active;
        private boolean maintenance;
        private boolean withUnacknowledgedEvents;
        private boolean withAcknowledgedEvents;
        private boolean withLastEventUnacknowledged;
        private boolean skipDependent;
        private Instant lastChangeSince;
        private Instant lastChangeTill;
        @JSONField(name = "only_true")
        private boolean onlyTrue;
        private Integer min_severity;
        private boolean expandComment;
        private boolean expandDescription;
        private boolean expandExpression;
        /**
         * select
         */
        @JSONField(name = "selectGroups")
        private String stringGroups;
        @JSONField(name = "selectGroups")
        private List<String> listGroups;

        @JSONField(name = "selectHosts")
        private String stringHosts;
        @JSONField(name = "selectHosts")
        private String[] listHosts;

        @JSONField(name = "selectItems")
        private String stringItems;
        @JSONField(name = "selectItems")
        private List<String> listItems;

        @JSONField(name = "selectFunctions")
        private String stringFunctions;
        @JSONField(name = "selectFunctions")
        private List<String> listFunctions;

        @JSONField(name = "selectDiscoveryRule")
        private String stringDiscoveryRule;
        @JSONField(name = "selectDiscoveryRule")
        private List<String> listDiscoveryRule;

        @JSONField(name = "selectLastEvent")
        private String stringLastEvent;
        @JSONField(name = "selectLastEvent")
        private List<String> listLastEvent;

        @JSONField(name = "selectTags")
        private String stringTags;
        @JSONField(name = "selectTags")
        private List<String> listTags;

        @Override
        public String toString() {
            return "Params{" +
                    "triggerIds=" + triggerIds +
                    ", groupIds=" + groupIds +
                    ", templateIds=" + templateIds +
                    ", hostIds=" + hostIds +
                    ", itemIds=" + itemIds +
                    ", applicationIds=" + applicationIds +
                    ", functions=" + functions +
                    ", hostgroup='" + group + '\'' +
                    ", host='" + host + '\'' +
                    ", inherited=" + inherited +
                    ", templated=" + templated +
                    ", monitored=" + monitored +
                    ", active=" + active +
                    ", maintenance=" + maintenance +
                    ", withUnacknowledgedEvents=" + withUnacknowledgedEvents +
                    ", withAcknowledgedEvents=" + withAcknowledgedEvents +
                    ", withLastEventUnacknowledged=" + withLastEventUnacknowledged +
                    ", skipDependent=" + skipDependent +
                    ", lastChangeSince=" + lastChangeSince +
                    ", lastChangeTill=" + lastChangeTill +
                    ", onlyTrue=" + onlyTrue +
                    ", min_severity=" + min_severity +
                    ", expandComment=" + expandComment +
                    ", expandDescription=" + expandDescription +
                    ", expandExpression=" + expandExpression +
                    ", stringGroups='" + stringGroups + '\'' +
                    ", listGroups=" + listGroups +
                    ", stringHosts='" + stringHosts + '\'' +
                    ", listHosts=" + listHosts +
                    ", stringItems='" + stringItems + '\'' +
                    ", listItems=" + listItems +
                    ", stringFunctions='" + stringFunctions + '\'' +
                    ", listFunctions=" + listFunctions +
                    ", stringDiscoveryRule='" + stringDiscoveryRule + '\'' +
                    ", listDiscoveryRule=" + listDiscoveryRule +
                    ", stringLastEvent='" + stringLastEvent + '\'' +
                    ", listLastEvent=" + listLastEvent +
                    ", stringTags='" + stringTags + '\'' +
                    ", listTags=" + listTags +
                    '}';
        }

        public List<String> getTriggerIds() {
            return triggerIds;
        }

        public Params setTriggerIds(List<String> triggerIds) {
            this.triggerIds = triggerIds;
            return this;
        }

        public List<String> getGroupIds() {
            return groupIds;
        }

        public Params setGroupIds(List<String> groupIds) {
            this.groupIds = groupIds;
            return this;
        }

        public List<String> getTemplateIds() {
            return templateIds;
        }

        public Params setTemplateIds(List<String> templateIds) {
            this.templateIds = templateIds;
            return this;
        }

        public List<String> getHostIds() {
            return hostIds;
        }

        public Params setHostIds(List<String> hostIds) {
            this.hostIds = hostIds;
            return this;
        }

        public List<String> getItemIds() {
            return itemIds;
        }

        public Params setItemIds(List<String> itemIds) {
            this.itemIds = itemIds;
            return this;
        }

        public List<String> getApplicationIds() {
            return applicationIds;
        }

        public Params setApplicationIds(List<String> applicationIds) {
            this.applicationIds = applicationIds;
            return this;
        }

        public List<String> getFunctions() {
            return functions;
        }

        public Params setFunctions(List<String> functions) {
            this.functions = functions;
            return this;
        }

        public String getGroup() {
            return group;
        }

        public Params setGroup(String group) {
            this.group = group;
            return this;
        }

        public String getHost() {
            return host;
        }

        public Params setHost(String host) {
            this.host = host;
            return this;
        }

        public boolean isInherited() {
            return inherited;
        }

        public Params setInherited(boolean inherited) {
            this.inherited = inherited;
            return this;
        }

        public boolean isTemplated() {
            return templated;
        }

        public Params setTemplated(boolean templated) {
            this.templated = templated;
            return this;
        }

        public boolean isMonitored() {
            return monitored;
        }

        public Params setMonitored(boolean monitored) {
            this.monitored = monitored;
            return this;
        }

        public boolean isActive() {
            return active;
        }

        public Params setActive(boolean active) {
            this.active = active;
            return this;
        }

        public boolean isMaintenance() {
            return maintenance;
        }

        public Params setMaintenance(boolean maintenance) {
            this.maintenance = maintenance;
            return this;
        }

        public boolean isWithUnacknowledgedEvents() {
            return withUnacknowledgedEvents;
        }

        public Params setWithUnacknowledgedEvents(boolean withUnacknowledgedEvents) {
            this.withUnacknowledgedEvents = withUnacknowledgedEvents;
            return this;
        }

        public boolean isWithAcknowledgedEvents() {
            return withAcknowledgedEvents;
        }

        public Params setWithAcknowledgedEvents(boolean withAcknowledgedEvents) {
            this.withAcknowledgedEvents = withAcknowledgedEvents;
            return this;
        }

        public boolean isWithLastEventUnacknowledged() {
            return withLastEventUnacknowledged;
        }

        public Params setWithLastEventUnacknowledged(boolean withLastEventUnacknowledged) {
            this.withLastEventUnacknowledged = withLastEventUnacknowledged;
            return this;
        }

        public boolean isSkipDependent() {
            return skipDependent;
        }

        public Params setSkipDependent(boolean skipDependent) {
            this.skipDependent = skipDependent;
            return this;
        }

        public Instant getLastChangeSince() {
            return lastChangeSince;
        }

        public Params setLastChangeSince(Instant lastChangeSince) {
            this.lastChangeSince = lastChangeSince;
            return this;
        }

        public Instant getLastChangeTill() {
            return lastChangeTill;
        }

        public Params setLastChangeTill(Instant lastChangeTill) {
            this.lastChangeTill = lastChangeTill;
            return this;
        }

        public boolean isOnlyTrue() {
            return onlyTrue;
        }

        public Params setOnlyTrue(boolean only_true) {
            this.onlyTrue = only_true;
            return this;
        }

        public Integer getMin_severity() {
            return min_severity;
        }

        public Params setMin_severity(Integer min_severity) {
            this.min_severity = min_severity;
            return this;
        }

        public boolean isExpandComment() {
            return expandComment;
        }

        public Params setExpandComment(boolean expandComment) {
            this.expandComment = expandComment;
            return this;
        }

        public boolean isExpandDescription() {
            return expandDescription;
        }

        public Params setExpandDescription(boolean expandDescription) {
            this.expandDescription = expandDescription;
            return this;
        }

        public boolean isExpandExpression() {
            return expandExpression;
        }

        public Params setExpandExpression(boolean expandExpression) {
            this.expandExpression = expandExpression;
            return this;
        }

        public String getStringGroups() {
            return stringGroups;
        }

        public Params setStringGroups(String stringGroups) {
            this.stringGroups = stringGroups;
            return this;
        }

        public List<String> getListGroups() {
            return listGroups;
        }

        public Params setListGroups(List<String> listGroups) {
            this.listGroups = listGroups;
            return this;
        }

        public String getStringHosts() {
            return stringHosts;
        }

        public Params setStringHosts(String stringHosts) {
            this.stringHosts = stringHosts;
            return this;
        }

        public String[] getListHosts() {
            return listHosts;
        }

        public Params setListHosts(String[] listHosts) {
            this.listHosts = listHosts;
            return this;
        }

        public String getStringItems() {
            return stringItems;
        }

        public Params setStringItems(String stringItems) {
            this.stringItems = stringItems;
            return this;
        }

        public List<String> getListItems() {
            return listItems;
        }

        public Params setListItems(List<String> listItems) {
            this.listItems = listItems;
            return this;
        }

        public String getStringFunctions() {
            return stringFunctions;
        }

        public Params setStringFunctions(String stringFunctions) {
            this.stringFunctions = stringFunctions;
            return this;
        }

        public List<String> getListFunctions() {
            return listFunctions;
        }

        public Params setListFunctions(List<String> listFunctions) {
            this.listFunctions = listFunctions;
            return this;
        }

        public String getStringDiscoveryRule() {
            return stringDiscoveryRule;
        }

        public Params setStringDiscoveryRule(String stringDiscoveryRule) {
            this.stringDiscoveryRule = stringDiscoveryRule;
            return this;
        }

        public List<String> getListDiscoveryRule() {
            return listDiscoveryRule;
        }

        public Params setListDiscoveryRule(List<String> listDiscoveryRule) {
            this.listDiscoveryRule = listDiscoveryRule;
            return this;
        }

        public String getStringLastEvent() {
            return stringLastEvent;
        }

        public Params setStringLastEvent(String stringLastEvent) {
            this.stringLastEvent = stringLastEvent;
            return this;
        }

        public List<String> getListLastEvent() {
            return listLastEvent;
        }

        public Params setListLastEvent(List<String> listLastEvent) {
            this.listLastEvent = listLastEvent;
            return this;
        }

        public String getStringTags() {
            return stringTags;
        }

        public Params setStringTags(String stringTags) {
            this.stringTags = stringTags;
            return this;
        }

        public List<String> getListTags() {
            return listTags;
        }

        public Params setListTags(List<String> listTags) {
            this.listTags = listTags;
            return this;
        }
    }
}
