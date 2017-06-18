package com.ws.bix4j.access.item;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.ZRequest;

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
        @JSONField(name = "selectHosts")
        private String stringHosts;
        @JSONField(name = "selectHosts")
        private List<String> listHosts;

        @JSONField(name = "selectInterfaces")
        private String stringInterfaces;
        @JSONField(name = "selectInterfaces")
        private List<String> listInterfaces;

        @JSONField(name = "selectTriggers")
        private String stringTriggers;
        @JSONField(name = "selectTriggers")
        private List<String> listTriggers;

        @JSONField(name = "selectGraphs")
        private String stringGraphs;
        @JSONField(name = "selectGraphs")
        private List<String> listGraphs;

        @JSONField(name = "selectApplications")
        private String stringApplications;
        @JSONField(name = "selectApplications")
        private List<String> listApplications;

        @JSONField(name = "selectDiscoveryRule")
        private String stringDiscoveryRule;
        @JSONField(name = "selectDiscoveryRule")
        private List<String> listDiscoveryRule;

        @JSONField(name = "selectItemDiscovery")
        private String stringItemDiscovery;
        @JSONField(name = "selectItemDiscovery")
        private List<String> listItemDiscovery;

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
                    ", hostgroup='" + group + '\'' +
                    ", host='" + host + '\'' +
                    ", application='" + application + '\'' +
                    ", withTriggers=" + withTriggers +
                    ", stringHosts='" + stringHosts + '\'' +
                    ", listHosts=" + listHosts +
                    ", stringInterfaces='" + stringInterfaces + '\'' +
                    ", listInterfaces=" + listInterfaces +
                    ", stringTriggers='" + stringTriggers + '\'' +
                    ", listTriggers=" + listTriggers +
                    ", stringGraphs='" + stringGraphs + '\'' +
                    ", listGraphs=" + listGraphs +
                    ", stringApplications='" + stringApplications + '\'' +
                    ", listApplications=" + listApplications +
                    ", stringDiscoveryRule='" + stringDiscoveryRule + '\'' +
                    ", listDiscoveryRule=" + listDiscoveryRule +
                    ", stringItemDiscovery='" + stringItemDiscovery + '\'' +
                    ", listItemDiscovery=" + listItemDiscovery +
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

        public String getStringHosts() {
            return stringHosts;
        }

        public Params setStringHosts(String stringHosts) {
            this.stringHosts = stringHosts;
            return this;
        }

        public List<String> getListHosts() {
            return listHosts;
        }

        public Params setListHosts(List<String> listHosts) {
            this.listHosts = listHosts;
            return this;
        }

        public String getStringInterfaces() {
            return stringInterfaces;
        }

        public Params setStringInterfaces(String stringInterfaces) {
            this.stringInterfaces = stringInterfaces;
            return this;
        }

        public List<String> getListInterfaces() {
            return listInterfaces;
        }

        public Params setListInterfaces(List<String> listInterfaces) {
            this.listInterfaces = listInterfaces;
            return this;
        }

        public String getStringTriggers() {
            return stringTriggers;
        }

        public Params setStringTriggers(String stringTriggers) {
            this.stringTriggers = stringTriggers;
            return this;
        }

        public List<String> getListTriggers() {
            return listTriggers;
        }

        public Params setListTriggers(List<String> listTriggers) {
            this.listTriggers = listTriggers;
            return this;
        }

        public String getStringGraphs() {
            return stringGraphs;
        }

        public Params setStringGraphs(String stringGraphs) {
            this.stringGraphs = stringGraphs;
            return this;
        }

        public List<String> getListGraphs() {
            return listGraphs;
        }

        public Params setListGraphs(List<String> listGraphs) {
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

        public List<String> getListApplications() {
            return listApplications;
        }

        public Params setListApplications(List<String> listApplications) {
            this.listApplications = listApplications;
            return this;
        }

        public String getStringDiscoveryRule() {
            return stringDiscoveryRule;
        }

        public Params setStringDiscoveryRule(String stringDiscoveryRule) {
            this.stringDiscoveryRule = stringDiscoveryRule;
            return this;
        }

        public List<String> getListDiscoveryRule() {
            return listDiscoveryRule;
        }

        public Params setListDiscoveryRule(List<String> listDiscoveryRule) {
            this.listDiscoveryRule = listDiscoveryRule;
            return this;
        }

        public String getStringItemDiscovery() {
            return stringItemDiscovery;
        }

        public Params setStringItemDiscovery(String stringItemDiscovery) {
            this.stringItemDiscovery = stringItemDiscovery;
            return this;
        }

        public List<String> getListItemDiscovery() {
            return listItemDiscovery;
        }

        public Params setListItemDiscovery(List<String> listItemDiscovery) {
            this.listItemDiscovery = listItemDiscovery;
            return this;
        }
    }

}
