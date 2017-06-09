package com.ws.bix4j.access.application;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.ZRequest;

import java.util.List;

/**
 * Created by pc on 2017/6/7.
 */
public class ApplicationGetRequest  extends ZRequest<ApplicationGetRequest.Params> {
    public ApplicationGetRequest() {
        this.setMethod("application.get");
    }
    private Params params = new Params();

    @Override
    public Params getParams() {
        return params;
    }

    public class Params extends GetRequestCommonParam {
        @JSONField(name = "applicationids")
        private List<String> applicationIds;
        @JSONField(name = "groupids")
        private List<String> groupIds;
        @JSONField(name = "hostids")
        private List<String> hostIds;

        //如果为true ，返回继承魔板中的监控点
        private boolean inherited;

        @JSONField(name = "itemids")
        private List<String> itemIds;

        //如果设置为true仅返回属于模板的监控点
        private boolean templated;

        //仅返回属于给定模板的应用程序
        @JSONField(name = "templateids")
        private List<String> templateIds;
        /**
         * select 参数
         */
        //在host属性中返回应用程序所属的主机
        private List<String> selectHost;

        //返回属性中应用程序中包含的项目items
        private List<String> selectItems;

        //返回在属性中创建监控点的LLD规则discoveryRule
        private List<String> selectDiscoveryRule;

        //返回在属性中创建监控点的自动发现监控点对象discoveryRule
        private List<String> selectApplicationDiscovery;

        public List<String> getApplicationIds() {
            return applicationIds;
        }

        public Params setApplicationIds(List<String> applicationIds) {
            this.applicationIds = applicationIds;
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

        public List<String> getSelectHost() {
            return selectHost;
        }

        public Params setSelectHost(List<String> selectHost) {
            this.selectHost = selectHost;
            return this;
        }

        public List<String> getSelectItems() {
            return selectItems;
        }

        public Params setSelectItems(List<String> selectItems) {
            this.selectItems = selectItems;
            return this;
        }

        public List<String> getSelectDiscoveryRule() {
            return selectDiscoveryRule;
        }

        public Params setSelectDiscoveryRule(List<String> selectDiscoveryRule) {
            this.selectDiscoveryRule = selectDiscoveryRule;
            return this;
        }

        public List<String> getSelectApplicationDiscovery() {
            return selectApplicationDiscovery;
        }

        public Params setSelectApplicationDiscovery(List<String> selectApplicationDiscovery) {
            this.selectApplicationDiscovery = selectApplicationDiscovery;
            return this;
        }
    }
}
