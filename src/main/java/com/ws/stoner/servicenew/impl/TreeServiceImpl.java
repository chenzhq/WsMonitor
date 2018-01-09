package com.ws.stoner.servicenew.impl;

import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.daonew.TreeDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.mongo.hosttree.HostNode;
import com.ws.stoner.model.mongo.plattree.PlatNode;
import com.ws.stoner.model.vo.tree.TreeNodeVO;
import com.ws.stoner.servicenew.TemplateService;
import com.ws.stoner.servicenew.TreeService;
import com.ws.stoner.utils.BaseUtils;
import com.ws.stoner.utils.StatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2017/12/18
 */
@Service
@Transactional
public class TreeServiceImpl implements TreeService {

    private static final Logger logger = LoggerFactory.getLogger(TreeServiceImpl.class);

    @Autowired
    private TreeDAO treeDAO;

    @Autowired
    private TemplateService templateService;


    @Override
    public List<HostNode> initHostTree(List<HostNode> allNodes,List<BriefHostDTO> hostDTOS) {
        if(hostDTOS == null || hostDTOS.size() == 0) {
            return new ArrayList<>();
        }
        //构造zbxhostids hostnodeids
        List<String> zbxHostIds = new ArrayList<>();
        for(BriefHostDTO hostDTO : hostDTOS) {
            zbxHostIds.add("h"+hostDTO.getHostId());
        }

        //处理配置为空的特殊情况
        if(allNodes == null || allNodes.size() == 0) {
            allNodes = new ArrayList<>();
            HostNode root = new HostNode(
                    "g0",
                    "root",
                    "#"
            );
            allNodes.add(root);
            for(String zbxHostId : zbxHostIds) {
                HostNode hostNode = new HostNode(
                        zbxHostId,
                        "",
                        "g0"
                );
                allNodes.add(hostNode);
            }
            try {
                treeDAO.saveAllHostNodes(allNodes);
            } catch (DAOException e) {
                logger.error("调用 hostTreeDAO 错误！",e.getMessage());
                new ServiceException(e.getMessage());
            }
            return allNodes;
        }
        List<String> mongoHostIds = new ArrayList<>();
        for(HostNode hostNode : allNodes) {
            if("h".equals(hostNode.getNodeId().substring(0,1))) {
                mongoHostIds.add(hostNode.getNodeId());
            }
        }
        //初始化判断
        //如果zbx和mongo中的 hostIds 不匹配
        if(!BaseUtils.compare(zbxHostIds,mongoHostIds)) {

            //mongo中有但zbx中没有的需要 删除
            List<String> delIds = new ArrayList<>(mongoHostIds);
            List<HostNode> allTempNodes = new ArrayList<>(allNodes);
            delIds.removeAll(zbxHostIds);
            for(HostNode node : allTempNodes ) {
                if(delIds.contains(node.getNodeId())) {
                    allNodes.remove(node);
                }
            }
            //mongo中没有但zbx中有的需要 增加
            List<String> addIds = new ArrayList<>(zbxHostIds);
            addIds.removeAll(mongoHostIds);
            for(String addId : addIds) {
                HostNode addNode = new HostNode(
                        addId,
                        "",
                        "g0"
                );
                allNodes.add(addNode);
            }
            try {
                treeDAO.deleteAllHostNodes();
                treeDAO.saveAllHostNodes(allNodes);
            } catch (DAOException e) {
                logger.error("调用 hostTreeDAO 错误！",e.getMessage());
                new ServiceException(e.getMessage());
            }
        }
        return allNodes;
    }

    @Override
    public List<TreeNodeVO> listHostTreeVO(List<HostNode> allNodes, List<BriefHostDTO> hostDTOS, List<BriefTemplateDTO> templateDTOS) {
        //初始化 allNodes
        allNodes = initHostTree(allNodes,hostDTOS);
        //按类型分组
        List<HostNode> hostNodes = new ArrayList<>();
        List<HostNode> groupNodes = new ArrayList<>();
        for(HostNode hostNode :allNodes) {
            if("h".equals(hostNode.getNodeId().substring(0,1))) {
                hostNodes.add(hostNode);
            }else {
                groupNodes.add(hostNode);
            }
        }
        //先遍历host 转化成 vo对象，获得 hostNodeVOS
        List<TreeNodeVO> treeNodeVos = new ArrayList<>();
        for(HostNode hostNode : hostNodes) {
            List<TreeNodeVO> nodeVos = getTreeVOHostWithPoint(hostNode.getNodeId(),hostNode.getParentId(),hostDTOS,templateDTOS);
            treeNodeVos.addAll(nodeVos);
        }
        //构造 groupNodeVOS
        List<TreeNodeVO> groupNodeVos = new ArrayList<>();
        for(HostNode groupNode : groupNodes) {
            String groupState ;
            int hostHightNum = 0;
            int hostWarNum = 0;
            for(TreeNodeVO hostVO : treeNodeVos) {
                if(groupNode.getNodeId().equals(hostVO.getParent())) {
                    if(StatusEnum.HIGH.text.equals(hostVO.getState())) {
                        hostHightNum++;
                    }else if(StatusEnum.WARNING.text.equals(hostVO.getState())) {
                        hostWarNum++;
                    }else {

                    }
                }
            }
            if(hostHightNum > 0) {
                groupState = StatusEnum.HIGH.text;
            }else if(hostWarNum > 0) {
                groupState = StatusEnum.WARNING.text;
            }else {
                groupState = StatusEnum.OK.text;
            }
            TreeNodeVO groupNodeVO = new TreeNodeVO(
                    groupNode.getNodeId(),
                    groupNode.getNodeName(),
                    "group",
                    groupState,
                    groupNode.getParentId()
            );
            groupNodeVos.add(groupNodeVO);
        }
        //5:合并 groupVO ,hostVO,pointVO
        treeNodeVos.addAll(groupNodeVos);
        return treeNodeVos;
    }

    @Override
    public List<HostNode> listAllHostNodes() {
        List<HostNode> allNodes = null;
        try {
            allNodes = treeDAO.listHostTreeFromMongo();
        } catch (DAOException e) {
            logger.error("调用 hostTreeDAO listHostTreeFromMongo 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return allNodes;
    }

    @Override
    public HostNode getHostNodeByNodeId(String nodeId) {
        HostNode hostNode = null;
        try {
            hostNode = treeDAO.getHostNodeByNodeId(nodeId);
        } catch (DAOException e) {
            logger.error("调用 treeDAO getHostNodeByNodeId 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return hostNode;
    }

    @Override
    public List<HostNode> getHostNodesNyNodeIds(List<String> nodeIds) {
        List<HostNode> hostNodes = null;
        try {
            hostNodes = treeDAO.getHostNodesByNodeIds(nodeIds);
        } catch (DAOException e) {
            logger.error("调用 treeDAO getHostNodeByNodeId 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return hostNodes;
    }

    @Override
    public List<PlatNode> listPlatNodes() {
        List<PlatNode> allNodes = null;
        try {
            allNodes = treeDAO.listPlatTreeFromMongo();
        } catch (DAOException e) {
            logger.error("调用 treeDAO listPlatTreeFromMongo 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return allNodes;
    }

    @Override
    public List<PlatNode> getPlatNodesByPlatId(String platId) throws ServiceException {
        List<PlatNode> platNodes = new ArrayList<>();
        //取业务节点
        try {
            PlatNode platNode = treeDAO.getPlatNodeByNodeId(platId);
            platNodes.add(platNode);
        } catch (DAOException e) {
            logger.error("调用 treeDAO getPlatNodeByNodeId 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        //取业务下集群节点（包括业务下设备）
        List<String> parentIds = new ArrayList<>();
        try {
            List<PlatNode> childNodes = treeDAO.getPlatNodesByParentId(platId);
            platNodes.addAll(childNodes);
            for(PlatNode child : childNodes) {
                if("c".equals(child.getNodeId().substring(0,1))) {
                    parentIds.add(child.getNodeId());
                }
            }
        } catch (DAOException e) {
            logger.error("调用 treeDAO getPlatNodesByParentId 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        //取集群下设备节点
        try {
            List<PlatNode> hostNodes = treeDAO.getPlatNodesByParentIds(parentIds);
            if(hostNodes != null && hostNodes.size() > 0) {
                platNodes.addAll(hostNodes);
            }
        } catch (DAOException e) {
            logger.error("调用 treeDAO getPlatNodesByParentIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return platNodes;
    }

    @Override
    public List<PlatNode> getPlatNodesByPlatIds(List<String> platNodeIds) throws ServiceException {
        List<PlatNode> nodes = new ArrayList<>();
        try {
            //取业务节点
            List<PlatNode> platNodes = treeDAO.getPlatNodesByNodeIds(platNodeIds);
            nodes.addAll(platNodes);
        } catch (DAOException e) {
            logger.error("调用 treeDAO getPlatNodesByNodeIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        //取业务下集群节点（包括业务下设备）
        List<String> parentIds = new ArrayList<>();
        try {
            List<PlatNode> childNodes = treeDAO.getPlatNodesByParentIds(platNodeIds);
            nodes.addAll(childNodes);
            for(PlatNode child : childNodes) {
                if("c".equals(child.getNodeId().substring(0,1))) {
                    parentIds.add(child.getNodeId());
                }
            }
        } catch (DAOException e) {
            logger.error("调用 treeDAO getPlatNodesByParentIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        //取集群下设备节点
        try {
            List<PlatNode> hostNodes = treeDAO.getPlatNodesByParentIds(parentIds);
            if(hostNodes != null && hostNodes.size() > 0) {
                nodes.addAll(hostNodes);
            }
        } catch (DAOException e) {
            logger.error("调用 treeDAO getPlatNodesByParentIds 出错！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return nodes;
    }

    @Override
    public List<PlatNode> initPlatTree(List<PlatNode> allNodes, List<BriefPlatformDTO> platformDTOS, List<BriefHostDTO> hostDTOS) {
        if(platformDTOS == null || platformDTOS.size() == 0) {
            return new ArrayList<>();
        }
        //构造zbxhostids hostnodeids zbxplatids
        List<String> zbxPlatIds = new ArrayList<>();
        for(BriefPlatformDTO platformDTO : platformDTOS) {
            zbxPlatIds.add("f"+platformDTO.getPlatformId());
        }
        List<String> zbxHostIds = new ArrayList<>();
        for(BriefHostDTO hostDTO : hostDTOS) {
            zbxHostIds.add("h"+hostDTO.getHostId());
        }

        //处理配置为空的特殊情况
        if(allNodes == null || allNodes.size() == 0) {
            allNodes = new ArrayList<>();
            PlatNode root = new PlatNode(
                    "root",
                    "root",
                    "#"
            );
            allNodes.add(root);
            //添加业务平台node
            for(String zbxPlatId : zbxPlatIds) {
                PlatNode platNode = new PlatNode(
                        zbxPlatId,
                        "",
                        "root"
                );
                allNodes.add(platNode);
            }
            //添加设备node
            for(BriefHostDTO hostDTO : hostDTOS) {
                List<BriefPlatformDTO> hostPDTOS = hostDTO.getPlatforms();
                for(BriefPlatformDTO hostPDTO : hostPDTOS) {
                    if(zbxPlatIds.contains("f"+hostPDTO.getPlatformId())) {
                        //构造 hostNode
                        PlatNode hostNode = new PlatNode(
                                "h" + hostDTO.getHostId(),
                                "",
                                "f" + hostPDTO.getPlatformId()
                        );
                        allNodes.add(hostNode);
                    }
                }
            }
            try {
                treeDAO.saveAllPlatNodes(allNodes);
            } catch (DAOException e) {
                logger.error("调用 platTreeDAO 错误！",e.getMessage());
                new ServiceException(e.getMessage());
            }
            return allNodes;
        }
        //mongo配置不为空 处理待定...
        return allNodes;


    }

    @Override
    public List<TreeNodeVO> listPlatTreeVO(List<PlatNode> allNodes, List<BriefPlatformDTO> platformDTOS, List<BriefHostDTO> hostDTOS, List<BriefTemplateDTO> templateDTOS) {
        //初始化 allNodes
        allNodes = initPlatTree(allNodes,platformDTOS,hostDTOS);
        //按类型分组
        List<PlatNode> platNodes = new ArrayList<>();
        List<PlatNode> hostNodes = new ArrayList<>();
        List<PlatNode> clusNodes = new ArrayList<>();
        for(PlatNode platNode :allNodes) {
            if("f".equals(platNode.getNodeId().substring(0,1))) {
                platNodes.add(platNode);
            }else if("c".equals(platNode.getNodeId().substring(0,1))) {
                clusNodes.add(platNode);
            }else if("h".equals(platNode.getNodeId().substring(0,1))) {
                hostNodes.add(platNode);
            }
        }
        //遍历host 转化成 vo对象，获得 hostNodeVOS
        List<TreeNodeVO> hostNodeVos = new ArrayList<>();
        for(PlatNode platNode : hostNodes) {
            List<TreeNodeVO> nodeVos = getTreeVOHostWithPoint(platNode.getNodeId(),platNode.getParentId(),hostDTOS,templateDTOS);
            hostNodeVos.addAll(nodeVos);
        }
        //遍历 cluster 转化成 vo 对象，获得 clusNodeVOS
        List<TreeNodeVO> clusNodeVos = new ArrayList<>();
        for(PlatNode platNode : clusNodes) {
            String clusState ;
            int hostHightNum = 0;
            int hostWarNum = 0;
            for(TreeNodeVO hostVO : hostNodeVos) {
                if(platNode.getNodeId().equals(hostVO.getParent())) {
                    if(StatusEnum.HIGH.text.equals(hostVO.getState())) {
                        hostHightNum++;
                    }else if(StatusEnum.WARNING.text.equals(hostVO.getState())) {
                        hostWarNum++;
                    }
                }
            }
            if(hostHightNum > 0) {
                clusState = StatusEnum.HIGH.text;
            }else if(hostWarNum > 0) {
                clusState = StatusEnum.WARNING.text;
            }else {
                clusState = StatusEnum.OK.text;
            }
            TreeNodeVO clusNodeVO = new TreeNodeVO(
                    platNode.getNodeId(),
                    platNode.getNodeName(),
                    "cluster",
                    clusState,
                    platNode.getParentId()
            );
            clusNodeVos.add(clusNodeVO);
        }
        //遍历 platNodes 转化成 vo 对象，获得 platNodeVOS
        List<TreeNodeVO> platNodeVos = new ArrayList<>();
        for(PlatNode platNode : platNodes) {
            String platId = platNode.getNodeId().substring(1);
            for(BriefPlatformDTO platformDTO : platformDTOS) {
                if(platId.equals(platformDTO.getPlatformId())) {
                    //构造 platNodeVOS
                    TreeNodeVO platNodeVO = new TreeNodeVO(
                            platNode.getNodeId(),
                            platformDTO.getName(),
                            "platform",
                            StatusConverter.getTextStatusTransform(platformDTO.getCustomState()),
                            platNode.getParentId()
                    );
                    platNodeVos.add(platNodeVO);
                }
            }
        }

        platNodeVos.addAll(clusNodeVos);
        platNodeVos.addAll(hostNodeVos);
        return platNodeVos;
    }

    @Override
    public List<TreeNodeVO> getTreeVOHostWithPoint(String nodeId, String parentId, List<BriefHostDTO> hostDTOS, List<BriefTemplateDTO> templateDTOS) {
        List<TreeNodeVO> nodeVOS = new ArrayList<>();
        String type ;
        String hostId = nodeId.substring(1);
        for(BriefHostDTO hostDTO : hostDTOS) {
            if(hostId.equals(hostDTO.getHostId())) {
                //构造 hostNodeVOS
                type = templateService.transformTypeBytemplateId(hostDTO.getParentTemplates(),templateDTOS);
                TreeNodeVO treeNodeVO = new TreeNodeVO(
                        nodeId,
                        hostDTO.getName(),
                        type,
                        StatusConverter.getTextStatusTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState()),
                        parentId
                );
                nodeVOS.add(treeNodeVO);
                //构造 pointNodeVOS
                List<BriefPointDTO> pointDTOS = hostDTO.getPoints();
                if(pointDTOS != null) {
                    for(BriefPointDTO pointDTO : pointDTOS) {
                        TreeNodeVO pointNodeVO = new TreeNodeVO(
                                "p" + pointDTO.getPointId(),
                                pointDTO.getName(),
                                "point",
                                StatusConverter.getTextStatusTransform(pointDTO.getCustomState()),
                                nodeId
                        );
                        nodeVOS.add(pointNodeVO);
                    }
                }
            }
        }
        return nodeVOS;
    }

    @Override
    public List<TreeNodeVO> getTreeVOPointWithItem(BriefPointDTO pointDTO) {
        if(pointDTO == null) {
            return new ArrayList<>();
        }
        List<TreeNodeVO> treeNodeVOS = new ArrayList<>();
        TreeNodeVO pointNodeVO = new TreeNodeVO(
                "p"+pointDTO.getPointId(),
                pointDTO.getName(),
                "point",
                StatusConverter.getTextStatusTransform(pointDTO.getCustomState()),
                "h" + pointDTO.getHostId()
        );
        treeNodeVOS.add(pointNodeVO);
        List<BriefItemDTO> itemDTOS = pointDTO.getItems();
        if(itemDTOS != null) {
            for(BriefItemDTO itemDTO : itemDTOS) {
                TreeNodeVO itemNodeVO = new TreeNodeVO(
                        "i"+itemDTO.getItemId(),
                        itemDTO.getName(),
                        "item",
                        StatusConverter.statusTransForItem(itemDTO.getCustomState(),itemDTO.getWeight()),
                        pointNodeVO.getId()
                );
                treeNodeVOS.add(itemNodeVO);
            }
        }
        return treeNodeVOS;

    }

    @Override
    public List<TreeNodeVO> getTreeVOHostWithItem(List<BriefHostDTO> hostDTOS,
                                                  List<BriefPointDTO> pointDTOS,
                                                  List<HostNode> hostNodes,
                                                  List<BriefTemplateDTO> templateDTOS) {
        List<TreeNodeVO> treeNodeVOS = new ArrayList<>();
        if(hostDTOS == null || hostDTOS.size() == 0 || hostNodes == null || hostNodes.size() == 0) {
            return treeNodeVOS;
        }
        for(HostNode hostNode : hostNodes) {
            for(BriefHostDTO hostDTO : hostDTOS) {
                if(hostNode.getNodeId().substring(1).equals(hostDTO.getHostId())) {
                    String type = templateService.transformTypeBytemplateId(hostDTO.getParentTemplates(),templateDTOS);
                    TreeNodeVO hostNodeVO = new TreeNodeVO(
                            hostNode.getNodeId(),
                            hostDTO.getName(),
                            type,
                            StatusConverter.getTextStatusTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState()),
                            hostNode.getParentId()
                    );
                    treeNodeVOS.add(hostNodeVO);
                    break;
                }
            }
            for(BriefPointDTO pointDTO : pointDTOS) {
                if(hostNode.getNodeId().substring(1).equals(pointDTO.getHostId())) {
                    List<TreeNodeVO> pointNodeVOS = getTreeVOPointWithItem(pointDTO);
                    treeNodeVOS.addAll(pointNodeVOS);
                }
            }
        }
        return treeNodeVOS;
    }

}
