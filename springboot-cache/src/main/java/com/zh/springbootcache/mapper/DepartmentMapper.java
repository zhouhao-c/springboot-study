package com.zh.springbootcache.mapper;

import com.zh.springbootcache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {
    @Select("select * from t_department where id = #{id}")
    Department getDeptById(Integer id);
}
