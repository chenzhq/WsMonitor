package com.ws.stoner.model;

import java.util.List;

/**
 * Created by zhongkf on 2018/2/6
 */
public class ExcelPointVO {
    private String name;
    private List<ExcelItemVO> items;

    public String getName() {
        return name;
    }

    public ExcelPointVO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ExcelItemVO> getItems() {
        return items;
    }

    public ExcelPointVO setItems(List<ExcelItemVO> items) {
        this.items = items;
        return this;
    }
}
