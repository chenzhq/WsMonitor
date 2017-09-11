package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefUserGroupDTO;

import java.util.List;

/**
 * Created by zkf on 2017/8/29.
 */
public interface UsergroupService {

    /**
     * 根据 userids 获取指定的 用户组 usergroupDTO
     * @param userIds
     * @return
     * @throws ServiceException
     */
    List<BriefUserGroupDTO> getUsrgrpsByUserIds(List<String> userIds) throws ServiceException;

}
