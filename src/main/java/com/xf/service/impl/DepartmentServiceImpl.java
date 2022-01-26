package com.xf.service.impl;

import com.xf.domain.Department;
import com.xf.repository.DepartmentRepository;
import com.xf.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department,Long> implements IDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }
}
