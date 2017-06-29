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
                            mongoHost.setType(templateDTO.getName());
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

        //step:1创建list，调用list = getGroupTree()
        //step:2循环 list，if name == null 则为主机
        //step:3
        //step:4
        //step:5
        //step:6

        return null;
    }

    public List<MongoGroupVO> getGroupTree(String name,List<MongoGroupVO> mongoGroupVOS)  {
        //step1:新建List<MongoGroupVO> list,MongoGroupVO mongoGroup,根据name查出mongoGroupDO
        MongoGroupDO mongoGroupDO = mongoGroupRepository.findByName(name);
        //step2:判断DO的group_children.length != 0
        if(mongoGroupDO.getGroupChildren().length != 0) {
            //是：循环 group_children ，取name，递归调用 list = getGroupTree(name,list)
            for(String groupName : mongoGroupDO.getGroupChildren()) {
                mongoGroupVOS = getGroupTree(groupName,mongoGroupVOS);
            }
        }

        //step3:取DO的 host_children,循环 add host 到 list中 return list
        MongoGroupVO mongoHost = new MongoGroupVO();
        for(String hostId : mongoGroupDO.getHostChildren()) {
            mongoHost.setpId(mongoGroupDO.getcId());
            mongoHost.setcId(hostId);
            mongoGroupVOS.add(mongoHost);
        }
        //step4:给vo赋值，cid，pid，name，添加vo到list中
        MongoGroupVO mongoGroup = new MongoGroupVO();
        mongoGroup.setcId(mongoGroupDO.getcId());
        mongoGroup.setpId(mongoGroupDO.getpId());
        mongoGroup.setName(mongoGroupDO.getName());
        mongoGroup.setType(MongoTypeEnum.Group.getName());
        mongoGroupVOS.add(mongoGroup);
        return mongoGroupVOS;
    }


}
