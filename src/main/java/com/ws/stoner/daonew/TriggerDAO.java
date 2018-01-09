package com.ws.stoner.daonew;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefTriggerDTO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/25
 */
public interface TriggerDAO {
    /**
     * 列出问题触发器 BriefTriggerDTOS
     * @return
     * @throws DAOException
     */
    List<BriefTriggerDTO> listProblemTriggers() throws DAOException;

    /**
     * 根据 hostids  获取 问题触发器
     * @param hostIds
     * @return
     * @throws DAOException
     */
    List<BriefTriggerDTO> getProblemTriggersByHostIds(List<String> hostIds) throws DAOException;

    /**
     * 根据 platformids 获取 问题触发器
     * @param platformIds
     * @return
     * @throws DAOException
     */
    List<BriefTriggerDTO> getProblemTriggersByPlatIds(List<String> platformIds) throws DAOException;

    /**
     * 根据 triggerIds 查询对应的 triggerDTOS selectHost
     * @param triggerIds
     * @return
     * @throws DAOException
     */
    List<BriefTriggerDTO> getTriggersByTriggerIds(List<String> triggerIds ) throws DAOException;
}
