package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.OverviewVO;

import java.util.List;

/**
 * Created by pc on 2017/6/28.
 */
public interface OverviewService {


    /**
     * 递归实现组装自定义分组 mongoGroup list
     * @return
     * @throws ServiceException
     */
    List<OverviewVO> listOverviewGroup() throws ServiceException;


}
