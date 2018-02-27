package com.ws.stoner.utils;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.constant.StatusEnum;

/**
 * Created by zkf on 2017/7/19.
 */
/*
对象状态转换器
 */
public class StatusConverter {
/*
host point item platform 等对象状态转换方法
 */
    //return "正常，警告，严重"
    public static String StatusTransform(int customStatus) {
        String status = "";
        if(StatusEnum.WARNING.code == customStatus) {
            status = StatusEnum.WARNING.getName();
        }else if(StatusEnum.HIGH.code == customStatus){
            status = StatusEnum.HIGH.getName();
        }else if(StatusEnum.OK.code == customStatus) {
            status = StatusEnum.OK.getName();
        }
        return status;
    }
    //return "正常，警告，严重" 重载
    public static String StatusTransform(int customStatus,int customAvailableState) {
        String status = "";
        if(StatusEnum.OK.code == customStatus && StatusEnum.OK.code == customAvailableState) {
            status = StatusEnum.OK.getName();
        }else if(StatusEnum.WARNING.code == customStatus && StatusEnum.OK.code == customAvailableState) {
            status = StatusEnum.WARNING.getName();
        }else if(StatusEnum.HIGH.code == customStatus && StatusEnum.OK.code == customAvailableState) {
            status = StatusEnum.HIGH.getName();
        }else if(StatusEnum.WARNING.code == customAvailableState) {
            status = StatusEnum.HIGH.getName();
        }else {
            status = StatusEnum.OK.getName();
        }
        return status;
    }

    //return "ok，warning，high"
    public static String getTextStatusTransform(int customStatus) {
        String status = "";
        if(StatusEnum.WARNING.code == customStatus) {
            status = StatusEnum.WARNING.text;
        }else if(StatusEnum.HIGH.code == customStatus){
            status = StatusEnum.HIGH.text;
        }else if(StatusEnum.OK.code == customStatus) {
            status = StatusEnum.OK.text;
        }
        return status;
    }
    //return "ok，warning，high"  重载
    public static String getTextStatusTransform(int customStatus,int customAvailableState) {
        String status = "";
        if(StatusEnum.OK.code == customStatus && StatusEnum.OK.code == customAvailableState) {
            status = StatusEnum.OK.text;
        }else if(StatusEnum.WARNING.code == customStatus && StatusEnum.OK.code == customAvailableState) {
            status = StatusEnum.WARNING.text;
        }else if(StatusEnum.HIGH.code == customStatus && StatusEnum.OK.code == customAvailableState) {
            status = StatusEnum.HIGH.text;
        }else if(StatusEnum.WARNING.code == customAvailableState) {
            status = StatusEnum.HIGH.text;
        }else {
            status = StatusEnum.OK.text;
        }
        return status;
    }

    //return "ok，warning，high"  监控项状态转换方法
    public static String statusTransForItem(int customStatus,int weight) {
        String status = "";
        if(StatusEnum.WARNING.code == customStatus ) {
            status = StatusEnum.WARNING.text;
        }else if(StatusEnum.HIGH.code == customStatus){
            status = StatusEnum.HIGH.text;
        }else if(StatusEnum.OK.code == customStatus && weight != 0) {
            status = StatusEnum.OK.text;
        }
        return status;
    }


    //return "green，yellow，red"
    public static String colorTransform(int customStatus) {
        String color = "";
        if(StatusEnum.WARNING.code == customStatus) {
            color = StatusEnum.WARNING.color;
        }else if(StatusEnum.HIGH.code == customStatus){
            color = StatusEnum.HIGH.color;
        }else if(StatusEnum.OK.code == customStatus) {
            color = StatusEnum.OK.color;
        }
        return color;
    }

    //return "green，yellow，red" 重载
    public static String colorTransform(int customStatus,int customAvailableState) {
        String color ;
        if(StatusEnum.OK.code == customStatus && StatusEnum.OK.code == customAvailableState) {
            color = StatusEnum.OK.color;
        }else if(StatusEnum.WARNING.code == customStatus && StatusEnum.OK.code == customAvailableState) {
            color = StatusEnum.WARNING.color;
        }else if(StatusEnum.HIGH.code == customStatus && StatusEnum.OK.code == customAvailableState) {
            color = StatusEnum.HIGH.color;
        }else if(StatusEnum.WARNING.code == customAvailableState) {
            color = StatusEnum.HIGH.color;
        }else {
            color = StatusEnum.OK.color;
        }
        return color;
    }


    /*
 监控项值、类型转换
  */
    //根据 zbx 的 valuetye 和 datatype 转换成 float、integer、Boolean、text、log、string
    public static String valueTypeTransform(String valueTypeString,String dataTypeString) {
        int valueType = Integer.parseInt(valueTypeString);
        int dataType = 0;
        if(dataTypeString != "" && dataTypeString != null) {
            dataType = Integer.parseInt(dataTypeString);
        }
        String type = "";
        if(ZApiParameter.ITEM_VALUE_TYPE.NUMERIC_FLOAT.value == valueType) {
            type = "float";
        }else if(ZApiParameter.ITEM_VALUE_TYPE.NUMERIC_UNSIGNED.value == valueType) {
            if(ZApiParameter.ITEM_DATA_TYPE.BOOLEAN.value == dataType) {
                type = "boolean";
            }else {
                type = "integer";
            }
        }else if(ZApiParameter.ITEM_VALUE_TYPE.TEXT.value == valueType) {
            type = "text";
        }else if(ZApiParameter.ITEM_VALUE_TYPE.LOG.value == valueType) {
            type = "log";
        }else if(ZApiParameter.ITEM_VALUE_TYPE.CHARACTOR.value == valueType) {
            type = "String";
        }
        return type;
    }

    //item 信息类型映射
    public static String getValueTypeString(String valueTypeString) {
        String typeString = "";
        int valueType = Integer.parseInt(valueTypeString);
        if(ZApiParameter.ITEM_VALUE_TYPE.NUMERIC_UNSIGNED.value == valueType) {
            typeString = "数字(无正负)";
        }else if(ZApiParameter.ITEM_VALUE_TYPE.NUMERIC_FLOAT.value == valueType) {
            typeString = "浮点数";
        }else if(ZApiParameter.ITEM_VALUE_TYPE.CHARACTOR.value == valueType) {
            typeString = "字符";
        }else if(ZApiParameter.ITEM_VALUE_TYPE.TEXT.value == valueType) {
            typeString = "文本";
        }else if(ZApiParameter.ITEM_VALUE_TYPE.LOG.value == valueType) {
            typeString = "日志";
        }else {
            typeString = "";
        }
        return typeString;
    }

    //return "正常，警告，严重" 根据阀值和表达式判断出状态值
    public static String getStatusByThresholdValue(Float value,Float warningPointValue,Float highPointValue,String symbol) {
        String status = "";
        Float point;
        Boolean highLevel;
       switch (symbol) {
           case ">":
               if(warningPointValue != null && value < warningPointValue) {
                   status = StatusEnum.OK.getName();
               }else if(warningPointValue == null && value < highPointValue) {
                   status = StatusEnum.OK.getName();
               }else if(highPointValue == null && value > warningPointValue) {
                   status = StatusEnum.WARNING.getName();
               }else if(warningPointValue != null && highPointValue != null && value >= warningPointValue && value <= highPointValue) {
                   status = StatusEnum.WARNING.getName();
               }else if(highPointValue != null && value > highPointValue) {
                   status = StatusEnum.HIGH.getName();
               }
               break;
           case "<":
               if(warningPointValue != null && value > warningPointValue) {
                   status = StatusEnum.OK.getName();
               }else if(warningPointValue == null && value > highPointValue) {
                   status = StatusEnum.OK.getName();
               }else if(highPointValue == null && value < warningPointValue) {
                   status = StatusEnum.WARNING.getName();
               }else if(warningPointValue != null && highPointValue != null && value <= warningPointValue && value >= highPointValue) {
                   status = StatusEnum.WARNING.getName();
               }else if(highPointValue != null && value < highPointValue) {
                   status = StatusEnum.HIGH.getName();
               }
               break;
           case "<>":
               point = warningPointValue != null ? warningPointValue : highPointValue;
               highLevel = warningPointValue != null ? false : true;
               if(Float.compare(value,point) == 0) {
                   status = StatusEnum.OK.getName();
               }else if(highLevel && Float.compare(value,point) != 0) {
                   status = StatusEnum.HIGH.getName();
               }else if(!highLevel && Float.compare(value,point) != 0) {
                   status = StatusEnum.WARNING.getName();
               }
               break;
           case "=":
               point = warningPointValue != null ? warningPointValue : highPointValue;
               highLevel = warningPointValue != null ? false : true;
               if(Float.compare(value,point) != 0) {
                   status = StatusEnum.OK.getName();
               }else if(highLevel && Float.compare(value,point) == 0) {
                   status = StatusEnum.HIGH.getName();
               }else if(!highLevel && Float.compare(value,point) == 0) {
                   status = StatusEnum.WARNING.getName();
               }
               break;
       }
       return status;
    }

    //根据触发器的 priority 获取对应的状态名称 return "正常，警告，严重"
    public static String getStatusByTriggerPriority(Integer priority) {
        //PriorityState
        if(priority.equals(ZApiParameter.TRIGGER_PRIORITY.WARNING.value) ) {
            return StatusEnum.WARNING.getName();
        }else if(priority.equals(ZApiParameter.TRIGGER_PRIORITY.HIGH.value)) {
            return StatusEnum.HIGH.getName();
        }else if(priority.equals(ZApiParameter.TRIGGER_PRIORITY.INFORMATION.value)) {
            return StatusEnum.WARNING.getName();
        }else {
            return StatusEnum.OK.getName();
        }

    }

    //根据触发器的 priority 获取对应的状态名称 return "ok,high,warning"
    public static String getStatusTextByTriggerPriority(Integer priority) {
        //PriorityState
        if(priority.equals(ZApiParameter.TRIGGER_PRIORITY.WARNING.value) ) {
            return StatusEnum.WARNING.text;
        }else if(priority.equals(ZApiParameter.TRIGGER_PRIORITY.HIGH.value)) {
            return StatusEnum.HIGH.text;
        }else if(priority.equals(ZApiParameter.TRIGGER_PRIORITY.INFORMATION.value)) {
            return StatusEnum.INFO.text;
        }else {
            return StatusEnum.OK.text;
        }

    }

    //根据触发器的 priority 获取对应的状态text return "green，yellow，red"
    public static String getColorByTriggerPriority(Integer priority) {
        //PriorityState
        if(priority.equals(ZApiParameter.TRIGGER_PRIORITY.WARNING.value) ) {
            return StatusEnum.WARNING.color;
        }else if(priority.equals(ZApiParameter.TRIGGER_PRIORITY.HIGH.value)) {
            return StatusEnum.HIGH.color;
        }else if(priority.equals(ZApiParameter.TRIGGER_PRIORITY.INFORMATION.value)) {
            return StatusEnum.WARNING.color;
        }else {
            return StatusEnum.OK.color;
        }

    }

    //事件状态转换 将 0 和 1 转换成 "ok" 和 "problem"
    public static String getEventValueState(Integer eventValue) {
        String eventState = "";
        if(eventValue.equals(ZApiParameter.EVENT_VALUE.OK.value)) {
            eventState = "ok";
        }else if(eventValue.equals(ZApiParameter.EVENT_VALUE.PROBLEM.value)) {
            eventState = "problem";
        }else {

        }
        return eventState;
    }

    //告警状态转换  success fail sending
    public static String transAlertState(Integer alertStatus) {
        String description ;
        if(alertStatus.equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_SENDING.value)) {
            description =  "sending";
        }else if(alertStatus.equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_FAILED.value)) {
            description = "failure";
        }else {
            description = "success";
        }
        return description;
    }



}
