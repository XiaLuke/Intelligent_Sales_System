package com.xf.repository;

import com.xf.domain.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *  只要发现咱们的接口继承了JpaRepository,它就会自动去完成相应的CRUD
 *      Employee:CRUD操作的哪一个类型
 *      Long:主键的类型
 */
public interface EmployeeRepository extends BaseRepository<Employee,Long> {

    @Query("select count(o) from Employee o where o.username=?1")
    Long getCountByUsername(String username);

    //根据用户名查询(这里写必需在问号后加顺序)
    @Query("select o from Employee  o where o.username =?1")
    Employee query01(String name);


    //根据用户名模糊查询(这里写必需在问号后加顺序)
    @Query("select o from Employee  o where o.username like ?1")
    List<Employee> query02(String name);

    //根据用户名与邮件进行模糊查询(这里写必需在问号后加顺序)
    @Query("select o from Employee  o where o.username like ?1 and o.email like ?2")
    List<Employee> query03(String name,String email);
//    @Query("select o from Employee  o where o.username like :name and o.email like :email")
//    List<Employee> query03(@Param("name") String name,@Param("email") String email);

    //执行原生SQL
    @Query(nativeQuery = true,value = "SELECT *  FROM  employee WHERE username LIKE ?")
    List<Employee> querySql(String name);

    //根据用户名进行查询
    Employee findByUsername(String username);

    //根据用户名进行模糊查询
    List<Employee> findByUsernameLike(String name);

    //根据用户名与邮件进行模糊查询
    List<Employee> findByUsernameLikeAndEmailLike(String username,String email);

    //根据部门名称来获取对应的员工
    List<Employee> findByDepartment_Name(String deptName);
}
