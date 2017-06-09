package com.ws.bix4j.access.item;

import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.bean.ItemDO;

import java.util.List;

/**
 * Created by pc on 2017/6/9.
 */
public class ItemGetResponse extends ZResponse {

    private List<ItemDO> result;

    @Override
    public List<ItemDO> getResult() {
        return result;
    }

    public ItemGetResponse setResult(List<ItemDO> result) {
        this.result = result;
        return this;
    }
}
