package com.lin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lin.bean.Department;
import com.lin.bean.Message;
import com.lin.service.DepartmentService;

//处理和部门有关的请求
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	//返回所有的部门信息
	@RequestMapping("/depts")
	@ResponseBody
	public Message getDepts() {
		//查出所有部门信息
		List<Department> list = departmentService.getDepts();
		return Message.success().add("depts", list);
	}
	
}
