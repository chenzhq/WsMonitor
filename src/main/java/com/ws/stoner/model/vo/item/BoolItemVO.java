package com.ws.stoner.model.vo.item;

/**
 * Created by zhongkf on 2017/12/16
 */
public class BoolItemVO extends ItemVO {

    private boolean lastValue;

    public BoolItemVO(boolean lastValue) {
        this.lastValue = lastValue;
    }

    public BoolItemVO() {

    }

    public boolean isLastValue() {
        return lastValue;
    }

    public BoolItemVO setLastValue(boolean lastValue) {
        this.lastValue = lastValue;
        return this;
    }
}
