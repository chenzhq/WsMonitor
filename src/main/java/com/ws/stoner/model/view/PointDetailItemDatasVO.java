package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by pc on 2017/7/24.
 */
public class PointDetailItemDatasVO {

    @JSONField(name = "item_id")
    private String itemId;
    @JSONField(name = "item_name")
    private String itemName;
    @JSONField(name = "item_datas")
    private List<ItemDatasVO> itemDatasVOS;

    public String getItemId() {
        return itemId;
    }

    public PointDetailItemDatasVO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public PointDetailItemDatasVO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public List<ItemDatasVO> getItemDatasVOS() {
        return itemDatasVOS;
    }

    public PointDetailItemDatasVO setItemDatasVOS(List<ItemDatasVO> itemDatasVOS) {
        this.itemDatasVOS = itemDatasVOS;
        return this;
    }

    @Override
    public String toString() {
        return "PointDetailItemDatasVO{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDatasVOS=" + itemDatasVOS +
                '}';
    }

    public class ItemDatasVO {
        private String state;
        private String value;
        private String units;
        @JSONField(name = "last_time")
        private String lastTime;

        public String getState() {
            return state;
        }

        public ItemDatasVO setState(String state) {
            this.state = state;
            return this;
        }

        public String getValue() {
            return value;
        }

        public ItemDatasVO setValue(String value) {
            this.value = value;
            return this;
        }

        public String getLastTime() {
            return lastTime;
        }

        public ItemDatasVO setLastTime(String lastTime) {
            this.lastTime = lastTime;
            return this;
        }

        public String getUnits() {
            return units;
        }

        public ItemDatasVO setUnits(String units) {
            this.units = units;
            return this;
        }

        @Override
        public String toString() {
            return "ItemDatasVO{" +
                    "state='" + state + '\'' +
                    ", value='" + value + '\'' +
                    ", units='" + units + '\'' +
                    ", lastTime='" + lastTime + '\'' +
                    '}';
        }
    }
}
