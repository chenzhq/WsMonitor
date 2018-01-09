package com.ws.stoner.servicenew;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.mongo.hosttree.HostNode;
import com.ws.stoner.model.mongo.plattree.PlatNode;
import com.ws.stoner.model.vo.tree.TreeNodeVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/18
 */
public interface TreeService {
/*
设备树
 */
    /**
     * 初始化设备树
     * @param hostNodes
     */
    List<HostNode> initHostTree(List<HostNode> hostNodes, List<BriefHostDTO> hostDTOS) ;

    /**
     * 获取展示 设备树
     * @return
     */
    List<TreeNodeVO> listHostTreeVO(List<HostNode> allNodes, List<BriefHostDTO> hostDTOS, List<BriefTemplateDTO> templateDTOS) ;

    List<HostNode> listAllHostNodes();

    HostNode getHostNodeByNodeId(String nodeId) ;

    List<HostNode> getHostNodesNyNodeIds(List<String> nodeIds);



/*
业务树
 */

    /**
     * 列出所有的业务树 节点
     * @return
     */
    List<PlatNode> listPlatNodes();

    /**
     * 获取指定 platId 的所有业务 节点
     * @param platId
     * @return
     * @throws ServiceException
     */
    List<PlatNode> getPlatNodesByPlatId(String platId) throws ServiceException;

    /**
     * 获取指定 platIds 的所有业务 节点
     * @param platNodeIds
     * @return
     * @throws ServiceException
     */
    List<PlatNode> getPlatNodesByPlatIds(List<String> platNodeIds) throws ServiceException;

    /**
     * 初始化业务树
     * @param allNodes
     * @param platformDTOS
     * @param hostDTOS
     * @return
     */
    List<PlatNode> initPlatTree(
            List<PlatNode> allNodes,
            List<BriefPlatformDTO> platformDTOS,
            List<BriefHostDTO> hostDTOS);

    /**
     * 获取业务树 TreeVO
     * @param allNodes
     * @param platformDTOS
     * @param hostDTOS
     * @param templateDTOS
     * @return
     */
    List<TreeNodeVO> listPlatTreeVO(
            List<PlatNode> allNodes,
            List<BriefPlatformDTO> platformDTOS,
            List<BriefHostDTO> hostDTOS,
            List<BriefTemplateDTO> templateDTOS);


/*
公共
 */

    /**
     * 获取 hostNodes 下 pointNodes
     * @param nodeId hostNodeId
     * @param parentId 父节点
     * @param hostDTOS
     * @param templateDTOS
     * @return
     */
    List<TreeNodeVO> getTreeVOHostWithPoint(
            String nodeId,
            String parentId,
            List<BriefHostDTO> hostDTOS,
            List<BriefTemplateDTO> templateDTOS);

    /**
     * 获取 监控点-监控项 节点树
     * @param pointDTO
     * @return
     */
    List<TreeNodeVO> getTreeVOPointWithItem(BriefPointDTO pointDTO);

    /**
     * 获取 设备-监控点-监控项 节点树
     * @param pointDTOS
     * @param hostNodes 用于定位 设备树 设备父节点
     * @return
     */
    List<TreeNodeVO> getTreeVOHostWithItem(List<BriefHostDTO> hostDTOS ,
                                           List<BriefPointDTO> pointDTOS,
                                           List<HostNode> hostNodes,
                                           List<BriefTemplateDTO> templateDTOS);




}
