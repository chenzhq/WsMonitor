package com.ws.stoner.model.dto;

import com.ws.stoner.constant.HostStatusEnum;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public class StateNumDTO {

    private int totalNum;
    private List<StateNum> stateNum;

    public int getTotalNum() {
        return totalNum;
    }

    public StateNumDTO setTotalNum(int totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    public List<StateNum> getStateNum() {
        return stateNum;
    }

    public StateNumDTO setStateNum(List<StateNum> stateNum) {
        this.stateNum = stateNum;
        return this;
    }

    public static class StateNum {
        private String name;
        private int value;

        public StateNum(HostStatusEnum hostStatusEnum, int value) {
            this.name = hostStatusEnum.getName();
            this.value = value;
        }

        public StateNum(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public StateNum setName(String name) {
            this.name = name;
            return this;
        }

        public int getValue() {
            return value;
        }

        public StateNum setValue(int value) {
            this.value = value;
            return this;
        }

        @Override
        public String toString() {
            return "StateNum{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "StateNumDTO{" +
                "totalNum=" + totalNum +
                ", stateNum=" + stateNum +
                '}';
    }
}
