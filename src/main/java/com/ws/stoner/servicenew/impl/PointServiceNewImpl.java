package com.ws.stoner.servicenew.impl;

import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.daonew.HostDAO;
import com.ws.stoner.daonew.PointDAO;
import com.ws.stoner.daonew.TemplateDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.vo.HostVO;
import com.ws.stoner.model.vo.PointVO;
import com.ws.stoner.model.vo.stat.StateVO;
import com.ws.stoner.servicenew.HostService;
import com.ws.stoner.servicenew.PointService;
import com.ws.stoner.utils.StatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2017/12/11
 */
@Service
public class PointServiceNewImpl implements PointService {
    private static final Logger logger = LoggerFactory.getLogger(PointServiceNewImpl.class);

    @Autowired
    private HostDAO hostDAO;

    @Autowired
    private PointDAO pointDAO;

    @Autowired
    private TemplateDAO templateDAO;

    @Autowired
    private HostService hostService;
    /**
     * 获取所有的 pointVO 对象
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PointVO> listAllPointVO() throws ServiceException {
        //获取allPointDTOS,allTemplateDTOS
        List<BriefPointDTO> pointDTOS = null;
        List<BriefHostDTO> hostDTOS = null;
        try {
            hostDTOS = hostDAO.listAllHosts();
        } catch (DAOException e) {
            logger.error("调用 hostDAO 获取 allhostDTOS错误",e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO hostDTO : hostDTOS) {
            hostIds.add(hostDTO.getHostId());
        }
        try {
            pointDTOS = pointDAO.getPointDTOSByHostIds(hostIds);
        } catch (DAOException e) {
            logger.error("调用 pointDAO 获取 allPointDTOS 错误！",e.getMessage());
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
        //循环创建VO
        List<PointVO> pointVOS = new ArrayList<>();
        for(BriefPointDTO pointDTO : pointDTOS) {
            BriefHostDTO pointHostDTO = pointDTO.getHost();
            for(BriefHostDTO hostDTO : hostDTOS) {
                if(hostDTO.getHostId().equals(pointHostDTO.getHostId())) {
                    pointDTO.getHost().setParentTemplates(hostDTO.getParentTemplates());
                }
            }
            PointVO pointVO = getPointVOByDTO(pointDTO,allTemplateDTOS);
            pointVOS.add(pointVO);
        }
        return pointVOS;

    }

    /**
     * 根据 DTO 获取 VO
     * @param pointDTO
     * @param templateDTOS 用于组装 host中的 type 属性
     * @return
     * @throws ServiceException
     */
    @Override
    public PointVO getPointVOByDTO(BriefPointDTO pointDTO,List<BriefTemplateDTO> templateDTOS) throws ServiceException {
        PointVO pointVO = new PointVO();
        pointVO.setId(pointDTO.getPointId());
        pointVO.setName(pointDTO.getName());
        pointVO.setState(StatusConverter.StatusTransform(pointDTO.getCustomState()));
        //host
        if(pointDTO.getHost() != null) {
            HostVO hostVO = hostService.getHostVOByDTO(pointDTO.getHost(),templateDTOS);
            pointVO.setHost(hostVO);
        }
        if(pointDTO.getItems() !=null && pointDTO.getItems().size() != 0) {
            StateVO stateVO = new StateVO();
            //allNum,warningNum,highNum
            int allNum = pointDTO.getItems().size();
            int warningNum = 0;
            int highNum = 0;
            int okNum = 0;
            for(BriefItemDTO item : pointDTO.getItems()) {
                if(StatusEnum.WARNING.code == item.getCustomState()) {
                    warningNum++;
                }
                if(StatusEnum.HIGH.code == item.getCustomState()) {
                    highNum++;
                }
                if(StatusEnum.OK.code == item.getCustomState() && item.getWeight() != 0) {
                    okNum++;
                }
            }
            stateVO.setTotal(allNum);
            stateVO.setWarning(warningNum);
            stateVO.setHigh(highNum);
            stateVO.setOk(okNum);
            pointVO.setStat(stateVO);
        }
        return pointVO;
    }

    /**
     * 根据 pointId 获取 pointVO
     * @param pointId
     * @return
     * @throws ServiceException
     */
    @Override
    public PointVO getPointVOByPointId(String pointId) throws ServiceException {
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        BriefPointDTO pointDTO = null;
        try {
            List<BriefPointDTO> pointDTOS = pointDAO.getPointDTOSByPointIds(pointIds);
            if(pointDTOS != null && pointDTOS.size() != 0) {
                pointDTO = pointDTOS.get(0);
            }
        } catch (DAOException e) {
            logger.error("调用 pointDAO 获取 getPointDTOSByHostIds错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        if(pointDTO == null) {
            return null;
        }
        BriefHostDTO hostDTO = hostService.getHostDTOByHostId(pointDTO.getHostId());
        pointDTO.setHost(hostDTO);
        //获取模板列表用于组装 type 属性
        List<BriefTemplateDTO> allTemplateDTOS = null;
        try {
            allTemplateDTOS = templateDAO.listAllTemplates();
        } catch (DAOException e) {
            logger.error("调用 templateDAO 获取 allTemplateDTOS 出错",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return getPointVOByDTO(pointDTO,allTemplateDTOS);
    }

    @Override
    public List<BriefPointDTO> getPointDTOSByHostIdWithItems(String hostId) throws ServiceException {
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        List<BriefPointDTO> pointDTOS = null;
        try {
            pointDTOS = pointDAO.getPointDTOSByHostIds(hostIds);
        } catch (DAOException e) {
            logger.error("调用 pointDAO 获取 getPointDTOSByHostIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return pointDTOS;
    }

    @Override
    public List<BriefPointDTO> getPointDTOSByHostIdsWithItems(List<String> hostIds) throws ServiceException {
        List<BriefPointDTO> pointDTOS = null;
        try {
            pointDTOS = pointDAO.getPointDTOSByHostIds(hostIds);
        } catch (DAOException e) {
            logger.error("调用 pointDAO 获取 getPointDTOSByHostIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return pointDTOS;
    }

    /**
     * 根据 hostId 获取指定的 pointVOS
     * @param hostId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PointVO> getPointVOSByHostId(String hostId) throws ServiceException {
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        List<BriefPointDTO> pointDTOS =null;
        try {
            pointDTOS = pointDAO.getPointDTOSNoHostByHostIds(hostIds);
        } catch (DAOException e) {
            logger.error("调用 pointDAO 获取 getPointDTOSByHostIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<PointVO> pointVOS = new ArrayList<>();
        for(BriefPointDTO pointDTO : pointDTOS) {
            PointVO pointVO = getPointVOByDTO(pointDTO,null);
            pointVOS.add(pointVO);
        }
        return pointVOS;

    }

    @Override
    public BriefPointDTO getPointDTOByPointId(String pointId) throws ServiceException {
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        BriefPointDTO pointDTO = null;
        try {
            List<BriefPointDTO> pointDTOS = pointDAO.getPointDTOSByPointIds(pointIds);
            if(pointDTOS != null && pointDTOS.size() != 0) {
                pointDTO = pointDTOS.get(0);
            }
        } catch (DAOException e) {
            logger.error("调用 pointDAO 获取 getPointDTOSByPointIds出错！",e.getMessage());
            new ServiceException(e.getMessage());
        }

        return pointDTO;

    }

    @Override
    public BriefPointDTO getPointDTOByPointIdWithItems(String pointId) throws ServiceException {
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        BriefPointDTO pointDTO = null;
        try {
            List<BriefPointDTO> pointDTOS = pointDAO.getPointDTOSByPointIdsWithItems(pointIds);
            if(pointDTOS != null && pointDTOS.size() != 0) {
                pointDTO = pointDTOS.get(0);
            }
        } catch (DAOException e) {
            logger.error("调用 pointDAO 获取 getPointDTOSByPointIdsWithItems 出错！",e.getMessage());
            new ServiceException(e.getMessage());
        }

        return pointDTO;
    }

    @Override
    public List<BriefPointDTO> getPointDTOSByPointIdsWithItems(List<String> pointIds) throws ServiceException {
        List<BriefPointDTO> pointDTOS = null;
        try {
            pointDTOS = pointDAO.getPointDTOSByPointIdsWithItems(pointIds);

        } catch (DAOException e) {
            logger.error("调用 pointDAO 获取 getPointDTOSByPointIdsWithItems 出错！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return pointDTOS;
    }

    @Override
    public BriefPointDTO getPointDTOByItemId(String itemId) {
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemId);
        BriefPointDTO pointDTO = null;
        try {
            List<BriefPointDTO> pointDTOS = pointDAO.getPointDTOSByItemIds(itemIds);
            if(pointDTOS != null && pointDTOS.size() != 0) {
                pointDTO = pointDTOS.get(0);
            }
        } catch (DAOException e) {
            logger.error("调用 pointDAO 获取 getPointDTOSByPointIds出错！",e.getMessage());
            new ServiceException(e.getMessage());
        }

        return pointDTO;
    }
}
