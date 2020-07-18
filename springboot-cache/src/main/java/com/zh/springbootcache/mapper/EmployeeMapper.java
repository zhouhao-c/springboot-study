package com.zh.springbootcache.mapper;

import com.zh.springbootcache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("select * from t_employee where id = #{id}")
    public Employee getEmpById(Integer id);

    @Update("update t_employee set lastName = #{lastName},email = #{email},gender = #{gender},d_id = #{dId}")
    public void updateEmp(Employee emp);

    @Delete("delete from t_employee where id = #{id}")
    public void deleteEmpById(Integer id);

    @Insert("insert into t_employee (lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmp(Employee emp);
}
