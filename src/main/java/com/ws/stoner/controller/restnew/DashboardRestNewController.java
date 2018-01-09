package com.ws.stoner.controller.restnew;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.constant.BaseConsts;
import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.constant.ResponseSuccessEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.mongo.hosttree.HostNode;
import com.ws.stoner.model.mongo.plattree.PlatNode;
import com.ws.stoner.model.vo.*;
import com.ws.stoner.model.vo.ack.AckVO;
import com.ws.stoner.model.vo.alert.AlertStatVO;
import com.ws.stoner.model.vo.alert.AlertVO;
import com.ws.stoner.model.vo.item.ItemVO;
import com.ws.stoner.model.vo.problem.ProblemVO;
import com.ws.stoner.model.vo.tree.TreeNodeVO;
import com.ws.stoner.model.vo.value.ValueVO;
import com.ws.stoner.servicenew.*;
import com.ws.stoner.utils.RestResultGenerator;
import com.ws.stoner.utils.StatusConverter;
import com.ws.stoner.utils.TypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_RESPONSE_SUCCESS;

/**
 * Created by zkf on 2017/12/7.
 */
@RestController
public class DashboardRestNewController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardRestNewController.class);

    @Autowired
    private HostService hostService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private PointService pointService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private TreeService treeService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private TriggerService triggerService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AlertService alertService;

    @Autowired
    private AckService ackService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private HistoryService historyService;

    /**
     *
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/hosts", method = RequestMethod.GET)
    public String hosts() throws ServiceException {
        List<HostVO> details = hostService.listAllHostVOS();
        AllHostVO allHostVO =  new AllHostVO(
                "host",
                details,
                details.size()
        );
        return RestResultGenerator.genSuccessResult(allHostVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/platforms", method = RequestMethod.GET)
    public String platforms() throws ServiceException {
        List<PlatformVO> details = platformService.listAllPlatVOS();
        AllPlatformVO allPlatformVO = new AllPlatformVO(
                "platform",
                details,
                details.size()
        );
        return RestResultGenerator.genSuccessResult(allPlatformVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/points", method = RequestMethod.GET)
    public String points() throws ServiceException {
        List<PointVO> details = pointService.listAllPointVO();
        AllPointVO allPointVO = new AllPointVO(
                "point",
                details,
                details.size()
        );
        return RestResultGenerator.genSuccessResult(allPointVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/platforms/:id", method = RequestMethod.GET)
    public String platformByPlatId(@RequestParam("platform_id") String platformId) throws ServiceException {
        PlatformVO platformVO = platformService.getPlatVOByPlatId(platformId);
        return RestResultGenerator.genResult(platformVO, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/hosts/:id", method = RequestMethod.GET)
    public String hostByHostId(@RequestParam("host_id") String host_id) throws ServiceException {
        HostVO hostVO = hostService.getHostVOByHostId(host_id);
        return RestResultGenerator.genSuccessResult(hostVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/points/:id", method = RequestMethod.GET)
    public String pointByPointId(@RequestParam("point_id") String point_id) throws ServiceException {
        PointVO pointVO = pointService.getPointVOByPointId(point_id);
        return RestResultGenerator.genSuccessResult(pointVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();

    }

    @RequestMapping(value = "/hosts/:id/points", method = RequestMethod.GET)
    public String pointsByHostId(@RequestParam("host_id") String hostId) throws ServiceException {
        List<PointVO> details = pointService.getPointVOSByHostId(hostId);
        BriefHostDTO hostDTO = hostService.getHostDTOByHostId(hostId);
        HostPointsVO hostPointsVO = new HostPointsVO(
                hostId,
                hostDTO.getName(),
                null,
                null,
                details
        );
        return RestResultGenerator.genSuccessResult(hostPointsVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "point/:id/items", method = RequestMethod.GET)
    public String itemsByPointId(@RequestParam("point_id") String pointId) throws ServiceException {
         List<ItemVO> details = itemService.getItemVOSByPOintId(pointId);
        BriefPointDTO pointDTO = pointService.getPointDTOByPointId(pointId);
        PointItemsVO pointItemsVO = new PointItemsVO(
                pointId,
                pointDTO.getName(),
                StatusConverter.StatusTransform(pointDTO.getCustomState()),
                details
        );
        return RestResultGenerator.genSuccessResult(pointItemsVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/hosts/:id/items", method = RequestMethod.GET)
    public String itemsByHostId(@RequestParam("host_id") String hostId) throws ServiceException {
        List<ItemVO> details = itemService.getItemVOSByHostId(hostId);
        BriefHostDTO hostDTO = hostService.getHostDTOByHostId(hostId);
        HostItemsVO hostItemsVO = new HostItemsVO(
                hostDTO.getHostId(),
                hostDTO.getName(),
                null,
                null,
                details
        );
        return RestResultGenerator.genSuccessResult(hostItemsVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/tree/items", method = RequestMethod.GET)
    public String itemsTree(@RequestParam(required = false, value = "host_ids[]") List<String> hostIds,
                            @RequestParam(required = false, value = "point_ids[]") List<String> pointIds
                            ) throws ServiceException {
        List<TreeNodeVO> treeNodeVOS;
        if(hostIds == null && pointIds == null) {
            //请求参数错误
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.ILLEGAL_PARAMS).toString();
        }else if(hostIds == null && pointIds != null) {
            List<BriefPointDTO> pointDTOS = pointService.getPointDTOSByPointIdsWithItems(pointIds);
            treeNodeVOS = new ArrayList<>();
            for(BriefPointDTO pointDTO : pointDTOS) {
                List<TreeNodeVO> nodeVOS = treeService.getTreeVOPointWithItem(pointDTO);
                treeNodeVOS.addAll(nodeVOS);
            }
            logger.info("调用 监控点--监控项 设备树API");
        }else if(hostIds != null && pointIds == null) {
            List<String> hostNodeIds = new ArrayList<>();
            for(String hostId : hostIds) {
                hostNodeIds.add("h"+hostId);
            }
            List<HostNode> hostNodes = treeService.getHostNodesNyNodeIds(hostNodeIds);
            List<BriefHostDTO> hostDTOS = hostService.getHostDTOSByHostIdsWithPoint(hostIds);
            List<BriefPointDTO> pointDTOS = pointService.getPointDTOSByHostIdsWithItems(hostIds);
            List<BriefTemplateDTO> templateDTOS = templateService.listAllTempDTOS();
            treeNodeVOS = treeService.getTreeVOHostWithItem(hostDTOS,pointDTOS,hostNodes,templateDTOS);
            logger.info("调用 设备--监控点--监控项 设备树API");
        }else {
            //请求参数错误
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.ILLEGAL_PARAMS).toString();
        }
        return RestResultGenerator.genSuccessResult(new TreeVO(treeNodeVOS), ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/tree/points", method = RequestMethod.GET)
    public String pointsTree(@RequestParam(required = false, value = "platform_ids[]") List<String> platformIds,
                             @RequestParam(required = false, value = "host_ids[]") List<String> hostIds) throws ServiceException{
        List<TreeNodeVO> treeNodeVOS;
        if(platformIds == null && hostIds == null) {
           //设备树 到 point
            List<HostNode> allNodes = treeService.listAllHostNodes();
            List<BriefHostDTO> hostDTOS = hostService.listAllHostDTOS();
            List<BriefTemplateDTO> templateDTOS = templateService.listAllTempDTOS();
            treeNodeVOS = treeService.listHostTreeVO(allNodes,hostDTOS,templateDTOS);
            logger.info("调用 分组--设备--监控点 概览设备树API 到point");
        }else if(platformIds == null && hostIds != null) {
            List<String> hostNodeIds = new ArrayList<>();
            for(String hostId : hostIds) {
                hostNodeIds.add("h"+hostId);
            }
            List<HostNode> hostNodes = treeService.getHostNodesNyNodeIds(hostNodeIds);
            List<BriefHostDTO> hostDTOS = hostService.getHostDTOSByHostIdsWithPoint(hostIds);
            List<BriefTemplateDTO> templateDTOS = templateService.listAllTempDTOS();
            treeNodeVOS = new ArrayList<>();
            for(HostNode hostNode : hostNodes) {
                List<TreeNodeVO> nodeVOS = treeService.getTreeVOHostWithPoint(hostNode.getNodeId(),hostNode.getParentId(),hostDTOS,templateDTOS);
                treeNodeVOS.addAll(nodeVOS);
            }
            logger.info("调用 设备--监控点 获取设备下监控点 设备树 API");
        }else if(platformIds != null && hostIds == null) {
            //业务树 到 point 用于业务详情 选择树
            List<String> platNodeIds = new ArrayList<>();
            for(String platformId : platformIds) {
                platNodeIds.add("f"+platformId);
            }
            List<PlatNode> platNodes = treeService.getPlatNodesByPlatIds(platNodeIds);
            List<BriefPlatformDTO> platformDTOS = platformService.getPlatDTOSByPlatIds(platformIds);
            List<BriefHostDTO> hostDTOS = hostService.getHostDTOSWithPlatByPlatIds(platformIds);
            List<BriefTemplateDTO> templateDTOS = templateService.listAllTempDTOS();
            treeNodeVOS = treeService.listPlatTreeVO(platNodes,platformDTOS,hostDTOS,templateDTOS);
            logger.info("调用 业务平台--集群--设备--监控点 获取指定业务平台 业务树API");
        }else {
            //请求参数错误
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.ILLEGAL_PARAMS).toString();
        }

        return RestResultGenerator.genSuccessResult(new TreeVO(treeNodeVOS), ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/tree/hosts", method = RequestMethod.GET)
    public String hostsTree(@RequestParam(required = false, value = "platform_ids[]") List<String> platformIds) throws ServiceException{
        List<TreeNodeVO> treeNodeVOS ;
        //获取设备选择树
        if(platformIds == null || platformIds.size() == 0) {
            List<HostNode> allNodes = treeService.listAllHostNodes();
            List<BriefHostDTO> hostDTOS = hostService.listAllHostDTOSNoPoints();
            List<BriefTemplateDTO> templateDTOS = templateService.listAllTempDTOS();
            treeNodeVOS = treeService.listHostTreeVO(allNodes,hostDTOS,templateDTOS);
            logger.info("调用 分组--设备 获取设备选择树API");
        }else {
            //获取指定业务平台 业务树 到设备
            List<BriefPlatformDTO> platformDTOS = platformService.getPlatDTOSByPlatIds(platformIds);
            if(platformDTOS == null || platformDTOS.size() == 0) {
                //返回错误代码
                return RestResultGenerator.genErrorResult(ResponseErrorEnum.ILLEGAL_PARAMS).toString();
            }
            List<String> platNodeIds = new ArrayList<>();
            for(String platformId : platformIds) {
                platNodeIds.add("f"+platformId);
            }
            List<PlatNode> platNodes = treeService.getPlatNodesByPlatIds(platNodeIds);
            if(platNodes == null || platNodes.size() == 0) {
                //返回错误代码
                return RestResultGenerator.genErrorResult(ResponseErrorEnum.ILLEGAL_PARAMS).toString();
            }
            List<BriefHostDTO> hostDTOS = hostService.getHostDTOSWithoutPointByPlatIds(platformIds);
            List<BriefTemplateDTO> templateDTOS = templateService.listAllTempDTOS();
            treeNodeVOS = treeService.listPlatTreeVO(platNodes,platformDTOS,hostDTOS,templateDTOS);
            logger.info("调用 业务平台--集群--设备 获取指定业务平台 业务树API");
        }
        return RestResultGenerator.genSuccessResult(new TreeVO(treeNodeVOS), ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/tree/platforms", method = RequestMethod.GET)
    public String platsTree() throws ServiceException{
        List<PlatNode> allNodes = treeService.listPlatNodes();
        List<BriefPlatformDTO> platformDTOS = platformService.listAllPlatformDTOS();
        List<BriefHostDTO> hostDTOS = hostService.listAllHostDTOSWithPlatform();
        List<BriefTemplateDTO> templateDTOS = templateService.listAllTempDTOS();
        List<TreeNodeVO> treeNodeVOS = treeService.listPlatTreeVO(allNodes,platformDTOS,hostDTOS,templateDTOS);
        logger.info("调用 获取所有业务平台 业务树API");
        return RestResultGenerator.genSuccessResult(new TreeVO(treeNodeVOS), ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }


    @RequestMapping(value = "/dynamic", method = RequestMethod.GET)
    public String problems() throws ServiceException {
        List<BriefTriggerDTO> problemTris = problemService.listProblemVOS();
        List<String> eventIds = new ArrayList<>();
        for(BriefTriggerDTO triggerDTO : problemTris) {
            eventIds.add(triggerDTO.getLastEvent().getEventId());
        }
        List<AlertStatVO> alertStatVOS = alertService.getAlertVOByEventIds(eventIds);
        List<ProblemVO> problemVOS = new ArrayList<>();
        for(BriefTriggerDTO problemTri : problemTris) {
            ProblemVO problemVO = problemService.getProblemVOByTriggerDTO(problemTri,alertStatVOS);
            problemVOS.add(problemVO);
        }
        ProblemsVO problemsVO = new ProblemsVO(problemVOS);
        return RestResultGenerator.genSuccessResult(problemsVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/platforms/:id/dynamic", method = RequestMethod.GET)
    public String problemsByPlatId(@RequestParam("platform_id") String platformId) throws ServiceException {
        List<BriefTriggerDTO> problemTris = problemService.getProblemVOSByPlatId(platformId);
        List<String> eventIds = new ArrayList<>();
        for(BriefTriggerDTO triggerDTO : problemTris) {
            eventIds.add(triggerDTO.getLastEvent().getEventId());
        }
        List<AlertStatVO> alertStatVOS = alertService.getAlertVOByEventIds(eventIds);
        List<ProblemVO> problemVOS = new ArrayList<>();
        for(BriefTriggerDTO problemTri : problemTris) {
            ProblemVO problemVO = problemService.getProblemVOByTriggerDTO(problemTri,alertStatVOS);
            problemVOS.add(problemVO);
        }
        ProblemsVO problemsVO = new ProblemsVO(problemVOS);
        return RestResultGenerator.genSuccessResult(problemsVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/hosts/:id/dynamic", method = RequestMethod.GET)
    public String problemsByHostId(@RequestParam("host_id") String hostId) throws ServiceException {
        List<BriefTriggerDTO> problemTris = problemService.getProblemVOSByHostId(hostId);
        List<String> eventIds = new ArrayList<>();
        for(BriefTriggerDTO triggerDTO : problemTris) {
            eventIds.add(triggerDTO.getLastEvent().getEventId());
        }
        List<AlertStatVO> alertStatVOS = alertService.getAlertVOByEventIds(eventIds);
        List<ProblemVO> problemVOS = new ArrayList<>();
        for(BriefTriggerDTO problemTri : problemTris) {
            ProblemVO problemVO = problemService.getProblemVOByTriggerDTO(problemTri,alertStatVOS);
            problemVOS.add(problemVO);
        }
        ProblemsVO problemsVO = new ProblemsVO(problemVOS);
        return RestResultGenerator.genSuccessResult(problemsVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/dynamic/:id", method = RequestMethod.GET)
    public String Onedynamic(@RequestParam("event_id") String eventId) {
        EventVO eventVO = new EventVO();
        BriefEventDTO eventDTO = eventService.getDTOByEventId(eventId);
        if(eventDTO == null) {
            return null;
        }
        String triggerId = eventDTO.getObjectId();
        BriefTriggerDTO eventTri = triggerService.getDTOByTriId(triggerId);
        if(eventTri.getHosts() != null && eventTri.getHosts().size() > 0) {
            BriefHostDTO hostDTO = eventTri.getHosts().get(0);
            BriefTemplateDTO templateDTO = templateService.getDTOByHostId(hostDTO.getHostId());
            String type = "";
            if(templateDTO != null) {
                type = templateDTO.getTemplateGroups().get(0).getName();
            }
            eventVO.setHost(
                    new HostVO(
                            hostDTO.getHostId(),
                            hostDTO.getName(),
                            TypeConverter.transforHostType(type),
                            StatusConverter.getTextStatusTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState()),
                            null
                    )
            );
        }
        if(eventTri.getItems() != null && eventTri.getItems().size() > 0) {
            BriefItemDTO itemDTO = eventTri.getItems().get(0);
            BriefPointDTO pointDTO = pointService.getPointDTOByItemId(itemDTO.getItemId());
            eventVO.setPoint(
                    new PointVO(
                            pointDTO.getPointId(),
                            pointDTO.getName(),
                            StatusConverter.getTextStatusTransform(pointDTO.getCustomState()),
                            null,
                            null

                    )
            );
            eventVO.setItem(
                    new ItemVO(
                            itemDTO.getItemId(),
                            itemDTO.getName(),
                            StatusConverter.statusTransForItem(itemDTO.getCustomState(),itemDTO.getWeight()),
                            itemDTO.getWeight()!=0
                    )
            );
        }
        eventVO.setId(eventId);
        eventVO.setTriggerId(eventDTO.getObjectId());
        eventVO.setDescription(eventTri.getName());
        eventVO.setState(StatusConverter.getEventValueState(eventDTO.getValue()));
        eventVO.setTime(eventDTO.getClock().format(BaseConsts.TIME_FORMATTER));
        //待定
        eventVO.setComments("");
        eventVO.setPriority(StatusConverter.getStatusTextByTriggerPriority(eventTri.getPriority()));
        eventVO.setRecoveryEventId(eventDTO.getrEventid());
        if(eventDTO.getrEventid() != null && eventDTO.getrEventid() != "") {
            BriefEventDTO rEventDTO = eventService.getDTOByEventId(eventDTO.getrEventid());
            eventVO.setRecoveryTime(rEventDTO.getClock().format(BaseConsts.TIME_FORMATTER));
        }else {
            eventVO.setRecoveryTime("");
        }

        //alerts ack
        if(eventDTO.getValue() == ZApiParameter.EVENT_VALUE.PROBLEM.value) {
            List<AlertVO> alertVOS = alertService.getAlertVOSByEventDTO(eventDTO);
            List<BriefAcknowledgeDTO> ackDTOS = eventDTO.getAcknowledges();
            List<AckVO> ackVOS = ackService.getAckVOSByDTOS(ackDTOS);
            eventVO.setAcks(ackVOS);
            eventVO.setAlerts(alertVOS);
        }
        return RestResultGenerator.genSuccessResult(eventVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "/history/:id", method = RequestMethod.GET)
    public String historyByItemId(@RequestParam("item_id") String itemId) {
        BriefItemDTO itemDTO = itemService.getDTOByItemId(itemId);
        String type = StatusConverter.valueTypeTransform(itemDTO.getValueType(),itemDTO.getDataType());
        List<BriefHistoryDTO> historyDTOS = historyService.getHistoryDTOSByItemDTO(itemDTO);
        List<ValueVO> valueVOS = historyService.getValueVOSByHisDTO(historyDTOS,type);
        HistoryVO historyVO = new HistoryVO(
                itemId,
                itemDTO.getName(),
                type,
                valueVOS,
                valueVOS.size()
        );
        return RestResultGenerator.genSuccessResult(historyVO, ResponseSuccessEnum.REST_RESPONSE_SUCCESS).toString();
    }

}

