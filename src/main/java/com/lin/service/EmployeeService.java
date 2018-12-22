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
		// 查询所有员工
		return employeeMapper.selectByExampleWithDept(null);
	}

	public void saveEmp(Employee employee) {
		// 员工保存方法
		employeeMapper.insertSelective(employee);
	}

	public boolean checkUserName(String empName) {
		// 检验用户名是否可用     return true：代表当前姓名可用\false：代表姓名不可用
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	public Employee getEmp(Integer id) {
		// 根据员工id查询员工
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	public void updateEmp(Employee employee) {
		// 更新保存员工信息
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}

	public void deleteEmp(Integer id) {
		// 单个删除员工信息
		employeeMapper.deleteByPrimaryKey(id);
		
	}

	public void deleteBatch(List<Integer> ids) {
		// 批量删除员工信息
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);	
	}

}
