package com.ws.bix4j.access.host;

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
public class HostGetRequest extends ZRequest<HostGetRequest.Params>{
    public HostGetRequest() {
        this.setMethod("host.get");
    }

    private Params params = new Params();
    @Override
    public Params getParams() {
        return params;
    }

    public class Params extends GetRequestCommonParam {
        @JSONField(name = "groupids")
        private List<String> groupIds;
        private List<String> applications;
        @JSONField(name = "dserviceids")
        private List<String> dserviceIds;
        @JSONField(name = "graphids")
        private List<String> graphIds;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        @JSONField(name = "httptestids")
        private List<String> httpTestIds;
        @JSONField(name = "interfaceids")
        private List<String> interfaceIds;

        @JSONField(name = "itemids")
        private List<String> itemIds;
        @JSONField(name = "maintenanceids")
        private List<String> maintenanceIds;
        /**
         * 返回在监控中的主机 <br>
         * flag
         */
        @JSONField(name = "monitored_hosts")
        private boolean monitoredHosts;
        /**
         * 返回代理 <br>
         * flag
         */
        @JSONField(name = "proxy_hosts")
        private String proxyHosts;

        @JSONField(name = "proxyids")
        private List<String> proxyIds;

        /**
         * 返回主机和模版 <br>
         * flag
         */
        @JSONField(name = "templated_hosts")
        private String templatedHosts;

        @JSONField(name = "templateids")
        private List<String> templateIds;

        @JSONField(name = "triggerids")
        private List<String> triggerIds;

        /**
         * with 2016.6.8 add
         */
        @JSONField(name = "with_items")
        private boolean withItems;
        @JSONField(name = "with_applications")
        private boolean withApplications;
        @JSONField(name = "with_graphs")
        private boolean withGraphs;
        @JSONField(name = "with_httptests")
        private boolean withHttptests;
        @JSONField(name = "with_monitored_httptests")
        private boolean withMonitoredHttptests;
        @JSONField(name = "with_monitored_items")
        private boolean withMonitoredItems;
        @JSONField(name = "with_monitored_triggers")
        private boolean withMonitoredTriggers;
        @JSONField(name = "with_triggers")
        private boolean withTriggers;
        private boolean withInventory;

        /**
         * select参数 string类型的可能值为 "extend" "count"
         * list类型的值是返回对象的属性列表
         */
        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectGroups;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectApplications;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectItems;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectTriggers;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectInterfaces;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectParentTemplates;


        @Override
        public String toString() {
            return "Params{" +
                    "groupIds=" + groupIds +
                    ", applications=" + applications +
                    ", dserviceIds=" + dserviceIds +
                    ", graphIds=" + graphIds +
                    ", hostIds=" + hostIds +
                    ", httpTestIds=" + httpTestIds +
                    ", interfaceIds=" + interfaceIds +
                    ", itemIds=" + itemIds +
                    ", maintenanceIds=" + maintenanceIds +
                    ", monitoredHosts=" + monitoredHosts +
                    ", proxyHosts='" + proxyHosts + '\'' +
                    ", proxyIds=" + proxyIds +
                    ", templatedHosts='" + templatedHosts + '\'' +
                    ", templateIds=" + templateIds +
                    ", triggerIds=" + triggerIds +
                    ", withItems=" + withItems +
                    ", withApplications=" + withApplications +
                    ", withGraphs=" + withGraphs +
                    ", withHttptests=" + withHttptests +
                    ", withMonitoredHttptests=" + withMonitoredHttptests +
                    ", withMonitoredItems=" + withMonitoredItems +
                    ", withMonitoredTriggers=" + withMonitoredTriggers +
                    ", withTriggers=" + withTriggers +
                    ", withInventory=" + withInventory +
                    ", selectGroups=" + Arrays.toString(selectGroups) +
                    ", selectApplications=" + Arrays.toString(selectApplications) +
                    ", selectItems=" + Arrays.toString(selectItems) +
                    ", selectTriggers=" + Arrays.toString(selectTriggers) +
                    ", selectInterfaces=" + Arrays.toString(selectInterfaces) +
                    ", selectParentTemplates=" + Arrays.toString(selectParentTemplates) +
                    '}';
        }

        public List<String> getGroupIds() {
            return groupIds;
        }

        public Params setGroupIds(List<String> groupIds) {
            this.groupIds = groupIds;
            return this;
        }

        public List<String> getApplications() {
            return applications;
        }

        public Params setApplications(List<String> applications) {
            this.applications = applications;
            return this;
        }

        public List<String> getDserviceIds() {
            return dserviceIds;
        }

        public Params setDserviceIds(List<String> dserviceIds) {
            this.dserviceIds = dserviceIds;
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

        public List<String> getHttpTestIds() {
            return httpTestIds;
        }

        public Params setHttpTestIds(List<String> httpTestIds) {
            this.httpTestIds = httpTestIds;
            return this;
        }

        public List<String> getInterfaceIds() {
            return interfaceIds;
        }

        public Params setInterfaceIds(List<String> interfaceIds) {
            this.interfaceIds = interfaceIds;
            return this;
        }

        public List<String> getItemIds() {
            return itemIds;
        }

        public Params setItemIds(List<String> itemIds) {
            this.itemIds = itemIds;
            return this;
        }

        public List<String> getMaintenanceIds() {
            return maintenanceIds;
        }

        public Params setMaintenanceIds(List<String> maintenanceIds) {
            this.maintenanceIds = maintenanceIds;
            return this;
        }

        public boolean isMonitoredHosts() {
            return monitoredHosts;
        }

        public Params setMonitoredHosts(boolean monitoredHosts) {
            this.monitoredHosts = monitoredHosts;
            return this;
        }

        public String getProxyHosts() {
            return proxyHosts;
        }

        public Params setProxyHosts(String proxyHosts) {
            this.proxyHosts = proxyHosts;
            return this;
        }

        public List<String> getProxyIds() {
            return proxyIds;
        }

        public Params setProxyIds(List<String> proxyIds) {
            this.proxyIds = proxyIds;
            return this;
        }

        public String getTemplatedHosts() {
            return templatedHosts;
        }

        public Params setTemplatedHosts(String templatedHosts) {
            this.templatedHosts = templatedHosts;
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

        public boolean isWithItems() {
            return withItems;
        }

        public Params setWithItems(boolean withItems) {
            this.withItems = withItems;
            return this;
        }

        public boolean isWithApplications() {
            return withApplications;
        }

        public Params setWithApplications(boolean withApplications) {
            this.withApplications = withApplications;
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

        public boolean isWithMonitoredHttptests() {
            return withMonitoredHttptests;
        }

        public Params setWithMonitoredHttptests(boolean withMonitoredHttptests) {
            this.withMonitoredHttptests = withMonitoredHttptests;
            return this;
        }

        public boolean isWithMonitoredItems() {
            return withMonitoredItems;
        }

        public Params setWithMonitoredItems(boolean withMonitoredItems) {
            this.withMonitoredItems = withMonitoredItems;
            return this;
        }

        public boolean isWithMonitoredTriggers() {
            return withMonitoredTriggers;
        }

        public Params setWithMonitoredTriggers(boolean withMonitoredTriggers) {
            this.withMonitoredTriggers = withMonitoredTriggers;
            return this;
        }

        public boolean isWithTriggers() {
            return withTriggers;
        }

        public Params setWithTriggers(boolean withTriggers) {
            this.withTriggers = withTriggers;
            return this;
        }

        public boolean isWithInventory() {
            return withInventory;
        }

        public Params setWithInventory(boolean withInventory) {
            this.withInventory = withInventory;
            return this;
        }

        public String[] getSelectGroups() {
            return selectGroups;
        }

        public Params setSelectGroups(String[] selectGroups) {
            this.selectGroups = selectGroups;
            return this;
        }

        public String[] getSelectApplications() {
            return selectApplications;
        }

        public Params setSelectApplications(String[] selectApplications) {
            this.selectApplications = selectApplications;
            return this;
        }

        public String[] getSelectItems() {
            return selectItems;
        }

        public Params setSelectItems(String[] selectItems) {
            this.selectItems = selectItems;
            return this;
        }

        public String[] getSelectTriggers() {
            return selectTriggers;
        }

        public Params setSelectTriggers(String[] selectTriggers) {
            this.selectTriggers = selectTriggers;
            return this;
        }

        public String[] getSelectInterfaces() {
            return selectInterfaces;
        }

        public Params setSelectInterfaces(String[] selectInterfaces) {
            this.selectInterfaces = selectInterfaces;
            return this;
        }

        public String[] getSelectParentTemplates() {
            return selectParentTemplates;
        }

        public Params setSelectParentTemplates(String[] selectParentTemplates) {
            this.selectParentTemplates = selectParentTemplates;
            return this;
        }
    }
}
