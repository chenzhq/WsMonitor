package com.ws.bix4j.access.LLD;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/30
 */
public class DiscoGetRequest extends ZRequest<DiscoGetRequest.Params> {

    public DiscoGetRequest() {
        this.setMethod("discoveryrule.get");
    }

    private Params params = new Params();

    @Override
    public Params getParams() {
        return params;
    }
    public class Params extends GetRequestCommonParam {

        @JSONField(name = "itemids")
        private List<String> itemIds;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        private boolean inherited;
        @JSONField(name = "interfaceids")
        private List<String> interfaceIds;
        private boolean monitored;
        private boolean templated;
        @JSONField(name = "templateids")
        private List<String> templateIds;


        /**
         * select参数 string类型的可能值为 "extend" "count"
         * list类型的值是返回对象的属性列表
         */
        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectFilter;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectGraphs;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectHostPrototypes;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectHosts;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectItems;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectTriggers;

        public List<String> getItemIds() {
            return itemIds;
        }

        public Params setItemIds(List<String> itemIds) {
            this.itemIds = itemIds;
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

        public List<String> getInterfaceIds() {
            return interfaceIds;
        }

        public Params setInterfaceIds(List<String> interfaceIds) {
            this.interfaceIds = interfaceIds;
            return this;
        }

        public boolean isMonitored() {
            return monitored;
        }

        public Params setMonitored(boolean monitored) {
            this.monitored = monitored;
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

        public String[] getSelectFilter() {
            return selectFilter;
        }

        public Params setSelectFilter(String[] selectFilter) {
            this.selectFilter = selectFilter;
            return this;
        }

        public String[] getSelectGraphs() {
            return selectGraphs;
        }

        public Params setSelectGraphs(String[] selectGraphs) {
            this.selectGraphs = selectGraphs;
            return this;
        }

        public String[] getSelectHostPrototypes() {
            return selectHostPrototypes;
        }

        public Params setSelectHostPrototypes(String[] selectHostPrototypes) {
            this.selectHostPrototypes = selectHostPrototypes;
            return this;
        }

        public String[] getSelectHosts() {
            return selectHosts;
        }

        public Params setSelectHosts(String[] selectHosts) {
            this.selectHosts = selectHosts;
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
    }
}
