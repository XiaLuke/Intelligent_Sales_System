package com.xf.service.impl;

import com.xf.domain.Producttype;
import com.xf.repository.ProducttypeRepository;
import com.xf.service.IProducttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service
public class ProducttypeServiceImpl extends BaseServiceImpl<Producttype,Long> implements IProducttypeService {

    @Autowired
    private ProducttypeRepository producttypeRepository;

    @Override
    public List<Producttype> findChildren() {
        return producttypeRepository.findChildren();
    }
}
