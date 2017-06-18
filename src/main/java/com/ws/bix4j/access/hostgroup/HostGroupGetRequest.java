package com.ws.bix4j.access.hostgroup;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.ZRequest;

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
    }



}
