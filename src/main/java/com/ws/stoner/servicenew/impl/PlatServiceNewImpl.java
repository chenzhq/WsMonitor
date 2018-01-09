package com.ws.stoner.servicenew.impl;

import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.daonew.ItemDAO;
import com.ws.stoner.daonew.PlatformDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import com.ws.stoner.model.vo.PlatformVO;
import com.ws.stoner.model.vo.stat.StateVO;
import com.ws.stoner.servicenew.PlatformService;
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
public class PlatServiceNewImpl implements PlatformService {
    private static final Logger logger = LoggerFactory.getLogger(PlatServiceNewImpl.class);

    @Autowired
    private PlatformDAO platformDAO;

    @Autowired
    private ItemDAO itemDAO;

    /**
     * 根据 platformId 查询 platformDTO
     * @param platformId
     * @return
     * @throws ServiceException
     */
    @Override
    public BriefPlatformDTO getPlatDTOByPlatId(String platformId) throws ServiceException {
        List<String> platformIds = new ArrayList<>();
        platformIds.add(platformId);
        BriefPlatformDTO platformDTO = null;
        try {
            List<BriefPlatformDTO> platformDTOS = platformDAO.getPlatByPlatIds(platformIds);
            if( platformDTOS!= null && platformDTOS.size() != 0) {
                platformDTO = platformDTOS.get(0);
            }

        } catch (DAOException e) {
            logger.error("调用 platformDAO 获取 getPlatByPlatIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return platformDTO;
    }

    @Override
    public List<BriefPlatformDTO> getPlatDTOSByPlatIds(List<String> platformIds) throws ServiceException {
        List<BriefPlatformDTO> platformDTOS = null;
        try {
            platformDTOS = platformDAO.getPlatByPlatIds(platformIds);
                   } catch (DAOException e) {
            logger.error("调用 platformDAO 获取 getPlatByPlatIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return platformDTOS;
    }

    @Override
    public List<BriefPlatformDTO> listAllPlatformDTOS() throws ServiceException{
        List<BriefPlatformDTO> platformDTOS = null;
        try {
            platformDTOS = platformDAO.listAllPlatDTOS();
        } catch (DAOException e) {
            logger.error("调用 platformDAO listAllPlatDTOS 错误！",e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return platformDTOS;
    }

    /**
     * 根据 platformDTO 获取 platformVO
     * @param platformDTO
     * @return
     * @throws ServiceException
     */
    @Override
    public PlatformVO getPlatVOByDTO(BriefPlatformDTO platformDTO) throws ServiceException {
        if(platformDTO == null) {
            return null;
        }
        PlatformVO platformVO = new PlatformVO();
        platformVO.setId(platformDTO.getPlatformId());
        platformVO.setName(platformDTO.getName());
        platformVO.setHealth(getHealthByPlatformId(platformDTO.getPlatformId()));
        platformVO.setState(StatusConverter.StatusTransform(platformDTO.getCustomState()));
        if(platformDTO.getHosts() != null && platformDTO.getHosts().size() != 0) {
            List<BriefHostDTO> hosts = platformDTO.getHosts();
            //allNum，warningNum,highNum
            int allNum = hosts.size();
            int warningNum = 0;
            int highNum = 0;
            for(BriefHostDTO host : hosts) {
                if(StatusEnum.WARNING.code == host.getCustomState() && StatusEnum.OK.code == host.getCustomAvailableState()) {
                    warningNum++;
                }
                if(StatusEnum.HIGH.code == host.getCustomState() || StatusEnum.WARNING.code == host.getCustomAvailableState()) {
                    highNum++;
                }
            }
            StateVO stateVO = new StateVO(
                allNum - warningNum - highNum,
                warningNum,
                highNum,
                allNum
            );
            platformVO.setStat(stateVO);
        }
        return platformVO;
    }


    /**
     * 获取所有的 platformVOS
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PlatformVO> listAllPlatVOS() throws ServiceException{
        List<BriefPlatformDTO> allPlatDTOS = null;
        try {
            allPlatDTOS = platformDAO.listAllPlatDTOS();
        } catch (DAOException e) {
            logger.error("调用 platformDAO 获取 allPlatDTOS 出错！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<PlatformVO> platformVOS = new ArrayList<>();
        for(BriefPlatformDTO platformDTO : allPlatDTOS) {
            PlatformVO platformVO = getPlatVOByDTO(platformDTO);
            platformVOS.add(platformVO);
        }
        return platformVOS;
    }

    /**
     * 根据 业务平台Id 获取 健康值
     * @param platformId
     * @return
     * @throws ServiceException
     */
    @Override
    public Float getHealthByPlatformId(String platformId) throws ServiceException {
        Float health = 0.0f;
        List<String> ids = new ArrayList<>();
        ids.add(platformId);
        List<BriefItemDTO> itemDTOS = null;
        try {
            itemDTOS = itemDAO.getCoreItemByPlatIds(ids);
        } catch (DAOException e) {
            logger.error("调用 itemDAO 查询 coreItemDTOS 出错", e.getMessage());
            new ServiceException(e.getMessage());
        }

        if(itemDTOS != null && itemDTOS.size() != 0) {
            //初始化值
            Float allWeight = 0.0f;
            Float problemWeight = 0.0f;
            for(BriefItemDTO itemDTO : itemDTOS) {
                allWeight += itemDTO.getWeight();
                problemWeight += itemDTO.getWeight() * (itemDTO.getCustomState() / 2.0f);
                health = (1 -  problemWeight / allWeight) * 100;
            }
        }else {
            health = 100f;
        }
        return health;
    }

    /**
     * 根据 platformId 获取 platformVO
     * @param platformId
     * @return
     * @throws ServiceException
     */
    @Override
    public PlatformVO getPlatVOByPlatId(String platformId) throws ServiceException {
        List<String> platformIds = new ArrayList<>();
        platformIds.add(platformId);
        BriefPlatformDTO platformDTO = null;
        try {
            List<BriefPlatformDTO> platformDTOS = platformDAO.getPlatByPlatIds(platformIds);
            if(platformDTOS != null && platformDTOS.size() != 0) {
                platformDTO = platformDTOS.get(0);
            }

        } catch (DAOException e) {
            logger.error("调用 platformDAO 获取 getPlatByPlatIds错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return getPlatVOByDTO(platformDTO);
    }
}
