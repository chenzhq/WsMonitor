package com.ws.bix4j.access.hostgroup;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.ZRequest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zkf on 2017/5/18.
 */
public class HostGroupGetRequest extends ZRequest<HostGroupGetRequest.Params> {
    public HostGroupGetRequest() {
        this.setMethod("hostgroup.get");
    }

    private Params params = new Params();
    @Override
    public Params getParams() {
        return params;
    }

    public class Params extends GetRequestCommonParam {
        /**
         * 常用参数
         */
        @JSONField(name = "graphids")
        private List<String> graphIds;
        @JSONField(name = "groupids")
        private List<String> groupIds;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        @JSONField(name = "maintenanceids")
        private List<String> maintenanceIds;
        @JSONField(name = "monitored_hosts")
        private boolean monitoredHosts;
        @JSONField(name = "real_hosts")
        private boolean realHosts;
        @JSONField(name = "templated_hosts")
        private String templatedHosts;
        @JSONField(name = "templateids")
        private List<String> templateIds;
        @JSONField(name = "triggerids")
        private List<String> triggerIds;
        /**
         * with 参数
         */


        /**
         * select 参数
         */
        @JSONField(name = "selectDiscoveryRule")
        private String stringDiscoveryRule;
        @JSONField(name = "selectDiscoveryRule")
        private String[] listDiscoveryRule;

        @JSONField(name = "selectGroupDiscovery")
        private String stringGroupDiscovery;
        @JSONField(name = "selectGroupDiscovery")
        private String[] listGroupDiscovery;

//        @JSONField(name = "selectHosts")
//        private String stringHosts;

        public String[] getListHosts() {
            return listHosts;
        }

        public Params setListHosts(String[] listHosts) {
            this.listHosts = listHosts;
            return this;
        }

        @JSONField(name = "selectHosts")
        private String[] listHosts;

        @JSONField(name = "selectTemplates")
        private String stringTemplates;
        @JSONField(name = "selectTemplates")
        private String[] listTemplates;


        /**
         * 其他参数
         */



        public List<String> getGraphIds() {
            return graphIds;
        }

        public Params setGraphIds(List<String> graphIds) {
            this.graphIds = graphIds;
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

        public boolean getRealHosts() {
            return realHosts;
        }

        public Params setRealHosts(boolean realHosts) {
            this.realHosts = realHosts;
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

        public boolean isRealHosts() {
            return realHosts;
        }

        public String getStringDiscoveryRule() {
            return stringDiscoveryRule;
        }

        public Params setStringDiscoveryRule(String stringDiscoveryRule) {
            this.stringDiscoveryRule = stringDiscoveryRule;
            return this;
        }

        public String[] getListDiscoveryRule() {
            return listDiscoveryRule;
        }

        public Params setListDiscoveryRule(String[] listDiscoveryRule) {
            this.listDiscoveryRule = listDiscoveryRule;
            return this;
        }

        public String getStringGroupDiscovery() {
            return stringGroupDiscovery;
        }

        public Params setStringGroupDiscovery(String stringGroupDiscovery) {
            this.stringGroupDiscovery = stringGroupDiscovery;
            return this;
        }

        public String[] getListGroupDiscovery() {
            return listGroupDiscovery;
        }

        public Params setListGroupDiscovery(String[] listGroupDiscovery) {
            this.listGroupDiscovery = listGroupDiscovery;
            return this;
        }

        @Override
        public String toString() {
            return "Params{" +
                    "graphIds=" + graphIds +
                    ", groupIds=" + groupIds +
                    ", hostIds=" + hostIds +
                    ", maintenanceIds=" + maintenanceIds +
                    ", monitoredHosts=" + monitoredHosts +
                    ", realHosts=" + realHosts +
                    ", templatedHosts='" + templatedHosts + '\'' +
                    ", templateIds=" + templateIds +
                    ", triggerIds=" + triggerIds +
                    ", stringDiscoveryRule='" + stringDiscoveryRule + '\'' +
                    ", listDiscoveryRule=" + Arrays.toString(listDiscoveryRule) +
                    ", stringGroupDiscovery='" + stringGroupDiscovery + '\'' +
                    ", listGroupDiscovery=" + Arrays.toString(listGroupDiscovery) +
                    ", listHosts=" + Arrays.toString(listHosts) +
                    ", stringTemplates='" + stringTemplates + '\'' +
                    ", listTemplates=" + Arrays.toString(listTemplates) +
                    '}';
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
    }



}
