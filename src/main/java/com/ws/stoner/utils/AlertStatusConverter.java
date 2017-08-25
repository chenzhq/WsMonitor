package com.ws.stoner.utils;

import com.ws.bix4j.ZApiParameter;

/**
 * Created by zkf on 2017/8/25.
 */
public class AlertStatusConverter {

    public static String getMassageByAlertStatus(Integer alertStatus) {
        if(alertStatus.equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_SENDING.value)) {
            return "正在发送通知";
        }else if(alertStatus.equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_FAILED.value)) {
            return "失败";
        }else {
            return "成功";
        }
    }
}
