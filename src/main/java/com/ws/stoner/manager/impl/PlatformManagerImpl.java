package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.manager.PlatformManager;
import com.ws.stoner.model.brief.HostGroupBrief;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 2017/6/1.
 */
@Service
public class PlatformManagerImpl implements PlatformManager {
    private static final Logger logger = LoggerFactory.getLogger(PlatformManagerImpl.class);
    @Autowired
    private ZApi zApi;
    @Override
    public List<BriefPlatformDTO> listPlatform(HostGroupGetRequest request) throws AuthExpireException {
        List<BriefPlatformDTO> groups;
        try {
            groups = zApi.Group().get(request, BriefPlatformDTO.class);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new AuthExpireException("");
            }
            e.printStackTrace();
            logger.error("查询业务平台错误！{}", e.getMessage());
            return null;
        }
        return groups;
    }

    /**
     * 根据request获取业务平台数量
     * @return
     * @throws ManagerException
     */
    @Override
    public int countPlatform(HostGroupGetRequest request) throws ManagerException {
        int hostGroupNum ;
        try {
            hostGroupNum = zApi.Group().count(request);
        } catch (ZApiException e) {
            e.printStackTrace();
            return 0;
        }
        return hostGroupNum;
    }
}
