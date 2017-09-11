package com.ws.stoner.model.dto;

/**
 * Created by zkf on 2017/8/29.
 */
public class BriefPermissionDTO {

    private String id;
    private Integer permission;

    public static final String[] PROPERTY_NAMES = {"id", "permission"};

    public String getId() {
        return id;
    }

    public BriefPermissionDTO setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getPermission() {
        return permission;
    }

    public BriefPermissionDTO setPermission(Integer permission) {
        this.permission = permission;
        return this;
    }

    @Override
    public String toString() {
        return "BriefPermissionDTO{" +
                "id='" + id + '\'' +
                ", permission=" + permission +
                '}';
    }
}
