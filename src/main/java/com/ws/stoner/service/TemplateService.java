package com.ws.stoner.service;

import com.ws.bix4j.access.template.TemplateGetRequest;
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
     * 根据 request 获取模板总数量
     * @return
     * @throws ServiceException
     */
    int countTemplate(TemplateGetRequest request) throws ServiceException;



    /**
     * List template list.
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefTemplateDTO> listTemplate(TemplateGetRequest request) throws ServiceException;

    /**
     * 获取所有模板 list all template
     * @return
     * @throws ServiceException
     */
    @Cacheable
    List<BriefTemplateDTO> listAllTemplate() throws ServiceException;

}
