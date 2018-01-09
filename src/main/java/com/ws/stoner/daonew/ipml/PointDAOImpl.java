package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.daonew.PointDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
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
public class PointDAOImpl  implements PointDAO{
    private static final Logger logger = LoggerFactory.getLogger(PointDAOImpl.class);
    @Autowired
    private ZApi zApi;
    /*
    私有公共方法
     */
    /**
     * 根据request 获取监控点数量
     * @param request
     * @return
     * @throws DAOException
     */
    private int countPoint(ApplicationGetRequest request) throws DAOException {
        int appNum ;
        try {
            appNum = zApi.Application().count(request);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                //throw new AuthExpireException(e.getMessage());
            }
            logger.error("zabbix api 查询 point count 错误 ", e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return appNum;
    }

    /**
     *
     * @return 获取监控点列表
     * @throws DAOException
     */
    private List<BriefPointDTO> listPoint(ApplicationGetRequest request) throws DAOException {
        List<BriefPointDTO> listApplication ;
        try {
            listApplication = zApi.Application().get(request,BriefPointDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                //throw new AuthExpireException(e.getMessage());
            }
            logger.error("zabbix api 查询 point list 错误", e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return listApplication;
    }

    /**
     * 获取指定 hostIds 的监控点 pointDTOS
     * @param hostIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefPointDTO> getPointDTOSByHostIds(List<String> hostIds) throws DAOException {
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams()
                .setHostIds(hostIds)
                .setSelectHost(BriefHostDTO.PROPERTY_NAMES)
                .setSelectItems(BriefItemDTO.PROPERTY_NAMES)
                .setOutput(BriefPointDTO.PROPERTY_NAMES);
        List<BriefPointDTO> points = listPoint(appRequest);
        return points;
    }

    /**
     * 获取指定 hostIds 的监控点 pointDTOS 不带 host
     * @param hostIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefPointDTO> getPointDTOSNoHostByHostIds(List<String> hostIds) throws DAOException {
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams()
                .setHostIds(hostIds)
                .setSelectItems(BriefItemDTO.PROPERTY_NAMES)
                .setOutput(BriefPointDTO.PROPERTY_NAMES);
        List<BriefPointDTO> points = listPoint(appRequest);
        return points;
    }

    /**
     * 获取指定的 pointIds 的监控点 pointDTOS
     * @param pointIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefPointDTO> getPointDTOSByPointIds(List<String> pointIds) throws DAOException {
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        List<String> sort = new ArrayList<>();
        sort.add(BriefPointDTO.PROPERTY_NAMES[1]);
        appRequest.getParams()
                .setApplicationIds(pointIds)
                .setOutput(BriefPointDTO.PROPERTY_NAMES)
                .setSortField(sort);
        List<BriefPointDTO> points = listPoint(appRequest);
        return points;
    }

    @Override
    public List<BriefPointDTO> getPointDTOSByPointIdsWithItems(List<String> pointIds) throws DAOException {
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        List<String> sort = new ArrayList<>();
        sort.add(BriefPointDTO.PROPERTY_NAMES[1]);
        appRequest.getParams()
                .setApplicationIds(pointIds)
                .setSelectHost(BriefHostDTO.PROPERTY_NAMES)
                .setSelectItems(BriefItemDTO.PROPERTY_NAMES)
                .setOutput(BriefPointDTO.PROPERTY_NAMES)
                .setSortField(sort);
        List<BriefPointDTO> points = listPoint(appRequest);
        return points;
    }

    /**
     * 获取指定的 itemIds 的监控点 pointDTOS
     * @param itemIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefPointDTO> getPointDTOSByItemIds(List<String> itemIds) throws DAOException {
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        List<String> sort = new ArrayList<>();
        sort.add(BriefPointDTO.PROPERTY_NAMES[1]);
        appRequest.getParams()
                .setItemIds(itemIds)
                .setOutput(BriefPointDTO.PROPERTY_NAMES)
                .setSortField(sort);
        List<BriefPointDTO> points = listPoint(appRequest);
        return points;
    }
}
