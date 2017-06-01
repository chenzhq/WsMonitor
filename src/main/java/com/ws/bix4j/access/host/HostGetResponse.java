package com.ws.bix4j.access.host;

import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.bean.HostDO;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/2.
 */
public class HostGetResponse extends ZResponse {
    private List<HostDO> result;
    private  int countHost;

    public int getCountHost() {
        return countHost;
    }

    public void setCountHost(int countHost) {
        this.countHost = countHost;
    }

    public HostGetResponse setResult(List<HostDO> result) {
        this.result = result;
        return this;
    }

    @Override
    public List<HostDO> getResult() {
        return result;
    }
}
