package com.ws.stoner.servicenew.impl;

import com.ws.stoner.daonew.TriggerDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.servicenew.TriggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2018/1/3
 */
@Service
public class TriggerServiceNewImpl implements TriggerService {

    private static final Logger logger = LoggerFactory.getLogger(TriggerServiceNewImpl.class);

    @Autowired
    private TriggerDAO triggerDAO;

    @Override
    public BriefTriggerDTO getDTOByTriId(String triggerId) {
        List<String> triggerIds = new ArrayList<>();
        BriefTriggerDTO proTri = null;
        triggerIds.add(triggerId);
        try {
            List<BriefTriggerDTO> proTris = triggerDAO.getTriggersByTriggerIds(triggerIds);
            if(proTris.size() > 0) {
                proTri = proTris.get(0);
            }
        } catch (DAOException e) {
            logger.error("调用 triggerDAO getTriggersByTriggerIds 错误！" ,e.getMessage());
            new ServiceException(e.getMessage());
        }
        return proTri;
    }
}
