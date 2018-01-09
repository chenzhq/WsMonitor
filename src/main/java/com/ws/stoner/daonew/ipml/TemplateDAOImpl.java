package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.template.TemplateGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.daonew.TemplateDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.dto.BriefTemplateGroupDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zhongkf on 2017/12/11
 */
@Repository
public class TemplateDAOImpl implements TemplateDAO {
    private static final Logger logger = LoggerFactory.getLogger(TemplateDAOImpl.class);
    @Autowired
    private ZApi zApi;

    /*
    私有公共方法
     */
    private List<BriefTemplateDTO> listTemplate(TemplateGetRequest request) throws DAOException {
        List<BriefTemplateDTO> templates;
        try {
            templates = zApi.Template().get(request,BriefTemplateDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                //throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询模版 {}", e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return templates;
    }
/*
zabbix api
 */
    /**
     * 获取指定 hostIds 的模板 list
     * @param hostIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefTemplateDTO> getTemplatesByHostIds(List<String> hostIds) throws DAOException {
        TemplateGetRequest templateGetRequest = new TemplateGetRequest();
        templateGetRequest.getParams()
                .setHostIds(hostIds)
                .setSelectGroups(BriefTemplateGroupDTO.PROPERTY_NAMES)
                .setOutput(BriefTemplateDTO.PROPERTY_NAMES);
        List<BriefTemplateDTO> templatesDTOS = listTemplate(templateGetRequest);
        return templatesDTOS;
    }

    /**
     * 获取所有的模板 list
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefTemplateDTO> listAllTemplates() throws DAOException {
        TemplateGetRequest templateGetRequest = new TemplateGetRequest();
        templateGetRequest.getParams()
                .setSelectGroups(BriefTemplateGroupDTO.PROPERTY_NAMES)
                .setOutput(BriefTemplateDTO.PROPERTY_NAMES);
        List<BriefTemplateDTO> templatesDTOS = listTemplate(templateGetRequest);
        return templatesDTOS;
    }


}
