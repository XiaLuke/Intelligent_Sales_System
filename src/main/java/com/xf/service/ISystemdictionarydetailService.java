package com.xf.service;

import com.xf.domain.Systemdictionarydetail;

import java.util.List;

/**
 */
public interface ISystemdictionarydetailService extends IBaseService<Systemdictionarydetail,Long>{

    List<Systemdictionarydetail> findAllUnit();
    List<Systemdictionarydetail> findAllBrand();

}
