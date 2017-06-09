package com.ws.bix4j.access.application;

import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.bean.ApplicationDO;

import java.util.List;

/**
 * Created by pc on 2017/6/7.
 */
public class ApplicationGetResponse  extends ZResponse {
    private List<ApplicationDO> result;

    @Override
    public List<ApplicationDO> getResult() {
        return result;
    }

    public ApplicationGetResponse setResult(List<ApplicationDO> result) {
        this.result = result;
        return this;
    }
}
