package com.ws.bix4j.access.valuemap;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.List;

/**
 * Created by pc on 2017/8/2.
 */
public class ValuemapGetRequest extends ZRequest<ValuemapGetRequest.Params> {

    public ValuemapGetRequest() {
        this.setMethod("valuemap.get");
    }

    private ValuemapGetRequest.Params params = new ValuemapGetRequest.Params();
    @Override
    public ValuemapGetRequest.Params getParams() {
        return params;
    }

    public class Params extends GetRequestCommonParam {
        @JSONField(name = "valuemapids")
        private List<String> valumapIds;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectMappings;

        public List<String> getValumapIds() {
            return valumapIds;
        }

        public Params setValumapIds(List<String> valumapIds) {
            this.valumapIds = valumapIds;
            return this;
        }

        public String[] getSelectMappings() {
            return selectMappings;
        }

        public Params setSelectMappings(String[] selectMappings) {
            this.selectMappings = selectMappings;
            return this;
        }
    }
}
