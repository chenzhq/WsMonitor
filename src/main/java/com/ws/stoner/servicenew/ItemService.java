package com.ws.stoner.servicenew;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.vo.item.ItemVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/15
 */
public interface ItemService {

    /**
     * 根据 pointId 查询 itemDTOS
     * @param pointId
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getItemDTOSByPointId(String pointId) throws ServiceException;

    /**
     * 获取 itemVO
     * @param pointId
     * @return
     * @throws ServiceException
     */
    List<ItemVO> getItemVOSByPOintId(String pointId) throws ServiceException;

    /**
     * 根据 DTO 转 VO
     * @param itemDTO
     * @return
     * @throws ServiceException
     */
    ItemVO getItemVOByDTO(BriefItemDTO itemDTO) throws ServiceException;

    /**
     * 根据 hostId 获取所有的 ItemVOS
     * @return
     * @throws ServiceException
     */
    List<ItemVO> getItemVOSByHostId(String hostId) throws ServiceException;

    /**
     * 根据 hostId 获取核心的 ItemVOS
     * @return
     * @throws ServiceException
     */
    List<ItemVO> getCoreItemVOSByHostId(String hostId) throws ServiceException;

    BriefItemDTO getDTOByItemId(String itemId);





}
