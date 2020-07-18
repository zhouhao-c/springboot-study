package com.zh.springbootcache.service;

import com.zh.springbootcache.bean.Employee;
import com.zh.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements EmployeeMapper {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getEmpById(Integer id) {
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    @Override
    public void updateEmp(Employee emp) {

    }

    @Override
    public void deleteEmpById(Integer id) {

    }

    @Override
    public void insertEmp(Employee emp) {

    }
}
