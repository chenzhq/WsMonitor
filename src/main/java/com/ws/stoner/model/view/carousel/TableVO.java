package com.ws.stoner.model.view.carousel;

/**
 * Created by zkf on 2017/9/14.
 */
public class TableVO extends BlockVO {

    private String htmlContents;

    public TableVO() {
    }

    public TableVO(String htmlContents) {
        this.htmlContents = htmlContents;
    }

    public String getHtmlContents() {
        return htmlContents;
    }

    public TableVO setHtmlContents(String htmlContents) {
        this.htmlContents = htmlContents;
        return this;
    }

    @Override
    public String toString() {
        return "TableVO{" +
                "htmlContents='" + htmlContents + '\'' +
                "} " + super.toString();
    }
}
