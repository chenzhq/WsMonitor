package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.history.HistoryGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHistoryDTO;
import com.ws.stoner.service.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by pc on 2017/7/13.
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServiceImpl.class);
    @Autowired
    private ZApi zApi;

    private List<BriefHistoryDTO> listHistory(HistoryGetRequest request) throws ServiceException {
        List<BriefHistoryDTO> historys;
        try {
            historys = zApi.History().get(request,BriefHistoryDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询历史数据错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return historys;
    }

    /**
     * 根据指定的 itemIds 获取 historys BriefHistoryDTO
     * @param itemId
     * @param valueType  指定用哪个历史数据库表，
     * @param time  指定取多少天的数据
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHistoryDTO> getHistoryByItemId(String itemId,String valueType,int time) throws ServiceException {
        HistoryGetRequest historyGetRequest = new HistoryGetRequest();
        List<String> sortFilter = new ArrayList<>();
        List<String> sortOrder = new ArrayList<>();
        sortFilter.add("clock");
        sortOrder.add("DESC");
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemId);
        historyGetRequest.getParams()
                .setHistory(Integer.parseInt(valueType))
                .setItemIds(itemIds)
                .setTimeFrom(String.valueOf(System.currentTimeMillis()/1000-time*24*3600))
                .setTimeTill(String.valueOf(System.currentTimeMillis()/1000))
                .setSortField(sortFilter)
                .setSortOrder(sortOrder);
        List<BriefHistoryDTO> historyDTOS = listHistory(historyGetRequest);
        return historyDTOS;
    }

    /**
     * 根据指定的 itemIds 获取指定条数的 historys BriefHistoryDTO
     * @param itemId
     * @param valueType  指定用哪个历史数据库表，
     * @param time  指定取多少条数据
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHistoryDTO> getHistoryByItemIdLimit(String itemId, String valueType, int time) throws ServiceException {
        List<String> sortFilter = new ArrayList<>();
        List<String> sortOrder = new ArrayList<>();
        sortFilter.add("clock");
        sortOrder.add("DESC");
        HistoryGetRequest historyGetRequest = new HistoryGetRequest();
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemId);
        historyGetRequest.getParams()
                .setHistory(Integer.parseInt(valueType))
                .setItemIds(itemIds)
                .setSortField(sortFilter)
                .setSortOrder(sortOrder)
                .setLimit(time);
        List<BriefHistoryDTO> historyDTOS = listHistory(historyGetRequest);
        return historyDTOS;
    }


}
