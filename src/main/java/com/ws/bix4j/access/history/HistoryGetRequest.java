package com.ws.bix4j.access.history;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.ZRequest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by zkf on 2017/7/12.
 */
public class HistoryGetRequest extends ZRequest<HistoryGetRequest.Params> {

    public HistoryGetRequest() {
        this.setMethod("history.get");
    }

    private HistoryGetRequest.Params params = new HistoryGetRequest.Params();
    @Override
    public HistoryGetRequest.Params getParams() {
        return params;
    }

    public class Params extends GetRequestCommonParam {
        private Integer history;
        @JSONField(name = "hostids")
        private List<String> hostIds;
        @JSONField(name = "itemids")
        private List<String> itemIds;
        @JSONField(name = "time_from")
        private String timeFrom;
        @JSONField(name = "time_till")
        private String timeTill;


        public Integer getHistory() {
            return history;
        }

        public Params setHistory(Integer history) {
            this.history = history;
            return this;
        }

        public List<String> getHostIds() {
            return hostIds;
        }

        public Params setHostIds(List<String> hostIds) {
            this.hostIds = hostIds;
            return this;
        }

        public List<String> getItemIds() {
            return itemIds;
        }

        public Params setItemIds(List<String> itemIds) {
            this.itemIds = itemIds;
            return this;
        }

        public String getTimeFrom() {
            return timeFrom;
        }

        public Params setTimeFrom(String timeFrom) {
            this.timeFrom = timeFrom;
            return this;
        }

        public String getTimeTill() {
            return timeTill;
        }

        public Params setTimeTill(String timeTill) {
            this.timeTill = timeTill;
            return this;
        }
    }

}
