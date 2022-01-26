package com.xf.domain;

import javax.persistence.*;

@Entity
@Table(name = "supplier")
public class Supplier  extends BaseDomain {


    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
