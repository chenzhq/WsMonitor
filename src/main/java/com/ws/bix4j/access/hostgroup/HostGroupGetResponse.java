package com.ws.bix4j.access.hostgroup;

import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.bean.HostGroupDO;

import java.util.List;
/**
 * Created by zkf on 2017/5/18.
 */
public class HostGroupGetResponse extends ZResponse {
    private List<HostGroupDO> result;

    @Override
    public List<HostGroupDO> getResult() {
        return result;
    }

    public HostGroupGetResponse setResult(List<HostGroupDO> result) {
        this.result = result;
        return this;
    }
}
