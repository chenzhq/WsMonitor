package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.stoner.model.dto.BriefHostDTO;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * Created by chenzheqi on 2017/6/12.
 */
public class BriefProblemVO {
    @JSONField(name = "lastchange")
    private Instant time;
    private String priority;
    private List<BriefHostDTO> hosts;
    private String description;
    private Duration duration;

    public final static String[] PROPERTY_NAMES = {"lastchange",
            "time", "priority", "description", "duration"};

    public Instant getTime() {
        return time;
    }

    public BriefProblemVO setTime(int time) {
        this.time = Instant.ofEpochSecond(time);
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

    public Duration getDuration() {
        return duration;
    }

    public BriefProblemVO setDuration(Duration duration) {
        this.duration = duration;
        return this;
    }
}
