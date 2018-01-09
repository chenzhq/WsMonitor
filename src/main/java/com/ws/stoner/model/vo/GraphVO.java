package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.item.ItemVO;
import com.ws.stoner.model.vo.value.ValueVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
class GraphVO {

    private String id;
    private String name;
    private String type;
    private ItemVO item;
    private PointVO point;
    private HostVO host;
    private List<ValueVO> values;

    @Override
    public String toString() {
        return "GraphVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", item=" + item +
                ", point=" + point +
                ", host=" + host +
                ", values=" + values +
                '}';
    }

    public String getId() {

        return id;
    }

    public GraphVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GraphVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public GraphVO setType(String type) {
        this.type = type;
        return this;
    }

    public ItemVO getItem() {
        return item;
    }

    public GraphVO setItem(ItemVO item) {
        this.item = item;
        return this;
    }

    public PointVO getPoint() {
        return point;
    }

    public GraphVO setPoint(PointVO point) {
        this.point = point;
        return this;
    }

    public HostVO getHost() {
        return host;
    }

    public GraphVO setHost(HostVO host) {
        this.host = host;
        return this;
    }

    public List<ValueVO> getValues() {
        return values;
    }

    public GraphVO setValues(List<ValueVO> values) {
        this.values = values;
        return this;
    }

    public GraphVO(String id, String name, String type, ItemVO item, PointVO point, HostVO host, List<ValueVO> values) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.item = item;
        this.point = point;
        this.host = host;
        this.values = values;
    }

    public GraphVO() {

    }
}
