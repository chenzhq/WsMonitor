package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.bix4j.bean.HostGroupDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.manager.GroupManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 2017/6/1.
 */
@Service
public class GroupManagerImpl implements GroupManager {
    private static final Logger logger = LoggerFactory.getLogger(GroupManagerImpl.class);
    @Autowired
    private ZApi zApi;
    @Override
    public List<HostGroupDO> listGroup() throws AuthExpireException {
        HostGroupGetRequest hostGroupGetRequest = new HostGroupGetRequest();
        hostGroupGetRequest.getParams();
        List<HostGroupDO> groups;
        try {
            groups = zApi.Group().get(hostGroupGetRequest);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new AuthExpireException("");
            }
            e.printStackTrace();
            logger.error("查询主机错误！{}", e.getMessage());
            return null;
        }
        return groups;
    }
}
