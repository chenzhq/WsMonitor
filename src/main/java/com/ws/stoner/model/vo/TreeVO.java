package com.ws.stoner.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.stoner.model.vo.tree.TreeNodeVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class TreeVO {
    @JSONField(name = "rows")
    private List<TreeNodeVO> hostTree;

    @Override
    public String toString() {
        return "HostTreeVO{" +
                "hostTree=" + hostTree +
                '}';
    }

    public List<TreeNodeVO> getHostTree() {
        return hostTree;
    }

    public TreeVO setHostTree(List<TreeNodeVO> hostTree) {
        this.hostTree = hostTree;
        return this;
    }

    public TreeVO(List<TreeNodeVO> hostTree) {

        this.hostTree = hostTree;
    }

    public TreeVO() {

    }
}
