package com.xf.service.impl;

import com.xf.domain.Role;
import com.xf.repository.RoleRepository;
import com.xf.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

}
