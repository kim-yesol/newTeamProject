package com.teamproject.myweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.teamproject.myweb.Service.boardService;
import com.teamproject.myweb.command.UserVO;

public class CheckHandler implements HandlerInterceptor{

	@Autowired
	boardService boardservice;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("시랭됨");
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		System.out.println(request.getContextPath());
		
		if(userVO == null) {
			System.out.println("도라가");
			System.out.println(request.getContextPath());
			response.sendRedirect(request.getContextPath()+ "/user/userLogin");
			return false;
		} else {
			System.out.println("컴온");
			return true;
		}
		
	}
	
	

}
