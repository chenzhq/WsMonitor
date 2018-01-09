package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.daonew.HostDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
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
public class HostDAOImpl implements HostDAO {
    private static final Logger logger = LoggerFactory.getLogger(HostDAOImpl.class);
    @Autowired
    private ZApi zApi;
    /*
     *count host
     */
    private int countHost(HostGetRequest request) throws DAOException {
        int allHost;
        try {
            allHost = zApi.Host().count(request);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                //throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询主机数量错误！{}", e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return allHost;
    }

    /**
     * 获取所有的主机列表
     * @return
     * @throws ServiceException
     */
    private List<BriefHostDTO> listHost(HostGetRequest request) throws DAOException {
        List<BriefHostDTO> hosts;
        try {
            hosts = zApi.Host().get(request,BriefHostDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                //throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询主机错误！{}", e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return hosts;
    }

    @Override
    public int countAllHosts() throws DAOException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams()
                .setFilter(statusFilter)
                .setCountOutput(true);
        int allHostNum = countHost(hostGetRequest);
        return allHostNum;
    }

    @Override
    public List<BriefHostDTO> listAllHosts() throws DAOException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setSelectApplications(BriefPointDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts  = listHost(hostGetRequest);
        return hosts;
    }

    /**
     *  获取简约所有主机list 不包含points
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefHostDTO> listAllHostsNoPoints() throws DAOException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts  = listHost(hostGetRequest);
        return hosts;
    }

    /**
     *  获取简约所有主机list 不包含points
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefHostDTO> listAllHostsWithPlatform() throws DAOException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setSelectGroups(BriefPlatformDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts  = listHost(hostGetRequest);
        return hosts;
    }

    /**
     * 根据 hostIds 获取 hostDTOS
     * @param hostIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefHostDTO> getHostsByHostIds(List<String> hostIds) throws DAOException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setHostIds(hostIds)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hostDTOS = listHost(hostGetRequest);
        return hostDTOS;
    }

    @Override
    public List<BriefHostDTO> getHostsByHostIdsWithPoint(List<String> hostIds) throws DAOException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setHostIds(hostIds)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setSelectApplications(BriefPointDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hostDTOS = listHost(hostGetRequest);
        return hostDTOS;
    }

    /**
     * 根据 platformIds 查询所有的 hostDTOS 带 point 和 platform
     * @param platformIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefHostDTO> getHostsByPlatIds(List<String> platformIds) throws DAOException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setGroupIds(platformIds)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setSelectGroups(BriefPlatformDTO.PROPERTY_NAMES)
                .setSelectApplications(BriefPointDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hostDTOS = listHost(hostGetRequest);
        return hostDTOS;
    }

    @Override
    public List<BriefHostDTO> getHostsByPlatIdsWithOutPoint(List<String> platformIds) throws DAOException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setGroupIds(platformIds)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setSelectGroups(BriefPlatformDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hostDTOS = listHost(hostGetRequest);
        return hostDTOS;
    }
}
