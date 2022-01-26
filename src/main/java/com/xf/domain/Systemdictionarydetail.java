package com.xf.domain;

import javax.persistence.*;

@Entity
@Table(name = "systemdictionarydetail")
public class Systemdictionarydetail  extends BaseDomain {


    private String name;

    //明细对应的类型
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    private Systemdictionarytype types;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Systemdictionarytype getTypes() {
        return types;
    }

    public void setTypes(Systemdictionarytype types) {
        this.types = types;
    }
}
