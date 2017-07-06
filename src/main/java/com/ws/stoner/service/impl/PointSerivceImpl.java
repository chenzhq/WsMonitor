package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.service.HostSerivce;
import com.ws.stoner.service.ItemSerivce;
import com.ws.stoner.service.PointSerivce;
import com.ws.stoner.service.TriggerSerivce;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zkf on 2017/6/8.
 */
@Service
public class PointSerivceImpl implements PointSerivce {

    private static final Logger logger = LoggerFactory.getLogger(HostSerivceImpl.class);
    @Autowired
    private ZApi zApi;

    @Autowired
    private HostSerivce hostSerivce;

    @Autowired
    private TriggerSerivce triggerSerivce;

    @Autowired
    private ItemSerivce itemSerivce;

    /**
     * 根据request 获取监控点数量
     * @param request
     * @return
     * @throws ManagerException
     */
    @Override
    public int countPoint(ApplicationGetRequest request) throws ManagerException {
        int appNum ;
        try {
            appNum = zApi.Application().count(request);
        } catch (ZApiException e) {
            e.printStackTrace();
            return 0;
        }
        return appNum;
    }

    /**
     *
     * @return 获取监控点列表
     * @throws ManagerException
     */
    @Override
    public List<BriefPointDTO> listPoint(ApplicationGetRequest request) throws ManagerException {
        List<BriefPointDTO> listApplication ;
        try {
            listApplication = zApi.Application().get(request,BriefPointDTO.class);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new ManagerException("");
            }
            e.printStackTrace();
            logger.error("查询监控点错误！{}", e.getMessage());
            return null;
        }
        return listApplication;
    }

    /**
     * 获取所有的监控点
     * 根据筛选监控中的主机得到所有的监控点
     * @throws ManagerException
     */
    @Override
    public int countAllPoint() throws ManagerException {
        //step1:筛选所有监控中的主机monitored
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostSerivce.listHost(hostGetRequest);
        //step2:根据这些主机筛选其下的所有应用集hostids，即为所有的应用集（排出了停用状态，和模版中的应用集）
        List<String> hostIds = new ArrayList<>();
        if(hosts == null) {
            return 0;
        }
        for(BriefHostDTO host : hosts) {
            hostIds.add(host.getHostId());
        }
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams()
                .setHostIds(hostIds)
                .setCountOutput(true);
        int appALlNum  = countPoint(appRequest);
        return appALlNum;
    }


    /**
     * 获取所有的警告监控点   point warning
     * 根据 custom_state 判断问题监控点 0正常，1警告，2严重
     * @return int
     * @throws ManagerException
     */
    @Override
    public int countWarningPoint() throws ManagerException {
        //step1:取所有监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostSerivce.listHost(hostGetRequest);
        if(hosts == null) {
            return 0;
        }
        //step2:根据hostids筛选出应用集
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO host : hosts) {
            hostIds.add(host.getHostId());
        }

        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        Map<String, Object> pointFilter = new HashMap<>();
        pointFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_WARNING.value);
        appRequest.getParams()
                .setHostIds(hostIds)
                .setFilter(pointFilter)
                .setCountOutput(true);
        int appWarningNum  = countPoint(appRequest);
        return appWarningNum;

    }

    /**
     * 获取所有的严重监控点   point hight
     * 根据 custom_state 判断问题监控点 0正常，1警告，2严重
     * @return int
     * @throws ManagerException
     */
    @Override
    public int countHightPoint() throws ManagerException {
        //step1:取所有监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostSerivce.listHost(hostGetRequest);
        if(hosts == null) {
            return 0;
        }
        //step2:根据hostids筛选出应用集
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO host : hosts) {
            hostIds.add(host.getHostId());
        }

        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        Map<String, Object> pointFilter = new HashMap<>();
        pointFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_HIGHT.value);
        appRequest.getParams()
                .setHostIds(hostIds)
                .setFilter(pointFilter)
                .setCountOutput(true);
        int appHightNum  = countPoint(appRequest);
        return appHightNum;

    }

    /**
     * 获取正常监控点 数量
     * okAppNum = allAppNum - problemAppNum
     * @return
     * @throws ManagerException
     */
    @Override
    public int countOkPoint() throws ManagerException {
        //step1:取所有监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostSerivce.listHost(hostGetRequest);
        if(hosts == null) {
            return 0;
        }
        //step2:根据hostids筛选出应用集
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO host : hosts) {
            hostIds.add(host.getHostId());
        }

        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        Map<String, Object> pointFilter = new HashMap<>();
        pointFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_OK.value);
        appRequest.getParams()
                .setHostIds(hostIds)
                .setFilter(pointFilter)
                .setCountOutput(true);
        int appOkNum  = countPoint(appRequest);
        return appOkNum;

    }

    /**
     * 获取指定主机的监控点数量
     * @param hostIds
     * @return
     * @throws ManagerException
     */
    @Override
    public int countAllPointByHostIds(List<String> hostIds) throws ManagerException {
        ApplicationGetRequest applicationGetRequest = new ApplicationGetRequest();
        applicationGetRequest.getParams()
                .setHostIds(hostIds)
                .setCountOutput(true);
        int pointsByHostId = countPoint(applicationGetRequest);
        return pointsByHostId;
    }

    @Override
    public int countProblemPointByHostIds(List<String> hostIds) throws ManagerException {
        //step1:获取问题触发器Ids
        List<String> triggerIds  = triggerSerivce.getProblemTriggerIds();
        //step2:根据触发器Ids获取items
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams()
                .setTriggerIds(triggerIds)
                .setMonitored(true)
                .setOutput(BriefItemDTO.PROPERTY_NAMES);
        List<BriefItemDTO> items  = itemSerivce.listItem(itemGetRequest);
        //step3:根据item筛选出应用集
        List<String> itemIds = new ArrayList<>();
        for(BriefItemDTO item : items) {
            itemIds.add(item.getItemId());
        }
        //筛选指定主机的监控点
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams()
                .setItemIds(itemIds)
                .setHostIds(hostIds)
                .setCountOutput(true);
        int appProblemNum  = countPoint(appRequest);
        return appProblemNum;
    }

/*
 *list point
 */

    /**
     * 获取简约监控点 point list
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefPointDTO> listAllPoint() throws ManagerException {
        //step1:获取监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostSerivce.listHost(hostGetRequest);
        //step2:根据主机ids获取所有的监控点
        List<String> hostIds = new ArrayList<>();
        if(hosts == null) {
            return null;
        }
        for(BriefHostDTO host : hosts) {
            hostIds.add(host.getHostId());
        }
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams()
                .setHostIds(hostIds)
                .setSelectHost(BriefHostDTO.PROPERTY_NAMES)
                .setSelectItems(BriefItemDTO.PROPERTY_NAMES)
                .setOutput(BriefPointDTO.PROPERTY_NAMES);
        List<BriefPointDTO> points = listPoint(appRequest);
        return points;
    }

    /**
     * 获取警告监控点 point list  warning
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefPointDTO> listWarningPoint() throws ManagerException {
        //step1:取所有监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostSerivce.listHost(hostGetRequest);
        if(hosts == null) {
            return null;
        }
        //step2:根据hostids筛选出应用集
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO host : hosts) {
            hostIds.add(host.getHostId());
        }
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        Map<String, Object> pointFilter = new HashMap<>();
        pointFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_WARNING.value);
        appRequest.getParams()
                .setHostIds(hostIds)
                .setFilter(pointFilter);
        List<BriefPointDTO> problemPoints = listPoint(appRequest);
        return problemPoints;
    }

    /**
     * 获取严重监控点 point list  hight
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefPointDTO> listHightPoint() throws ManagerException {
        //step1:取所有监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostSerivce.listHost(hostGetRequest);
        if(hosts == null) {
            return null;
        }
        //step2:根据hostids筛选出应用集
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO host : hosts) {
            hostIds.add(host.getHostId());
        }
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        Map<String, Object> pointFilter = new HashMap<>();
        pointFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_HIGHT.value);
        appRequest.getParams()
                .setHostIds(hostIds)
                .setFilter(pointFilter);
        List<BriefPointDTO> problemPoints = listPoint(appRequest);
        return problemPoints;
    }


}
