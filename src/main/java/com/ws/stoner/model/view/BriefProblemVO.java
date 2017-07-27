package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.stoner.model.dto.BriefHostDTO;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by chenzheqi on 2017/6/12.
 */
public class BriefProblemVO {
    @JSONField(name = "lastchange", format = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime time;
    private String priority;
    private List<BriefHostDTO> hosts;
    private String description;
    //持续时间的拼接字符串，格式为 **天**小时*分钟
    private String durationString;

    public final static String[] PROPERTY_NAMES = {"lastchange",
            "time", "priority", "description", "duration"};

    public LocalDateTime getTime() {
        return time;
    }

    public BriefProblemVO setTime(int time) {
        this.time = LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault());
        Duration duration = Duration.between(this.time, LocalDateTime.now());
        Long sec = duration.getSeconds();

        int days = (int) (sec / (24 * 3600));
        int hours = (int) (sec / 3600 % 24);
        int minute = (int) (sec / 60 % 60);
        StringBuilder timeStringBuilder = new StringBuilder();
        timeStringBuilder.append(days == 0 ? "" : days + "天");
        timeStringBuilder.append(hours == 0 ? "" : hours + "小时");
        timeStringBuilder.append(minute == 0 ? "" : minute + "分钟");
        this.durationString = timeStringBuilder.toString();
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public BriefProblemVO setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public List<BriefHostDTO> getHosts() {
        return hosts;
    }

    public BriefProblemVO setHosts(List<BriefHostDTO> hosts) {
        this.hosts = hosts;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BriefProblemVO setDescription(String description) {
        this.description = description;
        return this;
    }
    public String getDurationString() {
        return durationString;
    }
}
