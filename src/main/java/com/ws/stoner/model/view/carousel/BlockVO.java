package com.ws.stoner.model.view.carousel;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zkf on 2017/9/14.
 */
public class BlockVO {

    @JSONField(name = "block_name")
    private String blockName;

    public String getBlockName() {
        return blockName;
    }

    public BlockVO setBlockName(String blockName) {
        this.blockName = blockName;
        return this;
    }

}
