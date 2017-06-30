package com.ws.stoner.service.impl;


import com.ws.stoner.constant.MongoTypeEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.dao.MongoGroupRepository;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.manager.PointManager;
import com.ws.stoner.manager.TemplateManager;
import com.ws.stoner.manager.TriggerManager;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.mongoDO.MongoGroupDO;
import com.ws.stoner.model.view.MongoGroupVO;
import com.ws.stoner.service.MongoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pc on 2017/6/28.
 */
@Service
public class MongoGroupServiceImpl implements MongoGroupService {

    @Autowired
    private MongoGroupRepository mongoGroupRepository;

    @Autowired
    private HostManager hostManager;

    @Autowired
    private PointManager pointManager;

    @Autowired
    private TriggerManager triggerManager;

    @Autowired
    private TemplateManager templateManager;

    @Override
    public List<MongoGroupVO> listMongoGroup() throws ServiceException {
        //step1:取所有 mongo_group List<MongoGroupDO>，创建List<MongoGroupVO>
        List<MongoGroupDO> mongoGroupDOS = mongoGroupRepository.findAll();
        List<MongoGroupVO> mongoGroupVOS = new ArrayList<>();
        //step2:调用  listAllHost()，listProblemHost(triggerIds) ,listAllTemplate(),listProblemPoint(triggerIds),组装ProblemHostIds，problemPointIds
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
        //step3:循环List<MongoGroupDO>,创建MongoGroupVO，赋值cid,pid,type，name
        for(MongoGroupDO mongoGroupDO : mongoGroupDOS) {
            MongoGroupVO mongoGroup = new MongoGroupVO();
            //cid,pid,type,name
            mongoGroup.setcId(mongoGroupDO.getId());
            mongoGroup.setpId(mongoGroupDO.getpId());
            mongoGroup.setName(mongoGroupDO.getName());
            mongoGroup.setType(MongoTypeEnum.Group.getName());
            //step4:取host_children String[] ids,得到list<BriefHostDTO>,循环给cid,pid,name,type,state赋值
            String[] hostIds = mongoGroupDO.getHostChildren();
            List<BriefHostDTO> mongoHosts = new ArrayList<>();
            for(String hostId :hostIds) {
                for(BriefHostDTO host : allHosts) {
                    if(hostId.equals(host.getHostId())) {
                        mongoHosts.add(host);
                    }
                }
            }
            List<String> hostStates = new ArrayList<>();
            for(BriefHostDTO mongoHostDTO : mongoHosts) {
                //cid,pid,name
                MongoGroupVO mongoHost = new MongoGroupVO();
                mongoHost.setcId(mongoHostDTO.getHostId());
                mongoHost.setpId(mongoGroupDO.getId());
                mongoHost.setName(mongoHostDTO.getName());
                //type
                if(mongoHostDTO.getParentTemplates().size() != 0) {
                    String DTOTemplateId = mongoHostDTO.getParentTemplates().get(0).getTemplateId();
                    for(BriefTemplateDTO templateDTO : allTemplates) {
                        if(templateDTO.getTemplateId().equals(DTOTemplateId)) {
                            mongoHost.setType(templateDTO.getTemplateGroups().get(0).getName());
                        }
                    }
                }
                //state
                if(problemHostIds.contains(mongoHostDTO.getHostId())) {
                    mongoHost.setState(StatusEnum.PROBLEM.getName());
                }else {
                    mongoHost.setState(StatusEnum.OK.getName());
                }
                hostStates.add(mongoHost.getState());
                //将组装好的 host 装进 mongoGroupVOS
                mongoGroupVOS.add(mongoHost);
                //step5:循环list<BriefHostDTO>中的List<BriefBPointDTO>,赋值cid，pid，name，type,state
                List<BriefPointDTO> mongoPointDTOS = mongoHostDTO.getPoints();
                MongoGroupVO mongoPoint = new MongoGroupVO();
                for(BriefPointDTO mongoPointDTO : mongoPointDTOS) {
                    //cid，pid，name，type
                    mongoPoint.setcId(mongoPointDTO.getPointId());
                    mongoPoint.setpId(mongoHostDTO.getHostId());
                    mongoPoint.setName(mongoPointDTO.getName());
                    mongoPoint.setType(MongoTypeEnum.Point.getName());
                    //state
                    if(problemPointIds.contains(mongoPointDTO.getPointId())) {
                        mongoPoint.setState(StatusEnum.PROBLEM.getName());
                    }else {
                        mongoPoint.setState(StatusEnum.OK.getName());
                    }
                    //将组装好的 point 装进 mongoGroupVOS
                    mongoGroupVOS.add(mongoPoint);
                }
            }
            //step6:退出list<BriefHostDTO>循环，根据旗下的所有子主机的状态情况判断赋值创建MongoGroupVO 的 state
            if(hostStates.contains(StatusEnum.PROBLEM.getName())) {
                mongoGroup.setState(StatusEnum.PROBLEM.getName());
            }else {
                mongoGroup.setState(StatusEnum.OK.getName());
            }
            //将组装好的 mongogroup 装进 mongoGroupVOS
            mongoGroupVOS.add(mongoGroup);
        }
        return mongoGroupVOS;
    }

    @Override
    public List<MongoGroupVO> listMongo() throws ServiceException {
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
        MongoGroupDO root = mongoGroupRepository.findByName("root");
        if(root.getHostChildren().length == 0 && root.getGroupChildren().length == 0) {
            List<String> hostIds = new ArrayList<>();
            for(BriefHostDTO host : allHosts) {
                hostIds.add(host.getHostId());
            }
            String[] hostIdsString = hostIds.toArray(new String[hostIds.size()]);
            root.setHostChildren(hostIdsString);
            mongoGroupRepository.save(root);
        }
        //step:3 创建list，调用list = getGroupTree()
        List<MongoGroupVO> mongoGroupVOS = new ArrayList<>();
        mongoGroupVOS = getGroupTree("root",mongoGroupVOS,allHosts);
        //step:4 反转list，循环 list，
        Collections.reverse(mongoGroupVOS);
        for(MongoGroupVO mongoGroupVO : mongoGroupVOS) {
            //if type == "监控点"，则为point,给 point 赋值：state
            if(MongoTypeEnum.Point.getName().equals(mongoGroupVO.getType())) {
                if(problemHostIds.contains(mongoGroupVO.getcId())) {
                    mongoGroupVO.setState(StatusEnum.PROBLEM.getName());
                }else {
                    mongoGroupVO.setState(StatusEnum.OK.getName());
                }
            }
            //if type == null 则为主机，,给所有主机赋值 type，state
            if(mongoGroupVO.getType() == null) {
                for(BriefHostDTO host : allHosts) {
                    if(mongoGroupVO.getcId().equals(host.getHostId())) {
                        //type
                        if(host.getParentTemplates().size() != 0) {
                            String templateId = host.getParentTemplates().get(0).getTemplateId();
                            for(BriefTemplateDTO templateDTO : allTemplates) {
                                if(templateDTO.getTemplateId().equals(templateId)) {
                                    mongoGroupVO.setType(templateDTO.getTemplateGroups().get(0).getName());
                                }
                            }
                        }
                        //state
                        if(problemHostIds.contains(mongoGroupVO.getcId())) {
                            mongoGroupVO.setState(StatusEnum.PROBLEM.getName());
                        }else {
                            mongoGroupVO.setState(StatusEnum.OK.getName());
                        }
                    }
                }
            }
            //if type = "组" 给group赋值，state：根据cid  = pid，找所有子节点的state ，List<String>，根据所有子节点状态做处理赋值
            if(MongoTypeEnum.Group.getName().equals(mongoGroupVO.getType()) && !"root".equals(mongoGroupVO.getName())) {
                List<String> childStates = new ArrayList<>();
                for(MongoGroupVO childMongo : mongoGroupVOS) {
                    if(mongoGroupVO.getcId().equals(childMongo.getpId())) {
                        childStates.add(childMongo.getState());
                    }
                }
                //state赋值
                if(childStates.contains(StatusEnum.PROBLEM.getName())) {
                    mongoGroupVO.setState(StatusEnum.PROBLEM.getName());
                }else {
                    mongoGroupVO.setState(StatusEnum.OK.getName());
                }
            }

        }
        //step:5反转还原
        Collections.reverse(mongoGroupVOS);
        return mongoGroupVOS;
    }

    private List<MongoGroupVO> getGroupTree(String name,List<MongoGroupVO> mongoGroupVOS,List<BriefHostDTO> allHosts)  {
        //step1:新建List<MongoGroupVO> list,MongoGroupVO mongoGroup,根据name查出mongoGroupDO
        MongoGroupDO mongoGroupDO = mongoGroupRepository.findByName(name);
        //step2:给vo赋值，cid，pid，name，添加vo到list中
        MongoGroupVO mongoGroup = new MongoGroupVO();
        mongoGroup.setcId(mongoGroupDO.getcId());
        mongoGroup.setpId(mongoGroupDO.getpId());
        mongoGroup.setName(mongoGroupDO.getName());
        mongoGroup.setType(MongoTypeEnum.Group.getName());
        mongoGroupVOS.add(mongoGroup);
        //step3:判断DO的group_children.length != 0
        if(mongoGroupDO.getGroupChildren().length != 0) {
            //是：循环 group_children ，取name，递归调用 list = getGroupTree(name,list)
            for(String groupName : mongoGroupDO.getGroupChildren()) {
                mongoGroupVOS = getGroupTree(groupName,mongoGroupVOS,allHosts);
            }
        }
        //step4:取DO的 host_children,循环 add host 到 list中 return list
        for(String hostId : mongoGroupDO.getHostChildren()) {
            for(BriefHostDTO host : allHosts) {
                if(hostId.equals(host.getHostId())) {
                    MongoGroupVO mongoHost = new MongoGroupVO();
                    mongoHost.setpId(mongoGroupDO.getcId());
                    mongoHost.setcId(hostId);
                    mongoHost.setName(host.getName());
                    mongoGroupVOS.add(mongoHost);
                    //循环allHosts,取监控点
                    List<BriefPointDTO> points = host.getPoints();
                    for(BriefPointDTO point : points) {
                        MongoGroupVO mongoPoint = new MongoGroupVO();
                        mongoPoint.setcId(point.getPointId());
                        mongoPoint.setpId(hostId);
                        mongoPoint.setName(point.getName());
                        mongoPoint.setType(MongoTypeEnum.Point.getName());
                        mongoGroupVOS.add(mongoPoint);
                    }
                }

            }
        }
        return mongoGroupVOS;
    }


}
