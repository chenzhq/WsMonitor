package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.OverviewCreateGroupDTO;
import com.ws.stoner.model.view.OverviewVO;

import java.util.List;

/**
 * Created by pc on 2017/6/28.
 */
public interface OverviewService {


    /**
     * 递归实现组装并获取自定义分组list Overview Group list
     * @return
     * @throws ServiceException
     */
    List<OverviewVO> listOverviewGroup() throws ServiceException;

    /**
     * 创建分组，overview group create
     * @return
     * @throws ServiceException
     */
    OverviewCreateGroupDTO createOverviewGroup(String newGroupName, String supGroupId) throws ServiceException;


}
