package com.xf.service.impl;

import com.xf.domain.Permission;
import com.xf.repository.PermissionRepository;
import com.xf.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,Long> implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Set<String> findPersByUser(Long userId) {
        return permissionRepository.findPersByUser(userId);
    }
}
