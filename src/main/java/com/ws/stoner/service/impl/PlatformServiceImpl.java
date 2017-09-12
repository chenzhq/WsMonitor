package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.platform.*;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.BaseUtils;
import com.ws.stoner.utils.StatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private PointSerivce pointSerivce;

    @Autowired
    private ItemService itemService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ValuemapService valuemapService;

    private List<BriefPlatformDTO> listPlatform(HostGroupGetRequest request) throws ServiceException {
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
    private int countPlatform(HostGroupGetRequest request) throws ServiceException {
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
     * 获取所有业务平台数量 hostgroup number
     * @return
     * @throws ServiceException
     */
    @Override
    public int countAllPlatform() throws ServiceException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String , Object> groupFilter = new HashMap<>();
        groupFilter.put("internal",ZApiParameter.HOSTGROUP_INTERNAL.NOT_INTERNAL.value);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(groupFilter)
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
        groupFilter.put("internal",ZApiParameter.HOSTGROUP_INTERNAL.NOT_INTERNAL.value);
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
        groupFilter.put("internal",ZApiParameter.HOSTGROUP_INTERNAL.NOT_INTERNAL.value);
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
        groupFilter.put("internal",ZApiParameter.HOSTGROUP_INTERNAL.NOT_INTERNAL.value);
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
        Map<String , Object> groupFilter = new HashMap<>();
        groupFilter.put("internal",ZApiParameter.HOSTGROUP_INTERNAL.NOT_INTERNAL.value);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES)
                .setFilter(groupFilter);
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
        platformFilter.put("internal",ZApiParameter.HOSTGROUP_INTERNAL.NOT_INTERNAL.value);
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
        platformFilter.put("internal",ZApiParameter.HOSTGROUP_INTERNAL.NOT_INTERNAL.value);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(platformFilter)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> problemPlatforms  = listPlatform(groupRequest);
        return problemPlatforms;
    }

    /**
     * 根据 platformId 获取指定业务平台数据 其中包括 其下设备 hostDTOS
     * @param platformId
     * @return
     * @throws ServiceException
     */
    @Override
    public BriefPlatformDTO getPlatformByPlatformId(String platformId) throws ServiceException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        List<String > platformIds = new ArrayList<>();
        platformIds.add(platformId);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setGroupIds(platformIds)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> platforms  = listPlatform(groupRequest);
        if(platforms.size() == 0) {
            return null;
        }else {
            return platforms.get(0);
        }

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
     * 获取 业务平台 下拉框列表 id key ,name value
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PlatDetailPlatformVO> getPlatDetailSelect() throws ServiceException {
        List<BriefPlatformDTO> platformDTOS = listAllPlatform();
        List<PlatDetailPlatformVO> platDetailPlatformVOS = new ArrayList<>();
        for(BriefPlatformDTO platformDTO : platformDTOS) {
            PlatDetailPlatformVO platDetailPlatformVO = new PlatDetailPlatformVO(
                    platformDTO.getPlatformId(),
                    platformDTO.getName()
            );
            platDetailPlatformVOS.add(platDetailPlatformVO);
        }
        return platDetailPlatformVOS;
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
            PlatformListVO platformVO = new PlatformListVO(
                    platform.getPlatformId(), //id
                    platform.getName(),  //name
                    StatusConverter.StatusTransform(platform.getCustomState()), //state
                    healthMap.get(platform.getPlatformId()),  //health
                    warningNum,
                    highNum,
                    allNum
            );
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
            //type[],datas[][]
            List<String> hostIds = new ArrayList<>();
            for(BriefHostDTO hostDTO : platformDTO.getHosts()) {
                hostIds.add(hostDTO.getHostId());
            }
            Map<List<String>,List<List<Integer>>> typeStateMap = getTypeStateDatas(allHostDTOS,allTemplateDTOS,hostIds);
            List<String> typeList = typeStateMap.entrySet().iterator().next().getKey();
            List<List<Integer>> datasList = typeStateMap.entrySet().iterator().next().getValue();
            //组装 PlatformBlockVO 对象
            PlatformBlockVO platformBlockVO = new PlatformBlockVO(
                    platformDTO.getPlatformId(),//id
                    platformDTO.getName(), //name
                    healthMap.get(platformDTO.getPlatformId()), //health
                    StatusConverter.StatusTransform(platformDTO.getCustomState()), //state
                    typeList.toArray(new String[0]),  //types
                    BaseUtils.listsToArrays(datasList,Integer.class)  //datas
            );
            platformBlockVOS.add(platformBlockVO);

        }
        return platformBlockVOS;
    }

    /**
     * 获取业务监控的 指定id的 业务平台基础数据 PlatformBlockVO 概述中一部分
     * @return
     * @throws ServiceException
     */
    @Override
    public PlatformBlockVO getPlatformBlockById(String platformId) throws ServiceException {
        BriefPlatformDTO platformDTO = getPlatformByPlatformId(platformId);
        List<BriefHostDTO> hostDTOS = platformDTO.getHosts();
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO hostDTO : hostDTOS) {
            hostIds.add(hostDTO.getHostId());
        }
        List<BriefHostDTO> allHostDTOS = hostService.getHostsByHostIds(hostIds);
        //所有模板，用于匹配 type
        List<BriefTemplateDTO> allTemplateDTOS = templateService.listAllTemplate();
        List<String> platformIds = new ArrayList<>();
        platformIds.add(platformId);
        //获取 健康值 的map对象
        Map<String,Float> healthMap = getHealthByPlatformIds(platformIds);
        //type[],datas[][]
        Map<List<String>,List<List<Integer>>> typeStateMap = getTypeStateDatas(allHostDTOS,allTemplateDTOS,hostIds);
        List<String> typeList = typeStateMap.entrySet().iterator().next().getKey();
        List<List<Integer>> datasList = typeStateMap.entrySet().iterator().next().getValue();
        //组装 PlatformBlockVO 对象
        PlatformBlockVO platformBlockVO = new PlatformBlockVO(
                platformDTO.getPlatformId(),//id
                platformDTO.getName(), //name
                healthMap.get(platformDTO.getPlatformId()), //health
                StatusConverter.StatusTransform(platformDTO.getCustomState()), //state
                typeList.toArray(new String[0]),  //types
                BaseUtils.listsToArrays(datasList,Integer.class)  //datas
        );
        return platformBlockVO;
    }

    /**
     * 获取指定 platformId 的 List<PlatDetailHostVO> 用于分类菜单显示 概述中一部分
     * @param platformId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PlatDetailHostVO> getHostsByPlatformId(String platformId) throws ServiceException {
        List<String> platformIds = new ArrayList<>();
        platformIds.add(platformId);
        List<BriefHostDTO> hostDTOS = hostService.getHostByPlatformIds(platformIds);
        List<PlatDetailHostVO> hostVOS = new ArrayList<>();
        for(BriefHostDTO hostDTO : hostDTOS) {
            PlatDetailHostVO hostVO = new PlatDetailHostVO(
                    hostDTO.getHostId(),
                    hostDTO.getName(),
                    StatusConverter.StatusTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState())
            );
            hostVOS.add(hostVO);
        }
        return hostVOS;
    }

    /**
     * 获取指定 hostId 的 List<PlatDetailItemVO> 用于分类菜单 显示
     * @param hostId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PlatDetailPointVO> getPointsByHostId(String hostId) throws ServiceException {
        List<String > hostIds = new ArrayList<>();
        hostIds.add(hostId);
        List<BriefPointDTO> pointDTOS = pointSerivce.getPointsByHostIds(hostIds);
        List<PlatDetailPointVO> pointVOS = new ArrayList<>();
        for(BriefPointDTO pointDTO : pointDTOS) {
            PlatDetailPointVO pointVO = new PlatDetailPointVO(
                    pointDTO.getPointId(),
                    pointDTO.getName(),
                    StatusConverter.StatusTransform(pointDTO.getCustomState())
            );
            pointVOS.add(pointVO);
        }
        return pointVOS;
    }

    /**
     * 获取指定 pointId 的 List<PlatDetailPointVO> 用于分类菜单 显示
     * @param pointId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PlatDetailItemVO> getItemsByPointId(String pointId) throws ServiceException {
        List<String > pointIds = new ArrayList<>();
        pointIds.add(pointId);
        List<BriefItemDTO> itemDTOS = itemService.getItemsByPointIds(pointIds);
        List<BriefItemDTO> itemDTOSWithTriggers = itemService.getItemsWithTriggersByPointIds(pointIds);
        List<String> itemIdsWithTriggers = new ArrayList<>();
        for(BriefItemDTO itemDTO :itemDTOSWithTriggers) {
            itemIdsWithTriggers.add(itemDTO.getItemId());
        }
        List<PlatDetailItemVO> itemVOS = new ArrayList<>();
        for(BriefItemDTO itemDTO :itemDTOS) {
            //withTriggers
            boolean withTriggers ;
            if(itemIdsWithTriggers.contains(itemDTO.getItemId())) {
                withTriggers = true;
            }else {
                withTriggers = false;
            }
            //weight
            Integer weight;
            if(withTriggers) {
               weight = itemDTO.getWeight();
            }else {
                weight = 0;
            }
            //value
            String value = valuemapService.getTransformValue(itemDTO.getValuemapId(),itemDTO.getLastValue(),itemDTO.getUnits());

            PlatDetailItemVO itemVO = new PlatDetailItemVO(
                    itemDTO.getItemId(),
                    itemDTO.getName(),
                    StatusConverter.StatusTransform(itemDTO.getCustomState()),
                    value,
                    withTriggers,
                    weight
            );
            itemVOS.add(itemVO);
        }
        return itemVOS;
    }


    public static Map<List<String>,List<List<Integer>>> getTypeStateDatas(List<BriefHostDTO> allHostDTOS,List<BriefTemplateDTO> allTemplateDTOS,List<String> hostIds) throws ServiceException {
        Map<String,Map<String,Integer>> typeMap = new HashMap<>();
        String type = "";
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
        List<String> typeList = new ArrayList<>();
        List<Integer> oKList = new ArrayList<>();
        List<Integer> warningList = new ArrayList<>();
        List<Integer> highList = new ArrayList<>();
        for(String key : typeMap.keySet()) {
            //组装 typesList 类型list
            typeList.add(key);
            Map<String,Integer> stateMap = typeMap.get(key);
            oKList.add(stateMap.get(StatusEnum.OK.getName()));
            warningList.add(stateMap.get(StatusEnum.WARNING.getName()));
            highList.add(stateMap.get(StatusEnum.HIGH.getName()));
        }
        List<List<Integer>> dataList = new ArrayList<>();
        dataList.add(oKList);
        dataList.add(warningList);
        dataList.add(highList);
        Map<List<String> ,List<List<Integer>>> typeStateMap = new HashMap<>();
        typeStateMap.put(typeList,dataList);
        return typeStateMap;
    }

}
