package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefHostInterfaceDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.view.host.HostDetailInterfaceVO;
import com.ws.stoner.model.view.host.HostDetailPointVO;
import com.ws.stoner.model.view.host.HostDetailVO;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.TemplateService;
import com.ws.stoner.utils.StatusConverter;
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
 * Created by chenzheqi on 2017/5/2.
 */
@Service
public class HostServiceImpl implements HostService {

    private static final Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);
    @Autowired
    private ZApi zApi;

    @Autowired
    private TemplateService templateServiceImpl;

/*
 *count host
 */

    private int countHost(HostGetRequest request) throws ServiceException {
        int allHost;
        try {
            allHost = zApi.Host().count(request);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询主机数量错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return allHost;
    }

    /**
     * 获取所有的主机列表
     * @return
     * @throws ServiceException
     */
    private List<BriefHostDTO> listHost(HostGetRequest request) throws ServiceException {
        List<BriefHostDTO> hosts;
        try {
            hosts = zApi.Host().get(request,BriefHostDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询主机错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return hosts;
    }

    /**
     * 获取主机总数量，排除停用主机，filter： status:0
     * @return
     * @throws ServiceException
     */
    @Override
    public int countAllHost() throws ServiceException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams()
                .setFilter(statusFilter)
                .setCountOutput(true);
        int allHostNum = countHost(hostGetRequest);
        return allHostNum;
    }

    /**
     * 获取告警主机数量
     * 1、根据custom_state 和 custom_available_state字段联合判断是否是问题主机：
     *  custom_state = 1，主机正常 ， 1表示警告，2表示严重
     *  custom_available_state = 0，表示根据四种接口判断是否为问题主机
     * @return
     * @throws ServiceException
     */
    @Override
    public int countWarningHost() throws ServiceException {
        //step1:根据custom_state 和 custom_available_state字段联合判断是否是问题主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String,Object> hostFilter = new HashMap<>();
        hostFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_WARNING.value);
        hostFilter.put("custom_available_state",ZApiParameter.HOST_AVAILABLE_STATE.CUSTOM_AVAILABLE_STATE_OK.value);
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setFilter(hostFilter)
                .setCountOutput(true);
        int hostWarningNum = countHost(hostGetRequest);
        return hostWarningNum;
    }

    /**
     * 获取严重主机数量
     * 1、根据custom_state 和 custom_available_state字段联合判断是否是严重主机：
     *  custom_state = 2，主机正常 ， 1表示警告，2表示严重
     *  custom_available_state = 1，表示根据四种接口判断是否为问题主机
     * @return
     * @throws ServiceException
     */
    @Override
    public int countHighHost() throws ServiceException {
        //step1:根据custom_state 和 custom_available_state字段联合判断是否是严重主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String,Object> hostFilter = new HashMap<>();
        hostFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_HIGHT.value);
        hostFilter.put("custom_available_state",ZApiParameter.HOST_AVAILABLE_STATE.CUSTOM_AVAILABLE_STATE_PROBLEM.value);
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setFilter(hostFilter)
                .setSearchByAny(true)
                .setCountOutput(true);
        int hostHighNum = countHost(hostGetRequest);
        return hostHighNum;
    }

    /**
     * 获取正常主机的数量
     * 根据custom_state 和 custom_available_state字段联合判断是否是正常主机：
     * custom_state和custom_available_state同时为0即为正常
     * @return
     * @throws ServiceException
     */
    @Override
    public int countOkHost() throws ServiceException {
        //step1:根据custom_state 和 custom_available_state字段联合判断是否是正常主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String,Object> hostFilter = new HashMap<>();
        hostFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_OK.value);
        hostFilter.put("custom_available_state",ZApiParameter.HOST_AVAILABLE_STATE.CUSTOM_AVAILABLE_STATE_OK.value);
        hostFilter.put("status",ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams()
                .setFilter(hostFilter)
                .setCountOutput(true);
        int hostOkNum = countHost(hostGetRequest);
        return hostOkNum;
    }

    /**
     * 根据指定的hostIds 统计 警告主机数量
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    @Override
    public int countWarningHostByHostIds(List<String> hostIds) throws ServiceException {
        //step1:根据custom_state 和 custom_available_state字段联合判断是否是问题主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String,Object> hostFilter = new HashMap<>();
        hostFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_WARNING.value);
        hostFilter.put("custom_available_state",ZApiParameter.HOST_AVAILABLE_STATE.CUSTOM_AVAILABLE_STATE_OK.value);
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setHostIds(hostIds)
                .setFilter(hostFilter)
                .setCountOutput(true);
        int hostWarningNum = countHost(hostGetRequest);
        return hostWarningNum;
    }

    /**
     * 根据指定的hostIds 统计 严重主机数量
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    @Override
    public int countHighHostByHostIds(List<String> hostIds) throws ServiceException {
        //step1:根据custom_state 和 custom_available_state字段联合判断是否是严重主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String,Object> hostFilter = new HashMap<>();
        hostFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_HIGHT.value);
        hostFilter.put("custom_available_state",ZApiParameter.HOST_AVAILABLE_STATE.CUSTOM_AVAILABLE_STATE_PROBLEM.value);
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setHostIds(hostIds)
                .setFilter(hostFilter)
                .setSearchByAny(true)
                .setCountOutput(true);
        int hostHighNum = countHost(hostGetRequest);
        return hostHighNum;
    }

/*
 *list host
 */

    /**
     * 获取简约所有主机list 剔除停用的
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHostDTO> listAllHost() throws ServiceException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setSelectInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setSelectApplications(BriefPointDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts  = listHost(hostGetRequest);
        return hosts;
    }

    /**
     * 获取警告主机 list  warning
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHostDTO> listWarningHost() throws ServiceException {
        //step1:step1:根据custom_state 和 custom_available_state字段联合判断是否是问题主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String,Object> hostFilter = new HashMap<>();
        hostFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_WARNING.value);
        hostFilter.put("custom_available_state",ZApiParameter.HOST_AVAILABLE_STATE.CUSTOM_AVAILABLE_STATE_OK.value);
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setSelectInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES)
                .setFilter(hostFilter);
        List<BriefHostDTO> hosts = listHost(hostGetRequest);
        return hosts;

    }

    /**
     * 获取严重主机list hight
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHostDTO> listHightHost() throws ServiceException {
        //step1:step1:根据custom_state 和 custom_available_state字段联合判断是否是问题主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String,Object> hostFilter = new HashMap<>();
        hostFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_HIGHT.value);
        hostFilter.put("custom_available_state",ZApiParameter.HOST_AVAILABLE_STATE.CUSTOM_AVAILABLE_STATE_PROBLEM.value);
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setSelectInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES)
                .setFilter(hostFilter)
                .setSearchByAny(true);
        List<BriefHostDTO> hosts = listHost(hostGetRequest);
        return hosts;
    }

    /**
     * 获取OK主机 list OK
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHostDTO> listOkHost() throws ServiceException {
        //step1:step1:根据custom_state 和 custom_available_state字段联合判断是否是正常主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String,Object> hostFilter = new HashMap<>();
        hostFilter.put("custom_state", ZApiParameter.OBJECT_STATE.CUSTOM_STATE_OK.value);
        hostFilter.put("custom_available_state",ZApiParameter.HOST_AVAILABLE_STATE.CUSTOM_AVAILABLE_STATE_OK.value);
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setSelectInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES)
                .setFilter(hostFilter);
        List<BriefHostDTO> hosts = listHost(hostGetRequest);
        return hosts;
    }

    /**
     * 根据指定的 hostids 获取 List<BriefHostDTO> list
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHostDTO> getHostsByHostIds(List<String> hostIds) throws ServiceException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setHostIds(hostIds)
                .setSelectInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setSelectApplications(BriefPointDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hostDTOS = listHost(hostGetRequest);
        return hostDTOS;
    }

    /**
     * 根据指定的 platformIds 获取 List<BriefHostDTO> list 用于 分类菜单 显示设备
     * @param platformIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHostDTO> getHostByPlatformIds(List<String> platformIds) throws ServiceException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setGroupIds(platformIds)
                .setSelectParentTemplates(BriefPointDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hostDTOS = listHost(hostGetRequest);
        return hostDTOS;
    }

    /**
     * 根据 BriefHostDTO hostDTO 组装 基本信息的 HostDetailVO
     * @return
     * @throws ServiceException
     */
    @Override
    public HostDetailVO getHostDetailByHostDTO(BriefHostDTO hostDTO) throws ServiceException {
        HostDetailVO hostDetailVO = new HostDetailVO();
        //取所有模板
        List<BriefTemplateDTO>  allTemplateDTO = templateServiceImpl.listAllTemplate();
        //step2:组装 HostDetailVO 对象，赋值：
        // hostDetail[hostid,name,state,type,ip,description],
        hostDetailVO.setHostId(hostDTO.getHostId());
        hostDetailVO.setHostName(hostDTO.getName());
        hostDetailVO.setIp(hostDTO.getInterfaces().get(0).getIp());
        hostDetailVO.setDescription(hostDTO.getDescription());
        //state
        if(StatusEnum.OK.code == hostDTO.getCustomState() && StatusEnum.OK.code == hostDTO.getCustomAvailableState()) {
            hostDetailVO.setState(StatusEnum.OK.getName());
        }else if(StatusEnum.WARNING.code == hostDTO.getCustomState() && StatusEnum.OK.code == hostDTO.getCustomAvailableState()) {
            hostDetailVO.setState(StatusEnum.WARNING.getName());
        }else {
            hostDetailVO.setState(StatusEnum.HIGH.getName());
        }
        //type
        if(hostDTO.getParentTemplates().size() != 0) {
            String DTOTemplateId = hostDTO.getParentTemplates().get(0).getTemplateId();
            for(BriefTemplateDTO template : allTemplateDTO) {
                if(template.getTemplateId().equals(DTOTemplateId)) {
                    hostDetailVO.setType(template.getTemplateGroups().get(0).getName());
                }
            }
        }
        return hostDetailVO;
    }

    /**
     * 根据 BriefHostDTO hostDTO 组装 设备下所有监控点状态信息 的 pointVO
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostDetailPointVO> getPointsByHostDTO(BriefHostDTO hostDTO) throws ServiceException {
        List<HostDetailPointVO> pointVOS = new ArrayList<>();
        // Points[pointId,name,hostId,state]
        List<BriefPointDTO> pointDTOS = hostDTO.getPoints();
        for(BriefPointDTO pointDTO : pointDTOS) {
            HostDetailPointVO pointVO = new HostDetailPointVO();
            pointVO.setName(pointDTO.getName());
            pointVO.setPointId(pointDTO.getPointId());
            pointVO.setState(StatusConverter.StatusTransform(pointDTO.getCustomState()));
            pointVOS.add(pointVO);
        }
        return pointVOS;
    }


}
