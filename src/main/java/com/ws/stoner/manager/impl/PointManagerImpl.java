package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.manager.PointManager;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.manager.ItemManager;
import com.ws.stoner.manager.TriggerManager;
import com.ws.stoner.model.dto.BriefPointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zkf on 2017/6/8.
 */
@Service
public class PointManagerImpl implements PointManager {

    private static final Logger logger = LoggerFactory.getLogger(HostManagerImpl.class);
    @Autowired
    private ZApi zApi;

    /**
     * 根据request 获取监控点数量
     * @param request
     * @return
     * @throws ManagerException
     */
    @Override
    public int countPoint(ApplicationGetRequest request) throws ManagerException {
        int appNum ;
        try {
            appNum = zApi.Application().count(request);
        } catch (ZApiException e) {
            e.printStackTrace();
            return 0;
        }
        return appNum;
    }

    /**
     *
     * @return 获取监控点列表
     * @throws AuthExpireException
     */
    @Override
    public List<BriefPointDTO> listPoint(ApplicationGetRequest request) throws AuthExpireException {
        List<BriefPointDTO> listApplication ;
        try {
            listApplication = zApi.Application().get(request,BriefPointDTO.class);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new AuthExpireException("");
            }
            e.printStackTrace();
            logger.error("查询监控点错误！{}", e.getMessage());
            return null;
        }
        return listApplication;
    }



}
