package com.ws.stoner.model.DO.mongo.carousel;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by zkf on 2017/9/14.
 */
public class LayoutData {
    @Field("x_size")
    private Integer xSize;
    @Field("y_size")
    private Integer ySize;
    private Integer row;
    private Integer col;

    public LayoutData() {
    }

    public LayoutData(Integer xSize, Integer ySize, Integer row, Integer col) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.row = row;
        this.col = col;
    }

    public Integer getxSize() {
        return xSize;
    }

    public LayoutData setxSize(Integer xSize) {
        this.xSize = xSize;
        return this;
    }

    public Integer getySize() {
        return ySize;
    }

    public LayoutData setySize(Integer ySize) {
        this.ySize = ySize;
        return this;
    }

    public Integer getRow() {
        return row;
    }

    public LayoutData setRow(Integer row) {
        this.row = row;
        return this;
    }

    public Integer getCol() {
        return col;
    }

    public LayoutData setCol(Integer col) {
        this.col = col;
        return this;
    }

    @Override
    public String toString() {
        return "LayoutData{" +
                "xSize=" + xSize +
                ", ySize=" + ySize +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
