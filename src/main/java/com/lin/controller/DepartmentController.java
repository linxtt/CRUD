package com.lin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lin.bean.Department;
import com.lin.bean.Message;
import com.lin.service.DepartmentService;

//����Ͳ����йص�����
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	//�������еĲ�����Ϣ
	@RequestMapping("/depts")
	@ResponseBody
	public Message getDepts() {
		//������в�����Ϣ
		List<Department> list = departmentService.getDepts();
		return Message.success().add("depts", list);
	}
	
}
