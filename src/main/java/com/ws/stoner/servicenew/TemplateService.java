package com.ws.stoner.servicenew;

import com.ws.stoner.model.dto.BriefTemplateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/7
 */
@Service
public interface TemplateService {
    /**
     * 根据 templateId 获取host type
     * @param parentTemplates
     * @param allTemplateDTOS
     * @return
     */
    String transformTypeBytemplateId(List<BriefTemplateDTO> parentTemplates,List<BriefTemplateDTO> allTemplateDTOS) ;

    /**
     * 获取指定 hostid 的 template，用于获取设备类型
     * @param hostId
     * @return
     */
    BriefTemplateDTO getDTOByHostId(String hostId);

    List<BriefTemplateDTO> listAllTempDTOS();
}
