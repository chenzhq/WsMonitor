package com.ws.stoner.model.DO.mongo.carousel;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by zkf on 2017/9/13.
 */
@Document(collection = "view_page")
public class ViewPage {
    @Id
    private String id;
    @Field("page_name")
    @JSONField(name = "page_name")
    private String pageName;
    @Field("group_name")
    @JSONField(name = "group_name")
    private String groupName;
    @Field("layout_data")
    @JSONField(name = "layout_data")
    private List<LayoutData> layoutDataList;
    @Field("config_data")
    @JSONField(name = "config_data")
    private List<ConfigData> configDataList;

    public ViewPage() {
    }

    public ViewPage( String pageName, String groupName, List<LayoutData> layoutDataList, List<ConfigData> configDataList) {
        this.pageName = pageName;
        this.groupName = groupName;
        this.layoutDataList = layoutDataList;
        this.configDataList = configDataList;
    }

    public String getId() {
        return id;
    }

    public ViewPage setId(String id) {
        this.id = id;
        return this;
    }

    public String getPageName() {
        return pageName;
    }

    public ViewPage setPageName(String pageName) {
        this.pageName = pageName;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public ViewPage setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public List<LayoutData> getLayoutDataList() {
        return layoutDataList;
    }

    public ViewPage setLayoutDataList(List<LayoutData> layoutDataList) {
        this.layoutDataList = layoutDataList;
        return this;
    }

    public List<ConfigData> getConfigDataList() {
        return configDataList;
    }

    public ViewPage setConfigDataList(List<ConfigData> configDataList) {
        this.configDataList = configDataList;
        return this;
    }

    @Override
    public String toString() {
        return "ViewPage{" +
                "id='" + id + '\'' +
                ", pageName='" + pageName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", layoutDataList=" + layoutDataList +
                ", configDataList=" + configDataList +
                '}';
    }




}
