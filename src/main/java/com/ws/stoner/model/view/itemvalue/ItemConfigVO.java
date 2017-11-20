package com.ws.stoner.model.view.itemvalue;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.stoner.model.dto.BriefItemDTO;

/**
 * Created by pc on 2017/10/23.
 */
public class ItemConfigVO {

    @JSONField(name = "itemid")
    private String itemId;
    @JSONField(name = "hostid")
    private String hostId;
    @JSONField(name = "pointid")
    private String pointId;

    public ItemConfigVO() {
    }

    public ItemConfigVO(String itemId, String hostId, String pointId) {
        this.itemId = itemId;
        this.hostId = hostId;
        this.pointId = pointId;
    }

    public String getItemId() {
        return itemId;
    }

    public ItemConfigVO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public ItemConfigVO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getPointId() {
        return pointId;
    }

    public ItemConfigVO setPointId(String pointId) {
        this.pointId = pointId;
        return this;
    }

    @Override
    public String toString() {
        return "ItemConfigVO{" +
                "itemId='" + itemId + '\'' +
                ", hostId='" + hostId + '\'' +
                ", pointId='" + pointId + '\'' +
                '}';
    }

    public static ItemConfigVO transformItemConfig(BriefItemDTO itemDTO) {
        if(itemDTO !=null && itemDTO.getPoints().size() != 0) {
            return new ItemConfigVO(
                    itemDTO.getItemId(),
                    itemDTO.getPoints().get(0).getHostId(),
                    itemDTO.getPoints().get(0).getPointId()
            );
        }else {
            return null;
        }

    }
}
