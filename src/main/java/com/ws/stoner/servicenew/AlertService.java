package com.ws.stoner.servicenew;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.model.vo.alert.AlertStatVO;
import com.ws.stoner.model.vo.alert.AlertVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/28
 */
public interface AlertService {

    /**
     * 统计某个事件的告警数量和状态
     * @param eventIds
     * @return
     */
    List<AlertStatVO> getAlertVOByEventIds(List<String> eventIds) throws ServiceException;


    /**
     * 获取指定问题 eventId 的 问题和恢复告警
     * @param eventDTO
     * @return
     */
    List<AlertVO> getAlertVOSByEventDTO(BriefEventDTO eventDTO);
}
