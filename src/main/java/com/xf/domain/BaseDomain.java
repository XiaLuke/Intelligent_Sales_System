package com.xf.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * MappedSuperclass :JPA就知道它是一个父类
 */
@MappedSuperclass
public class BaseDomain {


    //protected:子类可以使用，外部不能使用
    @Id
    @GeneratedValue
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
