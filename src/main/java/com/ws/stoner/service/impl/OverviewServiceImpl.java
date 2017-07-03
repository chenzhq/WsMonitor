package com.ws.stoner.service.impl;


import com.ws.stoner.constant.OverviewTypeEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.dao.OverviewDAO;
import com.ws.stoner.dao.OverviewGroupRepository;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.manager.PointManager;
import com.ws.stoner.manager.TemplateManager;
import com.ws.stoner.manager.TriggerManager;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.DO.mongo.Group;
import com.ws.stoner.model.view.OverviewVO;
import com.ws.stoner.service.OverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by pc on 2017/6/28.
 */
@Service
public class OverviewServiceImpl implements OverviewService {

    @Autowired
    private OverviewGroupRepository overviewGroupRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private HostManager hostManager;

    @Autowired
    private PointManager pointManager;

    @Autowired
    private TriggerManager triggerManager;

    @Autowired
    private TemplateManager templateManager;

    @Autowired
    private OverviewDAO overviewDAO;


    @Override
    public List<OverviewVO> listOverviewGroup() throws ServiceException {
        //step1:调用  listAllHost()，listProblemHost(triggerIds) ,listAllTemplate(),组装ProblemHostIds
        List<BriefHostDTO> allHosts ;
        List<String> triggerIds ;
        List<BriefHostDTO>  problemHosts;
        List<BriefTemplateDTO> allTemplates;
        List<BriefPointDTO> problemPoints;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
            allHosts = hostManager.listAllHost();
            problemHosts = hostManager.listProblemHost(triggerIds);
            allTemplates = templateManager.listAllTemplate();
            problemPoints = pointManager.listProblemPoint(triggerIds);
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //组装ids
        List<String> problemHostIds = new ArrayList<>();
        List<String> problemPointIds = new ArrayList<>();
        for(BriefHostDTO host : problemHosts) {
            problemHostIds.add(host.getHostId());
        }
        for(BriefPointDTO point : problemPoints) {
            problemPointIds.add(point.getPointId());
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
        List<OverviewVO> overviewVOS = new ArrayList<>();
        overviewVOS = getGroupTree("root", overviewVOS,allHosts);
        //step:4 反转list，循环 list，
        Collections.reverse(overviewVOS);
        for(OverviewVO overviewVO : overviewVOS) {
            //if type == "监控点"，则为point,给 point 赋值：state
            if(OverviewTypeEnum.POINT.getName().equals(overviewVO.getType())) {
                if(problemHostIds.contains(overviewVO.getcId())) {
                    overviewVO.setState(StatusEnum.PROBLEM.getName());
                }else {
                    overviewVO.setState(StatusEnum.OK.getName());
                }
            }
            //if type == null 则为主机，,给所有主机赋值 type，state
            if(overviewVO.getType() == null) {
                for(BriefHostDTO host : allHosts) {
                    if(overviewVO.getcId().equals(host.getHostId())) {
                        //type
                        if(host.getParentTemplates().size() != 0) {
                            String templateId = host.getParentTemplates().get(0).getTemplateId();
                            for(BriefTemplateDTO templateDTO : allTemplates) {
                                if(templateDTO.getTemplateId().equals(templateId)) {
                                    overviewVO.setType(templateDTO.getTemplateGroups().get(0).getName());
                                }
                            }
                        }
                        //state
                        if(problemHostIds.contains(overviewVO.getcId())) {
                            overviewVO.setState(StatusEnum.PROBLEM.getName());
                        }else {
                            overviewVO.setState(StatusEnum.OK.getName());
                        }
                    }
                }
            }
            //if type = "组" 给group赋值，state：根据cid  = pid，找所有子节点的state ，List<String>，根据所有子节点状态做处理赋值
            if(OverviewTypeEnum.GROUP.getName().equals(overviewVO.getType()) && !"root".equals(overviewVO.getName())) {
                List<String> childStates = new ArrayList<>();
                for(OverviewVO childMongo : overviewVOS) {
                    if(overviewVO.getcId().equals(childMongo.getpId())) {
                        childStates.add(childMongo.getState());
                    }
                }
                //state赋值
                if(childStates.contains(StatusEnum.PROBLEM.getName())) {
                    overviewVO.setState(StatusEnum.PROBLEM.getName());
                }else {
                    overviewVO.setState(StatusEnum.OK.getName());
                }
            }

        }
        //step:5反转还原
        Collections.reverse(overviewVOS);
        return overviewVOS;
    }

    @Override
    public OverviewCreateGroupDTO createOverviewGroup(String newGroupName, String supGroupId) throws ServiceException {
        //step1:查最大cid，设置新的分组cid+1
        Group maxGroup;
        try {
            maxGroup = overviewDAO.findMaxGroupCId();
        } catch (DAOException e) {
            e.printStackTrace();
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
     * @param delGroupId
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
     * @param groupId
     * @param fromGroupId
     * @param toGroupId
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

    private List<OverviewVO> getGroupTree(String name, List<OverviewVO> overviewVOS, List<BriefHostDTO> allHosts)  {
        //step1:新建List<OverviewVO> list,OverviewVO mongoGroup,根据name查出mongoGroupDO
        Group group = overviewGroupRepository.findByName(name);
        //step2:给vo赋值，cid，pid，name，添加vo到list中
        OverviewVO mongoGroup = new OverviewVO();
        mongoGroup.setcId("g" + group.getcId());
        mongoGroup.setpId(group.getpId());
        mongoGroup.setName(group.getName());
        mongoGroup.setType(OverviewTypeEnum.GROUP.getName());
        overviewVOS.add(mongoGroup);
        //step3:判断DO的group_children.length != 0
        if(group.getGroupChildren().length != 0) {
            //是：循环 group_children ，取name，递归调用 list = getGroupTree(name,list)
            for(String groupName : group.getGroupChildren()) {
                overviewVOS = getGroupTree(groupName, overviewVOS,allHosts);
            }
        }
        //step4:取DO的 host_children,循环 add host 到 list中 return list
        for(String hostId : group.getHostChildren()) {
            for(BriefHostDTO host : allHosts) {
                if(hostId.equals(host.getHostId())) {
                    OverviewVO mongoHost = new OverviewVO();
                    mongoHost.setpId(group.getcId());
                    mongoHost.setcId("h" + hostId);
                    mongoHost.setName(host.getName());
                    overviewVOS.add(mongoHost);
                    //循环allHosts,取监控点
                    List<BriefPointDTO> points = host.getPoints();
                    for(BriefPointDTO point : points) {
                        OverviewVO mongoPoint = new OverviewVO();
                        mongoPoint.setcId("p" + point.getPointId());
                        mongoPoint.setpId(hostId);
                        mongoPoint.setName(point.getName());
                        mongoPoint.setType(OverviewTypeEnum.POINT.getName());
                        overviewVOS.add(mongoPoint);
                    }
                }

            }
        }
        return overviewVOS;
    }


}
