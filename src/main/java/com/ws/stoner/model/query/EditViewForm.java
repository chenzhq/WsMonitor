package com.ws.stoner.model.query;

import java.util.List;

/**
 * Created by zkf on 2017/9/20.
 */
public class EditViewForm {

    private String newName;
    private String oldName;
    private List<String> hostIds;
    private String type;
    private Integer maxNum;

    public String getNewName() {
        return newName;
    }

    public EditViewForm setNewName(String newName) {
        this.newName = newName;
        return this;
    }

    public String getOldName() {
        return oldName;
    }

    public EditViewForm setOldName(String oldName) {
        this.oldName = oldName;
        return this;
    }

    public List<String> getHostIds() {
        return hostIds;
    }

    public EditViewForm setHostIds(List<String> hostIds) {
        this.hostIds = hostIds;
        return this;
    }

    public String getType() {
        return type;
    }

    public EditViewForm setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public EditViewForm setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
        return this;
    }

    @Override
    public String toString() {
        return "EditViewForm{" +
                "newName='" + newName + '\'' +
                ", oldName='" + oldName + '\'' +
                ", hostIds=" + hostIds +
                ", type='" + type + '\'' +
                ", maxNum=" + maxNum +
                '}';
    }
}
