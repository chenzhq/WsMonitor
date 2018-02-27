package com.ws.bix4j.access.TriggerPro;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.List;


/**
 * Created by pc on 2017/6/8.
 */
public class TriggerProGetRequest extends ZRequest<TriggerProGetRequest.Params>{

    public TriggerProGetRequest() {
        this.setMethod("triggerprototype.get");
    }

    private Params params = new Params();

    public Params getParams() {
        return params;
    }

    public TriggerProGetRequest setParams(Params params) {
        this.params = params;
        return this;
    }

    public class Params extends GetRequestCommonParam{
        private boolean active;
        @JSONField(name = "applicationids")
        private List<String> applicationIds;
        @JSONField(name = "discoveryids")
        private List<String> discoveryIds;
        private List<String> functions;
        private String group;
        private String host;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        private boolean inherited;
        private boolean maintenance;
        private Integer min_severity;
        private boolean monitored;
        private boolean templated;
        @JSONField(name = "triggerids")
        private List<String> triggerIds;
        @JSONField(name = "groupids")
        private List<String> groupIds;
        @JSONField(name = "templateids")
        private List<String> templateIds;
        private boolean expandExpression;
        /**
         * select
         */
        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectDiscoveryRule;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectFunctions;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectGroups;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectHosts;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectItems;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectDependencies;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectTags;

        public boolean isActive() {
            return active;
        }

        public Params setActive(boolean active) {
            this.active = active;
            return this;
        }

        public List<String> getApplicationIds() {
            return applicationIds;
        }

        public Params setApplicationIds(List<String> applicationIds) {
            this.applicationIds = applicationIds;
            return this;
        }

        public List<String> getDiscoveryIds() {
            return discoveryIds;
        }

        public Params setDiscoveryIds(List<String> discoveryIds) {
            this.discoveryIds = discoveryIds;
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

        public List<String> getHostIds() {
            return hostIds;
        }

        public Params setHostIds(List<String> hostIds) {
            this.hostIds = hostIds;
            return this;
        }

        public boolean isInherited() {
            return inherited;
        }

        public Params setInherited(boolean inherited) {
            this.inherited = inherited;
            return this;
        }

        public boolean isMaintenance() {
            return maintenance;
        }

        public Params setMaintenance(boolean maintenance) {
            this.maintenance = maintenance;
            return this;
        }

        public Integer getMin_severity() {
            return min_severity;
        }

        public Params setMin_severity(Integer min_severity) {
            this.min_severity = min_severity;
            return this;
        }

        public boolean isMonitored() {
            return monitored;
        }

        public Params setMonitored(boolean monitored) {
            this.monitored = monitored;
            return this;
        }

        public boolean isTemplated() {
            return templated;
        }

        public Params setTemplated(boolean templated) {
            this.templated = templated;
            return this;
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

        public boolean isExpandExpression() {
            return expandExpression;
        }

        public Params setExpandExpression(boolean expandExpression) {
            this.expandExpression = expandExpression;
            return this;
        }

        public String[] getSelectDiscoveryRule() {
            return selectDiscoveryRule;
        }

        public Params setSelectDiscoveryRule(String[] selectDiscoveryRule) {
            this.selectDiscoveryRule = selectDiscoveryRule;
            return this;
        }

        public String[] getSelectFunctions() {
            return selectFunctions;
        }

        public Params setSelectFunctions(String[] selectFunctions) {
            this.selectFunctions = selectFunctions;
            return this;
        }

        public String[] getSelectGroups() {
            return selectGroups;
        }

        public Params setSelectGroups(String[] selectGroups) {
            this.selectGroups = selectGroups;
            return this;
        }

        public String[] getSelectHosts() {
            return selectHosts;
        }

        public Params setSelectHosts(String[] selectHosts) {
            this.selectHosts = selectHosts;
            return this;
        }

        public String[] getSelectItems() {
            return selectItems;
        }

        public Params setSelectItems(String[] selectItems) {
            this.selectItems = selectItems;
            return this;
        }

        public String[] getSelectDependencies() {
            return selectDependencies;
        }

        public Params setSelectDependencies(String[] selectDependencies) {
            this.selectDependencies = selectDependencies;
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
