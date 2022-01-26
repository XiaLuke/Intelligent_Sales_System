package com.xf.query;

import com.xf.domain.Department;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 只用于Department的查询条件
 */
public class DepartmentQuery extends BaseQuery {

    private String name;

    //返回咱们的查询条件 where username = ?
    @Override
    public Specification createSpec(){
        Specification<Department> spec = Specifications.<Department>and()
                .like(StringUtils.isNotBlank(name),"name", "%"+name+"%")
                .build();
        return spec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
