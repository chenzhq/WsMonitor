package com.ws.stoner.service.impl;

import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.manager.*;
import com.ws.stoner.model.dto.StateNumDTO;
import com.ws.stoner.service.CountStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Created by pc on 2017/6/12.
 */
@Service
public class CountStateServiceImpl implements CountStateService {

    @Autowired
    private HostManager hostManager;

    @Autowired
    private PointManager pointManager;

    @Autowired
    private PlatformManager platformManager;

    @Autowired
    private TriggerManager triggerManager;

    /**
     * 主机业务数据组合
     * @return
     * @throws ServiceException
     */
    @Override
    public StateNumDTO countHostState() throws ServiceException {
        StateNumDTO hostState = new StateNumDTO();
        List<StateNumDTO.StateNum> stateNums = new ArrayList<>();
        int allHostNum = 0;
        int problemhostNum = 0;
        List<String> triggerIds ;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
            allHostNum = hostManager.countAllHost();
            problemhostNum = hostManager.countProblemHost(triggerIds);
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        StateNumDTO.StateNum problemStateNum = new StateNumDTO.StateNum(StatusEnum.PROBLEM,problemhostNum);
        StateNumDTO.StateNum okStateNum = new StateNumDTO.StateNum(StatusEnum.OK,allHostNum-problemhostNum);
        stateNums.add(okStateNum);
        stateNums.add(problemStateNum);
        hostState.setTotalNum(allHostNum).setStateNum(stateNums);

        return hostState;
    }

    /**
     * 获取业务平台业务组合数据
     * @return
     * @throws ServiceException
     */
    @Override
    public StateNumDTO countPlatformState() throws ServiceException {
        StateNumDTO platformState = new StateNumDTO();
        List<StateNumDTO.StateNum> stateNums = new ArrayList<>();
        int allPlarformNum = 0;
        int problemPlatformNum = 0;
        List<String> triggerIds ;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
            allPlarformNum = platformManager.countAllPlatform();
            problemPlatformNum = platformManager.countProblemPlatform(triggerIds);
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        StateNumDTO.StateNum problemStateNum = new StateNumDTO.StateNum(StatusEnum.PROBLEM,problemPlatformNum);
        StateNumDTO.StateNum okStateNum = new StateNumDTO.StateNum(StatusEnum.OK,allPlarformNum-problemPlatformNum);
        stateNums.add(okStateNum);
        stateNums.add(problemStateNum);
        platformState.setTotalNum(allPlarformNum).setStateNum(stateNums);
        return platformState;
    }

    /**
     * 获取监控点业务组合数据
     * @return
     * @throws ServiceException
     */
    @Override
    public StateNumDTO countPointState() throws ServiceException {
        StateNumDTO pointState = new StateNumDTO();
        List<StateNumDTO.StateNum> stateNums = new ArrayList<>();
        int allPointNum = 0;
        int problemPointNum = 0;
        List<String> triggerIds;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
            allPointNum = pointManager.countAllPoint();
            problemPointNum = pointManager.countProblemPoint(triggerIds);
        } catch (ManagerException e) {
            e.printStackTrace();
        }
        StateNumDTO.StateNum problemStateNum = new StateNumDTO.StateNum(StatusEnum.PROBLEM,problemPointNum);
        StateNumDTO.StateNum okStateNum = new StateNumDTO.StateNum(StatusEnum.OK,allPointNum-problemPointNum);
        stateNums.add(okStateNum);
        stateNums.add(problemStateNum);
        pointState.setTotalNum(allPointNum).setStateNum(stateNums);
        return pointState;
    }




}
