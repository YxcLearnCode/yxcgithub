package com.sise.yxc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.sise.yxc.model.Userinfo;

public class SessionFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest) request;
		Userinfo user = (Userinfo)req.getSession().getAttribute("user");
		
		/*
		 * 若用户未登录
		 */
		if(user == null){
			//转到网页过期提醒页面
			req.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		req.getRequestDispatcher("/personmanage.jsp").forward(request, response);
		return;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
