package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.valuemap.ValuemapGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefValuemapDTO;
import com.ws.stoner.service.ValuemapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by pc on 2017/8/2.
 */
@Service
public class ValuemapServiceImpl implements ValuemapService{

    private static final Logger logger = LoggerFactory.getLogger(ValuemapServiceImpl.class);
    @Autowired
    private ZApi zApi;

    @Override
    public List<BriefValuemapDTO> listValuemap(ValuemapGetRequest request) throws ServiceException {
        List<BriefValuemapDTO> valuemaps;
        try {
            valuemaps = zApi.Valuemap().get(request,BriefValuemapDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询映射值错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return valuemaps;
    }

    /**
     * 根据 valuemapIds 查询出指定的 值映射  BriefValuemapDTO
     * @param valuemapIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefValuemapDTO> getValuemapByIds(List<String> valuemapIds) throws ServiceException {
        ValuemapGetRequest valuemapGetRequest = new ValuemapGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        valuemapGetRequest.getParams()
                .setValumapIds(valuemapIds)
                .setSelectMappings(BriefValuemapDTO.PROPERTY_MAPPINGS)
                .setOutput(BriefValuemapDTO.PROPERTY_NAMES);
        return listValuemap(valuemapGetRequest);

    }

    /**
     *
     * 根据 valuemapid 将 value 转换成 newvalue
     * @param valuemapId 值映射 id
     * @param value  映射 key
     * @return
     * @throws ServiceException
     */
    @Override
    public String getNewValueById(String valuemapId, String value) throws ServiceException {
        if("0".equals(valuemapId)) {
            return value;
        }
        String newValue = null;
        List<String> valuemapIds  = new ArrayList<>();
        valuemapIds.add(valuemapId);
        List<BriefValuemapDTO> valuemapDTOS = getValuemapByIds(valuemapIds);
        if(valuemapDTOS.size() == 0) {
            newValue = value;
        }
        BriefValuemapDTO valuemapDTO = valuemapDTOS.get(0);
        if(valuemapDTO.getMappings().size() != 0) {
            for(BriefValuemapDTO.Mapping mapping : valuemapDTO.getMappings()) {
                if(mapping.getValue().equals(value)) {
                    newValue = mapping.getNewValue();
                }
            }
        }else {
            newValue = value;
        }
        return newValue;
    }
}
