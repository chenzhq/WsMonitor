package com.ws.stoner.service;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiException;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.bean.HostDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzheqi on 2017/5/2.
 */
@Service
public class HostService {
    private static final Logger logger = LoggerFactory.getLogger(HostService.class);
    @Autowired
    private ZApi zApi;

    public List<HostDO> listHost() {
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams();
        List<HostDO> hosts = new ArrayList<>();
        try {
            hosts = zApi.Host().get(hostGetRequest).getResult();
        } catch (ZApiException e) {
            e.printStackTrace();
            logger.error("查询主机错误！{}", e.getMessage());
        }
        return hosts;
    }


}
