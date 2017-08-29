package com.ws.bix4j.access.usergroup;


import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.GetRequestCommonParam;
import com.ws.bix4j.access.SelectParamSerializer;
import com.ws.bix4j.access.ZRequest;

import java.util.List;

/**
 * Created by pc on 2017/8/29.
 */
public class UsergroupGetRequest  extends ZRequest<UsergroupGetRequest.Params> {

    public UsergroupGetRequest() {
        this.setMethod("usergroup.get");
    }

    private Params params = new Params();

    @Override
    public Params getParams() {
        return params;
    }

    public UsergroupGetRequest setParams(Params params) {
        this.params = params;
        return this;
    }

    public class Params extends GetRequestCommonParam {

        /**
         * 常用参数
         */
        private Integer status;

        @JSONField(name = "userids")
        private List<String> userIds;
        @JSONField(name = "usrgrpids")
        private List<String> usrgrpIds;

        @JSONField(name = "with_gui_access")
        private Integer withGuiAccess;

        /**
         * select 参数
         */
        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectUsers;

        @JSONField(serializeUsing = SelectParamSerializer.class)
        private String[] selectRights;

        public Integer getStatus() {
            return status;
        }

        public Params setStatus(Integer status) {
            this.status = status;
            return this;
        }

        public List<String> getUserIds() {
            return userIds;
        }

        public Params setUserIds(List<String> userIds) {
            this.userIds = userIds;
            return this;
        }

        public List<String> getUsrgrpIds() {
            return usrgrpIds;
        }

        public Params setUsrgrpIds(List<String> usrgrpIds) {
            this.usrgrpIds = usrgrpIds;
            return this;
        }

        public Integer getWithGuiAccess() {
            return withGuiAccess;
        }

        public Params setWithGuiAccess(Integer withGuiAccess) {
            this.withGuiAccess = withGuiAccess;
            return this;
        }

        public String[] getSelectUsers() {
            return selectUsers;
        }

        public Params setSelectUsers(String[] selectUsers) {
            this.selectUsers = selectUsers;
            return this;
        }

        public String[] getSelectRights() {
            return selectRights;
        }

        public Params setSelectRights(String[] selectRights) {
            this.selectRights = selectRights;
            return this;
        }
    }

}
