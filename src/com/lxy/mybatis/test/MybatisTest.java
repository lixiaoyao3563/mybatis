package com.lxy.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lxy.mybatis.bean.Employee;
import com.lxy.mybatis.dao.EmployeeMapper;
import com.lxy.mybatis.dao.EmployeeMapperAnnotation;

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
			Employee employee = employeeMapper.getEmpById(1);
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
	
	
	

}
