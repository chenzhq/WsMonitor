package com.ws.stoner.service;

import com.ws.bix4j.access.history.HistoryGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHistoryDTO;

import java.util.List;

/**
 * Created by pc on 2017/7/13.
 */
public interface HistoryService {

    /**
     * List history list.
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefHistoryDTO> listHistory(HistoryGetRequest request) throws ServiceException;

    /**
     * 根据指定的 itemIds 获取 historys BriefHistoryDTO
     * @param itemId
     * @param valueType  指定用哪个历史数据库表，
     * @return
     * @throws ServiceException
     */
    List<BriefHistoryDTO> getHistoryByItemId(String itemId,Integer valueType) throws ServiceException;
}
