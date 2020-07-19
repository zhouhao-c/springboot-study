package com.zh.springbootcache;

import com.zh.springbootcache.bean.Employee;
import com.zh.springbootcache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.jws.Oneway;

@SpringBootTest
class SpringbootCacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串的
    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象

    /**
     * Redis常见五大数据类型
     * String(字符串),List(列表),Set(集合),Hash(散列),zSet(有序集合)
     * stringRedisTemplate.opsForValue()[String(字符串)]
     * stringRedisTemplate.opsForList()[List(列表)]
     * stringRedisTemplate.opsForSet()[Set(集合)]
     * stringRedisTemplate.opsForHash()[Hash(散列)]
     * stringRedisTemplate.opsForZSet()[zSet(有序集合)]
     *
     *
     */
    @Test
    public void test01(){
//        stringRedisTemplate.opsForValue().append("msg","hello word");
        //String str = stringRedisTemplate.opsForValue().get("myname");
        //System.err.println(str);

    }

    /**
     * 测试保存对象
     * 默认如果保存对象,使用jdk序列化机制,序列化的数据保存在redis中
     *
     * 将数据以json的方式保存
     *      1.自己将对象转换成json
     *      2.redisTemplate默认序列化规则
     */
    @Test
    public void test02(){
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee.toString());
//        redisTemplate.opsForValue().set("emp-1",employee);
        redisTemplate.opsForValue().set("emp-02",employee);
    }

    @Test
    public void contextLoads() {
        Employee employee = employeeMapper.getEmpById(1);
        System.err.println(employee.toString());
    }

}
