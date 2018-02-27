package com.ws.stoner.model;

import java.util.List;

/**
 * Created by zhongkf on 2018/2/6
 */
public class ExcelTemplateVO {

    private String name;
    private List<ExcelPointVO> pointVOS;
    private List<ExcelTriggerVO> triggers;
    private List<ExcelDDLVO> ddls;
    private List<ExcelTriggerVO> triggerProS;

    public String getName() {
        return name;
    }

    public ExcelTemplateVO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ExcelPointVO> getPointVOS() {
        return pointVOS;
    }

    public ExcelTemplateVO setPointVOS(List<ExcelPointVO> pointVOS) {
        this.pointVOS = pointVOS;
        return this;
    }

    public List<ExcelTriggerVO> getTriggers() {
        return triggers;
    }

    public ExcelTemplateVO setTriggers(List<ExcelTriggerVO> triggers) {
        this.triggers = triggers;
        return this;
    }

    public List<ExcelDDLVO> getDdls() {
        return ddls;
    }

    public ExcelTemplateVO setDdls(List<ExcelDDLVO> ddls) {
        this.ddls = ddls;
        return this;
    }

    public List<ExcelTriggerVO> getTriggerProS() {
        return triggerProS;
    }

    public ExcelTemplateVO setTriggerProS(List<ExcelTriggerVO> triggerProS) {
        this.triggerProS = triggerProS;
        return this;
    }

    @Override
    public String toString() {
        return "ExcelTemplateVO{" +
                "name='" + name + '\'' +
                ", pointVOS=" + pointVOS +
                ", triggers=" + triggers +
                ", ddls=" + ddls +
                ", triggerProS=" + triggerProS +
                '}';
    }
}
