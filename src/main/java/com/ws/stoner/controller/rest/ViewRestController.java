package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.constant.ViewTypeEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.GraphView;
import com.ws.stoner.model.DO.mongo.ViewType;
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
    public String getViewState(@RequestParam("name") String name,@RequestParam("type") String type) throws ServiceException {
        if(ViewTypeEnum.STATEPIE.type.equals(type)) {
            StateViewVO stateViewVO = viewService.getStateViewByName(name);
            return RestResultGenerator.genResult(stateViewVO, REST_UPDATE_SUCCESS).toString();
        }else if(ViewTypeEnum.PROBLEMS.type.equals(type)) {
            List<ProblemListVO> problemListVOS = viewService.getProblemViewByName(name);
            return RestResultGenerator.genResult(problemListVOS, REST_UPDATE_SUCCESS).toString();
        }else if(ViewTypeEnum.APPLETREE.type.equals(type)) {
            //待完成
        }else {

        }
        return RestResultGenerator.genResult(null, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 添加 视图
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view/add", method = RequestMethod.POST)
    public String createGraphView(@RequestBody GraphView graphView) throws ServiceException {
        boolean success =  viewService.saveGraphView(graphView);
        if(success) {
            return getViewList(graphView);
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }

    /**
     * 获取指定视图的 hostIds
     * @return
     */
    @RequestMapping(value = "/view/get_hostids", method = RequestMethod.GET)
    public String getViewHostIds(@RequestParam("name") String name) throws ServiceException {
        GraphView graphView = viewService.getGraphViewByName(name);
        List<String> hostIds = graphView.getHostIds();
        if(hostIds == null) {
            hostIds = new ArrayList<>();
        }
        return RestResultGenerator.genResult(hostIds, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 修改 视图
     * @return
     */
    @RequestMapping(value = "/view/update", method = RequestMethod.POST)
    public String createGraphView(@RequestParam("old_name") String oldName,
                                  @RequestParam("new_name") String newName,
                                  @RequestParam("type") String type,
                                  @RequestParam("hostids[]") List<String> hostIds) throws ServiceException {
        GraphView graphView = new GraphView(newName,type,hostIds);
        boolean success =  viewService.updateGraphView(graphView,oldName);
        if(success) {
            return getViewList(graphView);
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }

    /**
     * 删除 视图
     * @return
     */
    @RequestMapping(value = "/view/delete", method = RequestMethod.DELETE)
    public String createGraphView(@RequestParam("name") String name,
                                  @RequestParam("type") String type) throws ServiceException {
        boolean success =  viewService.deleteGraphView(name);
        if(success) {
            GraphView graphView = viewService.getFirstGraphView(type);
            if(graphView != null) {
                return getViewList(graphView);
            }else{
                return RestResultGenerator.genResult(null, REST_UPDATE_SUCCESS).toString();
            }
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }


    //获取视图数据
    private String getViewList(GraphView graphView) throws ServiceException{
        if(ViewTypeEnum.STATEPIE.type.equals(graphView.getType())) {
            StateViewVO stateViewVO = viewService.getStateViewByName(graphView.getName());
            return RestResultGenerator.genResult(stateViewVO, REST_UPDATE_SUCCESS).toString();
        }else if(ViewTypeEnum.PROBLEMS.type.equals(graphView.getType())) {
            List<ProblemListVO> problemListVOS = viewService.getProblemViewByName(graphView.getName());
            return RestResultGenerator.genResult(problemListVOS, REST_UPDATE_SUCCESS).toString();
        }else if(ViewTypeEnum.APPLETREE.type.equals(graphView.getType())) {
            //待完成
        }else {

        }
        return RestResultGenerator.genResult(null, REST_UPDATE_SUCCESS).toString();
    }


}
