package com.ws.bix4j.access.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
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
        @JSONField(name = "selectGroups")
        private String stringGroups;
        @JSONField(name = "selectGroups")
        private String[] listGroups;

        @JSONField(name = "selectHosts")
        private String stringHosts;
        @JSONField(name = "selectHosts")
        private String[] listHosts;

        @JSONField(name = "selectTemplates")
        private String stringTemplates;
        @JSONField(name = "selectTemplates")
        private String[] listTemplates;

        @JSONField(name = "selectParentTemplates")
        private String stringParentTemplates;
        @JSONField(name = "selectParentTemplates")
        private String[] listParentTemplates;

        @JSONField(name = "selectHttpTests")
        private String stringHttpTests;
        @JSONField(name = "selectHttpTests")
        private String[] listHttpTests;

        @JSONField(name = "selectItems")
        private String stringItems;
        @JSONField(name = "selectItems")
        private String[] listItems;

        @JSONField(name = "selectDiscoveries")
        private String stringDiscoveries;
        @JSONField(name = "selectDiscoveries")
        private String[] listDiscoveries;

        @JSONField(name = "selectGraphs")
        private String stringGraphs;
        @JSONField(name = "selectGraphs")
        private String[] listGraphs;

        @JSONField(name = "selectApplications")
        private String stringApplications;
        @JSONField(name = "selectApplications")
        private String[] listApplications;

        @JSONField(name = "selectTriggers")
        private String stringTriggers;
        @JSONField(name = "selectTriggers")
        private String[] listTriggers;

        @JSONField(name = "selectMacros")
        private String stringMacros;
        @JSONField(name = "selectMacros")
        private String[] listMacros;

        @JSONField(name = "selectScreens")
        private String stringScreens;
        @JSONField(name = "selectScreens")
        private String[] listScreens;

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

        public String getStringGroups() {
            return stringGroups;
        }

        public Params setStringGroups(String stringGroups) {
            this.stringGroups = stringGroups;
            return this;
        }

        public String[] getListGroups() {
            return listGroups;
        }

        public Params setListGroups(String[] listGroups) {
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

        public String getStringTemplates() {
            return stringTemplates;
        }

        public Params setStringTemplates(String stringTemplates) {
            this.stringTemplates = stringTemplates;
            return this;
        }

        public String[] getListTemplates() {
            return listTemplates;
        }

        public Params setListTemplates(String[] listTemplates) {
            this.listTemplates = listTemplates;
            return this;
        }

        public String getStringParentTemplates() {
            return stringParentTemplates;
        }

        public Params setStringParentTemplates(String stringParentTemplates) {
            this.stringParentTemplates = stringParentTemplates;
            return this;
        }

        public String[] getListParentTemplates() {
            return listParentTemplates;
        }

        public Params setListParentTemplates(String[] listParentTemplates) {
            this.listParentTemplates = listParentTemplates;
            return this;
        }

        public String getStringHttpTests() {
            return stringHttpTests;
        }

        public Params setStringHttpTests(String stringHttpTests) {
            this.stringHttpTests = stringHttpTests;
            return this;
        }

        public String[] getListHttpTests() {
            return listHttpTests;
        }

        public Params setListHttpTests(String[] listHttpTests) {
            this.listHttpTests = listHttpTests;
            return this;
        }

        public String getStringItems() {
            return stringItems;
        }

        public Params setStringItems(String stringItems) {
            this.stringItems = stringItems;
            return this;
        }

        public String[] getListItems() {
            return listItems;
        }

        public Params setListItems(String[] listItems) {
            this.listItems = listItems;
            return this;
        }

        public String getStringDiscoveries() {
            return stringDiscoveries;
        }

        public Params setStringDiscoveries(String stringDiscoveries) {
            this.stringDiscoveries = stringDiscoveries;
            return this;
        }

        public String[] getListDiscoveries() {
            return listDiscoveries;
        }

        public Params setListDiscoveries(String[] listDiscoveries) {
            this.listDiscoveries = listDiscoveries;
            return this;
        }

        public String getStringGraphs() {
            return stringGraphs;
        }

        public Params setStringGraphs(String stringGraphs) {
            this.stringGraphs = stringGraphs;
            return this;
        }

        public String[] getListGraphs() {
            return listGraphs;
        }

        public Params setListGraphs(String[] listGraphs) {
            this.listGraphs = listGraphs;
            return this;
        }

        public String getStringApplications() {
            return stringApplications;
        }

        public Params setStringApplications(String stringApplications) {
            this.stringApplications = stringApplications;
            return this;
        }

        public String[] getListApplications() {
            return listApplications;
        }

        public Params setListApplications(String[] listApplications) {
            this.listApplications = listApplications;
            return this;
        }

        public String getStringTriggers() {
            return stringTriggers;
        }

        public Params setStringTriggers(String stringTriggers) {
            this.stringTriggers = stringTriggers;
            return this;
        }

        public String[] getListTriggers() {
            return listTriggers;
        }

        public Params setListTriggers(String[] listTriggers) {
            this.listTriggers = listTriggers;
            return this;
        }

        public String getStringMacros() {
            return stringMacros;
        }

        public Params setStringMacros(String stringMacros) {
            this.stringMacros = stringMacros;
            return this;
        }

        public String[] getListMacros() {
            return listMacros;
        }

        public Params setListMacros(String[] listMacros) {
            this.listMacros = listMacros;
            return this;
        }

        public String getStringScreens() {
            return stringScreens;
        }

        public Params setStringScreens(String stringScreens) {
            this.stringScreens = stringScreens;
            return this;
        }

        public String[] getListScreens() {
            return listScreens;
        }

        public Params setListScreens(String[] listScreens) {
            this.listScreens = listScreens;
            return this;
        }

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
                    ", stringGroups='" + stringGroups + '\'' +
                    ", listGroups=" + Arrays.toString(listGroups) +
                    ", stringHosts='" + stringHosts + '\'' +
                    ", listHosts=" + Arrays.toString(listHosts) +
                    ", stringTemplates='" + stringTemplates + '\'' +
                    ", listTemplates=" + Arrays.toString(listTemplates) +
                    ", stringParentTemplates='" + stringParentTemplates + '\'' +
                    ", listParentTemplates=" + Arrays.toString(listParentTemplates) +
                    ", stringHttpTests='" + stringHttpTests + '\'' +
                    ", listHttpTests=" + Arrays.toString(listHttpTests) +
                    ", stringItems='" + stringItems + '\'' +
                    ", listItems=" + Arrays.toString(listItems) +
                    ", stringDiscoveries='" + stringDiscoveries + '\'' +
                    ", listDiscoveries=" + Arrays.toString(listDiscoveries) +
                    ", stringGraphs='" + stringGraphs + '\'' +
                    ", listGraphs=" + Arrays.toString(listGraphs) +
                    ", stringApplications='" + stringApplications + '\'' +
                    ", listApplications=" + Arrays.toString(listApplications) +
                    ", stringTriggers='" + stringTriggers + '\'' +
                    ", listTriggers=" + Arrays.toString(listTriggers) +
                    ", stringMacros='" + stringMacros + '\'' +
                    ", listMacros=" + Arrays.toString(listMacros) +
                    ", stringScreens='" + stringScreens + '\'' +
                    ", listScreens=" + Arrays.toString(listScreens) +
                    '}';
        }
    }
}
