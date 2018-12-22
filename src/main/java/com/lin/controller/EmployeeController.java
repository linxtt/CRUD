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
 * 处理员工CRUD请求
 * */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	//分页查询员工信息（ajax）
	@RequestMapping("/emps")
	@ResponseBody
	public Message getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getAll();
		PageInfo<Employee> page = new PageInfo<Employee>(emps, 5);
		return Message.success().add("pageInfo", page);
	}
	
	//动态框中保存按钮保存员工信息
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Message saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String,Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError : errors) {
				System.out.println("错误字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Message.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Message.success();
		}
		
	}
	
	//检验用户名是否可用
	@RequestMapping("/checkUserName")
	@ResponseBody
	public Message checkUserName(@RequestParam("empName") String empName) {
		//先判断用户名是不是符合要求（正则表达式）
		String rex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!empName.matches(rex)) {
			return Message.fail().add("value_message", "用户名必须是6-16位英文和数字的组合or2-5位中文！");
		}
		//数据库用户名重复校验
		boolean b = employeeService.checkUserName(empName);
		if(b) {
			return Message.success();
		}else {
			return Message.fail().add("value_message", "用户名不可用");
		}
	}
	
	//根据id查询员工信息
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Message getEmp(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getEmp(id);
		return Message.success().add("emp", employee);
	}
	
	//更新保存员工信息
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Message updateEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Message.success();
	}
	
	//删除员工信息（根据员工Id,单个批量合为一个方法：1-2-3|1）
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Message deleteEmp(@PathVariable("ids") String ids) {
		//批量删除
		if(ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			for (String id : str_ids) {
				del_ids.add(Integer.parseInt(id));
			}
			employeeService.deleteBatch(del_ids);
		}else {
			//单个删除
			Integer id =Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Message.success();
	}
	
}
