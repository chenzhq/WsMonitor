package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.PlatformGraph;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.view.*;

import java.util.List;

/**
 * Created by pc on 2017/7/18.
 */
public interface GraphService {

    /*
    设备详情业务方法
     */
    /**
     * 根据 valueType 查询出对应支持的图形插件名称
     * @param valueType
     * @return
     * @throws ServiceException
     */
    List<String> getGraphTypeByValueTypeFromMongo(String valueType) throws ServiceException;

    /**
     * 根据 hostIds 查询出指定设备的 图形监控项 graph item
     * @param hostId
     * @return
     * @throws ServiceException
     */
    List<HostDetailItemVO> getGraphItemByHostId(String hostId) throws ServiceException;

    /**
     * 根据 itemId 获取 HostDetailItemGraphVO 类型对象 获取指定 监控项图形 配置参数
     * @param itemId
     * @return
     * @throws ServiceException
     */
    HostDetailItemGraphVO getGraphItemByItemId(String itemId) throws ServiceException;

    /**
     * 根据 pointId 查询出指定 point 的 图形监控项 graph item
     * @param pointId
     * @return
     * @throws ServiceException
     */
    List<HostDetailItemVO> getGraphItemByPointId(String pointId,int time) throws ServiceException;

    /*
    业务树
     */

    /**
     * 根据 platformId 组装页面 业务树  PlatformTreeVO 渲染
     * @param platformId
     * @return
     * @throws ServiceException
     */
    PlatformTreeVO getPlatTreeByPlatformId(String platformId) throws ServiceException;

    /**
     * 初始化业务树 并返回可视化业务树对象 PlatformTreeVO
     * @return
     * @throws ServiceException
     */
    List<PlatformTreeVO> initPlatTree() throws ServiceException;

    /**
     * 根据 platformTreeVO 保存业务树
     * @param updateVO
     * @return
     * @throws ServiceException
     */
    boolean updatePlatformTree(PlatformTreeUpdateVO updateVO) throws ServiceException;

    /**
     * 创建集群
     * @param platformId
     * @param clusterId
     * @param clusterName
     * @return
     * @throws ServiceException
     */
    boolean createCluster(String platformId,String clusterId,String clusterName) throws ServiceException;

    /**
     * 删除集群
     * @param platformId
     * @param clusterId
     * @return
     * @throws ServiceException
     */
    boolean deleteCluster(String platformId,String clusterId) throws ServiceException;

    /**
     * 根据 hostIds 获取业务平台监控项图形数据 PlatformGraphVO list
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<PlatformGraphVO> getPlatformGraphsByhostIds(List<String> hostIds) throws ServiceException;

    /**
     * 根据 platformId 获取业务平台监控项图形数据 platformGraphVO list
     * @param platformId
     * @return
     * @throws ServiceException
     */
    List<PlatformGraphVO> getPlatformGraphsByPlatformId(String platformId) throws ServiceException;

    /**
     * 保存业务图形报告
     * @param platformGraph
     * @return
     * @throws ServiceException
     */
    boolean savePlatformGraph(PlatformGraph platformGraph) throws ServiceException;

    /**
     * 根据 itemId 获取指定 HostDetailItemGraphVO 配置
     * @param itemId
     * @return
     * @throws ServiceException
     */
    HostDetailItemGraphVO getUpdatePlatformGraph(String itemId) throws ServiceException;

    /**
     * 更新 业务图形报告
     * @param platformGraph
     * @return
     * @throws ServiceException
     */
    boolean updatePlatformGraph(PlatformGraph platformGraph) throws ServiceException;

    /**
     * 删除 业务图形报告
     * @param itemId
     * @return
     * @throws ServiceException
     */
    boolean deletePlatformGraph(String itemId) throws ServiceException;
}
