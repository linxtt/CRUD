package com.lin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.bean.User;
import com.lin.dao.UserMapper;

@Service
public class UserService {
	
	@Autowired
	 UserMapper userMapper;
	 
	 //获取系统用户登录的账号密码
	 public User findUser(String userCode,String userPassword) {
		 User user = this.userMapper.findUser(userCode, userPassword);
		 System.out.println(user);
		 return user;
	 }
	
	
	
	
}
