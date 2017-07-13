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


    @Override
    public List<BriefHistoryDTO> listHistory(HistoryGetRequest request) throws ServiceException {
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
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHistoryDTO> getHistoryByItemId(String itemId,Integer valueType) throws ServiceException {
        HistoryGetRequest historyGetRequest = new HistoryGetRequest();
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemId);
        historyGetRequest.getParams()
                .setHistory(valueType)
                .setItemIds(itemIds)
                .setTimeFrom(((int)System.currentTimeMillis()/1000-1*24*3600))
                .setTimeTill((int)System.currentTimeMillis()/1000)
                .setOutput(BriefHistoryDTO.PROPERTY_NAMES);
        List<BriefHistoryDTO> historyDTOS = listHistory(historyGetRequest);
        return historyDTOS;
    }


}
