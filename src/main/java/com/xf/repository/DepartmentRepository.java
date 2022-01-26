package com.xf.repository;

import com.xf.domain.Department;

/**
 *  只要发现咱们的接口继承了JpaRepository,它就会自动去完成相应的CRUD
 *      Department:CRUD操作的哪一个类型
 *      Long:主键的类型
 */
public interface DepartmentRepository extends BaseRepository<Department,Long> {

    //根据名称拿到部门对象
    Department findByName(String name);
}
