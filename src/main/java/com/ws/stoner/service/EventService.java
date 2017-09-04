package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefAcknowledgeDTO;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.model.query.CalendarFormQuery;
import com.ws.stoner.model.view.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by pc on 2017/8/22.
 */
public interface EventService {

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

    /**
     * 根据 eventid 获取判断 事件的确认操作是否可以 关闭问题
     * @param eventId
     * @param userId  用于判断 Super Admin OR 所属用户群组 对 Trigger所属主机的主机群组 有读写权限（3）
     * @return
     * @throws ServiceException
     */
    AcknowledgeCheckboxVO getCheckboxVOByEventId(String eventId,String userId) throws ServiceException;

    /**
     * 根据 acknowledgeDTO 执行确认动作
     * @param acknowledgeDTO
     * @return AcknowledgeVO  eventids[] 影响的事件 events
     * @throws ServiceException
     */
    AcknowledgeVO acknowledgeEvent(BriefAcknowledgeDTO acknowledgeDTO) throws ServiceException;

    /**
     * 根据指定的 triggerId 组装 问题详情中 详情事件列表 和 时序属性 组合成的对象 ProblemDetailDatasVO
     * @param triggerId
     * @return
     * @throws ServiceException
     */
    ProblemDetailDatasVO getDetailDatasVOSByTriggerId(String triggerId) throws ServiceException;

    /**
     * 根据 eventId 组装 事件详情弹出框 事件细节信息 EventDetailVO
     * @param eventId
     * @return
     * @throws ServiceException
     */
    EventDetailVO getEventDetailByEventId(String eventId) throws ServiceException;

    /**
     * 组装当天一天的 问题事件数量 ProblemListVOS
     * @param formQuery
     * @return
     * @throws ServiceException
     */
    List<ProblemListVO> getOneDayProblemListVOS(CalendarFormQuery formQuery) throws ServiceException;


}
