package com.ws.stoner.model.view;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.utils.AlertStatusConverter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkf on 2017/9/4.
 */
public class ProblemGraphVO {

    private Long beginTime;
    private Long endTime;
    private int isAlert; //1表示告警，是线；0表示没有告警，即是块
    private String tooltip;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String color;

    public ProblemGraphVO() {
    }

    public ProblemGraphVO(Long beginTime, Long endTime, int isAlert, String tooltip, String color) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.isAlert = isAlert;
        this.tooltip = tooltip;
        this.color = color;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public ProblemGraphVO setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public ProblemGraphVO setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public int getIsAlert() {
        return isAlert;
    }

    public ProblemGraphVO setIsAlert(int isAlert) {
        this.isAlert = isAlert;
        return this;
    }

    public String getColor() {
        return color;
    }

    public ProblemGraphVO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getTooltip() {
        return tooltip;
    }

    public ProblemGraphVO setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @Override
    public String toString() {
        return "ProblemGraphVO{" +
                "beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", isAlert=" + isAlert +
                ", color='" + color + '\'' +
                ", tooltip='" + tooltip + '\'' +
                '}';
    }

    public static List<ProblemGraphVO> getGraphVOByAlertDTOS(List<BriefAlertDTO> alertDTOS) {
        //按步骤排序
        BriefAlertDTO.sortListByEscStep(alertDTOS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<ProblemGraphVO> graphVOS = new ArrayList<>();
        Integer currStep = 0;
        for(BriefAlertDTO alertDTO : alertDTOS) {
            if(!currStep.equals(alertDTO)) {
                ProblemGraphVO graphVO = new ProblemGraphVO();
                graphVO.setBeginTime(alertDTO.getClock().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                graphVO.setEndTime(0L);
                graphVO.setIsAlert(0);
                graphVO.setColor(null);
                graphVO.setTooltip(alertDTO.getClock().format(formatter) + "(" + AlertStatusConverter.getDiscriptionByStatus(alertDTO.getStatus()) + ")");
                graphVOS.add(graphVO);
                currStep = alertDTO.getEscStep();
            }
        }
        return graphVOS;
    }

}
