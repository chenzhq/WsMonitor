package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.template.TemplateGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.service.TemplateService;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.dto.BriefTemplateGroupDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 2017/6/19.
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private static final Logger logger = LoggerFactory.getLogger(HostSerivceImpl.class);
    @Autowired
    private ZApi zApi;

    @Override
    public int countTemplate(TemplateGetRequest request) throws ServiceException {
        return 0;
    }

    @Override
    public List<BriefTemplateDTO> listTemplate(TemplateGetRequest request) throws ServiceException {
        List<BriefTemplateDTO> templates;
        try {
            templates = zApi.Template().get(request,BriefTemplateDTO.class);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new ServiceException("");
            }
            e.printStackTrace();
            logger.error("查询主机list错误！{}", e.getMessage());
            return null;
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
}
