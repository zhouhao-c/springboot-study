package com.zh.springbootcache.service;

import com.zh.springbootcache.bean.Employee;
import com.zh.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService{

    @Autowired
    EmployeeMapper employeeMapper;
    /**
     * @Cacheable
     * 将方法的运行结果进行缓存，以后在要相同的数据，直接从缓存中获取。
     *
     * CacheManager管理多个Cache组件，对缓存真正的crud操作在Cache组件中，每个缓存组件有自己唯一的名字
     *
     * 原理：
     *  自动配置类：CacheAutoConfiguration
     *
     * 运行流程：
     *      1.方法运行之前，先查询Cache组件，按照cacheNames指定名称获取，第一次获取如果没有擦车组件
     *      则会自动创建。
     *      2.去cache中去查找缓存的内容（lookup），使用一个key
     *      3.没有查到缓存就调用方法
     *      4.将目标的方法返回值放入缓存中
     *
     * 核心：
     * 1、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件
     * 2、key使用keyGenerator生成的，默认是SimpleKeyGenerator
     * 3、@Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     * 如果没有就运行方法将结果放入缓存，之后再次调用就可以使用缓存中的数据。
     *
     * 几个属性：
     *      cacheNames/value：指定缓存组件的名字，以数组的方式可以指定多个缓存。
     *      key：缓存数据使用的key，可以用来指定。默认使用方法参数的值。
     *          SpEL表达式
     *      keyGenerator：key的生成器，可以自己指定
     *          key/keyGenerator二选一
     *      cacheManager：指定缓存管理器，或者cacheResolver指定获取解析器
     *      condition：指定符合条件的情况下缓存。
     *      unless：否定缓存，当unless指定的条件的值为true，方法的返回值不会缓存，可以获取到结果进行判断
     *          unless = "#result == null"
     *      sync：是否使用异步模式，该模式下unless不支持
     *
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"})
    public Employee getEmpById(Integer id) {
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut：即调用方法，又更新缓存数据，同步更新缓存
     * 注意：方法运行以后给缓存中存放数据
     * @param emp
     */
    @CachePut(value = "emp",key = "#emp.id")
    public Employee updateEmp(Employee emp) {
        System.out.println("更新："+emp);
        employeeMapper.updateEmp(emp);
        return emp;
    }

    /**
     * @CacheEvict：缓存清除
     * key:指定要清除的数据
     * allEntries = true:全部清空
     * beforeInvocation = false:缓存的清除是否在缓存之前
     *      默认代表是方法执行之后执行，如果异常缓存不会清除
     *      beforeInvocation = true：方法执行之前执行，无论方法是否异常都清除
     * @param id
     */
    @CacheEvict(value = "emp",key = "#id")
    public void deleteEmp(Integer id){
//        employeeMapper.deleteEmpById(id);
    }

    /**
     * @Caching定义复杂缓存规则
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        Employee emp = employeeMapper.getEmpByLastName(lastName);
        return emp;
    }

}
