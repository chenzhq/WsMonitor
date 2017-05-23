package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.problem.ProblemGetRequest;
import com.ws.bix4j.bean.HostDO;
import com.ws.bix4j.bean.ProblemDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.service.HostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenzheqi on 2017/5/2.
 */
@Service
public class HostServiceImpl implements HostService {
    private static final Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);
    @Autowired
    private ZApi zApi;

    public List<HostDO> listHost() throws AuthExpireException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams();
        List<HostDO> hosts;
        try {
            hosts = zApi.Host().get(hostGetRequest).getResult();
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new AuthExpireException("");
            }
            e.printStackTrace();
            logger.error("查询主机错误！{}", e.getMessage());
            return null;
        }
        return hosts;
    }

    public int countUnmonitoredHost() {
        return 0;
    }

    public int countMaintenanceHost() {
        return 0;
    }


    public int countProblemHost() throws ServiceException {

        ProblemGetRequest problemGetRequest = new ProblemGetRequest();
        problemGetRequest.getParams().setSource(0).setObject(0);
        List<ProblemDO> problems;
        List<String> triggerIds = new ArrayList<>();
        HostGetRequest hostGetRequest = new HostGetRequest();
        int problemHostNum = 0;

        try {
            problems = zApi.Problems().get(problemGetRequest).getResult();
            for (ProblemDO problem : problems) {
                triggerIds.add(problem.getObjectId());
            }
            hostGetRequest.getParams().setTriggerIds(triggerIds);
            problemHostNum = zApi.Host().count(hostGetRequest);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new AuthExpireException("");
            } else {
                throw new ServiceException("");
            }
        }
        return problemHostNum;
    }

    public int countOkHost() {

        return 0;
    }

    public HostDO getHost(String... hostId) {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams().setHostIds(Arrays.asList(hostId));
        HostDO hostDO = new HostDO();
        try {
            hostDO = zApi.Host().get(hostGetRequest).getResult().get(1);
        } catch (ZApiException e) {
            e.printStackTrace();
        }
        return hostDO;
    }

}
