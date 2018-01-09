package com.ws.stoner.servicenew;

import com.ws.stoner.model.dto.BriefHistoryDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.vo.value.ValueVO;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/3
 */
public interface HistoryService {

    /**
     * 根据 itemDTO 获取默认的 40 条历史数据
     * @param itemDTO
     * @return
     */
    List<BriefHistoryDTO> getHistoryDTOSByItemDTO(BriefItemDTO itemDTO) ;


    /**
     * DTO 转 VO
     * @param historyDTOS
     * @param type 为item类型，用于决定 value 转换成哪个类型的对象
     * @return
     */
    List<ValueVO> getValueVOSByHisDTO(List<BriefHistoryDTO> historyDTOS,String type) ;

}
