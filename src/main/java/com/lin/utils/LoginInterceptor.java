package com.lin.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lin.bean.User;

//自定义一个登录拦截器
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取请求的url
		String url = request.getRequestURI();
		//url:除了登录请求外都进行拦截
		if(url.indexOf("/login.jsp")>=0) {
			return true;
		}
		//获取session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User_Session");
		//判断Session中是否有数据，如果有返回true，继续向下执行
		if(user != null) {
			return true;
		}
		//不符合条件跳转回登录界面，并给出提示信息
		request.setAttribute("msg", "请先登录！");
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		return false;
	}

}
