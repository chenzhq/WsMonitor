package com.ws.bix4j.access;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2017/4/1.
 */
public abstract class GetRequestCommonParam {
    private String countOutput;
    private boolean editable;
    private String excludeSearch;
    private Map filter;
    private int limit;
    private String output;
    @JSONField(name = "preservekeys")
    private String preserveKeys;
    private Map search;
    private boolean searchByAny;
    private boolean searchWildcardsEnabled;
    @JSONField(name = "sortfield")
    private List<String> sortField;
    @JSONField(name = "sortorder")
    private List<String> sortOrder;
    private String startSearch;

    /**
     * 是否返回记录数 flag
     *
     * @return string
     */
    public String isCountOutput() {
        return countOutput;
    }

    /**
     * 设置 是否返回记录数，而不是详情 flag <br>
     * null／不设置 => false <br>
     * 任意值 => true
     *
     * @param countOutput output
     */
    public void setCountOutput(String countOutput) {
        this.countOutput = countOutput;
    }

    /**
     * 是否只返回当前用户有"写"权限的对象 <br>
     * 默认 false，只返回有"读"权限的对象
     *
     * @return boolean
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * 设置是否返回当前用户有"写"权限的对象 <br>
     *     默认 false，只返回有"读"权限的对象 <br>
     *     true, 返回有"写"权限的对象
     *
     * @param editable
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * 返回结果是否不满足Search中的通配符 flag <br>
     *
     * @return String flag
     */
    public String isExcludeSearch() {
        return excludeSearch;
    }

    /**
     * 设置返回不满足Search参数下通配符的结果 flag
     *
     * @param excludeSearch flag
     */
    public void setExcludeSearch(String excludeSearch) {
        this.excludeSearch = excludeSearch;
    }

    /**
     * get筛选条件
     *
     * @return the filter
     */
    public Map getFilter() {
        return filter;
    }

    /**
     * 设置筛选条件：只返回满足这些条件的结果 <br>
     * 不能用于类型为text的字段
     *
     * @param filter Map key：属性名 value：单值／多个值的数组
     */
    public void setFilter(Map filter) {
        this.filter = filter;
    }

    /**
     * Gets 限制返回的结果数.
     *
     * @return 限制数
     */
    public int getLimit() {
        return limit;
    }

    /**
     * 限制返回的结果数
     *
     * @param limit 限制数
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Gets output.
     *
     * @return the output
     */
    public String getOutput() {
        return output;
    }

    /**
     * 限制返回对象的属性 query 可以有以下几种值：<br>
     * 1.extend 所有 <br>
     * 2.count 检索的数量，用于确定的子查询中 <br>
     * 3.属性名的数组 String[]
     *
     * @param output the output
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * 是否将数组转换为对象
     *
     * @return the string
     */
    public String isPreserveKeys() {
        return preserveKeys;
    }

    /**
     * 将返回的数组转换为对象，key是每个对象的ID
     *
     * @param preserveKeys the preserve keys
     */
    public void setPreserveKeys(String preserveKeys) {
        this.preserveKeys = preserveKeys;
    }

    /**
     * 返回查询通配符
     *
     * @return Map key：属性名 value：查询字符串 默认"%…%"
     */
    public Map getSearch() {
        return search;
    }

    /**
     * 设置查询通配符
     *
     * @param search Map key：属性名 value：查询字符串 默认"%…%"
     */
    public void setSearch(Map search) {
        this.search = search;
    }

    /**
     * 返回的结果只需满足Search或filter的任一条件
     *
     * @return boolean 默认false
     */
    public boolean isSearchByAny() {
        return searchByAny;
    }

    /**
     * 返回的结果只需满足Search或filter的任一条件
     *
     * @param searchByAny 默认false
     */
    public void setSearchByAny(boolean searchByAny) {
        this.searchByAny = searchByAny;
    }

    /**
     * search字段中是否允许使用*作为通配符
     *
     * @return true=>允许 false=>不允许
     */
    public boolean isSearchWildcardsEnabled() {
        return searchWildcardsEnabled;
    }

    /**
     * search字段中允许使用*作为通配符
     *
     * @param searchWildcardsEnabled 默认false
     */
    public void setSearchWildcardsEnabled(boolean searchWildcardsEnabled) {
        this.searchWildcardsEnabled = searchWildcardsEnabled;
    }

    /**
     * 获取排序的字段
     *
     * @return List<String> 排序字段list
     */
    public List<String> getSortField() {
        return sortField;
    }

    /**
     * 设置排序字段，需要确定该对象哪些字段允许排序 <br>
     * 宏在排序前不会展开
     *
     * @param sortField List<String> 排序字段list
     */
    public void setSortField(List<String> sortField) {
        this.sortField = sortField;
    }

    /**
     * 获取排序的顺序
     *
     * @return List<String> 排序字段的顺序list
     */
    public List<String> getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置排序的顺序（升序／降序）<br>
     * 如果是数组，则每个值为sortField数组中对应属性的排序顺序
     *
     * @param sortOrder List<String> 排序字段的顺序list
     */
    public void setSortOrder(List<String> sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 是否只匹配search开头部分
     *
     * @return the string
     */
    public String isStartSearch() {
        return startSearch;
    }

    /**
     * Search字段只匹配开始部分，类似于 LIKE "...%" flag
     *
     * @param startSearch flag
     */
    public void setStartSearch(String startSearch) {
        this.startSearch = startSearch;
    }
}
