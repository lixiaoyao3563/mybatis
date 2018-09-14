package com.lxy.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import com.lxy.mybatis.bean.Employee;

public interface EmployeeMapperAnnotation {
	
	@Select("select * from employee where id = #{id}")
	public Employee getEmpById(Integer id);

}
