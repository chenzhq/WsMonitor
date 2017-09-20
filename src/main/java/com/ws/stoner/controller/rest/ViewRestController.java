package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.constant.ViewTypeEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.carousel.*;
import com.ws.stoner.model.DO.mongo.view.GraphView;
import com.ws.stoner.model.DO.mongo.view.ProblemsView;
import com.ws.stoner.model.DO.mongo.view.StateView;
import com.ws.stoner.model.DO.mongo.view.ViewType;
import com.ws.stoner.model.dto.BriefAcknowledgeDTO;
import com.ws.stoner.model.query.EditViewForm;
import com.ws.stoner.model.view.carousel.PageVO;
import com.ws.stoner.model.view.problem.ProblemListVO;
import com.ws.stoner.model.view.statepie.StateViewVO;
import com.ws.stoner.service.ViewService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 获取 轮播配置列表
     * @param
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/carouseltype/get_list", method = RequestMethod.GET)
    public String getCarouselType() throws ServiceException {
        List<CarouselType> carouselTypes = viewService.listCarouselType();
        List<CarouselType> carouselTypeVOS = new ArrayList<>();
        if(carouselTypes == null) {
            return RestResultGenerator.genResult(null, REST_UPDATE_SUCCESS).toString();
        }
        for(CarouselType carouselType : carouselTypes) {
            CarouselType carouselTypeVO = new CarouselType(
                    carouselType.getName(),
                    carouselType.getType()
            );
            carouselTypeVOS.add(carouselTypeVO);
        }
        return RestResultGenerator.genResult(carouselTypeVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 获取 控件配置项 列表
     * @param
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/charttype/get_list", method = RequestMethod.GET)
    public String getChartTypes() throws ServiceException {
        List<ChartType> chartTypes = viewService.listChartType();
        List<ChartType> chartTypeVOS = new ArrayList<>();
        if(chartTypes == null) {
            return RestResultGenerator.genResult(null, REST_UPDATE_SUCCESS).toString();
        }
        for(ChartType chartType : chartTypes) {
            ChartType chartTypeVO = new ChartType(
                    chartType.getName(),
                    chartType.getType()
            );
            chartTypeVOS.add(chartTypeVO);
        }
        return RestResultGenerator.genResult(chartTypeVOS, REST_UPDATE_SUCCESS).toString();
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
    public String updateStateView(@RequestBody EditViewForm editViewForm) throws ServiceException {

        StateView stateView = new StateView(
                editViewForm.getNewName(),
                editViewForm.getType(),
                editViewForm.getHostIds());
        boolean success =  viewService.updateStateView(stateView,editViewForm.getOldName());
        if(success) {
            return getViewList(stateView);
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }



    }

    /**
     * 修改 问题视图
     * @return
     */
    @RequestMapping(value = "/view/update_problems", method = RequestMethod.POST)
    public String updateProblemsView( @RequestBody EditViewForm editViewForm ) throws ServiceException {
        ProblemsView problemsView = new ProblemsView(
                editViewForm.getNewName(),
                editViewForm.getType(),
                editViewForm.getHostIds(),
                editViewForm.getMaxNum());
        boolean success =  viewService.updateProblemsView(problemsView,editViewForm.getOldName());
        if(success) {
            return getViewList(problemsView);
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }

    /**
     * 删除 问题视图
     * @return
     */
    @RequestMapping(value = "/view/delete", method = RequestMethod.GET)
    public String deleteProblemsView(@RequestParam("name") String name,
                                  @RequestParam("type") String type) throws ServiceException {
        boolean success =  viewService.deleteGraphView(name,type);
        return RestResultGenerator.genResult(success, REST_UPDATE_SUCCESS).toString();

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

    /**
     * 保存一个 viewpage 配置  添加页面 功能
     * @return
     */
    @RequestMapping(value = "/carousel/add_page", method = RequestMethod.POST)
    public String createViewPage(@RequestParam("page_name") String pageName,
                                 @RequestParam("group_name") String groupName) throws ServiceException {
        ViewPage viewPage = new ViewPage(
                pageName,
                groupName,
                new ArrayList<>(),
                new ArrayList<>()
        );
        boolean success = viewService.saveViewPage(viewPage);
        if(success) {
            PageVO pageVO = viewService.getPageVOByPageName(pageName);
            return RestResultGenerator.genResult(pageVO, REST_UPDATE_SUCCESS).toString();
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }

    /**
     * 保存一个 viewpage 配置 修改保存 功能
     * @return
     */
    @RequestMapping(value = "/carousel/save_page", method = RequestMethod.POST)
    public String UpdateViewPage(@RequestParam("page_name") String pageName,
                                 @RequestParam("group_name") String groupName,
                                 @RequestParam("config_data") ConfigData[] configData,
                                 @RequestParam("layout_data") LayoutData[] layoutData) throws ServiceException {
        ViewPage viewPage = new ViewPage(
                pageName,
                groupName,
                Arrays.asList(layoutData),
                Arrays.asList(configData)
        );
        boolean success = viewService.saveViewPage(viewPage);
        if(success) {
            PageVO pageVO = viewService.getPageVOByPageName(pageName);
            return RestResultGenerator.genResult(pageVO, REST_UPDATE_SUCCESS).toString();
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }

    /**
     *   轮播配置  根据 pageName， 获取页面 布局 渲染 相关数据
     * @return
     */
    @RequestMapping(value = "/carousel/get_data", method = RequestMethod.GET)
    public String getPageData(@RequestParam("page_name") String pageName) throws ServiceException {
        PageVO pageVO = viewService.getPageVOByPageName(pageName);
        return RestResultGenerator.genResult(pageVO, REST_UPDATE_SUCCESS).toString();
    }

    /**
     *   轮播配置  根据 pageName， 删除展示页
     * @return
     */
    @RequestMapping(value = "/carousel/delete_page", method = RequestMethod.GET)
    public String deleteViewPageByPageName(@RequestParam("page_name") String pageName) throws ServiceException {
        boolean success = viewService.deleteViewPageByPageName(pageName);
        return RestResultGenerator.genResult(success, REST_UPDATE_SUCCESS).toString();
    }

    /**
     *   轮播配置  根据 pageName， 获取 布局 & 配置 页面对象
     * @return
     */
    @RequestMapping(value = "/carousel/get_edit", method = RequestMethod.GET)
    public String getViewPageByPageName(@RequestParam("page_name") String pageName) throws ServiceException {
        ViewPage viewPage = viewService.getViewPageByPageName(pageName);
        if(viewPage != null) {
            return RestResultGenerator.genResult(viewPage, REST_UPDATE_SUCCESS).toString();
        }
        return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
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
