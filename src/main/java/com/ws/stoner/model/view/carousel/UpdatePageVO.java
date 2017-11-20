package com.ws.stoner.model.view.carousel;

/**
 * Created by zkf on 2017/9/25.
 */
public class UpdatePageVO {

    private String oldPageName;
    private String newPageName;
    private String groupName;

    public String getOldPageName() {
        return oldPageName;
    }

    public UpdatePageVO setOldPageName(String oldPageName) {
        this.oldPageName = oldPageName;
        return this;
    }

    public String getNewPageName() {
        return newPageName;
    }

    public UpdatePageVO setNewPageName(String newPageName) {
        this.newPageName = newPageName;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public UpdatePageVO setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
}
