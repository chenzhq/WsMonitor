package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.OverviewEditGroupDTO;
import com.ws.stoner.model.dto.OverviewListGroupDTO;
import com.ws.stoner.service.OverviewService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ws.stoner.constant.MessageConsts.*;



/**
 * Created by zkf on 2017/6/30.
 */
@RestController
public class OverviewRestController {

    @Autowired
    private OverviewService overviewService;

    /**
     * 列表展示
     * @return
     */
    @RequestMapping(value = "host/overview", method = RequestMethod.GET)
    public String listOverviewGroup() throws ServiceException {
        List<OverviewListGroupDTO> olg = overviewService.listOverviewGroup();
        return RestResultGenerator.genResult(olg, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 新建组
     * @param newGroupName
     * @param supGroupId
     * @return
     */
    @RequestMapping(value = "ov/group/create", method = RequestMethod.POST)
    public String createOverviewGroup(@RequestParam("new_group_name") String newGroupName,@RequestParam("sup_group") String supGroupId) throws ServiceException {
        boolean success =  overviewService.createOverviewGroup(newGroupName,supGroupId);
        if(success) {
            List<OverviewListGroupDTO> olg = overviewService.listOverviewGroup();
            return RestResultGenerator.genResult(olg, REST_CREATE_SUCCESS).toString();
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }

    /**
     * 编辑组
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "ov/group/edit", method = RequestMethod.POST)
    public String createOverviewGroup(@RequestParam("new_group_name") String newGroupName,@RequestParam("old_group_name") String oldGroupName,@RequestParam("sup_group_id") String supGroupVOId) throws ServiceException {
        OverviewEditGroupDTO oeg =  overviewService.editOverviewGroup(oldGroupName,newGroupName,supGroupVOId);
        return RestResultGenerator.genResult(oeg, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 删除组
     * @param delGroupId
     * @return
     */
    @RequestMapping(value = "ov/group/delete", method = RequestMethod.DELETE)
    public String deleteOverviewGroup(@RequestParam("del_group") String delGroupId) throws ServiceException {
        boolean success = overviewService.deleteOverviewGroup(delGroupId);
        if(success) {
            List<OverviewListGroupDTO> olg = overviewService.listOverviewGroup();
            return RestResultGenerator.genResult(olg, REST_DELETE_SUCCESS).toString();
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }

    }

    /**
     * 移动组
     * @param groupId
     * @param fromGroupId
     * @param toGroupId
     * @return
     */
    @RequestMapping(value = "ov/group/move", method = RequestMethod.PUT)
    public String moveOverviewGroup(@RequestParam("target_id") String groupId,@RequestParam("from_group") String fromGroupId,@RequestParam("to_group") String toGroupId) throws ServiceException {
       boolean success = overviewService.moveOverviewGroup(groupId,fromGroupId,toGroupId);
       if(success) {
           List<OverviewListGroupDTO> olg = overviewService.listOverviewGroup();
           return RestResultGenerator.genResult(olg, REST_MOVE_SUCCESS).toString();
       }else {
           return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
       }
    }

    /**
     * 移动设备
     * @param hostId
     * @param fromGroupId
     * @param toGroupId
     * @return
     */
    @RequestMapping(value = "ov/host/move", method = RequestMethod.PUT)
    public String moveOverviewHost(@RequestParam("target_id") String hostId,@RequestParam("from_group") String fromGroupId,@RequestParam("to_group") String toGroupId) throws ServiceException {
        boolean success = overviewService.moveOverviewHost(hostId,fromGroupId,toGroupId);
        if(success) {
            List<OverviewListGroupDTO> olg = overviewService.listOverviewGroup();
            return RestResultGenerator.genResult(olg, REST_MOVE_SUCCESS).toString();
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }

    /**
     * 在移动分组的时候需要先获取分组树供用户选择
     * @param groupName
     * @return
     */
    @RequestMapping(value = "ov/group/get_tree", method = RequestMethod.GET)
    public String getMoveGroupTree(@RequestParam("group_name") String groupName) throws ServiceException {
        List<OverviewListGroupDTO> olg = overviewService.getMoveGroupTree(groupName);
        return RestResultGenerator.genResult(olg, REST_RESPONSE_SUCCESS).toString();
    }

}
