package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.view.PlatformBlockVO;
import com.ws.stoner.model.view.PlatformListVO;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.BaseUtils;
import com.ws.stoner.utils.StatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by pc on 2017/6/1.
 */
@Service
public class PlatformServiceImpl implements PlatformService {
    private static final Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);
    @Autowired
    private ZApi zApi;

    @Autowired
    private HostService hostService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private TriggerService triggerService;

    @Autowired
    private TemplateService templateService;

    @Override
    public List<BriefPlatformDTO> listPlatform(HostGroupGetRequest request) throws ServiceException {
        List<BriefPlatformDTO> groups;
        try {
            groups = zApi.Group().get(request, BriefPlatformDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询业务平台 {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return groups;
    }

    /**
     * 根据request获取业务平台数量
     * @return
     * @throws ServiceException
     */
    @Override
    public int countPlatform(HostGroupGetRequest request) throws ServiceException {
        int hostGroupNum ;
        try {
            hostGroupNum = zApi.Group().count(request);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询业务平台 {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return hostGroupNum;
    }

    /**
     * 获取指定业务平台的所有主机数量 all host number by platformIds
     * @return
     * @throws ServiceException
     */
    @Override
    public int countAllHostByPlatformIds(List<String> platformIds) throws ServiceException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams()
                .setGroupIds(platformIds)
                .setFilter(statusFilter)
                .setCountOutput(true);
        int allHostNum = hostService.countHost(hostGetRequest);
        return allHostNum;
    }

    /**
     * 获取指定业务平台的问题主机数量 problem host number by platformIds
     * @param platformIds
     * @return
     * @throws ServiceException
     */
    @Override
    public int countProblemHostByPlatformIds(List<String> platformIds) throws ServiceException {
        //step1:获取问题触发器ids
        List<String> triggerIds = triggerService.getProblemTriggerIds();
        //step2:根据两个触发器的ids得到主机数量 hosts1
        HostGetRequest hostGetRequest1 = new HostGetRequest();
        Map<String,Object> hostFilter1 = new HashMap<>();
        hostFilter1.put("status",ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest1.getParams()
                .setTriggerIds(triggerIds)
                .setGroupIds(platformIds)
                .setFilter(hostFilter1)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> host1  = hostService.listHost(hostGetRequest1);
        //step4:筛选四种监控接口中至少一个有问题的主机数量 host2
        HostGetRequest hostGetRequest2 = new HostGetRequest();
        Map<String, Object> hostFilter2 = new HashMap<>();
        hostFilter2.put("monitored_hosts",true);
        hostFilter2.put("available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("ipmi_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("jmx_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("snmp_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostGetRequest2.getParams()
                .setGroupIds(platformIds)
                .setFilter(hostFilter2)
                .setSearchByAny(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> host2 = hostService.listHost(hostGetRequest2);
        //step5:去掉重复的主机并求和
        Set<BriefHostDTO> hosts = new HashSet<>();
        hosts.addAll(host1);
        hosts.addAll(host2);
        return hosts.size();
    }


    /**
     * 获取所有业务平台数量 hostgroup number
     * @return
     * @throws ServiceException
     */
    @Override
    public int countAllPlatform() throws ServiceException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setCountOutput(true);
        int hostGroupNum  = countPlatform(groupRequest);
        return hostGroupNum;
    }

    /**
     * 获取警告业务平台数量 warning
     * 根据 custom_state 字段判断
     * @return
     * @throws ServiceException
     */
    @Override
    public int countWarningPlatform() throws ServiceException {
        //step1:根据custom_state字段判断
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String,Object> groupFilter = new HashMap<>();
        groupFilter.put("custom_state",ZApiParameter.OBJECT_STATE.CUSTOM_STATE_WARNING.value);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(groupFilter)
                .setCountOutput(true);
        int warningHostGroupNum  = countPlatform(groupRequest);
        return warningHostGroupNum;
    }

    /**
     * 获取严重业务平台数量 high
     * 根据custom_state字段判断
     * @return
     * @throws ServiceException
     */
    @Override
    public int countHighPlatform() throws ServiceException {
        //step1:根据custom_state字段判断
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String,Object> groupFilter = new HashMap<>();
        groupFilter.put("custom_state",ZApiParameter.OBJECT_STATE.CUSTOM_STATE_HIGHT.value);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(groupFilter)
                .setCountOutput(true);
        int hightHostGroupNum  = countPlatform(groupRequest);
        return hightHostGroupNum;
    }

    /**
     * 获取正常的业务平台数量 OK
     * @return
     * @throws ServiceException
     */
    @Override
    public int countOkPlatform() throws ServiceException {
        //step1:根据custom_state字段判断
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String,Object> groupFilter = new HashMap<>();
        groupFilter.put("custom_state",ZApiParameter.OBJECT_STATE.CUSTOM_STATE_OK.value);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(groupFilter)
                .setCountOutput(true);
        int okHostGroupNum  = countPlatform(groupRequest);
        return okHostGroupNum;
    }

/*
 *list platform
 */


    /**
     * 获取简约listPlatform list all
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefPlatformDTO> listAllPlatform() throws ServiceException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> platforms  = listPlatform(groupRequest);
        return platforms;
    }

    /**
     * 获取警告的 platform list warning
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefPlatformDTO> listWarningPlatform() throws ServiceException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String, Object> platformFilter = new HashMap<>();
        platformFilter.put("custom_state",ZApiParameter.OBJECT_STATE.CUSTOM_STATE_WARNING);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(platformFilter)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> problemPlatforms  = listPlatform(groupRequest);
        return problemPlatforms;
    }

    /**
     * 获取严重的 platform list hight
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefPlatformDTO> listHighPlatform() throws ServiceException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String, Object> platformFilter = new HashMap<>();
        platformFilter.put("custom_state",ZApiParameter.OBJECT_STATE.CUSTOM_STATE_HIGHT);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(platformFilter)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> problemPlatforms  = listPlatform(groupRequest);
        return problemPlatforms;
    }

    /**
     * 根据 业务平台Ids 获取 健康值  Map<key:platformId ,value:health>
     * @param platformIds
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Float> getHealthByPlatformIds(List<String> platformIds) throws ServiceException {
        //health  = 1 - problemWeight / allWeight
        Map<String, Float> healthMap = new HashMap<>();
        Float health ;
        for(String platformId : platformIds) {
            List<String> ids = new ArrayList<>();
            ids.add(platformId);
            List<BriefItemDTO> itemDTOS = itemService.getItemsWithTriggersByPlatfromIds(ids);
            //初始化值
            Float allWeight = 0.0f;
            Float problemWeight = 0.0f;
            for(BriefItemDTO itemDTO : itemDTOS) {
                allWeight += itemDTO.getWeight();
                problemWeight += itemDTO.getWeight() * (itemDTO.getCustomState() / 2.0f);
            }
            health = (1 -  problemWeight / allWeight) * 100;
            healthMap.put(platformId,health);
        }
        return healthMap;
    }

    /**
     * 获取页面展示上的 业务平台列表
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PlatformListVO> getPlatformList() throws ServiceException {
        List<BriefPlatformDTO> allPlatformDTO = null;
        List<BriefHostDTO> hostDTOS = null;
        //step1:获取BriefPlatformDTO 类型的所有业务平台 allPlatformDTO
        allPlatformDTO = listAllPlatform();
        //step2:取所有监控中的主机，组装hostIds
        hostDTOS = hostService.listAllHost();
        List<String> hostIds = new ArrayList<>();
        List<String> platformIds = new ArrayList<>();
        for (BriefHostDTO host : hostDTOS) {
            hostIds.add(host.getHostId());
        }
        for(BriefPlatformDTO platformDTO : allPlatformDTO) {
            platformIds.add(platformDTO.getPlatformId());
        }
        //获取 健康值 的map对象
        Map<String,Float> healthMap = getHealthByPlatformIds(platformIds);
        //step3:新建List<PlatformListVO>，循环allplatformDTO，新建DashboardPlatformVO，分别赋值
        List<PlatformListVO> platformVOS = new ArrayList<>();
        for (BriefPlatformDTO platform : allPlatformDTO) {
            PlatformListVO platformVO = new PlatformListVO();
            //赋值 id,name,availability
            platformVO.setPlatformId(platform.getPlatformId());
            platformVO.setPlatformName(platform.getName());
            platformVO.setHealth(healthMap.get(platform.getPlatformId()));
            platformVO.setState(StatusConverter.StatusTransform(platform.getCustomState()));
            //allNum，warningNum,highNum
            int allNum = 0;
            int warningNum = 0;
            int highNum = 0;
            for (BriefHostDTO host : platform.getHosts()) {
                if (hostIds.contains(host.getHostId())) {
                    allNum++;
                }
                if (StatusEnum.WARNING.code == host.getCustomState() && StatusEnum.OK.code == host.getCustomAvailableState()) {
                    warningNum++;
                }
                if (StatusEnum.HIGH.code == host.getCustomState() || StatusEnum.WARNING.code == host.getCustomAvailableState()) {
                    highNum++;
                }
            }
            platformVO.setAllNum(allNum);
            platformVO.setWarningNum(warningNum);
            platformVO.setHighNum(highNum);
            platformVOS.add(platformVO);
        }
        return platformVOS;
    }


    /**
     * 获取业务监控的 业务方块 PlatformBlockVO
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PlatformBlockVO> getPlatformBlock() throws ServiceException {
        //所有业务平台
        List<BriefPlatformDTO> allPlatformDTOS = listAllPlatform();
        //所有设备
        List<BriefHostDTO> allHostDTOS = hostService.listAllHost();
        //所有模板，用于匹配 type
        List<BriefTemplateDTO> allTemplateDTOS = templateService.listAllTemplate();
        List<String> platformIds = new ArrayList<>();
        for(BriefPlatformDTO platformDTO : allPlatformDTOS) {
            platformIds.add(platformDTO.getPlatformId());
        }
        //获取 健康值 的map对象
        Map<String,Float> healthMap = getHealthByPlatformIds(platformIds);
        List<PlatformBlockVO> platformBlockVOS = new ArrayList<>();
        for(BriefPlatformDTO platformDTO : allPlatformDTOS) {
            //匹配着组装 type和对应state数量
            Map<String,Map<String,Integer>> typeMap = new HashMap<>();
            String type = "";
            List<String> typeList = new ArrayList<>();
            List<Integer> stateList = new ArrayList<>();
            List<List<Integer>> datasList = new ArrayList<>();
            PlatformBlockVO platformBlockVO = new PlatformBlockVO();
            //id,name
            platformBlockVO.setPlatformId(platformDTO.getPlatformId());
            platformBlockVO.setPlatformName(platformDTO.getName());
            //health
            platformBlockVO.setHealth(healthMap.get(platformDTO.getPlatformId()));
            //type[],datas[][]
            List<String> hostIds = new ArrayList<>();
            for(BriefHostDTO hostDTO : platformDTO.getHosts()) {
                hostIds.add(hostDTO.getHostId());
            }
            //循环设备 hostDTOS 获取typeMap值
            for(BriefHostDTO hostDTO : allHostDTOS) {
                if(hostIds.contains(hostDTO.getHostId())) {
                    if(hostDTO.getParentTemplates().size() != 0) {
                        String DTOTemplateId = hostDTO.getParentTemplates().get(0).getTemplateId();
                        for(BriefTemplateDTO template : allTemplateDTOS) {
                            if(template.getTemplateId().equals(DTOTemplateId)) {
                                type = template.getTemplateGroups().get(0).getName();
                            }
                        }
                    }else {
                        type = "未定义模板类型";
                    }
                    if(typeMap.get(type) == null) {
                        //循环取到 之前没有的 type 添加到 typeMap 中 并初始化
                        Map<String,Integer> stateMap = new HashMap<>();
                        Integer okNum = 0;
                        Integer warningNum = 0;
                        Integer highNum = 0;
                        String state = StatusConverter.StatusTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState());
                        if(StatusEnum.OK.getName().equals(state)) {
                            okNum += 1;
                        }else if(StatusEnum.WARNING.getName().equals(state)) {
                            warningNum += 1;
                        }else{
                            highNum += 1;
                        }
                        stateMap.put(StatusEnum.OK.getName(),okNum);
                        stateMap.put(StatusEnum.WARNING.getName(),warningNum);
                        stateMap.put(StatusEnum.HIGH.getName(),highNum);
                        typeMap.put(type,stateMap);
                    }else {
                        //typeMap 中存在 type 各个状态的主机数 + 1
                        Map<String,Integer> stateMap = typeMap.get(type);
                        String state = StatusConverter.StatusTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState());
                        if(StatusEnum.OK.getName().equals(state)) {
                            stateMap.put(StatusEnum.OK.getName(),stateMap.get(StatusEnum.OK.getName()) + 1);
                        }else if(StatusEnum.WARNING.getName().equals(state)) {
                            stateMap.put(StatusEnum.WARNING.getName(),stateMap.get(StatusEnum.WARNING.getName()) + 1);
                        }else{
                            stateMap.put(StatusEnum.HIGH.getName(),stateMap.get(StatusEnum.HIGH.getName()) + 1);
                        }
                        typeMap.put(type,stateMap);
                    }
                }
            }
            //循环map值做转换成数组
            for(String key : typeMap.keySet()) {
                //组装 typesList 类型list
                typeList.add(key);
                Map<String,Integer> stateMap = typeMap.get(key);
                for(String stateKey : stateMap.keySet()) {
                    //组装状态数据
                    stateList.add(stateMap.get(stateKey));
                }
                datasList.add(stateList);
            }
            platformBlockVO.setTypes(typeList.toArray(new String[0]));
            platformBlockVO.setDatas(BaseUtils.listsToArrays(datasList,Integer.class));
            platformBlockVOS.add(platformBlockVO);

        }
        return platformBlockVOS;
    }



}
