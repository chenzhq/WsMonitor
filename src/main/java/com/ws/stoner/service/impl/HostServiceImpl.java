package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.problem.ProblemGetRequest;
import com.ws.bix4j.bean.HostDO;
import com.ws.bix4j.bean.ProblemDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.constant.HostStatusEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.StateNumDTO;
import com.ws.stoner.service.HostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chenzheqi on 2017/5/2.
 */
@Service
public class HostServiceImpl implements HostService {
    private static final Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);
    @Autowired
    private ZApi zApi;

    @Override
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

    /**
     * Count unmonitored host int.
     *
     * @return the int
     */
    @Override
    public int countDisableHost() throws ServiceException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.UNMONITORED_HOST.value);
        hostGetRequest.getParams().setFilter(statusFilter).setCountOutput(true);
        int disableHostNum;
        try {
            disableHostNum = zApi.Host().count(hostGetRequest);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new AuthExpireException("");
            } else {
                throw new ServiceException("");
            }
        }
        return disableHostNum;
    }

    @Override
    public int countMaintenanceHost() {
        return 3;
    }


    @Override
    public int countDangerHost() throws ServiceException {

        ProblemGetRequest problemGetRequest = new ProblemGetRequest();
        problemGetRequest.getParams().setSource(ZApiParameter.SOURCE.TRIGGER.value)
                .setObject(ZApiParameter.OBJECT.TRIGGER.value);
        List<ProblemDO> problems;
        List<String> triggerIds = new ArrayList<>();
        HostGetRequest hostGetRequest = new HostGetRequest();
        int problemHostNum;

        try {
            problems = zApi.Problems().get(problemGetRequest).getResult();
            for (ProblemDO problem : problems) {
                triggerIds.add(problem.getObjectId());
            }
            hostGetRequest.getParams().setTriggerIds(triggerIds).setMonitoredHosts(true);
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

    @Override
    public int countUnsupportedHost() {
        return 0;
    }

    @Override
    public int countOkHost() {

        return 2;
    }

    @Override
    public StateNumDTO countAllHostState() throws ServiceException {
        StateNumDTO stateNumDTO = new StateNumDTO();
        List<StateNumDTO.StateNum> stateNumList = new ArrayList<>();

        stateNumList.add(new StateNumDTO.StateNum(HostStatusEnum.OK, countOkHost()));
        stateNumList.add(new StateNumDTO.StateNum(HostStatusEnum.DANGER, countDangerHost()));
        stateNumList.add(new StateNumDTO.StateNum(HostStatusEnum.MAINTENANCE, countMaintenanceHost()));
        stateNumList.add(new StateNumDTO.StateNum(HostStatusEnum.UNSUPPORT, countUnsupportedHost()));
        stateNumList.add(new StateNumDTO.StateNum(HostStatusEnum.DISABLE, countDisableHost()));

        stateNumDTO.setStateNum(stateNumList);
        stateNumDTO.setTotalNum(countAllHost());

        return stateNumDTO;
    }

    @Override
    public int countAllHost() {
        return 10;
    }

    @Override
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
