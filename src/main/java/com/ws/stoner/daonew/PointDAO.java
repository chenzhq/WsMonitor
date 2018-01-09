package com.ws.stoner.daonew;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefPointDTO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/11
 */
public interface PointDAO {
    /**
     * 获取指定 hostIds 的监控点 pointDTOS
     * @param hostIds
     * @return
     * @throws DAOException
     */
    List<BriefPointDTO> getPointDTOSByHostIds(List<String> hostIds) throws DAOException;

    /**
     * 获取指定 hostIds 的监控点 pointDTOS 不带 host
     * @param hostIds
     * @return
     * @throws DAOException
     */
    List<BriefPointDTO> getPointDTOSNoHostByHostIds(List<String> hostIds) throws DAOException;

    /**
     * 获取指定的 pointIds 的监控点 pointDTOS
     * @param pointIds
     * @return
     * @throws DAOException
     */
    List<BriefPointDTO> getPointDTOSByPointIds(List<String> pointIds) throws DAOException;

    /**
     * 获取指定的 pointIds 的监控点 pointDTOS 带 hostDTO 和 ItemDTOS
     * @param pointIds
     * @return
     * @throws DAOException
     */
    List<BriefPointDTO> getPointDTOSByPointIdsWithItems(List<String> pointIds) throws DAOException;

    /**
     * 获取指定的 itemIds 的监控点 pointDTOS
     * @param itemIds
     * @return
     * @throws DAOException
     */
    List<BriefPointDTO> getPointDTOSByItemIds(List<String> itemIds) throws DAOException;



}
