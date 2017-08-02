package com.ws.stoner.utils;

import com.ws.stoner.constant.StatusEnum;

/**
 * Created by zkf on 2017/7/19.
 */
/*
对象状态转换器
 */
public class StatusConverter {
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
               if(value == point) {
                   status = StatusEnum.OK.getName();
               }else if(highLevel && value != point) {
                   status = StatusEnum.HIGH.getName();
               }else if(!highLevel && value != point) {
                   status = StatusEnum.WARNING.getName();
               }
               break;
           case "=":
               point = warningPointValue != null ? warningPointValue : highPointValue;
               highLevel = warningPointValue != null ? false : true;
               if(value != point) {
                   status = StatusEnum.OK.getName();
               }else if(highLevel && value == point) {
                   status = StatusEnum.HIGH.getName();
               }else if(!highLevel && value == point) {
                   status = StatusEnum.WARNING.getName();
               }
               break;
       }
       return status;
    }

}
