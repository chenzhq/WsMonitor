package com.ws.stoner.model.vo.stat;

/**
 * Created by zhongkf on 2017/12/7
 */
public class StateVO {
    private Integer ok;
    private Integer warning;
    private Integer high;
    private Integer total;

    public StateVO() {

    }

    public StateVO(Integer ok, Integer warning, Integer high, Integer total) {
        this.ok = ok;
        this.warning = warning;
        this.high = high;
        this.total = total;
    }

    public Integer getOk() {
        return ok;
    }

    public StateVO setOk(Integer ok) {
        this.ok = ok;
        return this;
    }

    public Integer getWarning() {
        return warning;
    }

    public StateVO setWarning(Integer warning) {
        this.warning = warning;
        return this;
    }

    public Integer getHigh() {
        return high;
    }

    public StateVO setHigh(Integer high) {
        this.high = high;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public StateVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

    @Override
    public String toString() {
        return "StateVO{" +
                "ok=" + ok +
                ", warning=" + warning +
                ", high=" + high +
                ", total=" + total +
                '}';
    }
}
