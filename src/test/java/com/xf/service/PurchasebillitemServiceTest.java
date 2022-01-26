package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchasebillitemServiceTest extends BaseSpringTest {
    @Autowired
    private IPurchasebillitemService purchasebillitemService;

    @Test
    public void testFindAll()throws Exception{
        purchasebillitemService.findAll().forEach(e-> System.out.println(e));
    }

}
