package com.ws.stoner.model.view.host;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.utils.ThresholdUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zkf on 2017/7/12.
 */
public class HostDetailItemVO {

    @JSONField(name = "item_id")
    private String itemId;
    @JSONField(name = "item_name")
    private String itemName;
    @JSONField(name = "data_time")
    private String[] dataTime;
    private Float[] data;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String units;
    @JSONField(name = "graph_name")
    private String graphName;
    @JSONField(name = "graph_type")
    private String graphType;
    private String state;
    private boolean flag;
    @JSONField(name = "value_type")
    private String valueType;
    @JSONField(name = "warning_point",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String warningPoint;
    @JSONField(name = "high_point" ,serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String highPoint;
    @JSONField(name = "with_triggers")
    private boolean withTriggers;

    @Override
    public String toString() {
        return "HostDetailItemVO{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", dataTime=" + Arrays.toString(dataTime) +
                ", data=" + Arrays.toString(data) +
                ", units='" + units + '\'' +
                ", graphName='" + graphName + '\'' +
                ", graphType='" + graphType + '\'' +
                ", state='" + state + '\'' +
                ", flag=" + flag +
                ", valueType=" + valueType +
                ", warningPoint='" + warningPoint + '\'' +
                ", highPoint='" + highPoint + '\'' +
                ", withTriggers=" + withTriggers +
                '}';
    }

    public String getValueType() {
        return valueType;
    }

    public HostDetailItemVO setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public HostDetailItemVO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public HostDetailItemVO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String[] getDataTime() {
        return dataTime;
    }

    public HostDetailItemVO setDataTime(String[] dataTime) {
        this.dataTime = dataTime;
        return this;
    }

    public Float[] getData() {
        return data;
    }

    public HostDetailItemVO setData(Float[] data) {
        this.data = data;
        return this;
    }

    public String getGraphName() {
        return graphName;
    }

    public HostDetailItemVO setGraphName(String graphName) {
        this.graphName = graphName;
        return this;
    }

    public String getGraphType() {
        return graphType;
    }

    public HostDetailItemVO setGraphType(String graphType) {
        this.graphType = graphType;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostDetailItemVO setState(String state) {
        this.state = state;
        return this;
    }

    public boolean isFlag() {
        return flag;
    }

    public HostDetailItemVO setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }

    public String getUnits() {
        return units;
    }

    public HostDetailItemVO setUnits(String units) {
        this.units = units;
        return this;
    }

    public String getWarningPoint() {
        return warningPoint;
    }

    public HostDetailItemVO setWarningPoint(String warningPoint) {
        this.warningPoint = warningPoint;
        return this;
    }

    public String getHighPoint() {
        return highPoint;
    }

    public HostDetailItemVO setHighPoint(String highPoint) {
        this.highPoint = highPoint;
        return this;
    }

    public boolean isWithTriggers() {
        return withTriggers;
    }

    public HostDetailItemVO setWithTriggers(boolean withTriggers) {
        this.withTriggers = withTriggers;
        return this;
    }

    static public HostDetailItemVO setThresholdValueForItemVO(HostDetailItemVO itemVO, String itemDTOId, List<BriefTriggerDTO> triggerDTOS ) throws ServiceException {
        itemVO.setWithTriggers(true);
        //循环triggerDTOS，筛选出属于该itemDTO的触发器，取List<String> expression,priority  ,
        for(BriefTriggerDTO triggerDTO : triggerDTOS) {
            String expression = triggerDTO.getExpression();
            String itemIdInfo = triggerDTO.getItems().get(0).getItemId();
            if(itemIdInfo.equals(itemDTOId)) {
                if(triggerDTO.getPriority() == 2) {
                    // priority为2:警告阀值取expression的逻辑比较符号后面数据；
                    itemVO.setWarningPoint(ThresholdUtils.getThresholdValue(expression));
                }else if(triggerDTO.getPriority() == 4) {
                    // priority为4:严重阀值取expression的逻辑比较符号后面数据；
                    itemVO.setHighPoint(ThresholdUtils.getThresholdValue(expression));
                }
            }
        }
        return itemVO;
    }
}
