package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHistoryDTO;

import java.util.List;

/**
 * Created by pc on 2017/7/13.
 */
public interface HistoryService {

    /**
     * 根据指定的 itemIds 获取 historys BriefHistoryDTO
     * @param itemId
     * @param valueType  指定用哪个历史数据库表，
     * @param time  指定取多少天的数据
     * @return
     * @throws ServiceException
     */
    List<BriefHistoryDTO> getHistoryByItemId(String itemId,String valueType,int time) throws ServiceException;

    /**
     * 根据指定的 itemIds 获取指定条数的 historys BriefHistoryDTO
     * @param itemId
     * @param valueType  指定用哪个历史数据库表，
     * @param time  指定取多少条数据
     * @return
     * @throws ServiceException
     */
    List<BriefHistoryDTO> getHistoryByItemIdLimit(String itemId,String valueType,int time) throws ServiceException;
}
