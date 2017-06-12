package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.bix4j.bean.ApplicationDO;
import com.ws.bix4j.bean.HostDO;
import com.ws.bix4j.bean.ItemDO;
import com.ws.bix4j.bean.TriggerDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.manager.ApplicationManager;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.manager.ItemManager;
import com.ws.stoner.manager.TriggerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkf on 2017/6/8.
 */
@Service
public class ApplicationManagerImpl implements ApplicationManager {

    private static final Logger logger = LoggerFactory.getLogger(HostManagerImpl.class);
    @Autowired
    private HostManager hostManager;

    @Autowired
    private TriggerManager triggerManager;

    @Autowired
    private ItemManager itemManager;

    @Autowired
    private ZApi zApi;

    /**
     *
     * @return 获取监控点列表
     * @throws AuthExpireException
     */
    @Override
    public List<ApplicationDO> listApplication() throws AuthExpireException {
        ApplicationGetRequest applicationGetRequest = new ApplicationGetRequest();
        List<ApplicationDO> listApplication ;
        try {
            listApplication = zApi.Application().get(applicationGetRequest).getResult();
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

    /**
     * 获取 itemid 在给定的ItemIds list 中的所有application
     * @param itemIds
     * @return
     * @throws ManagerException
     */
    @Override
    public List<ApplicationDO> listAppByItemIds(List<String> itemIds) throws ManagerException {
        ApplicationGetRequest applicationGetRequest = new ApplicationGetRequest();
        applicationGetRequest.getParams().setItemIds(itemIds);
        List<ApplicationDO> listApplication ;
        try {
            listApplication = zApi.Application().get(applicationGetRequest).getResult();
        } catch (ZApiException e) {
            e.printStackTrace();
            return null;
        }
        return listApplication;
    }

    /**
     * 获取 itemid 在给定的ItemIds list 中的所有application 的数量
     * @param itemIds
     * @return
     * @throws ManagerException
     */
    @Override
    public int countAppByItemIds(List<String> itemIds) throws ManagerException {
        ApplicationGetRequest applicationGetRequest = new ApplicationGetRequest();
        applicationGetRequest.getParams().setItemIds(itemIds).setCountOutput(true);
        int appByItemIdsNum ;
        try {
            appByItemIdsNum = zApi.Application().count(applicationGetRequest);
        } catch (ZApiException e) {
            e.printStackTrace();
            return 0;
        }
        return appByItemIdsNum;
    }


    /**
     * 统计停用的监控点数量
     * 1、如果该监控点所属的主机为停用状态，则该监控点为停用状态
     * 2、如果所有配置触发器的监控项为停用状态（status : disable）,则该监控点为停用状态
     * @return
     * @throws ManagerException
     */
    @Override
    public int countDisableApp() throws ManagerException {
        //step1:查询所有停用状态主机
        List<HostDO> disableHosts = hostManager.listDisableHost();
        //step2:根据这些主机，筛选查询其下的所有应用集 hostids
        List<String> hostsIds = new ArrayList<String>();
        for(HostDO host : disableHosts) {
            hostsIds.add(host.getHostId());
        }
        ApplicationGetRequest applicationGetRequest = new ApplicationGetRequest();
        applicationGetRequest.getParams().setHostIds(hostsIds);
        int disableAppNum = 0;
        try {
            disableAppNum = zApi.Application().count(applicationGetRequest);
        } catch (ZApiException e) {
            e.printStackTrace();
        }
        return disableAppNum;
    }

    /**
     * 维护主机下的所有监控点（应用集）全为维护状态
     * @return
     * @throws ManagerException
     */
    @Override
    public int countMaintenanceApp() throws ManagerException {
        //step1:查询所有维护状态主机（非停用）
        List<HostDO> maintencanceHosts = hostManager.listMaintenanceHost();
        //step2:根据这些主机，筛选查询其下的所有应用集 hostids
        List<String> hostsIds = new ArrayList<String>();
        for(HostDO host : maintencanceHosts) {
            hostsIds.add(host.getHostId());
        }
        ApplicationGetRequest applicationGetRequest = new ApplicationGetRequest();
        applicationGetRequest.getParams().setHostIds(hostsIds);
        int maintencanceAppNum = 0;
        try {
            maintencanceAppNum = zApi.Application().count(applicationGetRequest);
        } catch (ZApiException e) {
            e.printStackTrace();
        }
        return maintencanceAppNum;
    }

    /**
     * 如果有任何一个触发器的state处于unknown状态，则该触发器所属的监控点为未知状态；如果不支持状态的监控项没有配置触发器，则视为正常状态
     * 如果一个监控点下配置有触发器的监控项中，同时存在问题和未知2种状态，视作未知状态
     * @return
     * @throws ManagerException
     */
    @Override
    public int countUnknownApp() throws ManagerException {
        //step1:筛选监控中monitored，非维护maintenance，状态为unknownfilter: state的触发器
        List<TriggerDO> unknownTriggers = triggerManager.listUnknownTrigger();
        //step2:根据这些触发器筛选item
        List<String> triggerIds = new ArrayList<String>();
        for(TriggerDO triggerDO : unknownTriggers) {
            triggerIds.add(triggerDO.getTriggerId());
        }
        List<ItemDO> items = itemManager.listItemByTriggerIds(triggerIds);
        //step3:根据item，查询它们所属的应用集
        List<String> itemIds = new ArrayList<String>();
        for(ItemDO item : items) {
            itemIds.add(item.getItemId());
        }
        int appByItemIdsNum = countAppByItemIds(itemIds);
        return appByItemIdsNum;
    }

    /**
     * 如果有任何一个监控项的值超过了触发器的阀值，则该监控点为问题状态
     * 如果一个监控点下配置有触发器的监控项中，同时存在问题和未知2种状态，视作未知状态
     * @return
     * @throws ManagerException
     */
    @Override
    public int countProblemApp() throws ManagerException {
        //step1:筛选监控中monitored，非维护maintenance，状态为up to datefilter: state，值为problemfilter: value的触发器
        //step2:根据这些触发器筛选item
        //step3:根据item，查询它们所属的应用集
        //step4:注意：这些应用集可能会包含有未知触发器的应用集，所以需要减去两者的交集

        return 0;
    }

    /**
     *
     * @return
     * @throws ManagerException
     */
    @Override
    public int countOkApp() throws ManagerException {
        //step1:筛选所有监控中，非维护，状态为up to date，值为OK的触发器
        //step2:根据这些触发器筛选item
        //step3:根据item，查询所属应用集
        //step4:减去与未知，问题状态的应用集的交集
        return 0;
    }


}
