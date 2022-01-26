package com.xf.domain;

import javax.persistence.*;

//数据字典类型
@Entity
@Table(name = "systemdictionarytype")
public class Systemdictionarytype  extends BaseDomain {

    public static final String PRODUCT_BRAND="productBrand";//品牌sn
    public static final String PRODUCT_UNIT="productUnit";//单位sn

    //编码
    private String sn;
    //名称
    private String name;


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
