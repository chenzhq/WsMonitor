package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.history.HistoryGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.daonew.HistoryDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefHistoryDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zhongkf on 2018/1/3
 */
@Repository
public class HistoryDAOImpl implements HistoryDAO {

    private static final Logger logger = LoggerFactory.getLogger(HistoryDAOImpl.class);

    @Autowired
    private ZApi zApi;

    private List<BriefHistoryDTO> listHistory(HistoryGetRequest request) throws DAOException {
        List<BriefHistoryDTO> historys;
        try {
            historys = zApi.History().get(request,BriefHistoryDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                //throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询历史数据错误！{}", e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return historys;
    }

    /**
     * 默认获取 40条 指定 itemDTO 的历史数据
     * @param itemDTO
     * @return
     */
    @Override
    public List<BriefHistoryDTO> getHistoryDTOSByItemDTO(BriefItemDTO itemDTO) throws DAOException {
        HistoryGetRequest historyGetRequest = new HistoryGetRequest();
        List<String> sortFilter = new ArrayList<>();
        List<String> sortOrder = new ArrayList<>();
        sortFilter.add("clock");
        sortOrder.add("DESC");
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemDTO.getItemId());
        historyGetRequest.getParams()
                .setHistory(Integer.parseInt(itemDTO.getValueType()))
                .setItemIds(itemIds)
                .setLimit(40)
                .setSortField(sortFilter)
                .setSortOrder(sortOrder);
        List<BriefHistoryDTO> historyDTOS = listHistory(historyGetRequest);
        return historyDTOS;
    }
}
