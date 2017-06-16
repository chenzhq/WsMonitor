package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.brief.ApplicationBrief;
import com.ws.stoner.model.brief.HostBrief;
import com.ws.stoner.model.brief.HostGroupBrief;
import com.ws.stoner.model.view.BriefProblemVO;

import java.util.List;

/**
 * Created by zkf on 2017/6/12.
 */
public interface FetchBriefService {
/*
 *host service
 */
    /**
     *  获取简约所有主机list 剔除停用的
     * @return
     * @throws ServiceException
     */
    List<HostBrief> listHost() throws ServiceException;

    /**
     * 获取问题主机 list  problem
     * @return
     * @throws ServiceException
     */
    List<HostBrief> listProblemHost() throws ServiceException;

    /**
     * 获取OK主机 list OK
     * @return
     * @throws ServiceException
     */
    List<HostBrief> listOkHost() throws ServiceException;

/*
 *hostgroup service
 */

    /**
     * 获取简约 platform list
     * @return
     * @throws ServiceException
     */
    List<HostGroupBrief> listPlatform() throws ServiceException;

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
