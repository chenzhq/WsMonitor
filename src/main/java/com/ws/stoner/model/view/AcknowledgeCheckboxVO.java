package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zkf on 2017/8/29.
 */
public class AcknowledgeCheckboxVO {


    @JSONField(name = "checkboxEnable")
    private boolean checkboxEnable;
    @JSONField(name = "disable_message")
    private String disableMessage;

    @Override
    public String toString() {
        return "AcknowledgeCheckboxVO{" +
                "checkboxEnable=" + checkboxEnable +
                ", disableMessage='" + disableMessage + '\'' +
                '}';
    }

    public boolean isCheckboxEnable() {
        return checkboxEnable;
    }

    public AcknowledgeCheckboxVO setCheckboxEnable(boolean checkboxEnable) {
        this.checkboxEnable = checkboxEnable;
        return this;
    }

    public String getDisableMessage() {
        return disableMessage;
    }

    public AcknowledgeCheckboxVO setDisableMessage(String disableMessage) {
        this.disableMessage = disableMessage;
        return this;
    }
}
