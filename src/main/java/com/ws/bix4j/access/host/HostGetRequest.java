package com.ws.bix4j.access.host;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.ZRequest;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/2.
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
        private String monitoredHosts;
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
         * with参数未实现
         */

        @JSONField(name = "selectGroups")
        private String stringGroups;
        @JSONField(name = "selectGroups")
        private List<String> listGroups;

        @JSONField(name = "selectApplications")
        private String stringApplications;
        @JSONField(name = "selectApplications")
        private List<String> listApplications;

        @JSONField(name = "selectItems")
        private String stringItems;
        @JSONField(name = "selectItems")
        private List<String> listItems;

        @JSONField(name = "selectTriggers")
        private String stringTriggers;
        @JSONField(name = "selectTriggers")
        private List<String> listTriggers;

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

        public String getMonitoredHosts() {
            return monitoredHosts;
        }

        public Params setMonitoredHosts(String monitoredHosts) {
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


        //以下为select参数设置

        /**
         *
         * @param queryString
         * @return
         */
        public Params setSelectGroups(String queryString) {
            this.stringGroups = queryString;
            return this;
        }

        public Params setSelectGroups(List<String> groupProps) {
            this.listGroups = groupProps;
            return this;
        }

        public Params setSelectApplications(String queryString) {
            this.stringApplications = queryString;
            return this;
        }

        public Params setListApplications(List<String> applicationProps) {
            this.listApplications = applicationProps;
            return this;
        }

        public Params setSelectItems(String queryString) {
            this.stringItems = queryString;
            return this;
        }

        public Params setSelectItems(List<String> itemProps) {
            this.listItems = listItems;
            return this;
        }

        public Params setSelectTriggers(String queryString) {
            this.stringTriggers = queryString;
            return this;
        }

        public Params setListTriggers(List<String> triggerProps) {
            this.listTriggers = triggerProps;
            return this;
        }
    }
}
