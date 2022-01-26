package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchasebillServiceTest extends BaseSpringTest {
    @Autowired
    private IPurchasebillService purchasebillService;

    @Test
    public void testFindAll()throws Exception{
        purchasebillService.findAll().forEach(e-> System.out.println(e));
    }

}
