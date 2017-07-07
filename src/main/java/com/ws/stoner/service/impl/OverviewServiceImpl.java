package com.ws.stoner.service.impl;


import com.ws.stoner.constant.OverviewTypeEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.dao.OverviewDAO;
import com.ws.stoner.dao.OverviewGroupRepository;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.Group;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.service.OverviewService;
import com.ws.stoner.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by pc on 2017/6/28.
 */
@Service
@Transactional
public class OverviewServiceImpl implements OverviewService {

    private static final Logger logger = LoggerFactory.getLogger(OverviewServiceImpl.class);

    @Autowired
    private OverviewGroupRepository overviewGroupRepository;

    @Autowired
    private HostServiceImpl hostServiceImpl;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private OverviewDAO overviewDAO;


    @Override
    public List<OverviewListGroupDTO> listOverviewGroup() throws ServiceException {
        //step1:调用  listAllHost()，listProblemHost(triggerIds) ,listAllTemplate(),组装ProblemHostIds
        List<BriefHostDTO> allHosts ;
        List<BriefTemplateDTO> allTemplates;
        try {
            allHosts = hostServiceImpl.listAllHost();
            allTemplates = templateService.listAllTemplate();
        } catch (ServiceException e) {
            e.printStackTrace();
            logger.error("查询API错误！{}", e.getMessage());
            return null;
        }
        //step:2初始化 root节点
        Group root = overviewGroupRepository.findByName("root");
        if(root.getHostChildren().length == 0 && root.getGroupChildren().length == 0) {
            List<String> hostIds = new ArrayList<>();
            for(BriefHostDTO host : allHosts) {
                hostIds.add(host.getHostId());
            }
            String[] hostIdsString = hostIds.toArray(new String[hostIds.size()]);
            root.setHostChildren(hostIdsString);
            overviewGroupRepository.save(root);
        }
        //step:3 创建list，调用list = getGroupTree()
        List<OverviewListGroupDTO> overviewListGroupDTOS = new ArrayList<>();
        overviewListGroupDTOS = getGroupTree("root", overviewListGroupDTOS,allHosts);
        //step:4 反转list，循环 list，
        Collections.reverse(overviewListGroupDTOS);
        for(OverviewListGroupDTO overviewListGroupDTO : overviewListGroupDTOS) {
            //if type == null 则为主机，,给所有主机赋值 type
            if(overviewListGroupDTO.getType() == null) {
                for(BriefHostDTO host : allHosts) {
                    if(overviewListGroupDTO.getcId().equals("h" + host.getHostId())) {
                        //type
                        if(host.getParentTemplates().size() != 0) {
                            String templateId = host.getParentTemplates().get(0).getTemplateId();
                            for(BriefTemplateDTO templateDTO : allTemplates) {
                                if(templateDTO.getTemplateId().equals(templateId)) {
                                    overviewListGroupDTO.setType(templateDTO.getTemplateGroups().get(0).getName());
                                }
                            }
                        }
                    }
                }
            }
            //if type = "组" 给group赋值，state：根据cid  = pid，找所有子节点的state ，List<String>，根据所有子节点状态做处理赋值
            if(OverviewTypeEnum.GROUP.getName().equals(overviewListGroupDTO.getType()) && !"root".equals(overviewListGroupDTO.getName())) {
                List<String> childStates = new ArrayList<>();
                for(OverviewListGroupDTO childMongo : overviewListGroupDTOS) {
                    if(overviewListGroupDTO.getcId().equals(childMongo.getpId())) {
                        childStates.add(childMongo.getState());
                    }
                }
                //state赋值
                if(childStates.contains(StatusEnum.HIGHT.getName())) {
                    overviewListGroupDTO.setState(StatusEnum.HIGHT.getName());
                }else if(childStates.contains(StatusEnum.WARNING.getName())){
                    overviewListGroupDTO.setState(StatusEnum.WARNING.getName());
                }else {
                    overviewListGroupDTO.setState(StatusEnum.OK.getName());
                }
            }

        }
        //step:5反转还原
        Collections.reverse(overviewListGroupDTOS);
        return overviewListGroupDTOS;
    }

    @Override
    public OverviewCreateGroupDTO createOverviewGroup(String newGroupName, String supGroupId) throws ServiceException {
        //step1:查最大cid，设置新的分组cid+1
        Group maxGroup;
        try {
            maxGroup = overviewDAO.findMaxGroupCId();
        } catch (DAOException e) {
            e.printStackTrace();
            logger.error("查询mongodb最大组错误！{}", e.getMessage());
            return null;
        }
        String cId = String.valueOf(Integer.parseInt(maxGroup.getcId()) + 1);
        //step2:设置flag，name，pid，group_children,host_children,save()到mongodb中
        String pId = supGroupId.substring(1);
        Group group = new Group();
        group.setcId(cId);
        group.setpId(pId);
        group.setName(newGroupName);
        if("0".equals(pId)) {
            group.setFlag("0");
        }else {
            group.setFlag("1");
        }
        group.setGroupChildren(new String[]{});
        group.setHostChildren(new String[]{});
        overviewGroupRepository.save(group);
        //step3:更新父级组的group_children，新增name进去。
        Group supGroup ;
        try {
            supGroup = overviewDAO.findGroupByCId(pId);
        } catch (DAOException e) {
            e.printStackTrace();
            logger.error("根据CID查询mongodb错误！{}", e.getMessage());
            return null;
        }
        List<String> supGroupListTemp = Arrays.asList(supGroup.getGroupChildren());
        List<String> supGroupList = new ArrayList<>(supGroupListTemp);
        supGroupList.add(newGroupName);
        supGroup.setGroupChildren((String[])supGroupList.toArray(new String[0]));
        overviewGroupRepository.save(supGroup);
        //step3:返回值：newGroupId，newGroupName,supGroupId
        OverviewCreateGroupDTO ocg = new OverviewCreateGroupDTO();
        ocg.setNewGroupId("g" + cId);
        ocg.setNewGroupName(newGroupName);
        ocg.setSupGroupId(supGroupId);
        return ocg;
    }

    /**
     * 删除指定分组，并将其下所有子节点移动到上一节点中
     * @param delGroupVOId
     * @return
     * @throws ServiceException
     */
    @Override
    public OverviewDelGroupDTO deleteOverviewGroup(String delGroupVOId) throws ServiceException {
        String delGroupId = delGroupVOId.substring(1);
        Group delGroup;
        Group supGroup;
        try {
            //step1:根据删除组cid取要删除组的group_children,host_children；
            delGroup = overviewDAO.findGroupByCId(delGroupId);
            //step2:根据删除组cid取该组的上一级节点
            supGroup = overviewDAO.findGroupByCId(delGroup.getpId());
        } catch (DAOException e) {
            e.printStackTrace();
            logger.error("根据CID查询mongodb错误！{}", e.getMessage());
            return null;
        }
        //step3:循环delGroup 的 group_children添加到 supGroup 的group_children中,同时更改子成员的pid为supGroup 的 cid
        List<String> delGroupChildren = Arrays.asList(delGroup.getGroupChildren());
        List<String> supGroupChildrenTemp = Arrays.asList(supGroup.getGroupChildren());
        List<String> supGroupChildren = new ArrayList<>(supGroupChildrenTemp);
        for(String groupName : delGroupChildren) {
            supGroupChildren.add(groupName);
            Group childGroup = overviewGroupRepository.findByName(groupName);
            childGroup.setpId(supGroup.getcId());
            //flag赋值
            if("0".equals(childGroup.getpId())) {
                childGroup.setFlag("0");
            }else {
                childGroup.setFlag("1");
            }
            overviewGroupRepository.save(childGroup);
        }
        //step4:循环delGroup 的 host_children添加到 supGroup 的host_children中
        List<String> delHostChildren = Arrays.asList(delGroup.getHostChildren());
        List<String> supHostChildrenTemp = Arrays.asList(supGroup.getHostChildren());
        List<String> supHostChildren = new ArrayList<>(supHostChildrenTemp);
        for(String hostId : delHostChildren) {
            supHostChildren.add(hostId);
        }
        supGroup.setGroupChildren((String[])supGroupChildren.toArray(new String[0]));
        supGroup.setHostChildren((String[])supHostChildren.toArray(new String[0]));
        overviewGroupRepository.save(supGroup);
        //step5:删除指定组
        overviewGroupRepository.delete(delGroup);
        OverviewDelGroupDTO odg = new OverviewDelGroupDTO();
        odg.setDelGroupId(delGroupVOId);
        odg.setToGroupId("g" + supGroup.getcId());
        return odg;
    }

    /**
     * 移动组 操作 move group
     * @param groupVOId
     * @param fromGroupVOId
     * @param toGroupVOId
     * @return
     * @throws ServiceException
     */
    @Override
    public OverviewMoveGroupDTO moveOverviewGroup(String groupVOId, String fromGroupVOId, String toGroupVOId) throws ServiceException {
        String groupId = groupVOId.substring(1);
        String fromGroupId = fromGroupVOId.substring(1);
        String toGroupId = toGroupVOId.substring(1);
        //step1:Group组的pid更新为toGroupId
        Group targetGroup;
        try {
            targetGroup = overviewDAO.findGroupByCId(groupId);
        } catch (DAOException e) {
            e.printStackTrace();
            logger.error("查询mongodb错误！{}", e.getMessage());
            return null;
        }
        targetGroup.setpId(toGroupId);
        if("0".equals(toGroupId)) {
            targetGroup.setFlag("0");
        }else {
            targetGroup.setFlag("1");
        }
        overviewGroupRepository.save(targetGroup);
        //step2:from_group组的group_children去掉group的name；
        Group fromGroup;
        try {
            fromGroup = overviewDAO.findGroupByCId(fromGroupId);
        } catch (DAOException e) {
            e.printStackTrace();
            logger.error("根据Cid查询mongodb错误！{}", e.getMessage());
            return null;
        }
        List<String> fromGroupChildrenTemp = Arrays.asList(fromGroup.getGroupChildren());
        List<String> fromGroupChildren = new ArrayList<>(fromGroupChildrenTemp);
        fromGroupChildren.remove(targetGroup.getName());
        fromGroup.setGroupChildren((String[])fromGroupChildren.toArray(new String[0]));
        overviewGroupRepository.save(fromGroup);
        //step3:to_group组的group_children添加group的name
        Group toGroup;
        try {
            toGroup = overviewDAO.findGroupByCId(toGroupId);
        } catch (DAOException e) {
            e.printStackTrace();
            logger.error("根据CId查询mongodb错误！{}", e.getMessage());
            return null;
        }
        List<String> toGroupChildrenTemp = Arrays.asList(toGroup.getGroupChildren());
        List<String> toGroupChildren = new ArrayList<>(toGroupChildrenTemp);
        toGroupChildren.add(targetGroup.getName());
        toGroup.setGroupChildren((String[])toGroupChildren.toArray(new String[0]));
        overviewGroupRepository.save(toGroup);
        //组装返回数据对象
        OverviewMoveGroupDTO omg = new OverviewMoveGroupDTO();
        omg.setGroupId(groupId);
        omg.setFromGroupId(fromGroupId);
        omg.setToGroupId(toGroupId);
        return omg;

    }

    /**
     * 移动设备 操作 move host
     * @param hostVOId
     * @param fromGroupVOId
     * @param toGroupVOId
     * @return
     * @throws ServiceException
     */
    @Override
    public OverviewMoveHostDTO moveOverviewHost(String hostVOId, String fromGroupVOId, String toGroupVOId) throws ServiceException {
        String hostId = hostVOId.substring(1);
        String fromGroupId = fromGroupVOId.substring(1);
        String toGroupId = toGroupVOId.substring(1);
        //step1:from_group组的host_children去掉host原设备的id；
        Group fromGroup ;
        try {
            fromGroup = overviewDAO.findGroupByCId(fromGroupId);
        } catch (DAOException e) {
            e.printStackTrace();
            logger.error("Cid查询mongodb错误！{}", e.getMessage());
            return null;
        }
        List<String> fromHostChildrenTemp = Arrays.asList(fromGroup.getHostChildren());
        List<String> fromHostChildren = new ArrayList<>(fromHostChildrenTemp);
        fromHostChildren.remove(hostId);
        fromGroup.setHostChildren((String[])fromHostChildren.toArray(new String[0]));
        overviewGroupRepository.save(fromGroup);
        //step2:to_group组的host_children添加host原设备的id
        Group toGroup ;
        try {
            toGroup= overviewDAO.findGroupByCId(toGroupId);
        } catch (DAOException e) {
            e.printStackTrace();
            logger.error("Cid查询 toGroup 的mongodb错误！{}", e.getMessage());
            return null;
        }
        List<String> toHostChildrenTemp = Arrays.asList(toGroup.getHostChildren());
        List<String> toHostChildren = new ArrayList<>(toHostChildrenTemp);
        toHostChildren.add(hostId);
        toGroup.setHostChildren((String[])toHostChildren.toArray(new String[0]));
        overviewGroupRepository.save(toGroup);
        //step3:组装返回数据
        OverviewMoveHostDTO omh = new OverviewMoveHostDTO();
        omh.setHostId(hostVOId);
        omh.setFromGroupId(fromGroupVOId);
        omh.setToGroupId(toGroupVOId);
        return omh;
    }

    private List<OverviewListGroupDTO> getGroupTree(String name, List<OverviewListGroupDTO> overviewListGroupDTOS, List<BriefHostDTO> allHosts)  {
        //step1:新建List<OverviewListGroupDTO> list,OverviewListGroupDTO mongoGroup,根据name查出mongoGroupDO
        Group group = overviewGroupRepository.findByName(name);
        //step2:给vo赋值，cid，pid，name，添加vo到list中
        OverviewListGroupDTO mongoGroup = new OverviewListGroupDTO();
        mongoGroup.setcId("g" + group.getcId());
        mongoGroup.setpId(group.getpId());
        mongoGroup.setName(group.getName());
        mongoGroup.setType(OverviewTypeEnum.GROUP.getName());
        overviewListGroupDTOS.add(mongoGroup);
        //step3:判断DO的group_children.length != 0
        if(group.getGroupChildren().length != 0) {
            //是：循环 group_children ，取name，递归调用 list = getGroupTree(name,list)
            for(String groupName : group.getGroupChildren()) {
                overviewListGroupDTOS = getGroupTree(groupName, overviewListGroupDTOS,allHosts);
            }
        }
        //step4:取DO的 host_children,循环 add host 到 list中 return list
        for(String hostId : group.getHostChildren()) {
            for(BriefHostDTO host : allHosts) {
                if(hostId.equals(host.getHostId())) {
                    OverviewListGroupDTO mongoHost = new OverviewListGroupDTO();
                    mongoHost.setpId(group.getcId());
                    mongoHost.setcId("h" + hostId);
                    mongoHost.setName(host.getName());
                    if("2".equals(host.getCustomState()) || "1".equals(host.getCustomAvailableState())) {
                        mongoHost.setState(StatusEnum.HIGHT.getName());
                    }else if("1".equals(host.getCustomState()) && "0".equals(host.getCustomAvailableState())){
                        mongoHost.setState(StatusEnum.WARNING.getName());
                    }else {
                        mongoHost.setState(StatusEnum.OK.getName());
                    }
                    overviewListGroupDTOS.add(mongoHost);
                    //循环allHosts,取监控点
                    List<BriefPointDTO> points = host.getPoints();
                    for(BriefPointDTO point : points) {
                        OverviewListGroupDTO mongoPoint = new OverviewListGroupDTO();
                        mongoPoint.setcId("p" + point.getPointId());
                        mongoPoint.setpId(hostId);
                        mongoPoint.setName(point.getName());
                        mongoPoint.setType(OverviewTypeEnum.POINT.getName());
                        if("1".equals(point.getCustomState())) {
                            mongoPoint.setState(StatusEnum.WARNING.getName());
                        }else if("2".equals(point.getCustomState())) {
                            mongoPoint.setState(StatusEnum.HIGHT.getName());
                        }else {
                            mongoPoint.setState(StatusEnum.OK.getName());
                        }
                        overviewListGroupDTOS.add(mongoPoint);
                    }
                }

            }
        }
        return overviewListGroupDTOS;
    }


}
