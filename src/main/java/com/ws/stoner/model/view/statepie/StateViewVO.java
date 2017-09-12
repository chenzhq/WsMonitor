package com.ws.stoner.model.view.statepie;

/**
 * Created by zkf on 2017/9/12.
 */
public class StateViewVO {

    private StateNumVO hostStateNum;
    private StateNumVO pointStateNum;

    public StateViewVO() {
    }

    public StateViewVO(StateNumVO hostStateNum, StateNumVO pointStateNum) {
        this.hostStateNum = hostStateNum;
        this.pointStateNum = pointStateNum;
    }

    public StateNumVO getHostStateNum() {
        return hostStateNum;
    }

    public StateViewVO setHostStateNum(StateNumVO hostStateNum) {
        this.hostStateNum = hostStateNum;
        return this;
    }

    public StateNumVO getPointStateNum() {
        return pointStateNum;
    }

    public StateViewVO setPointStateNum(StateNumVO pointStateNum) {
        this.pointStateNum = pointStateNum;
        return this;
    }

    @Override
    public String toString() {
        return "StateViewVO{" +
                "hostStateNum=" + hostStateNum +
                ", pointStateNum=" + pointStateNum +
                '}';
    }
}
