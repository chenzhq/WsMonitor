package com.ws.stoner.controller;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.HostDetailInterfaceVO;
import com.ws.stoner.model.view.HostDetailPointVO;
import com.ws.stoner.model.view.HostDetailVO;
import com.ws.stoner.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by pc on 2017/7/13.
 */
@Controller
@RequestMapping("/hostDetail")
public class HostDetailController {

    @Autowired
    private HostService hostService;

    @RequestMapping(value = {"/", ""})
    public ModelAndView detailHost(@RequestParam("host_id") String hostId) throws ServiceException {
        //step1:根据 hostId 取对应 BreifHostDTO，单个 hostDTO
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        BriefHostDTO hostDTO = hostService.getHostsByHostIds(hostIds).get(0);
        //新建DetailHostVO对象,ItemVO对象 List,PointVO对象list,InterfaceVO对象
        HostDetailVO hostDetailVO = hostService.getHostDetailByHostDTO(hostDTO);
        HostDetailInterfaceVO interfaceVO = hostService.getHostInterfaceByHostDTO(hostDTO);
        List<HostDetailPointVO> pointVOS = hostService.getPointsByHostDTO(hostDTO);
        ModelAndView mav = new ModelAndView("hostdetail");
        mav.addObject("hostDetailVO", hostDetailVO );
        mav.addObject("interfaceVO", interfaceVO );
        mav.addObject("pointVOS", pointVOS );
        return mav;
    }

}
