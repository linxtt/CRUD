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
 * ����dao��
 * ʹ��Spring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
 * 1������SpringTestģ��
 * 2��@ContextConfigurationָ��Spring�����ļ���λ��
 * 3��ʹ��@Autowiredע��
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
		//���벿��
		/*departmentMapper.insertSelective(new Department(null, "���в�"));
		departmentMapper.insertSelective(new Department(null, "���Բ�"));
		departmentMapper.insertSelective(new Department(null, "���ڲ�"));
		departmentMapper.insertSelective(new Department(null, "Ӫ����"));*/
		//�������Ա��
		//employeeMapper.insertSelective(new Employee(null, "Tom", "m", "Tom@xtt.com", 1));
		//������Ӷ��Ա��(100��)��ʹ�ÿ���ִ������������sqlSession
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
