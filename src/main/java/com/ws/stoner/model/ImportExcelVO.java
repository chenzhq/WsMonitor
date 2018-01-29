package com.ws.stoner.model;

/**
 * Created by zhongkf on 2018/1/26
 */
public class ImportExcelVO {

    private String code;
    private String name;
    private String date;
    private String money;

    public String getCode() {
        return code;
    }

    public ImportExcelVO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public ImportExcelVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDate() {
        return date;
    }

    public ImportExcelVO setDate(String date) {
        this.date = date;
        return this;
    }

    public String getMoney() {
        return money;
    }

    public ImportExcelVO setMoney(String money) {
        this.money = money;
        return this;
    }
}
