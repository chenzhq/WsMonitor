package com.ws.stoner.servicenew;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.vo.HostVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/7
 */
public interface HostService {

    /**
     * 根据 hostDTO 转换获得 hostVO
     * @param hostDTO
     * @param templateDTOS 用于组装 type 属性
     * @return
     */
    HostVO getHostVOByDTO(BriefHostDTO hostDTO, List<BriefTemplateDTO> templateDTOS);

    /**
     * 获取所有 监控中的 hosts 的 hostVO对象
     * @return
     * @throws ServiceException
     */
    List<HostVO> listAllHostVOS() throws ServiceException;

    /**
     * 获取所有的 DTOS 包含 point
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> listAllHostDTOS();

    List<BriefHostDTO> listAllHostDTOSNoPoints();

    List<BriefHostDTO> listAllHostDTOSWithPlatform();

    List<BriefHostDTO> getHostDTOSWithPlatByPlatId(String platId);

    List<BriefHostDTO> getHostDTOSWithPlatByPlatIds(List<String> platIds);

    List<BriefHostDTO> getHostDTOSWithoutPointByPlatIds(List<String> platIds);

    /**
     * 根据 hostId 获取指定的 hostVO
     * @param hostId
     * @return
     * @throws ServiceException
     */
    HostVO getHostVOByHostId(String hostId) throws ServiceException;

    /**
     * 根据 hostId 获取指定的 hostDTO
     * @param hostId
     * @return
     * @throws ServiceException
     */
    BriefHostDTO getHostDTOByHostId(String hostId) throws ServiceException;

    /**
     * 根据 hostIds 获取指定的 hostDTOS 带 pointDTOS
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> getHostDTOSByHostIdsWithPoint(List<String> hostIds) throws ServiceException;

}
