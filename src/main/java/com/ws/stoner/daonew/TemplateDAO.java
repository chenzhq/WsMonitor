package com.ws.stoner.daonew;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/11
 */
public interface TemplateDAO {

    /**
     * 获取指定 hostIds 的模板 list
     * @param hostIds
     * @return
     * @throws DAOException
     */
    List<BriefTemplateDTO> getTemplatesByHostIds(List<String> hostIds) throws DAOException;

    /**
     * 获取所有的模板 list
     * @return
     * @throws DAOException
     */
    @Cacheable
    List<BriefTemplateDTO> listAllTemplates() throws DAOException;



}
