package com.ws.stoner.manager;

import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.BriefHostDTO;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public interface HostManager {
    /**
     * 根据 request 获取主机总数量
     * @return
     * @throws ManagerException
     */
    int countHost(HostGetRequest request) throws ManagerException;



    /**
     * List host list.
     *
     * @return the list
     * @throws ManagerException the auth expire exception
     */
    List<BriefHostDTO> listHost(HostGetRequest request) throws ManagerException;

}
