package com.ws.stoner.manager;

import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.bix4j.bean.HostGroupDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
public interface GroupManager {
    /**
     * List hostgroup list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<HostGroupDO> listGroup(HostGroupGetRequest request) throws AuthExpireException;

    /**
     * 根据request获取业务平台数量 hostgroup number
     * @return
     * @throws ManagerException
     */
    int countHostGroup(HostGroupGetRequest request) throws ManagerException;


}
