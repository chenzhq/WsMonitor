package com.ws.stoner.daonew;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefAlertDTO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/28
 */
public interface AlertDAO {

    /**
     * 根据 eventids 获取 AlertDTOS
     * @param eventIds
     * @return
     * @throws DAOException
     */
    List<BriefAlertDTO> getAlertDTOSByEventIds(List<String> eventIds) throws DAOException;

}
