package com.ws.stoner.model.view;

import java.util.List;

/**
 * Created by zkf on 2017/8/30.
 */
public class CalendarVO {

    private String title;
    private List<String> range;
    private List<List<Object>> data;

    public CalendarVO() {
    }

    @Override
    public String toString() {
        return "CalendarVO{" +
                "title='" + title + '\'' +
                ", range=" + range +
                ", data=" + data +
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

    public List<List<Object>> getData() {
        return data;
    }

    public CalendarVO setData(List<List<Object>> data) {
        this.data = data;
        return this;
    }

    public CalendarVO(String title, List<String> range, List<List<Object>> data) {

        this.title = title;
        this.range = range;
        this.data = data;
    }
}
