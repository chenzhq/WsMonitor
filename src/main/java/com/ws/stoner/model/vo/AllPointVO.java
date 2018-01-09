package com.ws.stoner.model.vo;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class AllPointVO {
    private String category;
    private List<PointVO> details;
    private Integer total;

    @Override
    public String toString() {
        return "AllPointVO{" +
                "category='" + category + '\'' +
                ", details=" + details +
                ", total=" + total +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public AllPointVO setCategory(String category) {
        this.category = category;
        return this;
    }

    public List<PointVO> getDetails() {
        return details;
    }

    public AllPointVO setDetails(List<PointVO> details) {
        this.details = details;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public AllPointVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public AllPointVO(String category, List<PointVO> details, Integer total) {

        this.category = category;
        this.details = details;
        this.total = total;
    }

    public AllPointVO() {

    }
}
