package com.lin.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lin.bean.Employee;
import com.lin.dao.DepartmentMapper;
import com.lin.dao.EmployeeMapper;



/*
 * 测试dao层
 * 使用Spring的单元测试，可以自动注入我们需要的组件
 * 1、导入SpringTest模块
 * 2、@ContextConfiguration指定Spring配置文件的位置
 * 3、使用@Autowired注解
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void test() {
		//System.out.println(departmentMapper+"\r"+employeeMapper);
		//插入部门
		/*departmentMapper.insertSelective(new Department(null, "科研部"));
		departmentMapper.insertSelective(new Department(null, "测试部"));
		departmentMapper.insertSelective(new Department(null, "后勤部"));
		departmentMapper.insertSelective(new Department(null, "营销部"));*/
		//单个添加员工
		//employeeMapper.insertSelective(new Employee(null, "Tom", "m", "Tom@xtt.com", 1));
		//批量添加多个员工(100条)，使用可以执行批量操作的sqlSession
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i = 0;i<100;i++) {
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			String gender = i%2==0?"M":"W";
			mapper.insertSelective(new Employee(null,uid,gender,uid+"@xtt.com",1));
		}
		for(int i = 0;i<100;i++) {
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			String gender = i%2==0?"M":"W";
			mapper.insertSelective(new Employee(null,uid,gender,uid+"@xtt.com",2));
		}
		for(int i = 0;i<100;i++) {
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			String gender = i%2==0?"M":"W";
			mapper.insertSelective(new Employee(null,uid,gender,uid+"@xtt.com",3));
		}
		for(int i = 0;i<100;i++) {
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			String gender = i%2==0?"M":"W";
			mapper.insertSelective(new Employee(null,uid,gender,uid+"@xtt.com",4));
		}
	}
	
}
