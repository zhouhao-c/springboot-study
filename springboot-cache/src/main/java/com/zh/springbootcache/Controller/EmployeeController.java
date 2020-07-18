package com.zh.springbootcache.Controller;

import com.zh.springbootcache.bean.Employee;
import com.zh.springbootcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        return employeeService.getEmpById(id);
    }

    @GetMapping("/emp")
    public Employee update(Employee emp){
        return employeeService.updateEmp(emp);
    }
}
