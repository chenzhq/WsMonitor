package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.brief.ApplicationBrief;
import com.ws.stoner.model.brief.HostGroupBrief;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.view.BriefProblemVO;
import com.ws.stoner.model.view.DashboardHostVO;
import com.ws.stoner.model.view.DashboardPlatformVO;

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
    /**
     *  获取简约所有主机list 剔除停用的
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> listHost() throws ServiceException;

    /**
     * 获取问题主机 list  problem
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> listProblemHost() throws ServiceException;

    /**
     * 获取OK主机 list OK
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> listOkHost() throws ServiceException;



/*
 * template
 */

    /**
     * 获取所有模板 list all template
     * @return
     * @throws ServiceException
     */
    List<BriefTemplateDTO> listAllTemplate() throws ServiceException;

/*
 *hostgroup service
 */

    /**
     * 组装仪表板中的业务hostgroup <DashboardPlatformVO>list
     * @return
     * @throws ServiceException
     */
    List<DashboardPlatformVO> listDashboardPlatform() throws ServiceException;

    /**
     * 获取简约 platform list all
     * @return
     * @throws ServiceException
     */
    List<BriefPlatformDTO> listPlatform() throws ServiceException;

    /**
     * 获取问题的 platform list problem
     * @return
     * @throws ServiceException
     */
    List<BriefPlatformDTO> listProblemPlatform() throws ServiceException;

/*
 *application service
 */

    /**
     * 获取简约监控点application list
     * @return
     * @throws ServiceException
     */
    List<ApplicationBrief> listApp() throws ServiceException;
/*
 *item service
 */


/*
 *trigger service
 */

    /**
     *
     * @return
     * @throws ServiceException
     */
    List<BriefProblemVO> listBriefProblems() throws ServiceException;

}
