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

    //根据触发器的 priority 获取对应的状态text return "green，yellow，red"
    public static String getTextByTriggerPriority(Integer priority) {
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

}
