package com.ws.bix4j.access.hostInterface;

import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.bean.HostInterfaceDO;

import java.util.List;

/**
 * Created by zkf on 2017/5/18.
 */
public class HostInterfaceGetResponse extends ZResponse {
    private List<HostInterfaceDO> result;

    @Override
    public List<HostInterfaceDO> getResult() {
        return result;
    }

    public HostInterfaceGetResponse setResult(List<HostInterfaceDO> result) {
        this.result = result;
        return this;
    }
}
