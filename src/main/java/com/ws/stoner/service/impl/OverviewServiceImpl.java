package com.ws.stoner.service.impl;


import com.ws.stoner.constant.OverviewTypeEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.dao.MongoGroupDAO;
import com.ws.stoner.dao.OverviewGroupRepository;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.overview.Group;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.overview.OverViewHostVO;
import com.ws.stoner.model.view.overview.OverviewEditGroupVO;
import com.ws.stoner.model.view.overview.OverviewListGroupVO;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.OverviewService;
import com.ws.stoner.service.TemplateService;
import com.ws.stoner.utils.StatusConverter;
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
    private HostService hostService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private MongoGroupDAO mongoGroupDAO;


    @Override
    public List<OverviewListGroupVO> listOverviewGroup() throws ServiceException {
        //step1:调用  listAllHost()，listProblemHost(triggerIds) ,listAllTemplate(),组装ProblemHostIds
        List<BriefHostDTO> allHosts ;
        List<BriefTemplateDTO> allTemplates;
        allHosts = hostService.listAllHost();
        allTemplates = templateService.listAllTemplate();
        //step:2 初始化 root节点
            //root节点为空
        Group root = overviewGroupRepository.findByName("root");
        if(root.getHostChildren().length == 0 && root.getGroupChildren().length == 0) {
            List<String> hostIds = new ArrayList<>();
            for(BriefHostDTO host : allHosts) {
                hostIds.add(host.getHostId());
            }
            String[] hostIdsString = hostIds.toArray(new String[hostIds.size()]);
            root.setHostChildren(hostIdsString);
            overviewGroupRepository.save(root);
        }else {
            //有新添加或删减的设备
            //取所有 group 节点 host_children 形成hostIds ，和allHosts 的hostIds比较是否相同
            List<Group> allGroups = overviewGroupRepository.findAll();
            List<String> allHostIds = new ArrayList<>();
            List<String> allHostIdsAPI = new ArrayList<>();
            for(Group g : allGroups) {
                List<String> hostIds = Arrays.asList(g.getHostChildren());
                allHostIds.addAll(hostIds);
            }
            for(BriefHostDTO host : allHosts) {
                allHostIdsAPI.add(host.getHostId());
            }
            //不相同
            if(allHostIds.size() != allHostIdsAPI.size()) {
                //删减 allgroups中含有 delHostIds 的hostId
                for(Group g : allGroups) {
                    List<String> hostIdsTemp = Arrays.asList(g.getHostChildren());
                    List<String> hostIds = new ArrayList<>(hostIdsTemp);
                    for(String hostId : hostIdsTemp) {
                        if(!allHostIdsAPI.contains(hostId)) {
                            hostIds.remove(hostId);
                        }
                    }
                    g.setHostChildren(hostIds.toArray(new String[0]));
                    //批量更新的方法还未找到，先一个一个的save
                    overviewGroupRepository.save(g);
                }
                //新增 提取出新增的hostIds 组成String[] 赋值给root的 hostChildren
                Group newRoot = overviewGroupRepository.findByName("root");
                List<String> addHostIds = new ArrayList<>();
                for(String hostId : allHostIdsAPI) {
                    if(!allHostIds.contains(hostId)) {
                        addHostIds.add(hostId);
                    }
                }
                if(addHostIds.size() != 0) {
                    List<String> rootHostIdsTemp = Arrays.asList(root.getHostChildren());
                    List<String> rootHostIds = new ArrayList<>(rootHostIdsTemp);
                    rootHostIds.addAll(addHostIds);
                    newRoot.setHostChildren(rootHostIds.toArray(new String[0]));
                    overviewGroupRepository.save(newRoot);
                }
            }
        }
        //step:3 创建list，调用list = getGroupTree()
        List<OverviewListGroupVO> overviewListGroupVOS = new ArrayList<>();
        formatGroupTree("root", overviewListGroupVOS,allHosts);
        //step:4 反转list，循环 list，
        Collections.reverse(overviewListGroupVOS);
        for(OverviewListGroupVO overviewListGroupVO : overviewListGroupVOS) {
            //if type == null 则为主机，,给所有主机赋值 type
            if(overviewListGroupVO.getType() == null) {
                for(BriefHostDTO host : allHosts) {
                    if(overviewListGroupVO.getcId().equals("h" + host.getHostId())) {
                        //type
                        if(host.getParentTemplates().size() != 0) {
                            String templateId = host.getParentTemplates().get(0).getTemplateId();
                            for(BriefTemplateDTO templateDTO : allTemplates) {
                                if(templateDTO.getTemplateId().equals(templateId)) {
                                    overviewListGroupVO.setType(templateDTO.getTemplateGroups().get(0).getName());
                                }
                            }
                        }
                    }
                }
            }
            //if type = "组" 给group赋值，state：根据cid  = pid，找所有子节点的state ，List<String>，根据所有子节点状态做处理赋值
            if(OverviewTypeEnum.GROUP.getName().equals(overviewListGroupVO.getType()) && !"root".equals(overviewListGroupVO.getName())) {
                List<String> childStates = new ArrayList<>();
                for(OverviewListGroupVO childMongo : overviewListGroupVOS) {
                    if(overviewListGroupVO.getcId().equals(childMongo.getpId())) {
                        childStates.add(childMongo.getState());
                    }
                }
                //state赋值
                if(childStates.contains(StatusEnum.HIGH.getName())) {
                    overviewListGroupVO.setState(StatusEnum.HIGH.getName());
                }else if(childStates.contains(StatusEnum.WARNING.getName())){
                    overviewListGroupVO.setState(StatusEnum.WARNING.getName());
                }else {
                    overviewListGroupVO.setState(StatusEnum.OK.getName());
                }
            }

        }
        //step:5反转还原
        Collections.reverse(overviewListGroupVOS);
        return overviewListGroupVOS;
    }

    @Override
    public boolean createOverviewGroup(String newGroupName, String supGroupId) throws ServiceException {
        boolean success = false;
        //step1:查最大cid，设置新的分组cid+1
        Group maxGroup = null;
        try {
            maxGroup = mongoGroupDAO.findMaxGroupCId();
        } catch (DAOException e) {
            logger.error("查询mongodb最大组错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
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
        Group supGroup = null;
        try {
            supGroup = mongoGroupDAO.findGroupByCId(pId);
        } catch (DAOException e) {
            logger.error("根据CID查询mongodb错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<String> supGroupListTemp = Arrays.asList(supGroup.getGroupChildren());
        List<String> supGroupList = new ArrayList<>(supGroupListTemp);
        supGroupList.add(newGroupName);
        supGroup.setGroupChildren((String[])supGroupList.toArray(new String[0]));
        overviewGroupRepository.save(supGroup);
        //step3:返回值：
        success = true;
        return success;
    }

    /**
     * 删除指定分组，并将其下所有子节点移动到上一节点中
     * @param delGroupVOId
     * @return boolean true表示删除成功，false表示删除失败
     */
    @Override
    public boolean deleteOverviewGroup(String delGroupVOId) throws ServiceException {
        boolean success = false;
        String delGroupId = delGroupVOId.substring(1);
        Group delGroup = null;
        Group supGroup = null;
        try {
            //step1:根据删除组cid取要删除组的group_children,host_children；
            delGroup = mongoGroupDAO.findGroupByCId(delGroupId);
            //step2:根据删除组cid取该组的上一级节点
            supGroup = mongoGroupDAO.findGroupByCId(delGroup.getpId());
        } catch (DAOException e) {
            logger.error("根据CID查询mongodb错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        //step3:循环delGroup 的 group_children添加到 supGroup 的group_children中,同时更改子成员的pid为supGroup 的 cid
        List<String> delGroupChildren = Arrays.asList(delGroup.getGroupChildren());
        List<String> supGroupChildrenTemp = Arrays.asList(supGroup.getGroupChildren());
        List<String> supGroupChildren = new ArrayList<>(supGroupChildrenTemp);
        //剔除掉 在sup的group_children 中要删除的group的name
        supGroupChildren.remove(delGroup.getName());
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
        success = true;
        return success;
    }

    /**
     * 移动组 操作 move group
     * @param groupVOId
     * @param fromGroupVOId
     * @param toGroupVOId
     * @return boolean true表示删除成功，false表示删除失败
     * @throws ServiceException
     */
    @Override
    public boolean moveOverviewGroup(String groupVOId, String fromGroupVOId, String toGroupVOId) throws ServiceException {
        boolean success = false;
        String groupId = groupVOId.substring(1);
        String fromGroupId = fromGroupVOId.substring(1);
        String toGroupId = toGroupVOId.substring(1);
        //step1:Group组的pid更新为toGroupId
        Group targetGroup = null;
        try {
            targetGroup = mongoGroupDAO.findGroupByCId(groupId);
        } catch (DAOException e) {
            logger.error("查询mongodb错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        targetGroup.setpId(toGroupId);
        if("0".equals(toGroupId)) {
            targetGroup.setFlag("0");
        }else {
            targetGroup.setFlag("1");
        }
        overviewGroupRepository.save(targetGroup);
        //step2:from_group组的group_children去掉group的name；
        Group fromGroup = null;
        try {
            fromGroup = mongoGroupDAO.findGroupByCId(fromGroupId);
        } catch (DAOException e) {
            logger.error("根据Cid查询mongodb错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<String> fromGroupChildrenTemp = Arrays.asList(fromGroup.getGroupChildren());
        List<String> fromGroupChildren = new ArrayList<>(fromGroupChildrenTemp);
        fromGroupChildren.remove(targetGroup.getName());
        fromGroup.setGroupChildren(fromGroupChildren.toArray(new String[0]));
        overviewGroupRepository.save(fromGroup);
        //step3:to_group组的group_children添加group的name
        Group toGroup = null;
        try {
            toGroup = mongoGroupDAO.findGroupByCId(toGroupId);
        } catch (DAOException e) {
            logger.error("根据CId查询mongodb错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<String> toGroupChildrenTemp = Arrays.asList(toGroup.getGroupChildren());
        List<String> toGroupChildren = new ArrayList<>(toGroupChildrenTemp);
        toGroupChildren.add(targetGroup.getName());
        toGroup.setGroupChildren(toGroupChildren.toArray(new String[0]));
        overviewGroupRepository.save(toGroup);
        //组装返回数据对象
        success = true;
        return success;

    }

    /**
     * 移动设备 操作 move host
     * @param hostVOId
     * @param fromGroupVOId
     * @param toGroupVOId
     * @return boolean true表示移动成功，false表示移动失败
     * @throws ServiceException
     */
    @Override
    public boolean moveOverviewHost(String hostVOId, String fromGroupVOId, String toGroupVOId) throws ServiceException {
        boolean success = false;
        String hostId = hostVOId.substring(1);
        String fromGroupId = fromGroupVOId.substring(1);
        String toGroupId = toGroupVOId.substring(1);
        //step1:from_group组的host_children去掉host原设备的id；
        Group fromGroup = null;
        try {
            fromGroup = mongoGroupDAO.findGroupByCId(fromGroupId);
        } catch (DAOException e) {
            logger.error("Cid查询mongodb错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<String> fromHostChildrenTemp = Arrays.asList(fromGroup.getHostChildren());
        List<String> fromHostChildren = new ArrayList<>(fromHostChildrenTemp);
        fromHostChildren.remove(hostId);
        fromGroup.setHostChildren((String[])fromHostChildren.toArray(new String[0]));
        overviewGroupRepository.save(fromGroup);
        //step2:to_group组的host_children添加host原设备的id
        Group toGroup = null;
        try {
            toGroup= mongoGroupDAO.findGroupByCId(toGroupId);
        } catch (DAOException e) {
            logger.error("Cid查询 toGroup 的mongodb错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<String> toHostChildrenTemp = Arrays.asList(toGroup.getHostChildren());
        List<String> toHostChildren = new ArrayList<>(toHostChildrenTemp);
        toHostChildren.add(hostId);
        toGroup.setHostChildren((String[])toHostChildren.toArray(new String[0]));
        overviewGroupRepository.save(toGroup);
        //step3:组装返回数据
        success = true;
        return success;
    }

    /**
     * 编辑修改分组名 service
     * @param oldGroupName
     * @param newGroupName
     * @param supGroupVOId
     * @return
     * @throws ServiceException
     */
    @Override
    public OverviewEditGroupVO editOverviewGroup(String oldGroupName, String newGroupName, String supGroupVOId) throws ServiceException {
        String supGroupId = supGroupVOId.substring(1);
        //oldGroupName 查 group,修改name
        Group oldGroup = overviewGroupRepository.findByName(oldGroupName);
        oldGroup.setName(newGroupName);
        overviewGroupRepository.save(oldGroup);
        // supGroupVOId 查 sup_group，修改group_children
        Group supGroup = null;
        try {
            supGroup = mongoGroupDAO.findGroupByCId(supGroupId);
        } catch (DAOException e) {
            logger.error("Cid查询mongodb错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<String> groupChildrenTemp = Arrays.asList(supGroup.getGroupChildren());
        List<String> groupChildren = new ArrayList<>(groupChildrenTemp);
        groupChildren.remove(oldGroupName);
        groupChildren.add(newGroupName);
        supGroup.setGroupChildren(groupChildren.toArray(new String[0]));
        overviewGroupRepository.save(supGroup);
        return new OverviewEditGroupVO(oldGroupName,newGroupName,supGroupVOId);
    }

    /**
     * 在移动分组的时候需要先获取分组树供用户选择
     * @param groupName
     * @return
     * @throws ServiceException
     */
    @Override
    public List<OverviewListGroupVO> getMoveGroupTree(String groupName) throws ServiceException {
        //新建
        List<OverviewListGroupVO> rootTree = new ArrayList<>();
        List<OverviewListGroupVO> moveGroupTree = new ArrayList<>();
        //调用
        formatGroupTree("root",rootTree,new ArrayList<BriefHostDTO>());
        if(!"".equals(groupName) && groupName != null) {
            formatGroupTree(groupName,moveGroupTree,new ArrayList<BriefHostDTO>());
            //状态赋值，state = 0,表示可选择，1表示不可选择
            for(OverviewListGroupVO group : rootTree) {
                if(moveGroupTree.contains(group)) {
                    group.setEnable(false);
                }else {
                    group.setEnable(true);
                }
            }
        }else {
            //状态赋值，state = 0,表示可选择，1表示不可选择
            for(OverviewListGroupVO group : rootTree) {
                group.setEnable(true);
            }
        }
        return rootTree;
    }

    /**
     * 获取主机选择树
     * @return
     * @throws ServiceException
     */
    @Override
    public List<OverViewHostVO> getSelectHostVOS() throws ServiceException {
        List<Group> allGroups = overviewGroupRepository.findAll();
        List<BriefHostDTO> allHosts = hostService.listAllHost();
        List<OverViewHostVO> hostVOS = new ArrayList<>();
        for(Group group : allGroups) {
            List<String> hostIds = Arrays.asList(group.getHostChildren());
            int okNum = 0;
            int warningNum  = 0;
            int highNum = 0;
            for(BriefHostDTO hostDTO : allHosts) {
                if(hostIds.contains(hostDTO.getHostId())) {
                    String hostState = StatusConverter.getTextStatusTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState());
                    OverViewHostVO hostVO = new OverViewHostVO(
                            hostDTO.getHostId(),
                            group.getcId(),
                            hostDTO.getName(),
                            OverviewTypeEnum.HOST.text,
                            hostState
                    );
                    hostVOS.add(hostVO);
                   if(StatusEnum.HIGH.text.equals(hostState)) {
                       highNum++;
                   }else if(StatusEnum.WARNING.text.equals(hostState)) {
                       warningNum++;
                   }else {
                       okNum++;
                   }
                }
            }
            String groupState ;
            String parent ;
            if(highNum > 0) {
                groupState = StatusEnum.HIGH.text;
            }else if(warningNum > 0) {
                groupState = StatusEnum.WARNING.text;
            }else {
                groupState = StatusEnum.OK.text;
            }
            if("root".equals(group.getName())) {
                parent = "#";
            }else {
                parent = group.getpId();
            }
            OverViewHostVO groupVO = new OverViewHostVO(
                    group.getcId(),
                    parent,
                    group.getName(),
                    OverviewTypeEnum.GROUP.text,
                    groupState
            );
            hostVOS.add(groupVO);
        }
        return hostVOS;
    }

    /**
     * 根据指定顺序 格式化树
     * @param name 从哪个节点开始格式化
     * @param overviewListGroupVOS  被格式化组对象
     * @param allHosts 用于匹配设备id获取 name 和state，若为空则表示不显示设备格式化
     */
    private void formatGroupTree(String name, List<OverviewListGroupVO> overviewListGroupVOS, List<BriefHostDTO> allHosts)  {
        //step1:新建List<OverviewListGroupVO> list,OverviewListGroupVO mongoGroup,根据name查出mongoGroupDO
        Group group = overviewGroupRepository.findByName(name);
        //step2:给vo赋值，cid，pid，name,groupChildren，添加vo到list中
        OverviewListGroupVO mongoGroup = new OverviewListGroupVO();
        mongoGroup.setcId("g" + group.getcId());
        if("root".equals(group.getName())) {
            mongoGroup.setpId(null);
        }else {
            mongoGroup.setpId("g" + group.getpId());
        }
        mongoGroup.setName(group.getName());
        mongoGroup.setType(OverviewTypeEnum.GROUP.getName());
        mongoGroup.setGroupChildren(group.getGroupChildren());
        overviewListGroupVOS.add(mongoGroup);
        //step3:判断DO的group_children.length != 0
        if(group.getGroupChildren().length != 0) {
            //是：循环 group_children ，取name，递归调用 list = getGroupTree(name,list)
            for(String groupName : group.getGroupChildren()) {
                formatGroupTree(groupName, overviewListGroupVOS,allHosts);
            }
        }
        //step4:取DO的 host_children,循环 add host 到 list中 return list
        for(String hostId : group.getHostChildren()) {
            for(BriefHostDTO host : allHosts) {
                if(hostId.equals(host.getHostId())) {
                    OverviewListGroupVO mongoHost = new OverviewListGroupVO();
                    mongoHost.setpId("g" + group.getcId());
                    mongoHost.setcId("h" + hostId);
                    mongoHost.setName(host.getName());
                    if(StatusEnum.HIGH.code == host.getCustomState() || StatusEnum.WARNING.code == host.getCustomAvailableState()) {
                        mongoHost.setState(StatusEnum.HIGH.getName());
                    }else if(StatusEnum.WARNING.code ==host.getCustomState() && StatusEnum.OK.code ==host.getCustomAvailableState()){
                        mongoHost.setState(StatusEnum.WARNING.getName());
                    }else {
                        mongoHost.setState(StatusEnum.OK.getName());
                    }
                    overviewListGroupVOS.add(mongoHost);
                    //循环allHosts,取监控点
                    List<BriefPointDTO> points = host.getPoints();
                    for(BriefPointDTO point : points) {
                        OverviewListGroupVO mongoPoint = new OverviewListGroupVO();
                        mongoPoint.setcId("p" + point.getPointId());
                        mongoPoint.setpId("h" + hostId);
                        mongoPoint.setName(point.getName());
                        mongoPoint.setType(OverviewTypeEnum.POINT.getName());
                        if(StatusEnum.WARNING.code == point.getCustomState()) {
                            mongoPoint.setState(StatusEnum.WARNING.getName());
                        }else if(StatusEnum.HIGH.code ==point.getCustomState()) {
                            mongoPoint.setState(StatusEnum.HIGH.getName());
                        }else {
                            mongoPoint.setState(StatusEnum.OK.getName());
                        }
                        overviewListGroupVOS.add(mongoPoint);
                    }
                }

            }
        }
    }


}
