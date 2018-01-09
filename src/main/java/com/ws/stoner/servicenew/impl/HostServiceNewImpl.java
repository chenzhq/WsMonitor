package com.ws.stoner.servicenew.impl;


import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.daonew.HostDAO;
import com.ws.stoner.daonew.TemplateDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.vo.HostVO;
import com.ws.stoner.model.vo.stat.StateVO;
import com.ws.stoner.servicenew.HostService;
import com.ws.stoner.servicenew.TemplateService;
import com.ws.stoner.utils.StatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2017/12/7
 */
@Service
public class HostServiceNewImpl implements HostService {
    private static final Logger logger = LoggerFactory.getLogger(HostServiceNewImpl.class);

    @Autowired
    private HostDAO hostDAO;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateDAO templateDAO;

    /**
     * 根据 hostDTO 转换获得 hostVO
     * @param hostDTO
     * @param templateDTOS 用于组装 type 属性
     * @return
     */
    @Override
    public HostVO getHostVOByDTO(BriefHostDTO hostDTO,List<BriefTemplateDTO> templateDTOS) {
        if(hostDTO == null) {
            return  null;
        }
        HostVO hostVO = new HostVO();
        hostVO.setId(hostDTO.getHostId());
        hostVO.setName(hostDTO.getName());
        hostVO.setState(StatusConverter.StatusTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState()));
        hostVO.setType(templateService.transformTypeBytemplateId(hostDTO.getParentTemplates(),templateDTOS));
        if(hostDTO.getPoints() !=null && hostDTO.getPoints().size() != 0) {
            StateVO stateVO = new StateVO();
            //allNum
            int allNum = hostDTO.getPoints().size();
            //warningNum
            //highNum
            int warningNum = 0;
            int highNum = 0;
            for(BriefPointDTO point : hostDTO.getPoints()) {
                if(StatusEnum.WARNING.code == point.getCustomState()) {
                    warningNum++;
                }
                if(StatusEnum.HIGH.code == point.getCustomState()) {
                    highNum++;
                }
            }
            stateVO.setTotal(allNum);
            stateVO.setWarning(warningNum);
            stateVO.setHigh(highNum);
            stateVO.setOk(allNum - warningNum - highNum);
            hostVO.setStat(stateVO);
        }
        return hostVO;
    }

    /**
     * 获取所有 监控中的 hosts 的 hostVO对象
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostVO> listAllHostVOS() throws ServiceException{
        //step1:获取相关对象hostDTOS,templateDTOS
        List<BriefHostDTO> allHostDTOS = null;

        try {
            allHostDTOS = hostDAO.listAllHosts();
        } catch (DAOException e) {
            logger.error("调用 hostDAO 获取 allHostDTOS 出错",e.getMessage());
            new ServiceException(e.getMessage());
        }
        //获取模板列表用于组装 type 属性
        List<BriefTemplateDTO> allTemplateDTOS = null;
        try {
            allTemplateDTOS = templateDAO.listAllTemplates();
        } catch (DAOException e) {
            logger.error("调用 templateDAO 获取 allTemplateDTOS 出错",e.getMessage());
            new ServiceException(e.getMessage());
        }
        //step2:循环allHostDTOS，创建 hostVO对象
        List<HostVO> hostVOS = new ArrayList<>();
        for(BriefHostDTO hostDTO : allHostDTOS) {
            HostVO hostVO = getHostVOByDTO(hostDTO,allTemplateDTOS);
            hostVOS.add(hostVO);
        }
        return hostVOS;
    }

    @Override
    public List<BriefHostDTO> listAllHostDTOS()  {
        List<BriefHostDTO> hostDTOS = null;
        try {
            hostDTOS = hostDAO.listAllHosts();
        } catch (DAOException e) {
            logger.error("调用 hostDAO listAllHosts错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return hostDTOS;
    }

    @Override
    public List<BriefHostDTO> listAllHostDTOSNoPoints() {
        List<BriefHostDTO> hostDTOS = null;
        try {
            hostDTOS = hostDAO.listAllHostsNoPoints();
        } catch (DAOException e) {
            logger.error("调用 hostDAO listAllHostsNoPoints 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return hostDTOS;
    }

    @Override
    public List<BriefHostDTO> listAllHostDTOSWithPlatform() {
        List<BriefHostDTO> hostDTOS = null;
        try {
            hostDTOS = hostDAO.listAllHostsWithPlatform();
        } catch (DAOException e) {
            logger.error("调用 hostDAO listAllHostsWithPlatform 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return hostDTOS;
    }

    @Override
    public List<BriefHostDTO> getHostDTOSWithPlatByPlatId(String platId) {
        List<String> platIds = new ArrayList<>();
        platIds.add(platId);
        List<BriefHostDTO> hostDTOS = null;
        try {
            hostDTOS = hostDAO.getHostsByPlatIds(platIds);
        } catch (DAOException e) {
            logger.error("调用 hostDAO getHostsByPlatIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return hostDTOS;
    }

    @Override
    public List<BriefHostDTO> getHostDTOSWithPlatByPlatIds(List<String> platIds) {
        List<BriefHostDTO> hostDTOS = null;
        try {
            hostDTOS = hostDAO.getHostsByPlatIds(platIds);
        } catch (DAOException e) {
            logger.error("调用 hostDAO getHostsByPlatIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return hostDTOS;
    }

    @Override
    public List<BriefHostDTO> getHostDTOSWithoutPointByPlatIds(List<String> platIds) {
        List<BriefHostDTO> hostDTOS = null;
        try {
            hostDTOS = hostDAO.getHostsByPlatIdsWithOutPoint(platIds);
        } catch (DAOException e) {
            logger.error("调用 hostDAO getHostsByPlatIdsWithOutPoint 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return hostDTOS;
    }

    /**
     * 根据 hostId 获取指定的 hostVO
     * @param hostId
     * @return
     * @throws ServiceException
     */
    @Override
    public HostVO getHostVOByHostId(String hostId) throws ServiceException {
        //调用 getHostDTOByHostId 本地方法
        BriefHostDTO hostDTO =getHostDTOByHostId(hostId);
        //获取模板列表用于组装 type 属性
        List<BriefTemplateDTO> allTemplateDTOS = null;
        try {
            allTemplateDTOS = templateDAO.listAllTemplates();
        } catch (DAOException e) {
            logger.error("调用 templateDAO 获取 allTemplateDTOS 出错",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return getHostVOByDTO(hostDTO,allTemplateDTOS);
    }

    /**
     * 根据 hostId 获取指定的 hostDTO
     * @param hostId
     * @return
     * @throws ServiceException
     */
    @Override
    public BriefHostDTO getHostDTOByHostId(String hostId) throws ServiceException {
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        BriefHostDTO hostDTO = null;
        try {
            List<BriefHostDTO> hosts = hostDAO.getHostsByHostIds(hostIds);
            if( hosts!= null && hosts.size() != 0) {
                hostDTO = hosts.get(0);
            }

        } catch (DAOException e) {
            logger.error("调用 hostDAO 获取 getHostByHostIds错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return hostDTO;
    }

    @Override
    public List<BriefHostDTO> getHostDTOSByHostIdsWithPoint(List<String> hostIds) throws ServiceException {
        List<BriefHostDTO> hostDTOS = null;
        try {
            hostDTOS = hostDAO.getHostsByHostIdsWithPoint(hostIds);

        } catch (DAOException e) {
            logger.error("调用 hostDAO 获取 getHostsByHostIdsWithPoint 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return hostDTOS;
    }
}
