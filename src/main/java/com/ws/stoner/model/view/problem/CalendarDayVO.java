package com.ws.stoner.model.view.problem;

/**
 * Created by zkf on 2017/8/30.
 */
public class CalendarDayVO {

    private String date;
    private Integer problemNum;
    private boolean lighting;

    public CalendarDayVO(String date, Integer problemNum, boolean lighting) {
        this.date = date;
        this.problemNum = problemNum;
        this.lighting = lighting;
    }

    public String getDate() {
        return date;
    }

    public CalendarDayVO setDate(String date) {
        this.date = date;
        return this;
    }

    public Integer getProblemNum() {
        return problemNum;
    }

    public CalendarDayVO setProblemNum(Integer problemNum) {
        this.problemNum = problemNum;
        return this;
    }

    public boolean isLighting() {
        return lighting;
    }

    public CalendarDayVO setLighting(boolean lighting) {
        this.lighting = lighting;
        return this;
    }
}
