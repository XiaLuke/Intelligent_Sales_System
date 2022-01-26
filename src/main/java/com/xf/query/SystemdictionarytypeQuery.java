package com.xf.query;

import com.xf.domain.Systemdictionarytype;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 */
public class SystemdictionarytypeQuery extends BaseQuery {

    private String name;

    @Override
    public Specification createSpec(){
        Specification<Systemdictionarytype> spec = Specifications.<Systemdictionarytype>and()
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
