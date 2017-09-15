package com.ws.stoner.model.view.itemvalue;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.stoner.model.dto.BriefHistoryDTO;
import com.ws.stoner.model.view.carousel.BlockVO;
import com.ws.stoner.utils.ThresholdUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zkf on 2017/9/14.
 */
public class ItemTimeData extends BlockVO{

    private Float[] data;
    @JSONField(name = "data_time")
    private String[] dataTime;
    private String units;

    public Float[] getData() {
        return data;
    }

    public ItemTimeData setData(Float[] data) {
        this.data = data;
        return this;
    }

    public String[] getDataTime() {
        return dataTime;
    }

    public ItemTimeData setDataTime(String[] dataTime) {
        this.dataTime = dataTime;
        return this;
    }

    public String getUnits() {
        return units;
    }

    public ItemTimeData setUnits(String units) {
        this.units = units;
        return this;
    }

    public static ItemTimeData transformByHistoryDTOS(List<BriefHistoryDTO> historyDTOS, String units) {
        ItemTimeData timeData = new ItemTimeData();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss");
        List<Float> data = new ArrayList<>();
        List<String> dataTime = new ArrayList<>();
        //赋值 取list BriefHistory的 valueList 给 date，lastTimeList 给 data_time，
        for(BriefHistoryDTO historyDTO : historyDTOS) {
            Map<String,String> valueUnits = ThresholdUtils.transformGraphValue(historyDTO.getValue(),units);
            timeData.setUnits(valueUnits.entrySet().iterator().next().getKey());
            data.add(Float.parseFloat(valueUnits.entrySet().iterator().next().getValue()));
            String dataTimeString = historyDTO.getLastTime().format(formatter);
            dataTime.add(dataTimeString);
        }
        timeData.setData(data.toArray(new Float[0]));
        timeData.setDataTime(dataTime.toArray(new String[0]));
        return timeData;
    }
}
