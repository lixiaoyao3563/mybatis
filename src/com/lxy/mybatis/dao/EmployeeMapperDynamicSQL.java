package com.lxy.mybatis.dao;

import java.util.List;

import com.lxy.mybatis.bean.Employee;

public interface EmployeeMapperDynamicSQL {

	public List<Employee> getEmpsByConditionIf(Employee employee);
	
}
