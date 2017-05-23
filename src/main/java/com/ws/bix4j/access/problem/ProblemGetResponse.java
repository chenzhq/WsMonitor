package com.ws.bix4j.access.problem;

import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.bean.ProblemDO;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/22.
 */
public class ProblemGetResponse extends ZResponse {
    private List<ProblemDO> result;

    public ProblemGetResponse setResult(List<ProblemDO> result) {
        this.result = result;
        return this;
    }

    @Override
    public List<ProblemDO> getResult() {
        return result;
    }
}
