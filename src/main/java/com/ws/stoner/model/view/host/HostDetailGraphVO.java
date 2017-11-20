package com.ws.stoner.model.view.host;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/7/18.
 */
public class HostDetailGraphVO {

    @JSONField(name = "graph_type")
    private String graphType;
    @JSONField(name = "graph_name")
    private String graphName;

    @Override
    public String toString() {
        return "HostDetailGraphVO{" +
                "graphType='" + graphType + '\'' +
                ", graphName='" + graphName + '\'' +
                '}';
    }

    public String getGraphType() {
        return graphType;
    }

    public HostDetailGraphVO setGraphType(String graphType) {
        this.graphType = graphType;
        return this;
    }

    public String getGraphName() {
        return graphName;
    }

    public HostDetailGraphVO setGraphName(String graphName) {
        this.graphName = graphName;
        return this;
    }
}
