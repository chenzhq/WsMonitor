package com.ws.stoner.model.view.carousel;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.stoner.model.DO.mongo.carousel.ConfigData;
import com.ws.stoner.model.DO.mongo.carousel.LayoutData;
import com.ws.stoner.model.DO.mongo.carousel.ViewPage;

import java.util.List;

/**
 * Created by zkf on 2017/9/14.
 */
public class PageVO {

    @JSONField(name = "page_name")
    private String pageName;
    @JSONField(name = "layout_data")
    private List<LayoutData> layoutData;
    @JSONField(name = "config_data")
    private List<ConfigData> configData;
    @JSONField(name = "block_data")
    private List<BlockVO> blockData;

    public PageVO() {
    }

    public PageVO(String pageName, List<LayoutData> layoutData, List<ConfigData> configData) {
        this.pageName = pageName;
        this.layoutData = layoutData;
        this.configData = configData;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "pageName='" + pageName + '\'' +
                ", layoutData=" + layoutData +
                ", configData=" + configData +
                ", blockData=" + blockData +
                '}';
    }

    public String getPageName() {
        return pageName;
    }

    public PageVO setPageName(String pageName) {
        this.pageName = pageName;
        return this;
    }

    public List<LayoutData> getLayoutData() {
        return layoutData;
    }

    public PageVO setLayoutData(List<LayoutData> layoutData) {
        this.layoutData = layoutData;
        return this;
    }

    public List<ConfigData> getConfigData() {
        return configData;
    }

    public PageVO setConfigData(List<ConfigData> configData) {
        this.configData = configData;
        return this;
    }

    public List<BlockVO> getBlockData() {
        return blockData;
    }

    public PageVO setBlockData(List<BlockVO> blockData) {
        this.blockData = blockData;
        return this;
    }

    public static PageVO transPageVOByViewPage(ViewPage viewPage) {
        return null;
    }

}
