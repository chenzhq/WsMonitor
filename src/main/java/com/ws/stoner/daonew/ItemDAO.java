package com.ws.stoner.daonew;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefItemDTO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/11
 */
public interface ItemDAO {

/*
zbx api
 */
    /**
     * 根据 platformIds 获取附带有触发器的 items BriefItemDTO 用于获取健康值
     * @param platformIds
     * @return
     * @throws DAOException
     */
    List<BriefItemDTO> getCoreItemByPlatIds(List<String> platformIds) throws DAOException;

    /**
     * 根据 hostIds 获取所有 ItemDTOS
     * @param hostIds
     * @return
     * @throws DAOException
     */
    List<BriefItemDTO> getItemDTOByHostIds(List<String> hostIds) throws DAOException;

    /**
     * 根据 hostIDS 获取核心的 itemDTOS
     * @param hostids
     * @return
     * @throws DAOException
     */
    List<BriefItemDTO> getCoreItemByHostIds(List<String> hostids) throws DAOException;

    /**
     * 根据 pointIds查询 itemDTOS
     * @param pointIds
     * @return
     * @throws DAOException
     */
    List<BriefItemDTO> getItemDTOSByPointIds(List<String> pointIds) throws DAOException;

    /**
     * 根据 itemIds 查询 itemDTOS
     * @param itemIds
     * @return
     * @throws DAOException
     */
    List<BriefItemDTO> getItemDTOSByItemIds(List<String> itemIds) throws DAOException;






}
