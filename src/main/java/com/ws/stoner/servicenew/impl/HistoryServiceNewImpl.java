package com.ws.stoner.servicenew.impl;

import com.ws.stoner.constant.ValueTypeEnum;
import com.ws.stoner.daonew.HistoryDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHistoryDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.vo.value.*;
import com.ws.stoner.servicenew.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2018/1/3
 */
@Service
public class HistoryServiceNewImpl implements HistoryService {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServiceNewImpl.class);

    @Autowired
    private HistoryDAO historyDAO;

    /**
     * 根据 itemDTO 获取默认的 40 条历史数据
     * @param itemDTO
     * @return
     */
    @Override
    public List<BriefHistoryDTO> getHistoryDTOSByItemDTO(BriefItemDTO itemDTO) {
        List<BriefHistoryDTO> historyDTOS = null;
        try {
            historyDTOS = historyDAO.getHistoryDTOSByItemDTO(itemDTO);
        } catch (DAOException e) {
            logger.error("调用 historyDAO getHistoryDTOSByItemDTO 错误",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return historyDTOS;
    }

    @Override
    public List<ValueVO> getValueVOSByHisDTO(List<BriefHistoryDTO> historyDTOS,String type) {
        List<ValueVO> valueVOS = new ArrayList<>();
        for(BriefHistoryDTO historyDTO : historyDTOS) {
            //获取监控项数据类型
            ValueVO valueVO = null;
            if(ValueTypeEnum.INTEGER.type.equals(type)) {
                valueVO = new LongValueVO(
                        historyDTO.getLastTime().atZone(ZoneId.systemDefault()).toEpochSecond()*1000,
                        Long.parseLong(historyDTO.getValue()
                        ));
            }else if(ValueTypeEnum.FLOAT.type.equals(type)) {
                valueVO = new FloatValueVO(
                        historyDTO.getLastTime().atZone(ZoneId.systemDefault()).toEpochSecond()*1000,
                        Float.parseFloat(historyDTO.getValue()
                        ));
            }else if(ValueTypeEnum.BOOLEAN.type.equals(type)) {
                valueVO = new BoolValueVO(
                        historyDTO.getLastTime().atZone(ZoneId.systemDefault()).toEpochSecond()*1000,
                        Boolean.parseBoolean(historyDTO.getValue()
                        ));
            }else {
                valueVO = new StringValueVO(
                        historyDTO.getLastTime().atZone(ZoneId.systemDefault()).toEpochSecond()*1000,
                        historyDTO.getValue()
                );
            }
            valueVOS.add(valueVO);
        }
        return valueVOS;
    }


}
