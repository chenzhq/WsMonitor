package com.ws.bix4j.access.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.ZRequest;
import com.ws.bix4j.access.GetRequestCommonParam;

import java.util.List;

/**
 * Created by chen on 2017/4/1.
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
        private String selectMedias;
        @JSONField(name = "selectMediatypes")
        private String selectMediaTypes;
        @JSONField(name = "selectUsrgrps")
        private String selectUsrGrps;

        public List<String> getMediaIds() {
            return mediaIds;
        }

        public void setMediaIds(List<String> mediaIds) {
            this.mediaIds = mediaIds;
        }

        public List<String> getMediaTypeIds() {
            return mediaTypeIds;
        }

        public void setMediaTypeIds(List<String> mediaTypeIds) {
            this.mediaTypeIds = mediaTypeIds;
        }

        public List<String> getUserIds() {
            return userIds;
        }

        public void setUserIds(List<String> userIds) {
            this.userIds = userIds;
        }

        public List<String> getUserGrpIds() {
            return userGrpIds;
        }

        public void setUserGrpIds(List<String> userGrpIds) {
            this.userGrpIds = userGrpIds;
        }

        public String getGetAccess() {
            return getAccess;
        }

        public void setGetAccess(String getAccess) {
            this.getAccess = getAccess;
        }

        public String getSelectMedias() {
            return selectMedias;
        }

        public void setSelectMedias(String selectMedias) {
            this.selectMedias = selectMedias;
        }

        public String getSelectMediaTypes() {
            return selectMediaTypes;
        }

        public void setSelectMediaTypes(String selectMediaTypes) {
            this.selectMediaTypes = selectMediaTypes;
        }

        public String getSelectUsrGrps() {
            return selectUsrGrps;
        }

        public void setSelectUsrGrps(String selectUsrGrps) {
            this.selectUsrGrps = selectUsrGrps;
        }
    }


}
