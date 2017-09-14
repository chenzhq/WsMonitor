package com.ws.stoner.model.view.carousel;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zkf on 2017/9/14.
 */
public class ItemVO extends BlockVO {

    private List<Float> data;
    @JSONField(name = "data_time")
    private List<String> dataTime;
    private String unit;

    public ItemVO() {
    }

    public ItemVO(List<Float> data, List<String> dataTime, String unit) {
        this.data = data;
        this.dataTime = dataTime;
        this.unit = unit;
    }
}
