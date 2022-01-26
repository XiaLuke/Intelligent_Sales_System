package com.xf.service.impl;

import com.xf.domain.Product;
import com.xf.repository.ProductRepository;
import com.xf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product,Long> implements IProductService {

    @Autowired
    private ProductRepository productRepository;

}
