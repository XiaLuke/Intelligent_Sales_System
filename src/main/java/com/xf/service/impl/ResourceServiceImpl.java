package com.xf.service.impl;

import com.xf.domain.Resource;
import com.xf.repository.ResourceRepository;
import com.xf.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource,Long> implements IResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

}
