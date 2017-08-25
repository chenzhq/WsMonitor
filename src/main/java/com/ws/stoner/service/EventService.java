package com.ws.stoner.service;

import com.ws.bix4j.access.event.EventGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.model.view.ProblemAcknowledgeVO;
import com.ws.stoner.model.view.ProblemListVO;

import java.util.List;

/**
 * Created by pc on 2017/8/22.
 */
public interface EventService {

    /**
     * List event list.
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefEventDTO> listEvent(EventGetRequest request) throws ServiceException;

    /**
     * 根据 eventIds 查询所有指定的事件 BriefEventDTO
     * @param eventIds
     * @return
     * @throws ServiceException
     */
    List<BriefEventDTO> getEventByEventId(List<String> eventIds) throws ServiceException;

    /**
     * 获取指定时间区间内 的 所有事件  all eventDTOS
     * @param beginTime  秒数 字符串
     * @param endTime  秒数 字符串
     * @param triggerIds  用来过滤依赖关系的 event
     * @return
     * @throws ServiceException
     */
    List<BriefEventDTO> getAllEventsByTime(String beginTime,String endTime,List<String> triggerIds ) throws ServiceException;

    /**
     * 获取指定时间区间内 的 恢复事件  recovery eventDTOS
     * @param beginTime  秒数 字符串
     * @param endTime  秒数 字符串
     * @param triggerIds  用来过滤依赖关系的 event
     * @return
     * @throws ServiceException
     */
    List<BriefEventDTO> getRecoveryEventsByTime(String beginTime,String endTime,List<String> triggerIds ) throws ServiceException;

    /**
     * 获取指定时间区间内 的 问题事件 problem eventDTOS
     * @param beginTime  秒数 字符串
     * @param endTime  秒数 字符串
     * @param triggerIds  用来过滤依赖关系的 event
     * @return
     * @throws ServiceException
     */
    List<BriefEventDTO> getProblemEventsByTime(String beginTime,String endTime,List<String> triggerIds ) throws ServiceException;


 /*
  *问题管理模块
   */


    /**
     * 根据时间区间 获取 历史记录  问题列表 List<ProblemListVO>
     * @param beginTime
     * @param end_time
     * @return
     * @throws ServiceException
     */
    List<ProblemListVO> getHistoryProblemsByTime(String beginTime,String end_time) throws ServiceException;

    /**
     * 根据指定的 eventid 获取 问题列表中 确认记录 pop
     * @param eventId
     * @return
     * @throws ServiceException
     */
    List<ProblemAcknowledgeVO> getAcknowledgeVOSByEventId(String eventId) throws ServiceException;


}
