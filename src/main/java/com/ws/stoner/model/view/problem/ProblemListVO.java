package com.ws.stoner.model.view.problem;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.view.carousel.BlockVO;
import com.ws.stoner.utils.AlertStatusConverter;
import com.ws.stoner.utils.BaseUtils;
import com.ws.stoner.utils.StatusConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by zkf on 2017/8/22.
 */
public class ProblemListVO extends BlockVO {

    @JSONField(name = "problem_eventid")
    private String problemEventid;
    @JSONField(name = "recovery_eventid",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String recoveryEventid;
    @JSONField(name = "trigger_id")
    private String triggerId;
    @JSONField(name = "problem_time")
    private String problemTime;
    @JSONField(name = "recover_time",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String recoverTime;
    @JSONField(name = "host_name")
    private String hostName;
    private String description;
    @JSONField(name = "priority_state")
    private String priorityState;
    @JSONField(name = "problem_state")
    private String problemState;
    //持续时间的拼接字符串，格式为 **天**小时*分钟
    @JSONField(name = "duration_string")
    private String durationString;
    @JSONField(name = "alert_state")
    private String alertState;
    @JSONField(name = "alert_num")
    private Integer alertNum;
    private String acknowledged;

    public String getProblemEventid() {
        return problemEventid;
    }

    public ProblemListVO setProblemEventid(String problemEventid) {
        this.problemEventid = problemEventid;
        return this;
    }

    public String getRecoveryEventid() {
        return recoveryEventid;
    }

    public ProblemListVO setRecoveryEventid(String recoveryEventid) {
        this.recoveryEventid = recoveryEventid;
        return this;
    }

    public String getTriggerId() {
        return triggerId;
    }

    public ProblemListVO setTriggerId(String triggerId) {
        this.triggerId = triggerId;
        return this;
    }

    public String getProblemTime() {
        return problemTime;
    }

    public ProblemListVO setProblemTime(String problemTime) {
        this.problemTime = problemTime;
        return this;
    }

    public String getRecoverTime() {
        return recoverTime;
    }

    public ProblemListVO setRecoverTime(String recoverTime) {
        this.recoverTime = recoverTime;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public ProblemListVO setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProblemListVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPriorityState() {
        return priorityState;
    }

    public ProblemListVO setPriorityState(String priorityState) {
        this.priorityState = priorityState;
        return this;
    }

    public String getProblemState() {
        return problemState;
    }

    public ProblemListVO setProblemState(String problemState) {
        this.problemState = problemState;
        return this;
    }

    public String getAlertState() {
        return alertState;
    }

    public ProblemListVO setAlertState(String alertState) {
        this.alertState = alertState;
        return this;
    }

    public String getAcknowledged() {
        return acknowledged;
    }

    public ProblemListVO setAcknowledged(String acknowledged) {
        this.acknowledged = acknowledged;
        return this;
    }

    public String getDurationString() {
        return durationString;
    }

    public ProblemListVO setDurationString(LocalDateTime beginTime,LocalDateTime endTime) {

        this.durationString = BaseUtils.getDurationStringByTime(beginTime, endTime);
        return this;
    }

    public Integer getAlertNum() {
        return alertNum;
    }

    public ProblemListVO setAlertNum(Integer alertNum) {
        this.alertNum = alertNum;
        return this;
    }

    @Override
    public String toString() {
        return "ProblemListVO{" +
                "problemEventid='" + problemEventid + '\'' +
                ", recoveryEventid='" + recoveryEventid + '\'' +
                ", triggerId='" + triggerId + '\'' +
                ", problemTime='" + problemTime + '\'' +
                ", recoverTime='" + recoverTime + '\'' +
                ", hostName='" + hostName + '\'' +
                ", description='" + description + '\'' +
                ", priorityState='" + priorityState + '\'' +
                ", problemState='" + problemState + '\'' +
                ", durationString='" + durationString + '\'' +
                ", alertState='" + alertState + '\'' +
                ", alertNum=" + alertNum +
                ", acknowledged='" + acknowledged + '\'' +
                '}';
    }

    //根据Problem时间给对象排序
    public static List<ProblemListVO> getSortListByProblemTime(List<ProblemListVO> list){
        Collections.sort(list, new Comparator<ProblemListVO>() {
            @Override
            public int compare(ProblemListVO o1, ProblemListVO o2) {
               return o2.getProblemTime().compareTo(o1.getProblemTime() );
            }
        });
        return list;
    }

    //将BriefEventDTOS 转换成 ProblemListVOS 对象 list
    public static List<ProblemListVO> transformVOSUseBriefEventDTO(List<BriefEventDTO> problemEventDTOS,List<BriefEventDTO> recoveryEventDTOS) {
        List<ProblemListVO> problemListVOS = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        for(BriefEventDTO problemEventDTO : problemEventDTOS) {
            ProblemListVO problemListVO = new ProblemListVO();
            BriefTriggerDTO triggerDTO = problemEventDTO.getRelatedObject();
            //用于判断告警状态
            List<BriefAlertDTO> alertDTOS = new ArrayList<>();
            //开始赋值
            problemListVO.setProblemEventid(problemEventDTO.getEventId());
            problemListVO.setTriggerId(problemEventDTO.getObjectId());
            problemListVO.setProblemTime(problemEventDTO.getClock().format(formatter));
            problemListVO.setHostName(problemEventDTO.getHosts().get(0).getName());
            //名称中存在 宏变量未处理
            problemListVO.setDescription(triggerDTO.getName());
            //PriorityState
            problemListVO.setPriorityState(StatusConverter.getStatusByTriggerPriority(triggerDTO.getPriority()));
            //problemState，恢复时间
            if(!problemEventDTO.getrEventid().equals("0")) {
                for(BriefEventDTO recoveryEventDTO : recoveryEventDTOS) {
                    if(problemEventDTO.getrEventid().equals(recoveryEventDTO.getEventId())) {
                        //匹配到 恢复事件
                        //recoveryEventId
                        problemListVO.setRecoveryEventid(recoveryEventDTO.getEventId());
                        //RecoverTime
                        problemListVO.setRecoverTime(recoveryEventDTO.getClock().format(formatter));
                        //durationString
                        problemListVO.setDurationString(problemEventDTO.getClock(),recoveryEventDTO.getClock());
                        //ProblemState
                        problemListVO.setProblemState("已解决");
                        //从恢复事件 添加告警信息
                        List<BriefAlertDTO> recoveryAlertDTOS = recoveryEventDTO.getAlerts();
                        alertDTOS.addAll(recoveryAlertDTOS);
                    }
                }
            }else {
                //ProblemState
                problemListVO.setProblemState("问题");
                //durationString
                problemListVO.setDurationString(problemEventDTO.getClock(), LocalDateTime.now());
            }
            //从问题事件 添加告警信息
            List<BriefAlertDTO> problemAlertDTOS = problemEventDTO.getAlerts();
            alertDTOS.addAll(problemAlertDTOS);
            //循环 问题和恢复的告警,告警数
            Map<String,Integer> alertMap = AlertStatusConverter.getMassageByAlertStatus(alertDTOS);
            problemListVO.setAlertNum(alertMap.entrySet().iterator().next().getValue());
            problemListVO.setAlertState(alertMap.entrySet().iterator().next().getKey());
            //确认
            if(problemEventDTO.getAcknowledged().equals(ZApiParameter.ACKNOWLEDGE_ACTION.ACKNOWLEDGED.value)) {
                problemListVO.setAcknowledged("是");
            }else {
                problemListVO.setAcknowledged("否");
            }
            problemListVOS.add(problemListVO);
        }
        return problemListVOS;
    }

}
