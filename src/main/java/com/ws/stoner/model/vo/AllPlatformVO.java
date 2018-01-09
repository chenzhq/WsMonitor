package com.ws.stoner.model.vo;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/7
 */
public class AllPlatformVO {

    private String category;
    private List<PlatformVO> details;
    private Integer total;

    public AllPlatformVO() {
    }

    public AllPlatformVO(String category, List<PlatformVO> details, Integer total) {
        this.category = category;
        this.details = details;
        this.total = total;
    }

    public String getCategory() {
        return category;
    }

    public AllPlatformVO setCategory(String category) {
        this.category = category;
        return this;
    }

    public List<PlatformVO> getDetails() {
        return details;
    }

    public AllPlatformVO setDetails(List<PlatformVO> details) {
        this.details = details;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public AllPlatformVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

    @Override
    public String toString() {
        return "AllPlatformVO{" +
                "category='" + category + '\'' +
                ", details=" + details +
                ", total=" + total +
                '}';
    }
}
