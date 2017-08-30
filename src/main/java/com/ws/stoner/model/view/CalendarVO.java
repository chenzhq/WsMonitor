package com.ws.stoner.model.view;

import java.util.List;

/**
 * Created by zkf on 2017/8/30.
 */
public class CalendarVO {

    private String title;
    private List<String> range;
    private List<List<Object>> datas;

    public CalendarVO() {
    }

    @Override
    public String toString() {
        return "CalendarVO{" +
                "title='" + title + '\'' +
                ", range=" + range +
                ", datas=" + datas +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public CalendarVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<String> getRange() {
        return range;
    }

    public CalendarVO setRange(List<String> range) {
        this.range = range;
        return this;
    }

    public List<List<Object>> getDatas() {
        return datas;
    }

    public CalendarVO setDatas(List<List<Object>> datas) {
        this.datas = datas;
        return this;
    }

    public CalendarVO(String title, List<String> range, List<List<Object>> datas) {

        this.title = title;
        this.range = range;
        this.datas = datas;
    }
}
