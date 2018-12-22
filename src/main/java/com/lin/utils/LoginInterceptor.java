package com.lin.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lin.bean.User;

//�Զ���һ����¼������
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
		// ��ȡ�����url
		String url = request.getRequestURI();
		//url:���˵�¼�����ⶼ��������
		if(url.indexOf("/login.jsp")>=0) {
			return true;
		}
		//��ȡsession
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User_Session");
		//�ж�Session���Ƿ������ݣ�����з���true����������ִ��
		if(user != null) {
			return true;
		}
		//������������ת�ص�¼���棬��������ʾ��Ϣ
		request.setAttribute("msg", "���ȵ�¼��");
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		return false;
	}

}
