package com.xf.domain;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product  extends BaseDomain {


    private String name;

    private String color;

    private String pic;

    private String smallpic;
    //成本价
    private BigDecimal costprice;
    //销售价格
    private BigDecimal saleprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    private Producttype types;// 对应的二级产品类型
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Systemdictionarydetail unit;// 数据字典明细：单位
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Systemdictionarydetail brand;// 数据字典明细：品牌



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSmallpic() {
        return smallpic;
    }

    public void setSmallpic(String smallpic) {
        this.smallpic = smallpic;
    }

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }

    public BigDecimal getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    public Producttype getTypes() {
        return types;
    }

    public void setTypes(Producttype types) {
        this.types = types;
    }

    public Systemdictionarydetail getUnit() {
        return unit;
    }

    public void setUnit(Systemdictionarydetail unit) {
        this.unit = unit;
    }

    public Systemdictionarydetail getBrand() {
        return brand;
    }

    public void setBrand(Systemdictionarydetail brand) {
        this.brand = brand;
    }
}
