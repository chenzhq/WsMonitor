package com.ws.stoner.manager;

import com.ws.bix4j.bean.HostGroupDO;
import com.ws.stoner.exception.AuthExpireException;

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
    List<HostGroupDO> listGroup() throws AuthExpireException;


}
