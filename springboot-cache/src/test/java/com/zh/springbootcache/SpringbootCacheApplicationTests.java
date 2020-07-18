package com.zh.springbootcache;

import com.zh.springbootcache.bean.Employee;
import com.zh.springbootcache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootCacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void contextLoads() {
        Employee employee = employeeMapper.getEmpById(1);
        System.err.println(employee.toString());
    }

}
