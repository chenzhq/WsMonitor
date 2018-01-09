package com.ws.stoner.model.vo.item;

/**
 * Created by zhongkf on 2017/12/16
 */
public class LongItemVO extends ItemVO {


    private Long lastValue;

    public Long getLastValue() {
        return lastValue;
    }

    public LongItemVO setLastValue(Long lastValue) {
        this.lastValue = lastValue;
        return this;
    }

    public LongItemVO(Long lastValue) {

        this.lastValue = lastValue;
    }

    public LongItemVO() {

    }
}
