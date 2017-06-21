package com.ws.stoner.manager;

import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.BriefPointDTO;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
public interface PointManager {

    /**
     * 根据 request 获取监控点数量
     * @param request
     * @return
     * @throws ManagerException
     */
    int countPoint(ApplicationGetRequest request) throws ManagerException;

    /**
     * List applications list.
     *
     * @return the list
     * @throws ManagerException the auth expire exception
     */
    List<BriefPointDTO> listPoint(ApplicationGetRequest request) throws ManagerException;


}
