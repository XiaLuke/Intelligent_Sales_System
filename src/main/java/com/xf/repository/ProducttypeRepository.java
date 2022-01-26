package com.xf.repository;

import com.xf.domain.Producttype;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 */
public interface ProducttypeRepository extends BaseRepository<Producttype,Long>{

    //拿到所有子类型
    @Query("select o from Producttype o where o.parent.id is not null")
    List<Producttype> findChildren();
}
