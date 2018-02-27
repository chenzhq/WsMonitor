package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.itemprototype.ItemprotoGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.service.ItemProtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zhongkf on 2018/1/30
 */
@Service
public class ItemProtoServiceImpl implements ItemProtoService {

    private static final Logger logger = LoggerFactory.getLogger(ItemProtoServiceImpl.class);
    @Autowired
    private ZApi zApi;

    private List<BriefItemProtoDTO> listItemProto(ItemprotoGetRequest request) throws ServiceException {
        List<BriefItemProtoDTO> itemProtoDTOS;
        try {
            itemProtoDTOS = zApi.Itemproto().get(request,BriefItemProtoDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询监控项原型错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return itemProtoDTOS;
    }


    /**
     * 根据 hostIds 查询监控项原型 DTOS select host points triggers
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefItemProtoDTO> getItemProtoDTOSByHostIds(List<String> hostIds) throws ServiceException {
        ItemprotoGetRequest itemGetRequest = new ItemprotoGetRequest();
        List<String> sort = new ArrayList<>();
        sort.add(BriefItemDTO.PROPERTY_NAMES[1]);
        itemGetRequest.getParams()
                .setHostIds(hostIds)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setSelectApplications(BriefPointDTO.PROPERTY_NAMES)
                .setSelectApplicationPrototypes(BriefPointProtoDTO.PROPERTY_NAMES)
                .setSelectTriggers(BriefTriggerDTO.PROPERTY_NAMES)
        .setSortField(sort);
        List<BriefItemProtoDTO> itemDTOS = listItemProto(itemGetRequest);
        return itemDTOS;
    }
}
