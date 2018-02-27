package com.ws.stoner.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zhongkf on 2018/2/5
 */
public class ExcelTriggerVO {

    private String name;
    private String priority;
    private String expression;
    @JSONField(name = "manual_close")
    private String manualClose;
    private String comments;

    public String getName() {
        return name;
    }

    public ExcelTriggerVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public ExcelTriggerVO setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public ExcelTriggerVO setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public String getManualClose() {
        return manualClose;
    }

    public ExcelTriggerVO setManualClose(String manualClose) {
        this.manualClose = manualClose;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public ExcelTriggerVO setComments(String comments) {
        this.comments = comments;
        return this;
    }
}
