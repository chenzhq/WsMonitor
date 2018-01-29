package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.template.TemplateGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.dto.BriefTemplateGroupDTO;
import com.ws.stoner.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by pc on 2017/6/19.
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private static final Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);
    @Autowired
    private ZApi zApi;

    private int countTemplate(TemplateGetRequest request) throws ServiceException {
        throw new NotImplementedException();
    }

    private List<BriefTemplateDTO> listTemplate(TemplateGetRequest request) throws ServiceException {
        List<BriefTemplateDTO> templates;
        try {
            templates = zApi.Template().get(request,BriefTemplateDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询模版 {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return templates;
    }

    @Override
    public List<BriefTemplateDTO> listAllTemplate() throws ServiceException {
        TemplateGetRequest templateGetRequest = new TemplateGetRequest();
        templateGetRequest.getParams()
                .setSelectGroups(BriefTemplateGroupDTO.PROPERTY_NAMES)
                .setOutput(BriefTemplateDTO.PROPERTY_NAMES);
        List<BriefTemplateDTO> templatesDTO = listTemplate(templateGetRequest);
        return templatesDTO;
    }

    /**
     * 根据给定的模板名称 查询相关模板，select ItemDTOS and PointDTOS
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefTemplateDTO> getTempByName(String[] tempNames) throws ServiceException {
        TemplateGetRequest templateGetRequest = new TemplateGetRequest();
        Map<String,String[]> tempFilter = new HashMap<>();
        tempFilter.put("name",tempNames);
        templateGetRequest.getParams()
                .setSelectApplications(BriefPointDTO.PROPERTY_NAMES)
                .setFilter(tempFilter)
                .setOutput(BriefTemplateDTO.PROPERTY_NAMES);
        List<BriefTemplateDTO> templatesDTOS = listTemplate(templateGetRequest);
        return templatesDTOS;
    }
}
