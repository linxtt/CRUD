package com.lin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.lin.bean.Employee;
import com.lin.bean.EmployeeExample;
import com.lin.bean.EmployeeExample.Criteria;
import com.lin.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	public List<Employee> getAll() {
		// ��ѯ����Ա��
		return employeeMapper.selectByExampleWithDept(null);
	}

	public void saveEmp(Employee employee) {
		// Ա�����淽��
		employeeMapper.insertSelective(employee);
	}

	public boolean checkUserName(String empName) {
		// �����û����Ƿ����     return true������ǰ��������\false����������������
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	public Employee getEmp(Integer id) {
		// ����Ա��id��ѯԱ��
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	public void updateEmp(Employee employee) {
		// ���±���Ա����Ϣ
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}

	public void deleteEmp(Integer id) {
		// ����ɾ��Ա����Ϣ
		employeeMapper.deleteByPrimaryKey(id);
		
	}

	public void deleteBatch(List<Integer> ids) {
		// ����ɾ��Ա����Ϣ
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);	
	}

}
