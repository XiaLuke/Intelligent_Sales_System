package com.xf.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "purchasebill")
public class Purchasebill  extends BaseDomain {


    private Date vdate;// 交易时间 -> 需要录入(时间set的时候加上@DateTimeFormat(pattern = "yyyy-MM-dd"))
    private BigDecimal totalAmount; //总金额 -> 明细计算
    private BigDecimal totalNum; //总数量 -> 明细计算
    private Date inputTime = new Date(); //录入时间 ->当前系统时间
    private Date auditorTime; //审核时间 -> 可以为空,审核时自己生成
    /**
     * 0待审,1已审，-1作废
     */
    private Integer status = 0; //单据状态 -> 默认待审
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;// 多对一，非空 供应商(需要选择)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_id")
    private Employee auditor;// 多对一，可以为空
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inputUser_id")
    private Employee inputUser;// 多对一，非空 录入人 -> 登录用户就是录入人
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buyer_id")
    private Employee buyer;// 多对一，非空 采购员 -> 需要
    // 一般组合关系使用List
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Purchasebillitem> items = new ArrayList<Purchasebillitem>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getAuditorTime() {
        return auditorTime;
    }

    public void setAuditorTime(Date auditorTime) {
        this.auditorTime = auditorTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
    }

    public Employee getInputUser() {
        return inputUser;
    }

    public void setInputUser(Employee inputUser) {
        this.inputUser = inputUser;
    }

    public Employee getBuyer() {
        return buyer;
    }

    public void setBuyer(Employee buyer) {
        this.buyer = buyer;
    }

    public List<Purchasebillitem> getItems() {
        return items;
    }

    public void setItems(List<Purchasebillitem> items) {
        this.items = items;
    }
}
