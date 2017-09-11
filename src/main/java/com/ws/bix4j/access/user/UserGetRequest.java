package com.ws.bix4j.access.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.List;

/**
 * Created by chen on 2017/4/1.
 * Ref: https://www.zabbix.com/documentation/3.2/manual/api/reference/user/get
 */
public class UserGetRequest extends ZRequest {

    private Params params = new Params();

    public UserGetRequest() {
        this.setMethod("user.get");
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }


    public class Params extends GetRequestCommonParam {

        @JSONField(name = "mediaids")
        private List<String> mediaIds;
        @JSONField(name = "mediatypeids")
        private List<String> mediaTypeIds;
        @JSONField(name = "userids")
        private List<String> userIds;
        @JSONField(name = "usrgrpids")
        private List<String> userGrpIds;
        private String getAccess;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectMedias;
        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectMediatypes;
        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectUsrgrps;

        public List<String> getMediaIds() {
            return mediaIds;
        }

        public Params setMediaIds(List<String> mediaIds) {
            this.mediaIds = mediaIds;
            return this;
        }

        public List<String> getMediaTypeIds() {
            return mediaTypeIds;
        }

        public Params setMediaTypeIds(List<String> mediaTypeIds) {
            this.mediaTypeIds = mediaTypeIds;
            return this;
        }

        public List<String> getUserIds() {
            return userIds;
        }

        public Params setUserIds(List<String> userIds) {
            this.userIds = userIds;
            return this;
        }

        public List<String> getUserGrpIds() {
            return userGrpIds;
        }

        public Params setUserGrpIds(List<String> userGrpIds) {
            this.userGrpIds = userGrpIds;
            return this;
        }

        public String getGetAccess() {
            return getAccess;
        }

        public Params setGetAccess(String getAccess) {
            this.getAccess = getAccess;
            return this;
        }

        public String[] getSelectMedias() {
            return selectMedias;
        }

        public Params setSelectMedias(String[] selectMedias) {
            this.selectMedias = selectMedias;
            return this;
        }

        public String[] getSelectMediatypes() {
            return selectMediatypes;
        }

        public Params setSelectMediatypes(String[] selectMediatypes) {
            this.selectMediatypes = selectMediatypes;
            return this;
        }

        public String[] getSelectUsrgrps() {
            return selectUsrgrps;
        }

        public Params setSelectUsrgrps(String[] selectUsrgrps) {
            this.selectUsrgrps = selectUsrgrps;
            return this;
        }
    }


}
