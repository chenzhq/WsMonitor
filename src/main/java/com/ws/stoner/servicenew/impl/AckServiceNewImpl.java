package com.ws.stoner.servicenew.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.constant.BaseConsts;
import com.ws.stoner.model.dto.BriefAcknowledgeDTO;
import com.ws.stoner.model.vo.ack.AckVO;
import com.ws.stoner.servicenew.AckService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2018/1/3
 */
@Service
public class AckServiceNewImpl implements AckService {
    @Override
    public AckVO getAckVOByDTO(BriefAcknowledgeDTO acknowledgeDTO) {
        String action = "";
        if(Integer.parseInt(acknowledgeDTO.getAction()) == ZApiParameter.ACKNOWLEDGE_ACTION.ACKNOWLEDGED.value) {
            action = "close";
        }
        AckVO ackVO = new AckVO(
               acknowledgeDTO.getUserId(),
               acknowledgeDTO.getAlias(),
               acknowledgeDTO.getClock().format(BaseConsts.TIME_FORMATTER),
               acknowledgeDTO.getMessage(),
               action
        );
        return ackVO;
    }

    @Override
    public List<AckVO> getAckVOSByDTOS(List<BriefAcknowledgeDTO> acknowledgeDTOS) {
        List<AckVO> ackVOS = new ArrayList<>();
        for(BriefAcknowledgeDTO acknowledgeDTO : acknowledgeDTOS) {
            AckVO ackVO = getAckVOByDTO(acknowledgeDTO);
            ackVOS.add(ackVO);
        }
        return ackVOS;
    }
}
