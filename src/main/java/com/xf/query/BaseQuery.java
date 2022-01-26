package com.xf.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * 1.减少代码量(抽取公共代码)
 * 2.规范代码
 * 3.保持扩展的灵活性
 */
public abstract class BaseQuery {
    //当前页
    private int currentPage = 1;
    //每条条数
    private int pageSize = 10;
    //排序字段名(如果前台没有传字段名，代表不需要做排序)
    private String orderName;
    //排序的规则
    private String orderType = "ASC";

    //写个抽象方法去规范子类获取Specification对象的名称必需叫:createSpec
    public abstract Specification createSpec();

    //创建排序对象
    public Sort createSort(){
        if(StringUtils.isNotBlank(orderName)){
            return new Sort(Sort.Direction.valueOf(orderType.toUpperCase()),orderName);
        }
        return null;
    }

    public int getCurrentPage() {
        return currentPage;
    }
    //第一页从0开始计算
    public int getJpaCurrentPage() {
        return currentPage-1;
    }


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    //兼容EasyUI的分页
    public void setPage(int page) {
        this.currentPage = page;
    }
    public void setRows(int rows) {
        this.pageSize = rows;
    }
}
