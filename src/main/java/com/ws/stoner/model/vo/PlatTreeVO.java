package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.platform.ClusterVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class PlatTreeVO {

    private String id;
    private String name;
    private String state;
    private List<HostVO> details;
    private List<ClusterVO> clusters;

    public PlatTreeVO() {
    }

    @Override
    public String toString() {
        return "PlatTreeVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", details=" + details +
                ", clusters=" + clusters +
                '}';
    }

    public String getId() {
        return id;
    }

    public PlatTreeVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlatTreeVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public PlatTreeVO setState(String state) {
        this.state = state;
        return this;
    }

    public List<HostVO> getDetails() {
        return details;
    }

    public PlatTreeVO setDetails(List<HostVO> details) {
        this.details = details;
        return this;
    }

    public List<ClusterVO> getClusters() {
        return clusters;
    }

    public PlatTreeVO setClusters(List<ClusterVO> clusters) {
        this.clusters = clusters;
        return this;
    }

    public PlatTreeVO(String id, String name, String state, List<HostVO> details, List<ClusterVO> clusters) {

        this.id = id;
        this.name = name;
        this.state = state;
        this.details = details;
        this.clusters = clusters;
    }
}
