package com.ws.stoner.manager;

import com.ws.bix4j.bean.ApplicationDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
public interface ApplicationManager {
    /**
     * List applications list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<ApplicationDO> listApplication() throws AuthExpireException;

    /**
     * 获取 itemid 在给定的ItemIds list 中的所有application
     * @return
     * @throws ManagerException
     */
    List<ApplicationDO> listAppByItemIds(List<String> itemIds) throws ManagerException;

    /**
     * 统计 itemid 在给定的ItemIds list 中所有application 的数量
     * @param itemIds
     * @return
     * @throws ManagerException
     */
    int countAppByItemIds(List<String> itemIds) throws ManagerException;

    /**
     * 统计停用的监控点
     * @return停用的监控点数量
     * @throws ManagerException
     */
    int countDisableApp() throws ManagerException;

    /**
     * 统计维护中的监控点
     * @return 维护的监控点数量
     * @throws ManagerException
     */
    int countMaintenanceApp() throws ManagerException;

    /**
     * 统计未知的监控点
     * @return 未知的监控点数量
     * @throws ManagerException
     */
    int countUnknownApp() throws ManagerException;

    /**
     * 统计危险的监控点
     * @return 危险的监控点数量
     * @throws ManagerException
     */
    int countProblemApp() throws ManagerException;

    /**
     * 统计正常的监控点
     * @return 正常的监控点数量
     * @throws ManagerException
     */
    int countOkApp() throws ManagerException;



}
