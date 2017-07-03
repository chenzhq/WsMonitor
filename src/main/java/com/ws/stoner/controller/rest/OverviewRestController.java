package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.OverviewCreateGroupDTO;
import com.ws.stoner.model.dto.OverviewDelGroupDTO;
import com.ws.stoner.model.dto.OverviewMoveGroupDTO;
import com.ws.stoner.model.dto.OverviewMoveHostDTO;
import com.ws.stoner.model.view.OverviewVO;
import com.ws.stoner.service.OverviewService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_MONGOLIST_SUCCESS;


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
    public String listOverviewGroup() {
        List<OverviewVO> overviewVo;
        try {
            overviewVo = overviewService.listOverviewGroup();
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(overviewVo, REST_MONGOLIST_SUCCESS).toString();
    }

    /**
     * 新建组
     * @param newGroupName
     * @param supGroupId
     * @return
     */
    @RequestMapping(value = "vo/group", method = RequestMethod.POST)
    public String createOverviewGroup(@RequestParam("new_group_name") String newGroupName,@RequestParam("sup_group") String supGroupId) {
        OverviewCreateGroupDTO ocg = null;
        try {
            ocg =  overviewService.createOverviewGroup(newGroupName,supGroupId);
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(ocg, REST_MONGOLIST_SUCCESS).toString();
    }

    /**
     * 删除组
     * @param delGroupId
     * @return
     */
    @RequestMapping(value = "vo/group", method = RequestMethod.DELETE)
    public String deleteOverviewGroup(@RequestParam("del_group") String delGroupId) {
        OverviewDelGroupDTO odg = null;
        try {
            odg =  overviewService.deleteOverviewGroup(delGroupId);
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(odg, REST_MONGOLIST_SUCCESS).toString();
    }

    /**
     * 移动组
     * @param groupId
     * @param fromGroupId
     * @param toGroupId
     * @return
     */
    @RequestMapping(value = "vo/group/move", method = RequestMethod.PUT)
    public String moveOverviewGroup(@RequestParam("group") String groupId,@RequestParam("from_group") String fromGroupId,@RequestParam("to_group") String toGroupId) {
        OverviewMoveGroupDTO omg = null;
        try {
            omg =  overviewService.moveOverviewGroup(groupId,fromGroupId,toGroupId);
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(omg, REST_MONGOLIST_SUCCESS).toString();
    }

    /**
     * 移动组
     * @param hostId
     * @param fromGroupId
     * @param toGroupId
     * @return
     */
    @RequestMapping(value = "vo/host/move", method = RequestMethod.PUT)
    public String moveOverviewHost(@RequestParam("host") String hostId,@RequestParam("from_group") String fromGroupId,@RequestParam("to_group") String toGroupId) {
        OverviewMoveHostDTO omh = null;
        try {
            omh =  overviewService.moveOverviewHost(hostId,fromGroupId,toGroupId);
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(omh, REST_MONGOLIST_SUCCESS).toString();
    }


}
