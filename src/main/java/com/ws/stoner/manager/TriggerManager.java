package com.ws.stoner.manager;


import com.ws.bix4j.bean.TriggerDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;

import java.util.List;

/**
 * Created by zkf on 2017/6/8.
 */
public interface TriggerManager {
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
     * @throws ManagerException
     */
    List<TriggerDO> listUnknownTrigger() throws ManagerException;



}
