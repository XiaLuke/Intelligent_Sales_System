package com.xf.repository;

import com.xf.domain.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 */
public interface MenuRepository extends BaseRepository<Menu,Long>{

    /**
     * 根据不同的用户获取到不同的菜单
     */
    @Query("select distinct m from Employee e join e.roles r join r.permissions p join p.menu m where e.id =?1")
    List<Menu>  findByUser(Long userId);
}
