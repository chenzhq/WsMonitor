package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.constant.ViewTypeEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.view.GraphView;
import com.ws.stoner.model.DO.mongo.view.ProblemsView;
import com.ws.stoner.model.DO.mongo.view.StateView;
import com.ws.stoner.model.DO.mongo.view.ViewType;
import com.ws.stoner.model.view.problem.ProblemListVO;
import com.ws.stoner.model.view.statepie.StateViewVO;
import com.ws.stoner.service.ViewService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_UPDATE_SUCCESS;

/**
 * Created by zkf on 2017/9/12.
 */
@RestController
public class ViewRestController {

    @Autowired
    private ViewService viewService;

    /**
     * 监控视图 获取视图类型 下拉框
     * @return
     */
    @RequestMapping(value = "/viewtype/get_list", method = RequestMethod.GET)
    public String getViewTyes() throws ServiceException {
        List<ViewType> viewTypes = viewService.listViewType();
        return RestResultGenerator.genResult(viewTypes, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 根据 type 获取指定名称的 视图配置信息
     * @param type
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/view/get_names", method = RequestMethod.GET)
    public String getViewNameByType(@RequestParam("type") String type) throws ServiceException {
        List<GraphView> graphViews = viewService.listGraphViewsByType(type);
        List<GraphView> graphViewsVOS = new ArrayList<>();
        if(graphViews == null) {
            graphViews = new ArrayList<>();
        }
        for(GraphView graphView : graphViews) {
            GraphView graphViewVO = new GraphView(
                    graphView.getName(),
                    graphView.getType()
            );
            graphViewsVOS.add(graphViewVO);
        }
        return RestResultGenerator.genResult(graphViewsVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 根据 type 获取不同类型视图的图形数据信息
     * @param name
     * @param type
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/view/get_graph", method = RequestMethod.GET)
    public String getViewGraph(@RequestParam("name") String name,@RequestParam("type") String type) throws ServiceException {
        GraphView graphView = new GraphView(name,type);
        return getViewList(graphView);
    }

    /**
     * 添加 状态统计 视图
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view/add_state", method = RequestMethod.POST)
    public String createStateView(@RequestBody StateView stateView) throws ServiceException {
        //校验是否存在重名
        List<GraphView> graphViews = viewService.listGraphViewsByType(stateView.getType());
        boolean same = false;
        for(GraphView graphView : graphViews) {
            if(graphView.getName().equals(stateView.getName())) {
                same = true;
                break;
            }
        }
        if(same) {
            return RestResultGenerator.genResult("状态统计中已存在该名称", REST_UPDATE_SUCCESS).toString();
        }else {
            boolean success =  viewService.saveGraphView(stateView);
            if(success) {
                return getViewList(stateView);
            }else {
                return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
            }
        }
    }

    /**
     * 添加 问题视图
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view/add_problems", method = RequestMethod.POST)
    public String createProblemsView(@RequestBody ProblemsView problemsView) throws ServiceException {
        //校验是否存在重名
        List<GraphView> graphViews = viewService.listGraphViewsByType(problemsView.getType());
        boolean same = false;
        for(GraphView graphView : graphViews) {
            if(graphView.getName().equals(problemsView.getName())) {
                same = true;
                break;
            }
        }
        if(same) {
            return RestResultGenerator.genResult("问题统计中已存在该名称", REST_UPDATE_SUCCESS).toString();
        }else {
            boolean success =  viewService.saveGraphView(problemsView);
            if(success) {
                return getViewList(problemsView);
            }else {
                return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
            }
        }

    }

    /**
     * 获取 状态统计 视图的 hostIds
     * @return
     */
    @RequestMapping(value = "/stateview/get_hostids", method = RequestMethod.GET)
    public String getStateHostIds(@RequestParam("name") String name,
                                  @RequestParam("type") String type) throws ServiceException {
        StateView stateView = viewService.getGraphViewByName(name,type,StateView.class);
        List<String> hostIds = stateView.getHostIds();
        if(hostIds == null) {
            hostIds = new ArrayList<>();
        }
        return RestResultGenerator.genResult(hostIds, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 获取 问题视图 的 hostIds
     * @return
     */
    @RequestMapping(value = "/problemsview/get_hostids", method = RequestMethod.GET)
    public String getProblemsHostIds(@RequestParam("name") String name,
                                     @RequestParam("type") String type) throws ServiceException {
        ProblemsView problemsView = viewService.getGraphViewByName(name,type,ProblemsView.class);
        List<String> hostIds = problemsView.getHostIds();
        if(hostIds == null) {
            hostIds = new ArrayList<>();
        }
        return RestResultGenerator.genResult(hostIds, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 修改 状态统计 视图
     * @return
     */
    @RequestMapping(value = "/view/update_state", method = RequestMethod.POST)
    public String updateStateView(@RequestParam("old_name") String oldName,
                                  @RequestParam("new_name") String newName,
                                  @RequestParam("type") String type,
                                  @RequestParam("hostids[]") List<String> hostIds) throws ServiceException {

        StateView stateView = new StateView(newName,type,hostIds);
        //校验是否存在重名
        List<GraphView> graphViews = viewService.listGraphViewsByType(stateView.getType());
        boolean same = false;
        for(GraphView graphView : graphViews) {
            if(graphView.getName().equals(stateView.getName())) {
                same = true;
                break;
            }
        }
        if(same) {
            return RestResultGenerator.genResult("状态统计中已存在该名称", REST_UPDATE_SUCCESS).toString();
        }else {
            boolean success =  viewService.updateStateView(stateView,oldName);
            if(success) {
                return getViewList(stateView);
            }else {
                return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
            }
        }


    }

    /**
     * 修改 问题视图
     * @return
     */
    @RequestMapping(value = "/view/update_problems", method = RequestMethod.POST)
    public String updateStateView(@RequestParam("old_name") String oldName,
                                  @RequestParam("new_name") String newName,
                                  @RequestParam("type") String type,
                                  @RequestParam("hostids[]") List<String> hostIds,
                                  @RequestParam("max_num") Integer maxNum) throws ServiceException {
        ProblemsView problemsView = new ProblemsView(newName,type,hostIds,maxNum);
        //校验是否存在重名
        List<GraphView> graphViews = viewService.listGraphViewsByType(problemsView.getType());
        boolean same = false;
        for(GraphView graphView : graphViews) {
            if(graphView.getName().equals(problemsView.getName())) {
                same = true;
                break;
            }
        }
        if(same) {
            return RestResultGenerator.genResult("问题统计中已存在该名称", REST_UPDATE_SUCCESS).toString();
        }else {
            boolean success =  viewService.updateProblemsView(problemsView,oldName);
            if(success) {
                return getViewList(problemsView);
            }else {
                return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
            }
        }

    }

    /**
     * 删除 状态统计 视图
     * @return
     */
    @RequestMapping(value = "/view/delete_state", method = RequestMethod.DELETE)
    public String deleteStateView(@RequestParam("name") String name,
                                  @RequestParam("type") String type) throws ServiceException {
        StateView stateView =  viewService.deleteGraphView(name,type,StateView.class);
        if(stateView != null) {
            return getViewList(stateView);
        }else {
            return RestResultGenerator.genResult(stateView, REST_UPDATE_SUCCESS).toString();
        }
    }
    /**
     * 删除 问题视图
     * @return
     */
    @RequestMapping(value = "/view/delete_problems", method = RequestMethod.DELETE)
    public String deleteProblemsView(@RequestParam("name") String name,
                                  @RequestParam("type") String type) throws ServiceException {
        ProblemsView problemsView =  viewService.deleteGraphView(name,type,ProblemsView.class);
        if(problemsView != null) {
            return getViewList(problemsView);
        }else {
            return RestResultGenerator.genResult(problemsView, REST_UPDATE_SUCCESS).toString();
        }
    }
/*
 *轮播视图
 */
    /**
     * 获取指定展示组 的所有 展示页名称 用于下拉框展示页
     * @return
     */
    @RequestMapping(value = "/carousel/get_pages", method = RequestMethod.GET)
    public String getPageNamesBygroupName(@RequestParam("group_name") String groupName) throws ServiceException {
        List<String> names = viewService.getPageNamesByGroupNames(groupName);
        return RestResultGenerator.genResult(names, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 获取所有展示组的名称，用于展示组下拉框
     * @return
     */
    @RequestMapping(value = "/carousel/get_groups", method = RequestMethod.GET)
    public String getGroupNamesInViewPage() throws ServiceException {
        List<String> names = viewService.getAllGroupNames();
        return RestResultGenerator.genResult(names, REST_UPDATE_SUCCESS).toString();
    }


    //获取视图数据
    private String getViewList(GraphView graphView) throws ServiceException{
        if(ViewTypeEnum.STATEPIE.type.equals(graphView.getType())) {
            //获取 状态统计 视图数据
            StateViewVO stateViewVO = viewService.getStateViewByName(graphView.getName(),graphView.getType());
            return RestResultGenerator.genResult(stateViewVO, REST_UPDATE_SUCCESS).toString();
        }else if(ViewTypeEnum.PROBLEMS.type.equals(graphView.getType())) {
            //获取 问题视图 数据
            List<ProblemListVO> problemListVOS = viewService.getProblemViewByName(graphView.getName(),graphView.getType());
            return RestResultGenerator.genResult(problemListVOS, REST_UPDATE_SUCCESS).toString();
        }else if(ViewTypeEnum.APPLETREE.type.equals(graphView.getType())) {
            //待完成
        }else {

        }
        return RestResultGenerator.genResult(null, REST_UPDATE_SUCCESS).toString();
    }


}
