package com.xf.service;

import com.xf.domain.Department;

/**
 * 现在虽然没有方法，但是以后肯定会有一个Department特有的业务
 */
public interface IDepartmentService extends IBaseService<Department,Long> {

    Department findByName(String name);
}
