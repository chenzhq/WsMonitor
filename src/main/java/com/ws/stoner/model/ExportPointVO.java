package com.ws.stoner.model;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/29
 */
public class ExportPointVO {

    private String pointName;
    private List<ExportItemVO> items;

    public String getPointName() {
        return pointName;
    }

    public ExportPointVO setPointName(String pointName) {
        this.pointName = pointName;
        return this;
    }

    public List<ExportItemVO> getItems() {
        return items;
    }

    public ExportPointVO setItems(List<ExportItemVO> items) {
        this.items = items;
        return this;
    }
}

