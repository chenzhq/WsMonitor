package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zkf on 2017/8/29.
 */
public class AcknowledgeCheckboxVO {


    @JSONField(name = "checkbox_enable")
    private boolean checkboxEnable;
    @JSONField(name = "disable_message")
    private String disableMessage;
    @JSONField(name = "acknowledge_history")
    private List<ProblemAcknowledgeVO> acknowledgeHistory;

    @Override
    public String toString() {
        return "AcknowledgeCheckboxVO{" +
                "checkboxEnable=" + checkboxEnable +
                ", disableMessage='" + disableMessage + '\'' +
                ", acknowledgeHistory=" + acknowledgeHistory +
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

    public List<ProblemAcknowledgeVO> getAcknowledgeHistory() {
        return acknowledgeHistory;
    }

    public AcknowledgeCheckboxVO setAcknowledgeHistory(List<ProblemAcknowledgeVO> acknowledgeHistory) {
        this.acknowledgeHistory = acknowledgeHistory;
        return this;
    }
}
