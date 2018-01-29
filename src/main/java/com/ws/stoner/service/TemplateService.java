package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by zkf on 2017/6/19.
 */
@CacheConfig(cacheNames = "templateManager")
public interface TemplateService {

    /**
     * 获取所有模板 list all template
     * @return
     * @throws ServiceException
     */
    @Cacheable
    List<BriefTemplateDTO> listAllTemplate() throws ServiceException;

    /**
     * 根据给定的模板名称 查询相关模板，select ItemDTOS and PointDTOS
     * @return
     * @throws ServiceException
     */
    List<BriefTemplateDTO> getTempByName(String[] tempNames) throws ServiceException;

}
