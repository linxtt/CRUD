package com.lin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lin.bean.User;
import com.lin.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String userCode,String userPassword,Model model,HttpSession session) {
		User user = userService.findUser(userCode, userPassword);
		if(user!=null) {
			//将用户对象添加到session
			session.setAttribute("User_Session", user);
			//跳转到主页面
			return "list";
		}
		model.addAttribute("msg", "账号或密码输入错误，请重新输入！");
		return "login";
	} 
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String toLogin() {
		return "login";
	}
	
}
