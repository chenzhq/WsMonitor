package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.daonew.PlatformDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zhongkf on 2017/12/11
 */
@Repository
public class PlatformDAOImpl implements PlatformDAO{

    private static final Logger logger = LoggerFactory.getLogger(PlatformDAOImpl.class);
    @Autowired
    private ZApi zApi;
    /*
     *私有公共方法
     */
    private List<BriefPlatformDTO> listPlatform(HostGroupGetRequest request) throws DAOException {
        List<BriefPlatformDTO> groups;
        try {
            groups = zApi.Group().get(request, BriefPlatformDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                //throw new AuthExpireException(e.getMessage());
            }
            logger.error("zabbix api 查询业务平台 错误", e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return groups;
    }
    /**
     * 获取所有的业务平台 DTOS
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefPlatformDTO> listAllPlatDTOS() throws DAOException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String , Object> groupFilter = new HashMap<>();
        groupFilter.put("internal", ZApiParameter.HOSTGROUP_INTERNAL.NOT_INTERNAL.value);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES)
                .setFilter(groupFilter);
        List<BriefPlatformDTO> platforms  = listPlatform(groupRequest);
        return platforms;
    }

    /**
     * 根据 platformId 获取 platformDTO
     * @param platformIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefPlatformDTO> getPlatByPlatIds(List<String> platformIds) throws DAOException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setGroupIds(platformIds)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> platforms  = listPlatform(groupRequest);
        return platforms;
    }
}
