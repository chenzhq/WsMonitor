package com.ws.bix4j.access.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chenzheqi on 2017/5/2.
 * Ref: https://www.zabbix.com/documentation/3.2/manual/api/reference/host/get
 */
public class TemplateGetRequest extends ZRequest<TemplateGetRequest.Params>{
    public TemplateGetRequest() {
        this.setMethod("template.get");
    }

    private Params params = new Params();
    @Override
    public Params getParams() {
        return params;
    }

    public class Params extends GetRequestCommonParam {
        @JSONField(name = "templateids")
        private List<String> templateIds;

        @JSONField(name = "groupids")
        private List<String> groupIds;
        @JSONField(name = "parentTemplateids")
        private List<String> parentTemplateIds;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        @JSONField(name = "graphids")
        private List<String> graphIds;
        @JSONField(name = "itemids")
        private List<String> itemIds;

        @JSONField(name = "triggerids")
        private List<String> triggerIds;



        /**
         * with 2016.6.8 add
         */
        @JSONField(name = "with_items")
        private boolean withItems;
        @JSONField(name = "with_triggers")
        private boolean withTriggers;
        @JSONField(name = "with_graphs")
        private boolean withGraphs;
        @JSONField(name = "with_httptests")
        private boolean withHttptests;


        /**
         * select参数 string类型的可能值为 "extend" "count"
         * list类型的值是返回对象的属性列表
         */
        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectGroups;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectHosts;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectTemplates;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectParentTemplates;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectHttpTests;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectItems;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectDiscoveries;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectGraphs;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectApplications;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectTriggers;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectMacros;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectScreens;

        @Override
        public String toString() {
            return "Params{" +
                    "templateIds=" + templateIds +
                    ", groupIds=" + groupIds +
                    ", parentTemplateIds=" + parentTemplateIds +
                    ", hostIds=" + hostIds +
                    ", graphIds=" + graphIds +
                    ", itemIds=" + itemIds +
                    ", triggerIds=" + triggerIds +
                    ", withItems=" + withItems +
                    ", withTriggers=" + withTriggers +
                    ", withGraphs=" + withGraphs +
                    ", withHttptests=" + withHttptests +
                    ", selectGroups=" + Arrays.toString(selectGroups) +
                    ", selectHosts=" + Arrays.toString(selectHosts) +
                    ", selectTemplates=" + Arrays.toString(selectTemplates) +
                    ", selectParentTemplates=" + Arrays.toString(selectParentTemplates) +
                    ", selectHttpTests=" + Arrays.toString(selectHttpTests) +
                    ", selectItems=" + Arrays.toString(selectItems) +
                    ", selectDiscoveries=" + Arrays.toString(selectDiscoveries) +
                    ", selectGraphs=" + Arrays.toString(selectGraphs) +
                    ", selectApplications=" + Arrays.toString(selectApplications) +
                    ", selectTriggers=" + Arrays.toString(selectTriggers) +
                    ", selectMacros=" + Arrays.toString(selectMacros) +
                    ", selectScreens=" + Arrays.toString(selectScreens) +
                    '}';
        }

        public List<String> getTemplateIds() {
            return templateIds;
        }

        public Params setTemplateIds(List<String> templateIds) {
            this.templateIds = templateIds;
            return this;
        }

        public List<String> getGroupIds() {
            return groupIds;
        }

        public Params setGroupIds(List<String> groupIds) {
            this.groupIds = groupIds;
            return this;
        }

        public List<String> getParentTemplateIds() {
            return parentTemplateIds;
        }

        public Params setParentTemplateIds(List<String> parentTemplateIds) {
            this.parentTemplateIds = parentTemplateIds;
            return this;
        }

        public List<String> getHostIds() {
            return hostIds;
        }

        public Params setHostIds(List<String> hostIds) {
            this.hostIds = hostIds;
            return this;
        }

        public List<String> getGraphIds() {
            return graphIds;
        }

        public Params setGraphIds(List<String> graphIds) {
            this.graphIds = graphIds;
            return this;
        }

        public List<String> getItemIds() {
            return itemIds;
        }

        public Params setItemIds(List<String> itemIds) {
            this.itemIds = itemIds;
            return this;
        }

        public List<String> getTriggerIds() {
            return triggerIds;
        }

        public Params setTriggerIds(List<String> triggerIds) {
            this.triggerIds = triggerIds;
            return this;
        }

        public boolean isWithItems() {
            return withItems;
        }

        public Params setWithItems(boolean withItems) {
            this.withItems = withItems;
            return this;
        }

        public boolean isWithTriggers() {
            return withTriggers;
        }

        public Params setWithTriggers(boolean withTriggers) {
            this.withTriggers = withTriggers;
            return this;
        }

        public boolean isWithGraphs() {
            return withGraphs;
        }

        public Params setWithGraphs(boolean withGraphs) {
            this.withGraphs = withGraphs;
            return this;
        }

        public boolean isWithHttptests() {
            return withHttptests;
        }

        public Params setWithHttptests(boolean withHttptests) {
            this.withHttptests = withHttptests;
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

        public String[] getSelectTemplates() {
            return selectTemplates;
        }

        public Params setSelectTemplates(String[] selectTemplates) {
            this.selectTemplates = selectTemplates;
            return this;
        }

        public String[] getSelectParentTemplates() {
            return selectParentTemplates;
        }

        public Params setSelectParentTemplates(String[] selectParentTemplates) {
            this.selectParentTemplates = selectParentTemplates;
            return this;
        }

        public String[] getSelectHttpTests() {
            return selectHttpTests;
        }

        public Params setSelectHttpTests(String[] selectHttpTests) {
            this.selectHttpTests = selectHttpTests;
            return this;
        }

        public String[] getSelectItems() {
            return selectItems;
        }

        public Params setSelectItems(String[] selectItems) {
            this.selectItems = selectItems;
            return this;
        }

        public String[] getSelectDiscoveries() {
            return selectDiscoveries;
        }

        public Params setSelectDiscoveries(String[] selectDiscoveries) {
            this.selectDiscoveries = selectDiscoveries;
            return this;
        }

        public String[] getSelectGraphs() {
            return selectGraphs;
        }

        public Params setSelectGraphs(String[] selectGraphs) {
            this.selectGraphs = selectGraphs;
            return this;
        }

        public String[] getSelectApplications() {
            return selectApplications;
        }

        public Params setSelectApplications(String[] selectApplications) {
            this.selectApplications = selectApplications;
            return this;
        }

        public String[] getSelectTriggers() {
            return selectTriggers;
        }

        public Params setSelectTriggers(String[] selectTriggers) {
            this.selectTriggers = selectTriggers;
            return this;
        }

        public String[] getSelectMacros() {
            return selectMacros;
        }

        public Params setSelectMacros(String[] selectMacros) {
            this.selectMacros = selectMacros;
            return this;
        }

        public String[] getSelectScreens() {
            return selectScreens;
        }

        public Params setSelectScreens(String[] selectScreens) {
            this.selectScreens = selectScreens;
            return this;
        }
    }
}
