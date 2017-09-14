package com.ws.stoner.model.DO.mongo.view;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by zkf on 2017/9/14.
 */
@Document(collection = "graph_view")
public class StateView extends GraphView {
    @Field("hostids")
    private List<String> hostIds;

    public StateView() {
    }

    public StateView(String name, String type, List<String> hostIds) {
        super(name, type);
        this.hostIds = hostIds;
    }


    public List<String> getHostIds() {
        return hostIds;
    }

    public StateView setHostIds(List<String> hostIds) {
        this.hostIds = hostIds;
        return this;
    }

    @Override
    public String toString() {
        return "StateView{" +
                "hostIds=" + hostIds +
                "} " + super.toString();
    }
}
