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
        }else if(StatusEnum.WARNING.code == customAvailableState) {
            status = StatusEnum.HIGH.getName();
        }
        return status;
    }
}
