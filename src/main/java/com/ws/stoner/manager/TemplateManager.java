package com.ws.stoner.manager;

import com.ws.bix4j.access.template.TemplateGetRequest;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by zkf on 2017/6/19.
 */
@CacheConfig(cacheNames = "templateManager")
public interface TemplateManager {
    /**
     * 根据 request 获取模板总数量
     * @return
     * @throws ManagerException
     */
    int countTemplate(TemplateGetRequest request) throws ManagerException;



    /**
     * List template list.
     *
     * @return the list
     * @throws ManagerException the auth expire exception
     */
    List<BriefTemplateDTO> listTemplate(TemplateGetRequest request) throws ManagerException;

    /**
     * 获取所有模板 list all template
     * @return
     * @throws ManagerException
     */
    @Cacheable
    List<BriefTemplateDTO> listAllTemplate() throws ManagerException;

}
