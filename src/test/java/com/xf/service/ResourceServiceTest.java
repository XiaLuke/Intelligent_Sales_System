package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ResourceServiceTest extends BaseSpringTest {
    @Autowired
    private IResourceService resourceService;

    @Test
    public void testFindAll()throws Exception{
        resourceService.findAll().forEach(e-> System.out.println(e));
    }

}
