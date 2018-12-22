package com.lin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.bean.Employee;
import com.lin.bean.Message;
import com.lin.service.EmployeeService;

/*
 * ����Ա��CRUD����
 * */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	//��ҳ��ѯԱ����Ϣ��ajax��
	@RequestMapping("/emps")
	@ResponseBody
	public Message getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getAll();
		PageInfo<Employee> page = new PageInfo<Employee>(emps, 5);
		return Message.success().add("pageInfo", page);
	}
	
	//��̬���б��水ť����Ա����Ϣ
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Message saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			//У��ʧ�ܣ�Ӧ�÷���ʧ�ܣ���ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
			Map<String,Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError : errors) {
				System.out.println("�����ֶ�����"+fieldError.getField());
				System.out.println("������Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Message.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Message.success();
		}
		
	}
	
	//�����û����Ƿ����
	@RequestMapping("/checkUserName")
	@ResponseBody
	public Message checkUserName(@RequestParam("empName") String empName) {
		//���ж��û����ǲ��Ƿ���Ҫ��������ʽ��
		String rex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!empName.matches(rex)) {
			return Message.fail().add("value_message", "�û���������6-16λӢ�ĺ����ֵ����or2-5λ���ģ�");
		}
		//���ݿ��û����ظ�У��
		boolean b = employeeService.checkUserName(empName);
		if(b) {
			return Message.success();
		}else {
			return Message.fail().add("value_message", "�û���������");
		}
	}
	
	//����id��ѯԱ����Ϣ
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Message getEmp(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getEmp(id);
		return Message.success().add("emp", employee);
	}
	
	//���±���Ա����Ϣ
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Message updateEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Message.success();
	}
	
	//ɾ��Ա����Ϣ������Ա��Id,����������Ϊһ��������1-2-3|1��
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Message deleteEmp(@PathVariable("ids") String ids) {
		//����ɾ��
		if(ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			for (String id : str_ids) {
				del_ids.add(Integer.parseInt(id));
			}
			employeeService.deleteBatch(del_ids);
		}else {
			//����ɾ��
			Integer id =Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Message.success();
	}
	
}
