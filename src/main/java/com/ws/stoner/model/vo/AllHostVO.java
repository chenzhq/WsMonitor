package com.ws.stoner.model.vo;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/7
 */
public class AllHostVO {

    private String category;
    private List<HostVO> details;
    private Integer total;

    public AllHostVO(String category, List<HostVO> details, Integer total) {
        this.category = category;
        this.details = details;
        this.total = total;
    }

    public AllHostVO() {

    }

    public String getCategory() {
        return category;
    }

    public AllHostVO setCategory(String category) {
        this.category = category;
        return this;
    }

    public List<HostVO> getDetails() {
        return details;
    }

    public AllHostVO setDetails(List<HostVO> details) {
        this.details = details;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public AllHostVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

    @Override
    public String toString() {
        return "AllHostVO{" +
                "category='" + category + '\'' +
                ", details=" + details +
                ", total=" + total +
                '}';
    }
}
