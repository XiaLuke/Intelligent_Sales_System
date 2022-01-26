package com.xf.domain;

import javax.persistence.*;

@Entity
@Table(name = "dept")
public class Dept  extends BaseDomain {

    //我是个名字
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
