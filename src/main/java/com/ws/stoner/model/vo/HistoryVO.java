package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.value.ValueVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class HistoryVO {

    private String id;
    private String name;
    private String dataType;
    private List<ValueVO> values;
    private Integer count;

    @Override
    public String toString() {
        return "HistoryVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dataType='" + dataType + '\'' +
                ", values=" + values +
                ", count=" + count +
                '}';
    }

    public String getId() {
        return id;
    }

    public HistoryVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public HistoryVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public HistoryVO setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public List<ValueVO> getValues() {
        return values;
    }

    public HistoryVO setValues(List<ValueVO> values) {
        this.values = values;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public HistoryVO setCount(Integer count) {
        this.count = count;
        return this;
    }

    public HistoryVO(String id, String name, String dataType, List<ValueVO> values, Integer count) {

        this.id = id;
        this.name = name;
        this.dataType = dataType;
        this.values = values;
        this.count = count;
    }

    public HistoryVO() {

    }
}
