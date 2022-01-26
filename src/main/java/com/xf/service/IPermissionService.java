package com.xf.service;

import com.xf.domain.Permission;

import java.util.Set;

/**
 */
public interface IPermissionService extends IBaseService<Permission,Long>{

    Set<String> findPersByUser(Long userId);
}
