package com.ws.bix4j.access.template;

import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.bean.HostDO;

import java.util.List;

/**
 * Created by zkf on 2017/6/16.
 */
public class TemplateGetResponse extends ZResponse {
    private List<HostDO> result;

    public TemplateGetResponse setResult(List<HostDO> result) {
        this.result = result;
        return this;
    }

    @Override
    public List<HostDO> getResult() {
        return result;
    }
}
