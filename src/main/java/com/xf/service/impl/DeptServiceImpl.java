package com.xf.service.impl;

import com.xf.domain.Dept;
import com.xf.repository.DeptRepository;
import com.xf.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept,Long> implements IDeptService {

    @Autowired
    private DeptRepository deptRepository;

}
