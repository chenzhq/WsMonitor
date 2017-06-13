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
        private String monitoredHosts;
        @JSONField(name = "real_hosts")
        private String realHosts;
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

        public void setGraphIds(List<String> graphIds) {
            this.graphIds = graphIds;
        }

        public List<String> getGroupIds() {
            return groupIds;
        }

        public void setGroupIds(List<String> groupIds) {
            this.groupIds = groupIds;
        }

        public List<String> getHostIds() {
            return hostIds;
        }

        public void setHostIds(List<String> hostIds) {
            this.hostIds = hostIds;
        }

        public List<String> getMaintenanceIds() {
            return maintenanceIds;
        }

        public void setMaintenanceIds(List<String> maintenanceIds) {
            this.maintenanceIds = maintenanceIds;
        }

        public String getMonitoredHosts() {
            return monitoredHosts;
        }

        public void setMonitoredHosts(String monitoredHosts) {
            this.monitoredHosts = monitoredHosts;
        }

        public String getRealHosts() {
            return realHosts;
        }

        public void setRealHosts(String realHosts) {
            this.realHosts = realHosts;
        }

        public String getTemplatedHosts() {
            return templatedHosts;
        }

        public void setTemplatedHosts(String templatedHosts) {
            this.templatedHosts = templatedHosts;
        }

        public List<String> getTemplateIds() {
            return templateIds;
        }

        public void setTemplateIds(List<String> templateIds) {
            this.templateIds = templateIds;
        }

        public List<String> getTriggerIds() {
            return triggerIds;
        }

        public void setTriggerIds(List<String> triggerIds) {
            this.triggerIds = triggerIds;
        }
    }



}
