package com.ws.bix4j.access.item;

import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.ZRequest;

/**
 * Created by pc on 2017/6/9.
 */
public class ItemGetRequest extends ZRequest<ItemGetRequest.Params>{
    public ItemGetRequest() {
        this.setMethod("item.get");
    }

    private Params params = new Params();

    @Override
    public Params getParams() {
        return params;
    }

    public ItemGetRequest setParams(Params params) {
        this.params = params;
        return this;
    }

    public class Params extends GetRequestCommonParam {

    }

}
