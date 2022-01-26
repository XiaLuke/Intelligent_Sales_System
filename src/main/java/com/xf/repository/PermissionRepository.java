package com.xf.repository;

import com.xf.domain.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 */
public interface PermissionRepository extends BaseRepository<Permission,Long>{

    /**
     * 根据当前登录用户获取到他对应的所有权限
     *  JPA关连的两个法则: 1.不需要写on  2.关连的前面的对象的别名.属性
     */
    @Query("select p.sn from Employee o join o.roles r join r.permissions p where o.id =?1")
    Set<String> findPersByUser(Long userId);

}
