package com.xf.query;

import com.xf.domain.Producttype;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 */
public class ProducttypeQuery extends BaseQuery {

    private String name;

    @Override
    public Specification createSpec(){
        Specification<Producttype> spec = Specifications.<Producttype>and()
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
