package com.ws.stoner.manager;

import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.BriefPlatformDTO;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
public interface PlatformManager {
    /**
     * List hostgroup list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<BriefPlatformDTO> listPlatform(HostGroupGetRequest request) throws ManagerException;

    /**
     * 根据request获取业务平台数量 hostgroup number
     * @return
     * @throws ManagerException
     */
    int countPlatform(HostGroupGetRequest request) throws ManagerException;


}
