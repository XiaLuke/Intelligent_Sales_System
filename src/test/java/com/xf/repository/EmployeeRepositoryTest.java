package com.xf.repository;

import com.xf.BaseSpringTest;
import com.xf.domain.Employee;
import com.xf.query.EmployeeQuery;
import com.github.wenhao.jpa.Specifications;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;


public class EmployeeRepositoryTest extends BaseSpringTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void testFindAll()throws Exception{
        System.out.println(employeeRepository); //SimpleJpaRepository
        System.out.println(employeeRepository.getClass()); //$Proxy30

       //$Proxy30 extends SimpleJpaRepository imp  EmployeeRepository

        List<Employee> list = employeeRepository.findAll();

        list.forEach(e-> System.out.println(e));
    }
    @Test
    public void testFindOne()throws Exception{
        Employee employee = employeeRepository.findOne(1L);
        System.out.println(employee);
    }

    @Test
    public void testSave()throws Exception{
        Employee employee = new Employee();
        employee.setUsername("小黑");
        employee.setAge(45);
        employee.setEmail("hehe@qq.com");
        employee.setPassword("12345");

        employeeRepository.save(employee);
    }

    @Test
    public void testUpdate()throws Exception{
        Employee employee = new Employee();
        employee.setId(274L);
        employee.setUsername("小白");
        employee.setAge(45);
        employee.setEmail("hehe@qq.com");
        employee.setPassword("12345");

        employeeRepository.save(employee);
    }

    @Test
    public void testDelete()throws Exception{
        employeeRepository.delete(274L);
    }

    //测试分页功能
    @Test
    public void testPage()throws Exception{

        //创建分页条件
        /**
         * int page:当前页(从0开始计算)
         * int size:每页条数
         */
        Pageable pageable = new PageRequest(13,10);
        /**
         * page:分页对象
         */
        Page<Employee> page = employeeRepository.findAll(pageable);

        System.out.println(page.getTotalElements()); //总条数
        System.out.println(page.getTotalPages()); //总页
        System.out.println(page.getNumber()); //1 当前页
        System.out.println(page.getNumberOfElements()); //当前页的数量
        System.out.println(page.getSize()); //每页条数
        System.out.println(page.getContent()); //当前页的数据

        page.forEach(e-> System.out.println(e));
    }


    //排序
    @Test
    public void testSort()throws Exception{
        //创建排序对象
        /**
         * 第一个参数:排序规则
         * 第二个参数:排序的字段
         */
        Sort sort = new Sort(Sort.Direction.DESC,"age");
        List<Employee> list = employeeRepository.findAll(sort);
        list.forEach(e-> System.out.println(e));
    }

    //分页排序
    @Test
    public void testPageSort()throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC,"age");
        //分页与排序都封装起来
        Pageable pageable = new PageRequest(0,10,sort);

        Page<Employee> page = employeeRepository.findAll(pageable);
        page.forEach(e-> System.out.println(e));
    }

    //根据用户名查询
    @Test
    public void testQueryRule01()throws Exception{
        Employee employee = employeeRepository.findByUsername("admin");
        System.out.println(employee);
    }
    //根据用户名模糊查询
    @Test
    public void testQueryRule02()throws Exception{
        List<Employee> list = employeeRepository.findByUsernameLike("%1%");
        list.forEach(e-> System.out.println(e));
    }

    //根据用户名与邮件模糊查询
    @Test
    public void testQueryRule03()throws Exception{
        List<Employee> list = employeeRepository.findByUsernameLikeAndEmailLike("%1%","%2%");
        list.forEach(e-> System.out.println(e));
    }

    //根据用户名查询
    @Test
    public void testQuery01()throws Exception{
        Employee employee = employeeRepository.query01("admin");
        System.out.println(employee);
    }

    //根据用户名模糊查询
    @Test
    public void testQuery02()throws Exception{
        List<Employee> list = employeeRepository.query02("%1%");
        list.forEach(e-> System.out.println(e));
    }

    //根据用户名与邮件模糊查询
    @Test
    public void testQuery03()throws Exception{
        List<Employee> list = employeeRepository.query03("%1%","%2%");
        list.forEach(e-> System.out.println(e));
    }

    //根据用户名与邮件模糊查询
    @Test
    public void testQuery04()throws Exception{
        List<Employee> list = employeeRepository.querySql("%1%");
        list.forEach(e-> System.out.println(e));
    }


    //查询一个数据
    @Test
    public void testJpaSpecificationExecutor()throws Exception{

        /**
         * 根据相应的规则(Specification) 查询对应数据
         *  Predicate: where username=? and email =?
         *      root : 根 -> 可以获取到类中的属性(username,email)
         *      criteriaQuery: 如select,from,where,group by ,order by 等
         *      criteriaBuilder:解决 username=? / username like ? / age > ?
         *                      多个条件结合 username=?  and/or  age > ?
         */
        List<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //1.使用root去拿到相应的username属性
                Path usernamePath = root.get("username");
                //2.对这个属性添加查询规则
                Predicate predicate = cb.like(usernamePath, "%1%");

                return predicate;
            }
        });

        list.forEach(e-> System.out.println(e));
    }

    /**
     * 多个条件查询
     * @throws Exception
     */
    @Test
    public void testJpaSpecificationExecutor02()throws Exception{

        List<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //1.username like ?
                Path usernamePath = root.get("username");
                Predicate p1 = cb.like(usernamePath, "%1%");
                //2.email like ?
                Path emailPath = root.get("email");
                Predicate p2 = cb.like(emailPath, "%2%");
                //3.age>20
                Path agePath = root.get("age");
                Predicate p3 = cb.gt(agePath, 20);
                //多个条件连接起来
                Predicate predicate = cb.and(p1, p2,p3);
                return predicate;
            }
        });
        list.forEach(e-> System.out.println(e));
    }

    /**
     * 多个条件查询+分页+排序
     * @throws Exception
     */
    @Test
    public void testJpaSpecificationExecutor03()throws Exception{
        //排序
        Sort sort = new Sort(Sort.Direction.DESC,"age");
        //分页
        Pageable pageable = new PageRequest(0,10,sort);
        Page<Employee> page = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path usernamePath = root.get("username");
                Predicate predicate = criteriaBuilder.like(usernamePath, "%1%");
                return predicate;
            }
        },pageable);

        page.forEach(e-> System.out.println(e));
    }


    /**
     * 查询一条数据
     * @throws Exception
     */
    @Test
    public void testJpaSpec01()throws Exception{

        //获取到查询的规则
        Specification<Employee> spec = Specifications.<Employee>and()
                .like("username", "%1%")
                .build();
        //根据规则完成查询
        List<Employee> list = employeeRepository.findAll(spec);

        list.forEach(e-> System.out.println(e));
    }

    /**
     * 多条件查询数据
     * @throws Exception
     */
    @Test
    public void testJpaSpec02()throws Exception{
        //获取到查询的规则
        Specification<Employee> spec = Specifications.<Employee>and()
                .like("username", "%1%")
                .like("email","%2%")
                .gt("age",20)
                .build();
        //根据规则完成查询
        List<Employee> list = employeeRepository.findAll(spec);

        list.forEach(e-> System.out.println(e));
    }

    /**
     * 多条件查询数据
     * @throws Exception
     */
    @Test
    public void testJpaSpec03()throws Exception{
        Sort sort = new Sort(Sort.Direction.valueOf("DESC"),"age");
        Pageable pageable = new PageRequest(0,10,sort);

        //获取到查询的规则
        Specification<Employee> spec = Specifications.<Employee>and()
                .like("username", "%1%")
//                .like("email","%2%")
//                .gt("age",20)
                .build();
        //根据规则完成查询
        Page<Employee> page = employeeRepository.findAll(spec,pageable);

        page.forEach(e-> System.out.println(e));
    }



    @Test
    public void testJpaSpecQuery()throws Exception{
        //模拟前台用户传参
        EmployeeQuery query = new EmployeeQuery();
        query.setUsername("1");
        query.setEmail("2");
        query.setAge(20);
        //从Query中获取到这个对象
        Specification spec = query.createSpec();

        List<Employee> list = employeeRepository.findAll(spec);
        list.forEach(e-> System.out.println(e));
    }

    /**
     * 分页功能
     * @throws Exception
     */
    @Test
    public void testJpaSpecPageQuery()throws Exception{
        //模拟前台用户传参
        EmployeeQuery query = new EmployeeQuery();
        query.setUsername("1");
//        query.setEmail("2");
//        query.setAge(20);
//        query.setCurrentPage(2);
        query.setOrderName("age");
        query.setOrderType("DESC");
        //从Query中获取到这个对象
        Specification spec = query.createSpec();

        Sort sort = query.createSort();
        //创建Page对象
        Pageable pageable = new PageRequest(query.getJpaCurrentPage(),query.getPageSize(),sort);

        Page<Employee> page = employeeRepository.findAll(spec,pageable);
        page.forEach(e-> System.out.println(e));
    }

    @Test
    public void testFindPage()throws Exception{
        EmployeeQuery query = new EmployeeQuery();
        query.setUsername("1");

        //BaseRepositoryImpl@56f71edb
        System.out.println(employeeRepository);
        System.out.println(employeeRepository.getClass());

        Page<Employee> page = employeeRepository.findPageByQuery(query);
        page.forEach(e-> System.out.println(e));
    }
}
