package com.ws.stoner.model.query;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.model.dto.BriefEventDTO;

import java.util.List;

/**
 * Created by zkf on 2017/8/30.
 */
public class CalendarFormQuery {

    private List<String> hostIds;
    private List<Integer> problemNum;
    private String priority;
    private String acknowledge;
    private String today;//当天

    public List<String> getHostIds() {
        return hostIds;
    }

    public CalendarFormQuery setHostIds(List<String> hostIds) {
        this.hostIds = hostIds;
        return this;
    }

    public List<Integer> getProblemNum() {
        return problemNum;
    }

    public CalendarFormQuery setProblemNum(List<Integer> problemNum) {
        this.problemNum = problemNum;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public CalendarFormQuery setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public String getAcknowledge() {
        return acknowledge;
    }

    public CalendarFormQuery setAcknowledge(String acknowledge) {
        this.acknowledge = acknowledge;
        return this;
    }

    public String getToday() {
        return today;
    }

    public CalendarFormQuery setToday(String today) {
        this.today = today;
        return this;
    }

    public static boolean selectEventByFormQuery(CalendarFormQuery formQuery, BriefEventDTO eventDTO) {
        boolean selectHost;
        boolean selectPrority ;
        boolean selectAcknowledge;
        //host查询
        if(formQuery.getHostIds() == null || formQuery.getHostIds().size() == 0) {
            selectHost = true;
        }else {
            if(formQuery.getHostIds().contains(eventDTO.getHosts().get(0).getHostId())) {
                //事件所属设备id在查询表单中的hostids中
                selectHost = true;
            }else {
                selectHost = false;
            }
        }
        //严重性查询
        if(formQuery.getPriority() == null) {
            selectPrority = true;
        }else {
            if(StatusEnum.WARNING.text.equals(formQuery.getPriority())) {
                selectPrority = eventDTO.getRelatedObject().getPriority().equals(ZApiParameter.TRIGGER_PRIORITY.WARNING.value);
            }else if(StatusEnum.HIGH.text.equals(formQuery.getPriority())) {
                selectPrority = eventDTO.getRelatedObject().getPriority().equals(ZApiParameter.TRIGGER_PRIORITY.HIGH.value);
            }else if("all".equals(formQuery.getPriority())) {
                selectPrority = true;
            }else {
                selectPrority = false;
            }
        }
        //确认查询
        if(formQuery.getAcknowledge() == null || "all".equals(formQuery.getAcknowledge()) ) {
            selectAcknowledge = true;
        }else {
            selectAcknowledge = eventDTO.getAcknowledged().equals(Integer.parseInt(formQuery.getAcknowledge()));
        }
        return selectHost && selectAcknowledge && selectPrority;
    }
}
