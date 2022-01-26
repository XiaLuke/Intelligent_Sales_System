package com.xf.service;

import com.xf.domain.Producttype;

import java.util.List;

/**
 */
public interface IProducttypeService extends IBaseService<Producttype,Long>{

    List<Producttype> findChildren();
}
