package com.ws.bix4j.access.hostInterface;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.ZRequest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zkf on 2017/5/18.
 */
public class HostInterfaceGetRequest extends ZRequest<HostInterfaceGetRequest.Params> {
    public HostInterfaceGetRequest() {
        this.setMethod("hostinterface.get");
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
        @JSONField(name = "hostids")
        private List<String> hostIds;
        @JSONField(name = "interfaceids")
        private List<String> interfaceIds;
        @JSONField(name = "itemids")
        private List<String> itemIds;
        @JSONField(name = "triggerids")
        private List<String> triggerIds;

        /**
         * with 参数
         */
        /**
         * select 参数
         */
        @JSONField(name = "selectItems")
        private String stringItems;
        @JSONField(name = "selectItems")
        private String[] listItems;

        @JSONField(name = "selectHosts")
        private String stringHosts;
        @JSONField(name = "selectHosts")
        private String[] listHosts;

        @Override
        public String toString() {
            return "Params{" +
                    "hostIds=" + hostIds +
                    ", interfaceIds=" + interfaceIds +
                    ", itemIds=" + itemIds +
                    ", triggerIds=" + triggerIds +
                    ", stringItems='" + stringItems + '\'' +
                    ", listItems=" + Arrays.toString(listItems) +
                    ", stringHosts='" + stringHosts + '\'' +
                    ", listHosts=" + Arrays.toString(listHosts) +
                    '}';
        }

        public List<String> getHostIds() {
            return hostIds;
        }

        public Params setHostIds(List<String> hostIds) {
            this.hostIds = hostIds;
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

        public List<String> getTriggerIds() {
            return triggerIds;
        }

        public Params setTriggerIds(List<String> triggerIds) {
            this.triggerIds = triggerIds;
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
    }



}
