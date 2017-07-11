package com.ws.stoner.controller.rest;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
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
        List<OverviewListGroupDTO> overviewListGroupDTO;
        overviewListGroupDTO = overviewService.listOverviewGroup();
        return RestResultGenerator.genResult(overviewListGroupDTO, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 新建组
     * @param newGroupName
     * @param supGroupId
     * @return
     */
    @RequestMapping(value = "ov/group", method = RequestMethod.POST)
    public String createOverviewGroup(@RequestParam("new_group_name") String newGroupName,@RequestParam("sup_group") String supGroupId) throws ServiceException {
        OverviewCreateGroupDTO ocg = null;
        ocg =  overviewService.createOverviewGroup(newGroupName,supGroupId);
        return RestResultGenerator.genResult(ocg, REST_CREATE_SUCCESS).toString();
    }

    /**
     * 删除组
     * @param delGroupId
     * @return
     */
    @RequestMapping(value = "ov/group", method = RequestMethod.DELETE)
    public String deleteOverviewGroup(@RequestParam("del_group") String delGroupId) throws ServiceException {
        OverviewDelGroupDTO odg = null;
        odg =  overviewService.deleteOverviewGroup(delGroupId);
        return RestResultGenerator.genResult(odg, REST_DELETE_SUCCESS).toString();
    }

    /**
     * 移动组
     * @param groupId
     * @param fromGroupId
     * @param toGroupId
     * @return
     */
    @RequestMapping(value = "ov/group/move", method = RequestMethod.PUT)
    public String moveOverviewGroup(@RequestParam("group") String groupId,@RequestParam("from_group") String fromGroupId,@RequestParam("to_group") String toGroupId) throws ServiceException {
        OverviewMoveGroupDTO omg = null;
        omg =  overviewService.moveOverviewGroup(groupId,fromGroupId,toGroupId);
        return RestResultGenerator.genResult(omg, REST_MOVE_SUCCESS).toString();
    }

    /**
     * 移动设备
     * @param hostId
     * @param fromGroupId
     * @param toGroupId
     * @return
     */
    @RequestMapping(value = "ov/host/move", method = RequestMethod.PUT)
    public String moveOverviewHost(@RequestParam("host") String hostId,@RequestParam("from_group") String fromGroupId,@RequestParam("to_group") String toGroupId) throws ServiceException {
        OverviewMoveHostDTO omh = null;
        omh =  overviewService.moveOverviewHost(hostId,fromGroupId,toGroupId);
        return RestResultGenerator.genResult(omh, REST_MOVE_SUCCESS).toString();
    }

    /**
     * 在移动分组的时候需要先获取分组树供用户选择
     * @param fromGroupId
     * @return
     */
    @RequestMapping(value = "ov/group/get_tree", method = RequestMethod.GET)
    public String getMoveGroupTree(@RequestParam("from_group") String fromGroupId) throws ServiceException {
        List<OverviewListGroupDTO> olg = overviewService.getMoveGroupTree(fromGroupId);
        return RestResultGenerator.genResult(olg, REST_MOVE_SUCCESS).toString();
    }

}
