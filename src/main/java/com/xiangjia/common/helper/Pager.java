package com.xiangjia.common.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 *分页对象
 * <p>完成日期：2016年6月12日</p>
 *
 * @author hj
 * @version 1.0
 */
public class Pager {
    /**
     * 默认每页数据条数
     */
    private Integer limit=10;
    /**
     * 默认偏移量
     */
    private Integer offset=0;
    /**
     * 排序条件
     */
    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    private Map<String,Object> props=new HashMap<>();

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }

    private Integer total=0;
    private List<Map<String,Object>> list=new ArrayList<>();

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
}
