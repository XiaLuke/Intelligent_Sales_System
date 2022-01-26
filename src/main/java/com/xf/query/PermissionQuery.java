package com.xf.query;

import com.xf.domain.Permission;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 */
public class PermissionQuery extends BaseQuery {

    private String name;

    @Override
    public Specification createSpec(){
        Specification<Permission> spec = Specifications.<Permission>and()
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
