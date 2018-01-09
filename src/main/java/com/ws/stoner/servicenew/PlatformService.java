package com.ws.stoner.servicenew;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import com.ws.stoner.model.vo.PlatformVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/11
 */
public interface PlatformService {

    /**
     * 根据 platformId 查询 platformDTO
     * @param platformId
     * @return
     * @throws ServiceException
     */
    BriefPlatformDTO getPlatDTOByPlatId(String platformId) throws ServiceException;

    /**
     * 根据 platformIds 查询 platformDTOS
     * @param platformIds
     * @return
     * @throws ServiceException
     */
    List<BriefPlatformDTO> getPlatDTOSByPlatIds(List<String> platformIds) throws ServiceException;

    /**
     * 获取所有 platformDTOS
     * @return
     */
    List<BriefPlatformDTO> listAllPlatformDTOS() throws ServiceException;

    /**
     * 根据 platformDTO 获取 platformVO
     * @param platformDTO
     * @return
     * @throws ServiceException
     */
    PlatformVO getPlatVOByDTO(BriefPlatformDTO platformDTO) throws ServiceException;

    /**
     * 获取所有的 platformVOS
     * @return
     * @throws ServiceException
     */
    List<PlatformVO> listAllPlatVOS() throws ServiceException;

    /**
     * 根据 业务平台Id 获取 健康值
     * @param platformId
     * @return
     * @throws ServiceException
     */
    Float getHealthByPlatformId(String platformId) throws ServiceException;

    /**
     * 根据 platformId 获取 platformVO
     * @param platformId
     * @return
     * @throws ServiceException
     */
    PlatformVO getPlatVOByPlatId(String platformId) throws ServiceException;



}
