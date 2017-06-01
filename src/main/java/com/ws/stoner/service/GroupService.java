package com.ws.stoner.service;

import com.ws.bix4j.bean.GroupDO;
import com.ws.bix4j.bean.HostDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.StateNumDTO;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
public interface GroupService {
    /**
     * List hostgroup list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<GroupDO> listGroup() throws AuthExpireException;


}
