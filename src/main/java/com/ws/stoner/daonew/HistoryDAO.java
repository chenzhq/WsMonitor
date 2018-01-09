package com.ws.stoner.daonew;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefHistoryDTO;
import com.ws.stoner.model.dto.BriefItemDTO;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/3
 */
public interface HistoryDAO {

    /**
     * 默认获取 40条 指定 itemDTO 的历史数据
     * @param itemDTO
     * @return
     */
    List<BriefHistoryDTO> getHistoryDTOSByItemDTO(BriefItemDTO itemDTO) throws DAOException;

}
