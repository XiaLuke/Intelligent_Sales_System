package com.xf.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xf.domain.Purchasebillitem;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

/**
 */
public class PurchasebillitemQuery extends BaseQuery {

    //开始时间
    private Date beginDate;
    //结束时间
    private Date endDate;
    //状态
    private Integer status;

    //分组条件(默认供应商分组)
    private Integer groupBy = 0;
    @Override
    public Specification createSpec(){
        Date tempDate = null;
        if(endDate!=null) {
            //返回新的值，就是加一天的值
            tempDate = DateUtils.addDays(endDate, 1);
        }
        Specification<Purchasebillitem> spec = Specifications.<Purchasebillitem>and()
                .ge(beginDate!=null,"bill.vdate",beginDate)
                //注意:这里设计成小于
                .lt(tempDate!=null,"bill.vdate",tempDate)
                .eq(status!=null,"bill.status",status)
                .build();
        return spec;
    }

    //查询的参数
    private List<Object> params = new ArrayList<>();
    //获取到where条件的JPQL
    public String getWhereJpql(){
        StringBuilder jpql =  new StringBuilder();
        if(beginDate!=null){
            jpql.append(" and o.bill.vdate >= ? ");
            params.add(beginDate);
        }
        if(endDate!=null){
            //返回新的值，就是加一天的值
            Date tempDate = DateUtils.addDays(endDate, 1);
            jpql.append(" and o.bill.vdate < ? ");
            params.add(tempDate);
        }
        if(status!=null){
            jpql.append(" and o.bill.status =? ");
            params.add(status);
        }
        return jpql.toString().replaceFirst("and","where");
    }

    //获取到分组的字符串
    public String getGroupByStr(){
        if(groupBy==1){
            //根据采购员分组
            return "o.bill.buyer.username";
        }else if(groupBy==2){
            //根据月份分组
            return "MONTH(o.bill.vdate)";
        }else{
            //根据供应商分组
            return "o.bill.supplier.name";
        }
    }

    public Date getBeginDate() {
        return beginDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(Integer groupBy) {
        this.groupBy = groupBy;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }
}
