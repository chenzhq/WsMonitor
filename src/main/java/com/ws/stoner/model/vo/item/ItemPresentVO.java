package com.ws.stoner.model.vo.item;

/**
 * Created by zhongkf on 2017/12/8
 */
public class ItemPresentVO {

    private String id;
    private Integer index;
    private String name;
    private String alias;
    private String value;
    private String unit;

    @Override
    public String toString() {
        return "ItemPresentVO{" +
                "id='" + id + '\'' +
                ", index=" + index +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }

    public String getId() {

        return id;
    }

    public ItemPresentVO setId(String id) {
        this.id = id;
        return this;
    }

    public ItemPresentVO(String id, Integer index, String name, String alias, String value, String unit) {

        this.id = id;
        this.index = index;
        this.name = name;
        this.alias = alias;
        this.value = value;
        this.unit = unit;
    }

    public ItemPresentVO() {

    }
}
