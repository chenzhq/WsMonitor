package com.ws.bix4j.access.group;

import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.bean.GroupDO;
import java.util.List;
/**
 * Created by zkf on 2017/5/18.
 */
public class GroupGetResponse extends ZResponse{
    private List<GroupDO> result;

    @Override
    public List<GroupDO> getResult() {
        return result;
    }

    public GroupGetResponse setResult(List<GroupDO> result) {
        this.result = result;
        return this;
    }
}
