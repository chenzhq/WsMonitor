package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.item.ItemPresentVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class HostPresentVO {

    private String id;
    private String name;
    private String description;
    private List<ItemPresentVO> presentItems;

    public HostPresentVO() {
    }

    @Override
    public String toString() {
        return "HostPresentVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", presentItems=" + presentItems +
                '}';
    }

    public String getName() {

        return name;
    }

    public HostPresentVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public HostPresentVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<ItemPresentVO> getPresentItems() {
        return presentItems;
    }

    public HostPresentVO setPresentItems(List<ItemPresentVO> presentItems) {
        this.presentItems = presentItems;
        return this;
    }

    public String getId() {

        return id;
    }

    public HostPresentVO setId(String id) {
        this.id = id;
        return this;
    }

    public HostPresentVO(String id, String name, String description, List<ItemPresentVO> presentItems) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.presentItems = presentItems;
    }
}
