package com.ws.stoner.service.impl;

import com.ws.stoner.constant.GraphTypeEnum;
import com.ws.stoner.constant.PlatformTreeTypeEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.dao.MongoGraphDAO;
import com.ws.stoner.dao.MongoPlatformTreeDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.GraphType;
import com.ws.stoner.model.DO.mongo.Item;
import com.ws.stoner.model.DO.mongo.PlatformTree;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.HostDetailItemGraphVO;
import com.ws.stoner.model.view.HostDetailItemVO;
import com.ws.stoner.model.view.PlatformTreeVO;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.StatusConverter;
import com.ws.stoner.utils.ThresholdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by pc on 2017/7/18.
 */
@Service
public class GraphServiceImpl implements GraphService {

    private static final Logger logger = LoggerFactory.getLogger(GraphServiceImpl.class);

    @Autowired
    private MongoGraphDAO mongoGraphDAO;

    @Autowired
    private MongoPlatformTreeDAO mongoPlatformTreeDAO;

    @Autowired
    private HostService hostService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TriggerService triggerService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private PlatformService platformService;


    /**
     * 根据 valueType 查询出对应支持的图形插件名称
     * @param valueType
     * @return
     * @throws ServiceException
     */
    @Override
    public List<String> getGraphTypeByValueTypeFromMongo(String valueType) throws ServiceException {
        GraphType graphType = null;
        try {
            graphType = mongoGraphDAO.findGraphTypeByValueType(valueType);
        } catch (DAOException e) {
            logger.error("根据 valueType 查询 GraphType 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<String> graphTypesTemp = Arrays.asList(graphType.getGraphType());
        List<String> graphTypes = new ArrayList<>(graphTypesTemp);
        return graphTypes;
    }

    /**
     * 根据 hostId 查询出指定设备的 图形监控项 graph item  设备详情 图形展示
     * @param hostId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostDetailItemVO> getGraphItemByHostId(String hostId) throws ServiceException {
        //取 BriefItemDTO list, 过滤出 value_type=0,3
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        List<BriefItemDTO> itemDTOS = itemService.getValueItemsByHostIds(hostIds);
        List<BriefItemDTO> withTriggersItemDTOS = itemService.getItemsWithTriggersByHostIds(hostIds);
        List<String> itemIds = new ArrayList<>();
        for(BriefItemDTO itemDTO : withTriggersItemDTOS) {
            itemIds.add(itemDTO.getItemId());
        }
        //根据含有触发器的itemIds获取相关触发器 triggerDTO list
        List<BriefTriggerDTO> triggerDTOS = triggerService.getTriggersByItemIds(itemIds);
        //取mongodb的所有hostid下的items
        List<Item> mongoItems = itemService.getItemsByHostIdFromMongo(hostId);
        //step3:循环 List BriefItemDTO，根据itemid取mongodb的 items，新建ItemVO对象
        List<HostDetailItemVO> itemVOS = new ArrayList<>();
        //用于组装既是问题也是自定义的itemIds
        List<String> problemIds = new ArrayList<>();
        //循环组装自定义item
        for(BriefItemDTO itemDTO : itemDTOS) {
            HostDetailItemVO itemVO = new HostDetailItemVO();

            //阀值赋值：highPoint,warningPoint
            if(itemIds.contains(itemDTO.getItemId())) {
                itemVO = ThresholdUtils.setThresholdValueForItemVO( itemVO, itemDTO.getItemId(), triggerDTOS );
            }else {
                itemVO.setWithTriggers(false);
            }

            for(Item mongoItem :mongoItems) {
                if(mongoItem.getItemId().equals(itemDTO.getItemId())) {
                    // if 有匹配的，确定是用户自定义,flag赋给ItemVO的flag，用的是什么图形，graph_type赋给ItemVO的graph_type，graph_name赋给itemVO 的graph_name
                    itemVO.setItemId(itemDTO.getItemId());
                    itemVO.setItemName(itemDTO.getName());
                    itemVO.setFlag(true);
                    itemVO.setGraphName(mongoItem.getGraphName());
                    itemVO.setGraphType(mongoItem.getGraphType());
                    itemVO.setValueType(itemDTO.getValueType());
                    itemVO.setUnits(itemDTO.getUnits());
                    //state
                    if(StatusEnum.HIGH.code == itemDTO.getCustomState()) {
                        itemVO.setState(StatusEnum.HIGH.getName());
                        problemIds.add(itemDTO.getItemId());
                    }else if(StatusEnum.WARNING.code == itemDTO.getCustomState()) {
                        itemVO.setState(StatusEnum.WARNING.getName());
                        problemIds.add(itemDTO.getItemId());
                    }else {
                        itemVO.setState(StatusEnum.OK.getName());
                    }
                    itemVOS.add(itemVO);
                }
            }
        }
        //循环组装问题item
        for(BriefItemDTO itemDTO : itemDTOS) {
            //问题item，且非用户自定义
            if(itemDTO.getCustomState() != StatusEnum.OK.code && !problemIds.contains(itemDTO.getItemId())) {
                HostDetailItemVO itemVO = new HostDetailItemVO();
                //阀值赋值：highPoint,warningPoint
                if(itemIds.contains(itemDTO.getItemId())) {
                    itemVO = ThresholdUtils.setThresholdValueForItemVO( itemVO, itemDTO.getItemId(), triggerDTOS );
                }else {
                    itemVO.setWithTriggers(false);
                }

                itemVO.setItemId(itemDTO.getItemId());
                itemVO.setItemName(itemDTO.getName());
                itemVO.setFlag(false);
                itemVO.setGraphName(itemDTO.getName());
                itemVO.setGraphType("line");
                itemVO.setValueType(itemDTO.getValueType());
                itemVO.setState(StatusConverter.StatusTransform(itemDTO.getCustomState()));
                itemVO.setUnits(itemDTO.getUnits());
                itemVOS.add(itemVO);
            }
        }
        //根据value_type取对应的history.get,时间区间为前1天的数据 得到 BriefHistory list
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss");
        for(HostDetailItemVO itemVO : itemVOS) {
            List<BriefHistoryDTO> historyDTOS = historyService.getHistoryByItemId(itemVO.getItemId(),itemVO.getValueType(),1);
            Collections.reverse(historyDTOS);
            List<Float> datas = new ArrayList<>();
            List<String> dataTime = new ArrayList<>();
            //赋值 取list BriefHistory的 valueList 给 date，lastTimeList 给 data_time，
            String upUnits = itemVO.getUnits();
            for(BriefHistoryDTO historyDTO : historyDTOS) {
                Map<String,String> valueUnits = ThresholdUtils.transformGraphValue(historyDTO.getValue(),upUnits);
                itemVO.setUnits(valueUnits.entrySet().iterator().next().getKey());
                datas.add(Float.parseFloat(valueUnits.entrySet().iterator().next().getValue()));
                String dataTimeString = historyDTO.getLastTime().format(formatter);
                dataTime.add(dataTimeString);
            }
            itemVO.setData(datas.toArray(new Float[0]));
            itemVO.setDataTime(dataTime.toArray(new String[0]));
        }
        return itemVOS;
    }

    /**
     * 根据 itemId 获取 HostDetailItemGraphVO 类型对象 获取指定 设备详情 图形配置参数
     * @param itemId
     * @return
     * @throws ServiceException
     */
    @Override
    public HostDetailItemGraphVO getGraphItemByItemId(String itemId) throws ServiceException {
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemId);
        Item itemMongo = itemService.getItemByItemIdFromMongo(itemId);
        BriefItemDTO itemDTO = itemService.getItemsByItemIds(itemIds).get(0);
        HostDetailItemGraphVO itemGraphVO = new HostDetailItemGraphVO();
        itemGraphVO.setItemId(itemMongo.getItemId());
        itemGraphVO.setItemName(itemDTO.getName());
        itemGraphVO.setGraphName(itemMongo.getGraphName());
        itemGraphVO.setGraphType(itemMongo.getGraphType());
        itemGraphVO.setPointId(itemDTO.getPoints().get(0).getPointId());
        itemGraphVO.setPointName(itemDTO.getPoints().get(0).getName());
        itemGraphVO.setGraphValue(GraphTypeEnum.getName(itemMongo.getGraphType()));
        //valueType
        if("%".equals(itemDTO.getUnits())) {
            itemGraphVO.setValueType(itemDTO.getUnits());
        }else {
            itemGraphVO.setValueType(itemDTO.getValueType());
        }
        return itemGraphVO;
    }

    /**
     * 根据 pointId 查询出 图形监控项 graph item  监控点详情 图形报告 标签页
     * @param pointId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostDetailItemVO> getGraphItemByPointId(String pointId,int time) throws ServiceException {
        //取 BriefItemDTO list, 过滤出 value_type=0,3
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        List<BriefItemDTO> itemDTOS = itemService.getValueItemsByPointIds(pointIds);
        //step3:循环 List BriefItemDTO
        List<HostDetailItemVO> itemVOS = new ArrayList<>();
        //循环组装itemVO
        for(BriefItemDTO itemDTO : itemDTOS) {
                HostDetailItemVO itemVO = new HostDetailItemVO();
                itemVO.setItemId(itemDTO.getItemId());
                itemVO.setItemName(itemDTO.getName());
                itemVO.setGraphName(itemDTO.getName());
                itemVO.setGraphType("line");
                itemVO.setValueType(itemDTO.getValueType());
                itemVO.setUnits(itemDTO.getUnits());
                itemVO.setState(StatusConverter.StatusTransform(itemDTO.getCustomState()));
                itemVOS.add(itemVO);
        }
        //根据value_type取对应的history.get,时间区间为前 time 天的数据 得到 BriefHistory list
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss");
        for(HostDetailItemVO itemVO : itemVOS) {
            List<BriefHistoryDTO> historyDTOS = null;
            if(time == 40) {
                historyDTOS = historyService.getHistoryByItemIdLimit(itemVO.getItemId(),itemVO.getValueType(),time);

            }else {
                historyDTOS = historyService.getHistoryByItemId(itemVO.getItemId(),itemVO.getValueType(),time);
            }
            Collections.reverse(historyDTOS);
            List<Float> datas = new ArrayList<>();
            List<String> dataTime = new ArrayList<>();
            String upUnits = itemVO.getUnits();
            //赋值 取list BriefHistory的 valueList 给 date，lastTimeList 给 data_time，
            for(BriefHistoryDTO historyDTO : historyDTOS) {
                Map<String,String> valueUnits = ThresholdUtils.transformGraphValue(historyDTO.getValue(),upUnits);
                itemVO.setUnits(valueUnits.entrySet().iterator().next().getKey());
                datas.add(Float.parseFloat(valueUnits.entrySet().iterator().next().getValue()));
                String dataTimeString = historyDTO.getLastTime().format(formatter);
                dataTime.add(dataTimeString);
            }
            itemVO.setData(datas.toArray(new Float[0]));
            itemVO.setDataTime(dataTime.toArray(new String[0]));
        }
        return itemVOS;
    }

    /**
     * 根据 platformId 组装页面 业务树  PlatformTreeVO 渲染
     * @param platformId
     * @return
     * @throws ServiceException
     */
    @Override
    public PlatformTreeVO getPlatTreeByPlatformId(String platformId) throws ServiceException {
        List<String> platformIds = new ArrayList<>();
        platformIds.add(platformId);
        //判断mongodb中是否有业务平台数据，没有，则初始化
        List<PlatformTree> platformTrees = null;
        try {
             platformTrees = mongoPlatformTreeDAO.findAll();
        } catch (DAOException e) {
            logger.error("查询所有 platformTrees 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        if(platformTrees.size() == 0) {
            //初始化


        }
        //获取mongodb中 对应业务结构数据
        PlatformTree platform = null;
        try {
            platform = mongoPlatformTreeDAO.findById(platformId);
        } catch (DAOException e) {
            logger.error("根据 platformId 查询 platformTree 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        //获取指定业务平台所有设备 hostDTO
        List<BriefHostDTO> hostDTOS = hostService.getHostByPlatformIds(platformIds);
        //获取所有Template 用于指定type
        List<BriefTemplateDTO> allTemplateDTOS = templateService.listAllTemplate();
        //id,label
        String platformLabel = platform.getLabel();
        //所有集群
        List<PlatformTree> platformchildren = platform.getChildren();
        //用于组装集群VO
        List<PlatformTreeVO> clusterList = new ArrayList<>();
        for(PlatformTree cluster : platformchildren) {
            if(cluster.getChildren() == null) {
                //是设备
                //color,type
                String type = "";
                String color = "";
                for(BriefHostDTO hostDTO : hostDTOS) {
                    if(cluster.getId().equals(hostDTO.getHostId())) {
                        color = StatusConverter.colorTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState());
                        if(hostDTO.getParentTemplates().size() != 0) {
                            String DTOTemplateId = hostDTO.getParentTemplates().get(0).getTemplateId();
                            for(BriefTemplateDTO template : allTemplateDTOS) {
                                if(template.getTemplateId().equals(DTOTemplateId)) {
                                    type = template.getTemplateGroups().get(0).getName();
                                }
                            }
                        }else {
                            type = "其他";
                        }
                    }
                }
                PlatformTreeVO hostTreeVO = new PlatformTreeVO(
                        cluster.getId(),
                        cluster.getLabel(),
                        color,
                        type
                );
                clusterList.add(hostTreeVO);
            }else {
                List<PlatformTreeVO> hostList = new ArrayList<>();
                for(PlatformTree hostTree : cluster.getChildren()) {
                    String type = "";
                    String color = "";
                    for(BriefHostDTO hostDTO : hostDTOS) {
                        if(hostTree.getId().equals(hostDTO.getHostId())) {
                            color = StatusConverter.colorTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState());
                            if(hostDTO.getParentTemplates().size() != 0) {
                                String DTOTemplateId = hostDTO.getParentTemplates().get(0).getTemplateId();
                                for(BriefTemplateDTO template : allTemplateDTOS) {
                                    if(template.getTemplateId().equals(DTOTemplateId)) {
                                        type = template.getTemplateGroups().get(0).getName();
                                    }
                                }
                            }else {
                                type = "其他";
                            }
                        }
                    }
                    PlatformTreeVO hostTreeVO = new PlatformTreeVO(
                            hostTree.getId(),
                            hostTree.getLabel(),
                            color,
                            type
                    );
                    hostList.add(hostTreeVO);
                }
                String clusterColor = "";
                List<String> colors = new ArrayList<>();
                for(PlatformTreeVO hostTree : hostList) {
                    colors.add(hostTree.getColor());
                }
                if(colors.contains(StatusEnum.HIGH.color)) {
                    clusterColor = StatusEnum.HIGH.color;
                }else if(colors.contains(StatusEnum.WARNING.color)) {
                    clusterColor = StatusEnum.WARNING.color;
                }else {
                    clusterColor = StatusEnum.OK.color;
                }
                PlatformTreeVO clusterTree = new PlatformTreeVO(
                        cluster.getId(),
                        cluster.getLabel(),
                        clusterColor,
                        PlatformTreeTypeEnum.CLUSTER.getName(),
                        hostList
                );
                clusterList.add(clusterTree);
            }
        }
        //查询业务平台状态
        BriefPlatformDTO platformDTO = platformService.getPlatformByPlatformId(platformId);
        String platformColor = StatusConverter.colorTransform(platformDTO.getCustomState());
        PlatformTreeVO platformTreeVO = new PlatformTreeVO(
                platformId,
                platform.getLabel(),
                platformColor,
                PlatformTreeTypeEnum.PLATFORM.getName(),
                clusterList
        );
        return platformTreeVO;
    }

    /**
     * 初始化业务树 并返回可视化业务树对象 PlatformTreeVO
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PlatformTreeVO> initPlatTree(List<BriefTemplateDTO> allTemplateDTOS) throws ServiceException {

        List<BriefPlatformDTO> allPlatformDTOS = platformService.listAllPlatform();
        List<PlatformTreeVO> platformTreeVOS = new ArrayList<>();
        for(BriefPlatformDTO platformDTO : allPlatformDTOS) {
            List<BriefHostDTO> hostDTOS = platformDTO.getHosts();
            List<PlatformTree> hostList = new ArrayList<>();
            List<PlatformTreeVO> hostListVO = new ArrayList<>();
            for(BriefHostDTO hostDTO : hostDTOS) {
                String color = StatusConverter.colorTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState());
                PlatformTree hostTree = new PlatformTree(
                        hostDTO.getHostId(),
                        hostDTO.getName(),
                        PlatformTreeTypeEnum.HOST.getName()
                );
                hostList.add(hostTree);
                PlatformTreeVO hostTreeVO = new PlatformTreeVO(
                        hostDTO.getHostId(),
                        hostDTO.getName(),
                        color,
                        PlatformTreeTypeEnum.HOST.getName()
                );
                hostListVO.add(hostTreeVO);
            }
            PlatformTree platformTree = new PlatformTree(
                    platformDTO.getPlatformId(),
                    platformDTO.getName(),
                    PlatformTreeTypeEnum.PLATFORM.getName(),
                    hostList
            );
            try {
                mongoPlatformTreeDAO.save(platformTree);
            } catch (DAOException e) {
                logger.error("保存 platformTree 错误！{}", e.getMessage());
                new ServiceException(e.getMessage());
            }
            PlatformTreeVO platformTreeVO = new PlatformTreeVO(
                    platformDTO.getPlatformId(),
                    platformDTO.getName(),
                    StatusConverter.colorTransform(platformDTO.getCustomState()),
                    PlatformTreeTypeEnum.PLATFORM.getName(),
                    hostListVO
            );
            platformTreeVOS.add(platformTreeVO);

        }
        return platformTreeVOS;
    }

}
