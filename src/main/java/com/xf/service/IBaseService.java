package com.xf.service;

import com.xf.query.BaseQuery;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T,ID extends Serializable> {
    //添加与修改
    void save(T t);
    void delete(ID id);
    T findOne(ID id);
    List<T> findAll();
    //根据Query拿到分页对象(分页)
    Page findPageByQuery(BaseQuery baseQuery);
    //根据Query拿到对应的所有数据(不分页)
    List<T> findByQuery(BaseQuery baseQuery);
    //根据jpql与对应的参数拿到数据
    List findByJpql(String jpql,Object... values);
}
