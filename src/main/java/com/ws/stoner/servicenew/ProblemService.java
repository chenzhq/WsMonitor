package com.ws.stoner.servicenew;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.vo.alert.AlertStatVO;
import com.ws.stoner.model.vo.problem.ProblemVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/28
 */
public interface ProblemService {

    /**
     * 通过triggerDTO 转 problemVO
     * @param problemDTO
     * @return
     * @throws ServiceException
     */
    ProblemVO getProblemVOByTriggerDTO(BriefTriggerDTO problemDTO, List<AlertStatVO> alertStatVOS) throws ServiceException;

    /**
     * 列出 当前所有问题列表
     * @return
     * @throws ServiceException
     */
    List<BriefTriggerDTO> listProblemVOS() throws ServiceException;

    /**
     * 根据 hostid 获取当前问题列表
     * @param hostId
     * @return
     * @throws ServiceException
     */
    List<BriefTriggerDTO> getProblemVOSByHostId(String hostId) throws ServiceException;

    /**
     * 根据 platformId 获取当前问题列表
     * @param platformId
     * @return
     * @throws ServiceException
     */
    List<BriefTriggerDTO> getProblemVOSByPlatId(String platformId) throws ServiceException;


}
