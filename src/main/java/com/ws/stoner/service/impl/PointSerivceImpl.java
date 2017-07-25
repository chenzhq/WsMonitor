package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.HostDetailPointItemVO;
import com.ws.stoner.model.view.HostDetailPointVO;
import com.ws.stoner.model.view.PointDetailItemDatasVO;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.StatusConverter;
import com.ws.stoner.utils.ThresholdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zkf on 2017/6/8.
 */
@Service
public class PointSerivceImpl implements PointSerivce {

    private static final Logger logger = LoggerFactory.getLogger(HostService.class);
    @Autowired
    private ZApi zApi;

    @Autowired
    private HostService hostService;

    @Autowired
    private TriggerService triggerService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private HistoryService historyService;

    /**
     * 根据request 获取监控点数量
     * @param request
     * @return
     * @throws ServiceException
     */
    @Override
    public int countPoint(ApplicationGetRequest request) throws ServiceException {
        int appNum ;
        try {
            appNum = zApi.Application().count(request);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询监控点 {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return appNum;
    }

    /**
     *
     * @return 获取监控点列表
     * @throws ServiceException
     */
    @Override
    public List<BriefPointDTO> listPoint(ApplicationGetRequest request) throws ServiceException {
        List<BriefPointDTO> listApplication ;
        try {
            listApplication = zApi.Application().get(request,BriefPointDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询监控点 {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return listApplication;
    }

    /**
     * 获取所有的监控点
     * 根据筛选监控中的主机得到所有的监控点
     * @throws ServiceException
     */
    @Override
    public int countAllPoint() throws ServiceException {
        //step1:筛选所有监控中的主机monitored
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostService.listHost(hostGetRequest);
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
     * @throws ServiceException
     */
    @Override
    public int countWarningPoint() throws ServiceException {
        //step1:取所有监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostService.listHost(hostGetRequest);
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
     * @throws ServiceException
     */
    @Override
    public int countHighPoint() throws ServiceException {
        //step1:取所有监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostService.listHost(hostGetRequest);
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
        int appHighNum  = countPoint(appRequest);
        return appHighNum;

    }

    /**
     * 获取正常监控点 数量
     * okAppNum = allAppNum - problemAppNum
     * @return
     * @throws ServiceException
     */
    @Override
    public int countOkPoint() throws ServiceException {
        //step1:取所有监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostService.listHost(hostGetRequest);
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
     * @throws ServiceException
     */
    @Override
    public int countAllPointByHostIds(List<String> hostIds) throws ServiceException {
        ApplicationGetRequest applicationGetRequest = new ApplicationGetRequest();
        applicationGetRequest.getParams()
                .setHostIds(hostIds)
                .setCountOutput(true);
        int pointsByHostId = countPoint(applicationGetRequest);
        return pointsByHostId;
    }

    @Override
    public int countProblemPointByHostIds(List<String> hostIds) throws ServiceException {
        //step1:获取问题触发器Ids
        List<String> triggerIds  = triggerService.getProblemTriggerIds();
        //step2:根据触发器Ids获取items
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams()
                .setTriggerIds(triggerIds)
                .setMonitored(true)
                .setOutput(BriefItemDTO.PROPERTY_NAMES);
        List<BriefItemDTO> items  = itemService.listItem(itemGetRequest);
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
     * @throws ServiceException
     */
    @Override
    public List<BriefPointDTO> listAllPoint() throws ServiceException {
        //step1:获取监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostService.listHost(hostGetRequest);
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
     * @throws ServiceException
     */
    @Override
    public List<BriefPointDTO> listWarningPoint() throws ServiceException {
        //step1:取所有监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostService.listHost(hostGetRequest);
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
     * 获取严重监控点 point list  high
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefPointDTO> listHighPoint() throws ServiceException {
        //step1:取所有监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts = hostService.listHost(hostGetRequest);
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

    /**
     * 根据 pointId 组装设备详情页面中 监控点悬浮框 的业务数据
     * @param pointId
     * @return
     * @throws ServiceException
     */
    @Override
    public HostDetailPointVO getItemsByPointId(String pointId) throws ServiceException {
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        List<BriefItemDTO> itemDTOS = itemService.getItemsByPointIds(pointIds);
        List<BriefItemDTO> withTriggersItemDTOS = itemService.getItemsWithTriggersByPointIds(pointIds);
        List<String> itemIds = new ArrayList<>();
        for(BriefItemDTO itemDTO : withTriggersItemDTOS) {
            itemIds.add(itemDTO.getItemId());
        }
        List<HostDetailPointItemVO> itemVOS = new ArrayList<>();
        HostDetailPointVO pointVO = new HostDetailPointVO();
        for(BriefItemDTO itemDTO :itemDTOS) {
            HostDetailPointItemVO itemVO = new HostDetailPointItemVO();
            itemVO.setItemId(itemDTO.getItemId());
            itemVO.setName(itemDTO.getName());
            itemVO.setValue(itemDTO.getLastValue());
            itemVO.setState(StatusConverter.StatusTransform(itemDTO.getCustomState()));
            //withTriggers
            if(itemIds.contains(itemDTO.getItemId())) {
                itemVO.setWithTriggers(true);
            }else  {
                itemVO.setWithTriggers(false);
            }
            itemVOS.add(itemVO);
        }
        pointVO.setItems(itemVOS);
        pointVO.setPointId(pointId);
        if(itemDTOS.size() != 0) {
            //point name
            pointVO.setName(itemDTOS.get(0).getPoints().get(0).getName());
            //point state
            int customState = itemDTOS.get(0).getPoints().get(0).getCustomState();
            pointVO.setState(StatusConverter.StatusTransform(customState));
        }else {
            pointVO.setName("监控点中没有监控项");
            pointVO.setState(StatusEnum.OK.getName());
        }
        return pointVO;
    }

    /**
     * 根据 pointId 组装监控点详情页面中 概述 的业务数据
     * @param pointId
     * @return
     * @throws ServiceException
     */
    @Override
    public HostDetailPointVO getDetailPointByPointId(String pointId) throws ServiceException {
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        List<BriefItemDTO> itemDTOS = itemService.getItemsByPointIds(pointIds);
        List<BriefItemDTO> withTriggersItemDTOS = itemService.getItemsWithTriggersByPointIds(pointIds);
        List<String> itemIds = new ArrayList<>();
        for(BriefItemDTO itemDTO : withTriggersItemDTOS) {
            itemIds.add(itemDTO.getItemId());
        }
        //根据含有触发器的itemIds获取相关触发器 triggerDTO list
        List<BriefTriggerDTO> triggerDTOS = triggerService.getTriggersByItemIds(itemIds);
        List<HostDetailPointItemVO> itemVOS = new ArrayList<>();
        HostDetailPointVO pointVO = new HostDetailPointVO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for(BriefItemDTO itemDTO :itemDTOS) {
            HostDetailPointItemVO itemVO = new HostDetailPointItemVO();
            itemVO.setItemId(itemDTO.getItemId());
            itemVO.setName(itemDTO.getName());
            itemVO.setValue(itemDTO.getLastValue());
            itemVO.setState(StatusConverter.StatusTransform(itemDTO.getCustomState()));
            if(itemDTO.getLastTime() != null) {
                itemVO.setLastTime(itemDTO.getLastTime().format(formatter));
            }
            //withTriggers
            if(itemIds.contains(itemDTO.getItemId())) {
                itemVO.setWithTriggers(true);
                //阀值赋值：highPoint,warningPoint
                //循环triggerDTOS，筛选出属于该itemDTO的触发器，取List<String> expression,priority  ,
                for(BriefTriggerDTO triggerDTO : triggerDTOS) {
                    String expression = triggerDTO.getExpression();
                    String itemIdInfo = triggerDTO.getItems().get(0).getItemId();
                    if(itemIdInfo.equals(itemDTO.getItemId())) {
                        if(triggerDTO.getPriority() == 2) {
                            // priority为2:警告阀值取expression的逻辑比较符号后面数据；
                            itemVO.setWarningPoint(ThresholdUtils.getThresholdValue(expression));
                        }else if(triggerDTO.getPriority() == 4) {
                            // priority为4:严重阀值取expression的逻辑比较符号后面数据；
                            itemVO.setHighPoint(ThresholdUtils.getThresholdValue(expression));
                        }
                    }
                }

            }else  {
                itemVO.setWithTriggers(false);
            }

            itemVOS.add(itemVO);
        }
        pointVO.setItems(itemVOS);
        pointVO.setPointId(pointId);
        if(itemDTOS.size() != 0) {
            //point name
            pointVO.setName(itemDTOS.get(0).getPoints().get(0).getName());
            //point state
            int customState = itemDTOS.get(0).getPoints().get(0).getCustomState();
            pointVO.setState(StatusConverter.StatusTransform(customState));
        }else {
            pointVO.setName("无监控项");
            pointVO.setState(StatusEnum.OK.getName());
        }
        return pointVO;
    }

    /**
     * 根据 pointId 组装监控点详情页面中 时序数据 的业务数据
     * @param pointId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PointDetailItemDatasVO> getItemDatasByPointId(String pointId,int time) throws ServiceException {
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        //step1:取所有的 itemDTOS
        List<BriefItemDTO> itemDTOS = itemService.getItemsByPointIds(pointIds);
        List<BriefItemDTO> withTriggersItemDTOS = itemService.getItemsWithTriggersByPointIds(pointIds);
        List<String> itemIds = new ArrayList<>();
        for(BriefItemDTO itemDTO : withTriggersItemDTOS) {
            itemIds.add(itemDTO.getItemId());
        }
        //根据含有触发器的itemIds获取相关触发器 triggerDTO list
        List<BriefTriggerDTO> triggerDTOS = triggerService.getTriggersByItemIds(itemIds);
        //step2:循环 itemDTOS 取指定时间段 time 的历史数据，组装到 itemVO ,add到itemVOS
        List<PointDetailItemDatasVO> pointDetailItemDatasVOS = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for(BriefItemDTO itemDTO :itemDTOS) {
            PointDetailItemDatasVO pointDetailItemDatasVO = new PointDetailItemDatasVO();
            pointDetailItemDatasVO.setItemId(itemDTO.getItemId());
            pointDetailItemDatasVO.setItemName(itemDTO.getName());
            //state,value,last_time
            List<BriefHistoryDTO> historyDTOS = null;
            if(time == 40) {
                historyDTOS = historyService.getHistoryByItemIdLimit(itemDTO.getItemId(),itemDTO.getValueType(),time);
            }else {
                historyDTOS = historyService.getHistoryByItemId(itemDTO.getItemId(),itemDTO.getValueType(),time);
            }
            //获取阀值
            String highPoint = "";
            String warningPoint = "";
            if(itemIds.contains(itemDTO.getItemId())) {
                //阀值赋值：highPoint,warningPoint
                //循环triggerDTOS，筛选出属于该itemDTO的触发器，取List<String> expression,priority  ,
                for(BriefTriggerDTO triggerDTO : triggerDTOS) {
                    String expression = triggerDTO.getExpression();
                    String itemIdInfo = triggerDTO.getItems().get(0).getItemId();
                    if(itemIdInfo.equals(itemDTO.getItemId())) {
                        if(triggerDTO.getPriority() == 2) {
                            // priority为2:警告阀值取expression的逻辑比较符号后面数据；
                            highPoint = ThresholdUtils.getThresholdValue(expression);
                        }else if(triggerDTO.getPriority() == 4) {
                            // priority为4:严重阀值取expression的逻辑比较符号后面数据；
                            warningPoint = ThresholdUtils.getThresholdValue(expression);
                        }
                    }
                }
            }
            //赋值 取list BriefHistory的 valueList，lastTimeList
            List<PointDetailItemDatasVO.ItemDatasVO> itemDatasVOS = new ArrayList<>();
            for(BriefHistoryDTO historyDTO : historyDTOS) {
                PointDetailItemDatasVO.ItemDatasVO itemDatasVO = pointDetailItemDatasVO.new ItemDatasVO();
                itemDatasVO.setValue(historyDTO.getValue());
                itemDatasVO.setLastTime(historyDTO.getLastTime().format(formatter));
                //state
                itemDatasVO.setState(StatusConverter.StatusTransform(itemDTO.getCustomState()));
                itemDatasVOS.add(itemDatasVO);
            }
            pointDetailItemDatasVO.setItemDatasVOS(itemDatasVOS);
            pointDetailItemDatasVOS.add(pointDetailItemDatasVO);
        }
        return pointDetailItemDatasVOS;

    }

}
