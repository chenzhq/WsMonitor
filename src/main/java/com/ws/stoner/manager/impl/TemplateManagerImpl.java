package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.template.TemplateGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.manager.TemplateManager;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 2017/6/19.
 */
@Service
public class TemplateManagerImpl implements TemplateManager {

    private static final Logger logger = LoggerFactory.getLogger(HostManagerImpl.class);
    @Autowired
    private ZApi zApi;

    @Override
    public int countTemplate(TemplateGetRequest request) throws ManagerException {
        return 0;
    }

    @Override
    public List<BriefTemplateDTO> listTemplate(TemplateGetRequest request) throws AuthExpireException {
        List<BriefTemplateDTO> templates;
        try {
            templates = zApi.Template().get(request,BriefTemplateDTO.class);
        } catch (ZApiException e) {
            e.printStackTrace();
            return null;
        }
        return templates;
    }
}
