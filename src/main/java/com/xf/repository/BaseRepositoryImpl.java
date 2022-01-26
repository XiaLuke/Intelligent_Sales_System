package com.xf.repository;

import com.xf.query.BaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * 实现父类中的三个方法
 * @param <T>
 * @param <ID>
 */
public class BaseRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID> {

    private final EntityManager entityManager;

    //必需要实现父类的这个构造器
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    public Page findPageByQuery(BaseQuery baseQuery) {
        //第一步：拿到所有高级查询条件
        Specification spec = baseQuery.createSpec();
        //第二步:拿到排序的值
        Sort sort = baseQuery.createSort();
        //第三步:根据条件查询分页数据并且返回
        Pageable pageable = new PageRequest(baseQuery.getJpaCurrentPage(), baseQuery.getPageSize(),sort);
        Page<T> page = super.findAll(spec, pageable);
        return page;
    }

    @Override
    public List<T> findByQuery(BaseQuery baseQuery) {
        //第一步：拿到所有高级查询条件
        Specification spec = baseQuery.createSpec();
        //第二步:拿到排序的值
        Sort sort = baseQuery.createSort();
        //第三步:拿到数据返回
        return  super.findAll(spec, sort);
    }

    //select ...  where xxx =? and yy =? ...
    @Override
    public List findByJpql(String jpql, Object... values) {
        //第一步:创建Query对象
        Query query = entityManager.createQuery(jpql);
        //第二步:把值设置到Query对象中去
        if (values!=null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        //第三步：返回数据
        return query.getResultList();
    }
}
