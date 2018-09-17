package com.lxy.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lxy.mybatis.bean.Employee;
import com.lxy.mybatis.dao.EmployeeMapper;
import com.lxy.mybatis.dao.EmployeeMapperAnnotation;
import com.lxy.mybatis.dao.EmployeeMapperDynamicSQL;

public class MybatisTest {
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource  = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	

	@Test
	public void test() throws IOException {

		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Employee employee = sqlSession.selectOne("com.lxy.mybatis.dao.EmployeeMapper.getEmpById", 1);
			System.out.println(employee);
		}finally {
			sqlSession.close();
		}
		
	}
	
	
	
	@Test
	public void testGetEmp() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
//			Employee employee = employeeMapper.getEmpById(1);
			Employee employee = employeeMapper.getEmpByIdAndLastName(1, "Jam");
			System.out.println(employee);
		}finally {
			sqlSession.close();
		}
	}
	
	
	@Test
	public void testGetEmpAnno() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
			Employee employee = mapper.getEmpById(1);
			System.out.println(employee);
			
		}finally {
			sqlSession.close();
		}
	}
	
	
	@Test
	public void testInsertEmp() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Employee employee = new Employee();
			employee.setLastName("Jam");
			employee.setEmail("Jam@qq.com");
			employee.setGender("1");
			EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
			employeeMapper.insertEmp(employee);
			System.out.println(employee);
			
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	
	
	@Test
	public void testUpdateEmp() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Employee employee = new Employee();
			employee.setId(1);
			employee.setLastName("Jam");
			employee.setEmail("Jam@qq.com");
			employee.setGender("0");
			EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
			employeeMapper.updateEmp(employee);
			System.out.println(employee);
			
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testDeleteEmp() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
			employeeMapper.deleteEmp(5);
			
//			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testGetEmpsByConditionIf() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee = new Employee(1, "%Jam%", null, null);
			List<Employee> emps = mapper.getEmpsByConditionIf(employee);
			System.out.println(emps);
		}finally {
			sqlSession.close();
		}
	}
	

}
