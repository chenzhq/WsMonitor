package com.ws.stoner.service;


import com.ws.bix4j.bean.TriggerDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;

import java.util.List;

/**
 * Created by zkf on 2017/6/8.
 */
public interface TriggerService {
    /**
     * List trigger list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<TriggerDO> listTrigger() throws AuthExpireException;

    /**
     * 获取监控中monitored，非维护maintenance，状态为unknown的触发器
     * @return
     * @throws ServiceException
     */
    List<TriggerDO> listUnknownTrigger() throws ServiceException;



}
