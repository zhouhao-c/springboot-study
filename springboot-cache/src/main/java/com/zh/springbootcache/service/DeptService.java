package com.zh.springbootcache.service;

import com.zh.springbootcache.bean.Department;
import com.zh.springbootcache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id){
        System.out.println("查询部门:"+id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }
}
