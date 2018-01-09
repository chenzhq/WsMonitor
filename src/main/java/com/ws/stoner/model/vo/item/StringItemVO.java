package com.ws.stoner.model.vo.item;

/**
 * Created by zhongkf on 2017/12/16
 */
public class StringItemVO extends ItemVO {

    private String lastValue;

    public StringItemVO() {
    }

    public StringItemVO(String lastValue) {
        this.lastValue = lastValue;
    }

    public String getLastValue() {
        return lastValue;
    }

    public StringItemVO setLastValue(String lastValue) {
        this.lastValue = lastValue;
        return this;
    }
}
