package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.BriefProblemVO;
import com.ws.stoner.model.view.DashboardHostVO;
import com.ws.stoner.model.view.DashboardPlatformVO;
import com.ws.stoner.model.view.DashboardPointVO;
import org.springframework.cache.annotation.CacheConfig;
import java.util.List;

/**
 * Created by zkf on 2017/6/12.
 */
public interface FetchBriefService {
/*
 *host service
 */

    /**
     * 组装仪表板中的业务host <DashboardHostVO>list
     * @return
     * @throws ServiceException
     */
    List<DashboardHostVO> listDashBoardHosts() throws ServiceException;


/*
 *hostgroup service
 */

    /**
     * 组装仪表板中的业务平台 platform <DashboardPlatformVO>list
     * @return
     * @throws ServiceException
     */
    List<DashboardPlatformVO> listDashboardPlatform() throws ServiceException;

/*
 *point service
 */

    /**
     * 组装仪表板中的监控点 point <DashboardPointVO>list
     * @return
     * @throws ServiceException
     */
    List<DashboardPointVO> listDashboardPoint() throws ServiceException;




}
