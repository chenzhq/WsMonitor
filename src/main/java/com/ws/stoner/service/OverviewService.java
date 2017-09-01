package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.OverViewHostVO;

import java.util.List;

/**
 * Created by pc on 2017/6/28.
 */
public interface OverviewService {


    /**
     * 递归实现组装并获取自定义分组list Overview GROUP list
     * @return
     * @throws ServiceException
     */
    List<OverviewListGroupDTO> listOverviewGroup() throws ServiceException;

    /**
     * 创建分组，overview group create
     * @return boolean true表示删除成功，false表示删除失败
     * @throws ServiceException
     */
    boolean createOverviewGroup(String newGroupName, String supGroupId) throws ServiceException;

    /**
     * 删除指定分组，并将其下所有子节点移动到上一节点中
     * @return boolean true表示删除成功，false表示删除失败
     * @throws ServiceException
     */
    boolean deleteOverviewGroup(String delGroupVOId) throws ServiceException;

    /**
     * 移动组 操作 move group
     * @param groupVOId
     * @param fromGroupVOId
     * @param toGroupVOId
     * @return boolean true表示删除成功，false表示删除失败
     * @throws ServiceException
     */
    boolean moveOverviewGroup(String groupVOId,String fromGroupVOId, String toGroupVOId) throws ServiceException;

    /**
     * 移动设备 操作 move host
     * @param hostVOId
     * @param fromGroupVOId
     * @param toGroupVOId
     * @return boolean true表示删除成功，false表示删除失败
     * @throws ServiceException
     */
    boolean moveOverviewHost(String hostVOId,String fromGroupVOId,String toGroupVOId) throws ServiceException;

    /**
     * 编辑修改分组名 service
     * @param oldGroupName
     * @param newGroupName
     * @param supGroupVOId
     * @return
     * @throws ServiceException
     */
    OverviewEditGroupDTO editOverviewGroup(String oldGroupName,String newGroupName, String supGroupVOId) throws ServiceException;


    /**
     * 在移动分组的时候需要先获取分组树供用户选择
     * @param groupName
     * @return
     * @throws ServiceException
     */
    List<OverviewListGroupDTO> getMoveGroupTree(String groupName) throws ServiceException;

    /**
     * 获取主机选择树
     * @return
     * @throws ServiceException
     */
    List<OverViewHostVO> getSelectHostVOS() throws ServiceException;




}
