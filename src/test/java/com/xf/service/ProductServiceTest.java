package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceTest extends BaseSpringTest {
    @Autowired
    private IProductService productService;

    @Test
    public void testFindAll()throws Exception{
        productService.findAll().forEach(e-> System.out.println(e));
    }

}
