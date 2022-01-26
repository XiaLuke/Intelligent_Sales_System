package com.xf.common;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 代表是返回前端的分页对象
 */
public class UIPage<T> {

    //总条数
    private Long total;
    //当前页数据
    private List<T> rows;

    public UIPage() {}
    public UIPage(Page page) {
        this.total = page.getTotalElements();
        this.rows = page.getContent();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
