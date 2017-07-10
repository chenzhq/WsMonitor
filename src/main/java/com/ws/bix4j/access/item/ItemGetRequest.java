package com.ws.bix4j.access.item;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pc on 2017/6/9.
 */
public class ItemGetRequest extends ZRequest<ItemGetRequest.Params>{
    public ItemGetRequest() {
        this.setMethod("item.get");
    }

    private Params params = new Params();

    @Override
    public Params getParams() {
        return params;
    }

    public ItemGetRequest setParams(Params params) {
        this.params = params;
        return this;
    }

    public class Params extends GetRequestCommonParam {
        @JSONField(name = "itemids")
        private List<String> itemIds;
        @JSONField(name = "groupids")
        private List<String> groupIds;
        @JSONField(name = "templateids")
        private List<String> templateIds;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        @JSONField(name = "proxyids")
        private List<String> proxyIds;
        @JSONField(name = "interfaceids")
        private List<String> interfaceIds;
        @JSONField(name = "graphids")
        private List<String> graphIds;
        @JSONField(name = "triggerids")
        private List<String> triggerIds;
        @JSONField(name = "applicationids")
        private List<String> applicationIds;
        private boolean webitems;
        private boolean inherited;
        private boolean templated;
        private boolean monitored;
        private String group;
        private String host;
        private String application;
        @JSONField(name = "with_triggers")
        private boolean withTriggers;
        /**
         * select 参数
         */
        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectHosts;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectInterfaces;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectTriggers;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectApplications;

        @Override
        public String toString() {
            return "Params{" +
                    "itemIds=" + itemIds +
                    ", groupIds=" + groupIds +
                    ", templateIds=" + templateIds +
                    ", hostIds=" + hostIds +
                    ", proxyIds=" + proxyIds +
                    ", interfaceIds=" + interfaceIds +
                    ", graphIds=" + graphIds +
                    ", triggerIds=" + triggerIds +
                    ", applicationIds=" + applicationIds +
                    ", webitems=" + webitems +
                    ", inherited=" + inherited +
                    ", templated=" + templated +
                    ", monitored=" + monitored +
                    ", group='" + group + '\'' +
                    ", host='" + host + '\'' +
                    ", application='" + application + '\'' +
                    ", withTriggers=" + withTriggers +
                    ", selectHosts=" + Arrays.toString(selectHosts) +
                    ", selectInterfaces=" + Arrays.toString(selectInterfaces) +
                    ", selectTriggers=" + Arrays.toString(selectTriggers) +
                    ", selectApplications=" + Arrays.toString(selectApplications) +
                    '}';
        }

        public List<String> getItemIds() {
            return itemIds;
        }

        public Params setItemIds(List<String> itemIds) {
            this.itemIds = itemIds;
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

        public List<String> getProxyIds() {
            return proxyIds;
        }

        public Params setProxyIds(List<String> proxyIds) {
            this.proxyIds = proxyIds;
            return this;
        }

        public List<String> getInterfaceIds() {
            return interfaceIds;
        }

        public Params setInterfaceIds(List<String> interfaceIds) {
            this.interfaceIds = interfaceIds;
            return this;
        }

        public List<String> getGraphIds() {
            return graphIds;
        }

        public Params setGraphIds(List<String> graphIds) {
            this.graphIds = graphIds;
            return this;
        }

        public List<String> getTriggerIds() {
            return triggerIds;
        }

        public Params setTriggerIds(List<String> triggerIds) {
            this.triggerIds = triggerIds;
            return this;
        }

        public List<String> getApplicationIds() {
            return applicationIds;
        }

        public Params setApplicationIds(List<String> applicationIds) {
            this.applicationIds = applicationIds;
            return this;
        }

        public boolean isWebitems() {
            return webitems;
        }

        public Params setWebitems(boolean webitems) {
            this.webitems = webitems;
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

        public String getApplication() {
            return application;
        }

        public Params setApplication(String application) {
            this.application = application;
            return this;
        }

        public boolean isWithTriggers() {
            return withTriggers;
        }

        public Params setWithTriggers(boolean withTriggers) {
            this.withTriggers = withTriggers;
            return this;
        }

        public String[] getSelectHosts() {
            return selectHosts;
        }

        public Params setSelectHosts(String[] selectHosts) {
            this.selectHosts = selectHosts;
            return this;
        }

        public String[] getSelectInterfaces() {
            return selectInterfaces;
        }

        public Params setSelectInterfaces(String[] selectInterfaces) {
            this.selectInterfaces = selectInterfaces;
            return this;
        }

        public String[] getSelectTriggers() {
            return selectTriggers;
        }

        public Params setSelectTriggers(String[] selectTriggers) {
            this.selectTriggers = selectTriggers;
            return this;
        }

        public String[] getSelectApplications() {
            return selectApplications;
        }

        public Params setSelectApplications(String[] selectApplications) {
            this.selectApplications = selectApplications;
            return this;
        }
    }

}
