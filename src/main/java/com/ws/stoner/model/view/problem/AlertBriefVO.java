package com.ws.stoner.model.view.problem;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.model.dto.BriefAlertDTO;

import java.util.List;

/**
 * Created by zkf on 2017/9/15.
 */
//告警简约对象
public class AlertBriefVO {

    private String alertState;
    private Integer alertNum;

    public String getAlertState() {
        return alertState;
    }

    public static String transAlertState(Integer alertStatus) {
        String description ;
        if(alertStatus.equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_SENDING.value)) {
            description =  "发送中";
        }else if(alertStatus.equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_FAILED.value)) {
            description = "失败";
        }else {
            description = "成功";
        }
        return description;
    }

    public AlertBriefVO setAlertState(String alertState) {
        this.alertState = alertState;
        return this;
    }

    public Integer getAlertNum() {
        return alertNum;
    }

    public AlertBriefVO setAlertNum(Integer alertNum) {
        this.alertNum = alertNum;
        return this;
    }


    public static AlertBriefVO transformByAlertDTOS(List<BriefAlertDTO> alertDTOS) {
       AlertBriefVO alertBriefVO = new AlertBriefVO();
        //循环 问题和恢复的告警,告警数
        alertBriefVO.setAlertNum(alertDTOS.size());
        if(alertDTOS.size() == 0) {
            alertBriefVO.setAlertState("未告警");
        }else {
            for(BriefAlertDTO alertDTO : alertDTOS) {
                if(alertDTO.getStatus().equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_SENDING.value)) {
                    alertBriefVO.setAlertState("发送中");
                    break;
                }else if(alertDTO.getStatus().equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_FAILED.value)) {
                    alertBriefVO.setAlertState("失败");
                    break;
                }else {
                    alertBriefVO.setAlertState("成功");
                }
            }
        }
        return alertBriefVO;
    }

}
