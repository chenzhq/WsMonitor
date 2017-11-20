package com.ws.stoner.model.DO.mongo.view;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by zkf on 2017/9/14.
 */
@Document(collection = "graph_view")
public class ProblemsView extends GraphView{
    @Field("hostids")
    private List<String> hostIds;
    @Field("max_num")
    private Integer maxNum;

    public ProblemsView() {
    }

    public ProblemsView(String name, String type, List<String> hostIds, Integer maxNum) {
        super(name, type);
        this.hostIds = hostIds;
        this.maxNum = maxNum;
    }

    public List<String> getHostIds() {
        return hostIds;
    }

    public ProblemsView setHostIds(List<String> hostIds) {
        this.hostIds = hostIds;
        return this;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public ProblemsView setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
        return this;
    }

    @Override
    public String toString() {
        return "ProblemsView{" +
                "hostIds=" + hostIds +
                ", maxNum=" + maxNum +
                "} " + super.toString();
    }
}
