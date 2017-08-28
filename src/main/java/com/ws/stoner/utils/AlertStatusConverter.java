package com.ws.stoner.utils;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.model.dto.BriefAlertDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zkf on 2017/8/25.
 */
public class AlertStatusConverter {

    //key:告警状态  value：告警数量
    public static Map<String,Integer> getMassageByAlertStatus(List<BriefAlertDTO> alertDTOS) {
        Map<String,Integer> alertMap = new HashMap<>();
        String alertStatus = "";
        Integer alertNum = null;
        //循环 问题和恢复的告警,告警数
        alertNum = alertDTOS.size();
        if(alertDTOS.size() == 0) {
            alertStatus = "未告警";
        }else {
            for(BriefAlertDTO alertDTO : alertDTOS) {
                if(alertDTO.getStatus().equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_SENDING.value)) {
                    alertStatus =  "发送中";
                    break;
                }else if(alertDTO.getStatus().equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_FAILED.value)) {
                    alertStatus = "失败";
                    break;
                }else {
                    alertStatus = "成功";
                }
            }
        }
        alertMap.put(alertStatus,alertNum);
        return alertMap;
    }

    public static String getDiscriptionByStatus(Integer alertStatus) {
        String discription ;
        if(alertStatus.equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_SENDING.value)) {
            discription =  "发送中";
        }else if(alertStatus.equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_FAILED.value)) {
            discription = "失败";
        }else {
            discription = "成功";
        }
        return discription;
    }
}
