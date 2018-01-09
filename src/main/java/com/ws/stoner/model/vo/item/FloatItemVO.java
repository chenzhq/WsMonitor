package com.ws.stoner.model.vo.item;

/**
 * Created by zhongkf on 2017/12/16
 */
public class FloatItemVO extends ItemVO {
    private Float lastValue;

    public FloatItemVO() {
    }

    public FloatItemVO(Float lastValue) {
        this.lastValue = lastValue;
    }

    public Float getLastValue() {
        return lastValue;
    }

    public FloatItemVO setLastValue(Float lastValue) {
        this.lastValue = lastValue;
        return this;
    }
}
