package com.ws.stoner.servicenew;

import com.ws.stoner.model.dto.BriefAcknowledgeDTO;
import com.ws.stoner.model.vo.ack.AckVO;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/3
 */
public interface AckService {

    AckVO getAckVOByDTO(BriefAcknowledgeDTO acknowledgeDTO);

    List<AckVO> getAckVOSByDTOS(List<BriefAcknowledgeDTO> acknowledgeDTOS);

}
