package com.ws.bix4j.access.mediatype;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.List;


/**
 * Created by zkf on 2017/8/29.
 */
public class MediatypeGetRequest extends ZRequest<MediatypeGetRequest.Params> {

    public MediatypeGetRequest() {
        this.setMethod("mediatype.get");
    }

    private Params params = new Params();

    @Override
    public Params getParams() {
        return params;
    }

    public MediatypeGetRequest setParams(Params params) {
        this.params = params;
        return this;
    }

    public class Params extends GetRequestCommonParam {

        @JSONField(name = "mediatypeids")
        private List<String> mediatypeIds;
        @JSONField(name = "mediaids")
        private List<String> mediaIds;
        @JSONField(name = "userids")
        private List<String> userIds;

        /**
         * select参数 string类型的可能值为 "extend" "count"
         * list类型的值是返回对象的属性列表
         */
        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] dserviceIds;

    }
}
