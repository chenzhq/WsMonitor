package com.ws.stoner.daonew;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefPlatformDTO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/11
 */
public interface PlatformDAO {
    /**
     * 获取所有的业务平台 DTOS
     * @return
     * @throws DAOException
     */
    List<BriefPlatformDTO> listAllPlatDTOS() throws DAOException;

    /**
     * 根据 platformIds 获取 platformDTO list
     * @param platformIds
     * @return
     * @throws DAOException
     */
    List<BriefPlatformDTO> getPlatByPlatIds(List<String> platformIds) throws DAOException;
}
