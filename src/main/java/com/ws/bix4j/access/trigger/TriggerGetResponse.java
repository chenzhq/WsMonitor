package com.ws.bix4j.access.trigger;

import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.bean.TriggerDO;

import java.util.List;

/**
 * Created by pc on 2017/6/8.
 */
public class TriggerGetResponse extends ZResponse {

    private List<TriggerDO> result;

    @Override
    public List<TriggerDO> getResult() {
        return result;
    }

    public TriggerGetResponse setResult(List<TriggerDO> result) {
        this.result = result;
        return this;
    }
}

