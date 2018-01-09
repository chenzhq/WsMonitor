package com.ws.stoner.servicenew;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.vo.PointVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/7
 */
public interface PointService {

    /**
     * 获取所有的 pointVO 对象
     * @return
     * @throws ServiceException
     */
    List<PointVO> listAllPointVO() throws ServiceException;

    /**
     * 根据 DTO 获取 VO
     * @param pointDTO
     * @param templateDTOS 用于组装 host中的 type 属性
     * @return
     * @throws ServiceException
     */
    PointVO getPointVOByDTO(BriefPointDTO pointDTO, List<BriefTemplateDTO> templateDTOS) throws ServiceException;

    /**
     * 根据 pointId 获取 pointVO
     * @param pointId
     * @return
     * @throws ServiceException
     */
    PointVO getPointVOByPointId(String pointId) throws ServiceException ;

    /**
     * 根据 hostId 获取指定的 pointDTOS 带 hostDTO 和 itemDTOS
     * @param hostId
     * @return
     * @throws ServiceException
     */
    List<BriefPointDTO> getPointDTOSByHostIdWithItems(String hostId) throws ServiceException;

    /**
     * 根据 hostIds 获取指定的 pointDTOS 带 hostDTO 和 itemDTOS
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<BriefPointDTO> getPointDTOSByHostIdsWithItems(List<String> hostIds) throws ServiceException;

    /**
     * 根据 hostId 获取指定的 pointVOS
     * @param hostId
     * @return
     * @throws ServiceException
     */
    List<PointVO> getPointVOSByHostId(String hostId) throws ServiceException;

    /**
     * 根据 pointId 获取 pointDTO
     * @param pointId
     * @return
     * @throws ServiceException
     */
    BriefPointDTO getPointDTOByPointId(String pointId) throws ServiceException;

    /**
     * 根据 pointId 获取 pointDTO 带 hostDTO 和 ItemDTOS
     * @param pointId
     * @return
     * @throws ServiceException
     */
    BriefPointDTO getPointDTOByPointIdWithItems(String pointId) throws ServiceException;

    /**
     * 根据 pointIds 获取 pointDTOS 带 hostDTO 和 ItemDTOS
     * @param pointIds
     * @return
     * @throws ServiceException
     */
    List<BriefPointDTO> getPointDTOSByPointIdsWithItems(List<String> pointIds) throws ServiceException;

    /**
     * 根据 itemId 获取 pointDTO
     * @param itemId
     * @return
     * @throws ServiceException
     */
    BriefPointDTO getPointDTOByItemId(String itemId);
}
