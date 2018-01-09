package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.daonew.ItemDAO;
import com.ws.stoner.exception.DAOException;
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
 * Created by zhongkf on 2017/12/11
 */
@Repository
public class ItemDAOImpl implements ItemDAO {
    private static final Logger logger = LoggerFactory.getLogger(ItemDAOImpl.class);

    @Autowired
    private ZApi zApi;

    /**
     * 根据request 获取 所有的item
     * @return
     * @throws DAOException
     */
    private List<BriefItemDTO> listItem(ItemGetRequest request) throws DAOException {
        List<BriefItemDTO> items ;
        try {
            items = zApi.Item().get(request,BriefItemDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                //throw new AuthExpireException(e.getMessage());
            }
            logger.error("zabbix api 查询监控项错误！{}", e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return items;
    }

    /**
     * 根据 platformIds 获取附带有触发器的 items BriefItemDTO 用于获取健康值
     * @param platformIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefItemDTO> getCoreItemByPlatIds(List<String> platformIds) throws DAOException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        List<String> sort = new ArrayList<>();
        sort.add(BriefItemDTO.PROPERTY_NAMES[1]);
        itemGetRequest.getParams()
                .setMonitored(true)
                .setGroupIds(platformIds)
                .setWithTriggers(true)
                .setOutput(BriefItemDTO.PROPERTY_NAMES)
                .setSortField(sort);
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }

    @Override
    public List<BriefItemDTO> getItemDTOByHostIds(List<String> hostIds) throws DAOException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        List<String> sort = new ArrayList<>();
        sort.add(BriefItemDTO.PROPERTY_NAMES[1]);
        itemGetRequest.getParams()
                .setMonitored(true)
                .setHostIds(hostIds)
                .setOutput(BriefItemDTO.PROPERTY_NAMES)
                .setSortField(sort);
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }

    @Override
    public List<BriefItemDTO> getCoreItemByHostIds(List<String> hostIds) throws DAOException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        List<String> sort = new ArrayList<>();
        sort.add(BriefItemDTO.PROPERTY_NAMES[1]);
        itemGetRequest.getParams()
                .setMonitored(true)
                .setHostIds(hostIds)
                .setWithTriggers(true)
                .setOutput(BriefItemDTO.PROPERTY_NAMES)
                .setSortField(sort);
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }

    /**
     * 根据 pointIds查询 itemDTOS
     * @param pointIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefItemDTO> getItemDTOSByPointIds(List<String> pointIds) throws DAOException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        List<String> sort = new ArrayList<>();
        sort.add(BriefItemDTO.PROPERTY_NAMES[1]);
        itemGetRequest.getParams()
                .setMonitored(true)
                .setApplicationIds(pointIds)
                .setOutput(BriefItemDTO.PROPERTY_NAMES)
                .setSortField(sort );
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }

    @Override
    public List<BriefItemDTO> getItemDTOSByItemIds(List<String> itemIds) throws DAOException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        List<String> sort = new ArrayList<>();
        sort.add(BriefItemDTO.PROPERTY_NAMES[1]);
        itemGetRequest.getParams()
                .setMonitored(true)
                .setItemIds(itemIds)
                .setOutput(BriefItemDTO.PROPERTY_NAMES)
                .setSortField(sort );
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }

}
