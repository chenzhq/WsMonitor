package com.ws.stoner.controller;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.HostDetailItemVO;
import com.ws.stoner.model.view.HostDetailPointVO;
import com.ws.stoner.model.view.HostDetailVO;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.ItemService;
import com.ws.stoner.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by pc on 2017/7/13.
 */
@Controller
@RequestMapping("/hostDetail")
public class HostDetailController {

    @Autowired
    private HostService hostService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private TemplateService templateService;

    @RequestMapping(value = {"/", ""})
    public ModelAndView detailHost(@RequestParam String hostId) throws ServiceException {
        //step1:根据 hostId 取对应 BreifHostDTO，单个 hostDTO
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        BriefHostDTO hostDTO = hostService.getHostsByHostIds(hostIds).get(0);
        //取 BriefItemDTO list, 过滤出 value_type=0,3
        List<BriefItemDTO> itemDTOS = itemService.getItemsByHostIds(hostIds);
        //取所有模板
        List<BriefTemplateDTO>  allTemplateDTO = templateService.listAllTemplate();
        //新建DetailHostVO对象,ItemVO对象 List,PointVO对象list
        HostDetailVO hostDetailVO = new HostDetailVO();
        List<HostDetailItemVO> itemVOS = new ArrayList<>();
        List<HostDetailPointVO> pointVOS = new ArrayList<>();
        //step2:组装 HostDetailVO 对象，赋值：
        // hostDetail[hostid,name,state,type,ip,description],
        hostDetailVO.setHostId(hostDTO.getHostId());
        hostDetailVO.setHostName(hostDTO.getName());
        hostDetailVO.setIp(hostDTO.getInterfaces().get(0).getIp());
        hostDetailVO.setDescription(hostDTO.getDescription());
        //state
        if(StatusEnum.OK.code == hostDTO.getCustomState() && StatusEnum.OK.code == hostDTO.getCustomAvailableState()) {
            hostDetailVO.setState(StatusEnum.OK.getName());
        }else if(StatusEnum.WARNING.code == hostDTO.getCustomState() && StatusEnum.OK.code == hostDTO.getCustomAvailableState()) {
            hostDetailVO.setState(StatusEnum.WARNING.getName());
        }else {
            hostDetailVO.setState(StatusEnum.HIGH.getName());
        }
        //type
        if(hostDTO.getParentTemplates().size() != 0) {
            String DTOTemplateId = hostDTO.getParentTemplates().get(0).getTemplateId();
            for(BriefTemplateDTO template : allTemplateDTO) {
                if(template.getTemplateId().equals(DTOTemplateId)) {
                    hostDetailVO.setType(template.getTemplateGroups().get(0).getName());
                }
            }
        }
        // interfaces[interfaceid,dns ,hostid ,ip ,type],
        List<BriefHostInterfaceDTO> interfaces = hostDTO.getInterfaces();
        String agent = String.valueOf(ZApiParameter.HOST_INTERFACE_TYPE.AGENT.value);
        for(BriefHostInterfaceDTO interfaceDTO : interfaces) {
            if(String.valueOf(ZApiParameter.HOST_INTERFACE_TYPE.AGENT.value).equals(interfaceDTO.getType())) {
                hostDetailVO.setAgentDNS(interfaceDTO.getDns());
                hostDetailVO.setAgentIp(interfaceDTO.getIp());
            }else if(String.valueOf(ZApiParameter.HOST_INTERFACE_TYPE.SNMP.value).equals(interfaceDTO.getType())) {
                hostDetailVO.setSNMPDNS(interfaceDTO.getDns());
                hostDetailVO.setSNMPIp(interfaceDTO.getIp());
            }else if(String.valueOf(ZApiParameter.HOST_INTERFACE_TYPE.IPMI.value).equals(interfaceDTO.getType())) {
                hostDetailVO.setIPMIDNS(interfaceDTO.getDns());
                hostDetailVO.setIPMIIp(interfaceDTO.getIp());
            }else if(String.valueOf(ZApiParameter.HOST_INTERFACE_TYPE.JMX.value).equals(interfaceDTO.getType())) {
                hostDetailVO.setJMXDNS(interfaceDTO.getDns());
                hostDetailVO.setJMXIp(interfaceDTO.getIp());
            }
        }
        // Points[pointId,name,hostId,state]

        List<BriefPointDTO> pointDTOS = hostDTO.getPoints();
        for(BriefPointDTO pointDTO : pointDTOS) {
            HostDetailPointVO pointVO = new HostDetailPointVO();
            pointVO.setHostId(pointDTO.getHostId());
            pointVO.setName(pointDTO.getName());
            pointVO.setPointId(pointDTO.getPointId());
            //state
            if(StatusEnum.WARNING.code == pointDTO.getCustomState()) {
                pointVO.setState(StatusEnum.WARNING.getName());
            }else if(StatusEnum.HIGH.code == pointDTO.getCustomState()){
                pointVO.setState(StatusEnum.HIGH.getName());
            }else {
                pointVO.setState(StatusEnum.OK.getName());
            }
            pointVOS.add(pointVO);
        }
        hostDetailVO.setPoints(pointVOS);
        ModelAndView mav = new ModelAndView("hostdetail");
        mav.addObject("hostDetailVO", hostDetailVO );
        return mav;
    }

}
