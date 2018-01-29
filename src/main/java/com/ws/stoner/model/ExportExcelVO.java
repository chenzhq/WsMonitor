package com.ws.stoner.model;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/29
 */
public class ExportExcelVO {

    private String tempName;
    private List<ExportPointVO> points;

    public String getTempName() {
        return tempName;
    }

    public ExportExcelVO setTempName(String tempName) {
        this.tempName = tempName;
        return this;
    }

    public List<ExportPointVO> getPoints() {
        return points;
    }

    public ExportExcelVO setPoints(List<ExportPointVO> points) {
        this.points = points;
        return this;
    }
}
