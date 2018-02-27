package com.ws.bix4j.access.itemprototype;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.List;

/**
 * Created by pc on 2017/6/9.
 */
public class ItemprotoGetRequest extends ZRequest<ItemprotoGetRequest.Params>{
    public ItemprotoGetRequest() {
        this.setMethod("itemprototype.get");
    }

    private Params params = new Params();

    @Override
    public Params getParams() {
        return params;
    }

    public ItemprotoGetRequest setParams(Params params) {
        this.params = params;
        return this;
    }

    public class Params extends GetRequestCommonParam {

        @JSONField(name = "discoveryids")
        private List<String> discoveryIds;
        @JSONField(name = "graphids")
        private List<String> graphIds;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        private boolean inherited;
        @JSONField(name = "itemids")
        private List<String> itemIds;
        private boolean monitored;
        private boolean templated;
        @JSONField(name = "templateids")
        private List<String> templateIds;
        @JSONField(name = "triggerids")
        private List<String> triggerIds;

        /**
         * select 参数
         */
        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectApplications;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectApplicationPrototypes;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectDiscoveryRule;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectGraphs;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectHosts;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectTriggers;

        public List<String> getDiscoveryIds() {
            return discoveryIds;
        }

        public Params setDiscoveryIds(List<String> discoveryIds) {
            this.discoveryIds = discoveryIds;
            return this;
        }

        public List<String> getGraphIds() {
            return graphIds;
        }

        public Params setGraphIds(List<String> graphIds) {
            this.graphIds = graphIds;
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

        public List<String> getItemIds() {
            return itemIds;
        }

        public Params setItemIds(List<String> itemIds) {
            this.itemIds = itemIds;
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

        public List<String> getTemplateIds() {
            return templateIds;
        }

        public Params setTemplateIds(List<String> templateIds) {
            this.templateIds = templateIds;
            return this;
        }

        public List<String> getTriggerIds() {
            return triggerIds;
        }

        public Params setTriggerIds(List<String> triggerIds) {
            this.triggerIds = triggerIds;
            return this;
        }

        public String[] getSelectApplications() {
            return selectApplications;
        }

        public Params setSelectApplications(String[] selectApplications) {
            this.selectApplications = selectApplications;
            return this;
        }

        public String[] getSelectApplicationPrototypes() {
            return selectApplicationPrototypes;
        }

        public Params setSelectApplicationPrototypes(String[] selectApplicationPrototypes) {
            this.selectApplicationPrototypes = selectApplicationPrototypes;
            return this;
        }

        public String[] getSelectDiscoveryRule() {
            return selectDiscoveryRule;
        }

        public Params setSelectDiscoveryRule(String[] selectDiscoveryRule) {
            this.selectDiscoveryRule = selectDiscoveryRule;
            return this;
        }

        public String[] getSelectGraphs() {
            return selectGraphs;
        }

        public Params setSelectGraphs(String[] selectGraphs) {
            this.selectGraphs = selectGraphs;
            return this;
        }

        public String[] getSelectHosts() {
            return selectHosts;
        }

        public Params setSelectHosts(String[] selectHosts) {
            this.selectHosts = selectHosts;
            return this;
        }

        public String[] getSelectTriggers() {
            return selectTriggers;
        }

        public Params setSelectTriggers(String[] selectTriggers) {
            this.selectTriggers = selectTriggers;
            return this;
        }
    }

}
