package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zkf on 2017/9/4.
 */
public class ProblemDetailDatasVO {

    @JSONField(name = "list_datas")
    private List<ProblemDetailListVO> listVOS;
    @JSONField(name = "graph_datas")
    private List<ProblemGraphVO> graphVOS;

    public ProblemDetailDatasVO() {
    }

    public ProblemDetailDatasVO(List<ProblemDetailListVO> listVOS, List<ProblemGraphVO> graphVOS) {
        this.listVOS = listVOS;
        this.graphVOS = graphVOS;
    }

    @Override
    public String toString() {
        return "ProblemDetailDatasVO{" +
                "listVOS=" + listVOS +
                ", graphVOS=" + graphVOS +
                '}';
    }

    public List<ProblemDetailListVO> getListVOS() {
        return listVOS;
    }

    public ProblemDetailDatasVO setListVOS(List<ProblemDetailListVO> listVOS) {
        this.listVOS = listVOS;
        return this;
    }

    public List<ProblemGraphVO> getGraphVOS() {
        return graphVOS;
    }

    public ProblemDetailDatasVO setGraphVOS(List<ProblemGraphVO> graphVOS) {
        this.graphVOS = graphVOS;
        return this;
    }
}
