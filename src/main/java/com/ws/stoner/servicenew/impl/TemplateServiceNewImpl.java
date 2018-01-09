package com.ws.stoner.servicenew.impl;

import com.ws.stoner.daonew.TemplateDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.servicenew.TemplateService;
import com.ws.stoner.utils.TypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2017/12/11
 */
@Service
public class TemplateServiceNewImpl implements TemplateService {

    private static final Logger logger = LoggerFactory.getLogger(TemplateServiceNewImpl.class);

    @Autowired
    private TemplateDAO templateDAO;

    /**
     * 根据 templateId 获取host type
     * @param parentTemplates
     * @param allTemplateDTOS
     * @return
     */
    @Override
    public String transformTypeBytemplateId(List<BriefTemplateDTO> parentTemplates, List<BriefTemplateDTO> allTemplateDTOS) {
        String type = "";
        if(parentTemplates == null || parentTemplates.size() == 0 || allTemplateDTOS == null) {
            return TypeConverter.transforHostType(type);
        }
        String templateId = parentTemplates.get(0).getTemplateId();
        for(BriefTemplateDTO templateDTO : allTemplateDTOS) {
            if(templateDTO.getTemplateId().equals(templateId)) {
                type = templateDTO.getTemplateGroups().get(0).getName();
            }
        }
        return TypeConverter.transforHostType(type);
    }

    /**
     * 获取指定 hostid 的 template，用于获取设备类型
     * @param hostId
     * @return
     */
    @Override
    public BriefTemplateDTO getDTOByHostId(String hostId) {
        BriefTemplateDTO templateDTO = null;
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        try {
            List<BriefTemplateDTO> templateDTOS = templateDAO.getTemplatesByHostIds(hostIds);
            if(templateDTOS != null && templateDTOS.size() > 0) {
                templateDTO = templateDTOS.get(0);
            }
        } catch (DAOException e) {
            logger.error("调用 templateDAO getTemplatesByHostIds 错误",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return templateDTO;
    }

    @Override
    public List<BriefTemplateDTO> listAllTempDTOS() {
        List<BriefTemplateDTO> templateDTOS = null;
        try {
            templateDTOS = templateDAO.listAllTemplates();
        } catch (DAOException e) {
            logger.error("调用 templateDAO 获取 allTemplateDTOS 出错",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return templateDTOS;
    }

}
