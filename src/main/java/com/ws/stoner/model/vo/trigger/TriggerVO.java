package com.ws.stoner.model.vo.trigger;

/**
 * Created by zhongkf on 2017/12/8
 */
public class TriggerVO {

    private Float warningValue;
    private String warningSymbol;
    private Float highValue;
    private String highSymbol;

    public TriggerVO() {
    }

    public TriggerVO(Float warningValue, String warningSymbol, Float highValue, String highSymbol) {
        this.warningValue = warningValue;
        this.warningSymbol = warningSymbol;
        this.highValue = highValue;
        this.highSymbol = highSymbol;
    }

    @Override
    public String toString() {
        return "TriggerVO{" +
                "warningValue=" + warningValue +
                ", warningSymbol='" + warningSymbol + '\'' +
                ", highValue=" + highValue +
                ", highSymbol='" + highSymbol + '\'' +
                '}';
    }

    public Float getWarningValue() {
        return warningValue;
    }

    public TriggerVO setWarningValue(Float warningValue) {
        this.warningValue = warningValue;
        return this;
    }

    public String getWarningSymbol() {
        return warningSymbol;
    }

    public TriggerVO setWarningSymbol(String warningSymbol) {
        this.warningSymbol = warningSymbol;
        return this;
    }

    public Float getHighValue() {
        return highValue;
    }

    public TriggerVO setHighValue(Float highValue) {
        this.highValue = highValue;
        return this;
    }

    public String getHighSymbol() {
        return highSymbol;
    }

    public TriggerVO setHighSymbol(String highSymbol) {
        this.highSymbol = highSymbol;
        return this;
    }
}
