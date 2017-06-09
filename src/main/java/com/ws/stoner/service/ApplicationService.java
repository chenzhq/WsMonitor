package com.ws.stoner.service;

import com.ws.bix4j.bean.ApplicationDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
public interface ApplicationService {
    /**
     * List applications list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<ApplicationDO> listApplication() throws AuthExpireException;

    /**
     * 统计停用的监控点
     * @return停用的监控点数量
     * @throws ServiceException
     */
    int countDisableApp() throws ServiceException;

    /**
     * 统计维护中的监控点
     * @return 维护的监控点数量
     * @throws ServiceException
     */
    int countMaintenanceApp() throws ServiceException;

    /**
     * 统计未知的监控点
     * @return 未知的监控点数量
     * @throws ServiceException
     */
    int countUnknownApp() throws ServiceException;

    /**
     * 统计危险的监控点
     * @return 危险的监控点数量
     * @throws ServiceException
     */
    int countProblemApp() throws ServiceException;

    /**
     * 统计正常的监控点
     * @return 正常的监控点数量
     * @throws ServiceException
     */
    int countOkApp() throws  ServiceException;



}
