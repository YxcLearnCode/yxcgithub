package com.sise.yxc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
    //编码格式
	private String encoding;
	//对response进行编码设置
    private boolean forceEncoding;
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		//编码格式设置
		if(encoding != null && (forceEncoding || request.getCharacterEncoding() == null))
        {
            request.setCharacterEncoding(encoding);
            if(forceEncoding)
                response.setCharacterEncoding(encoding);
        }
		//过滤链继续执行
        filterChain.doFilter(request, response);
	}
	/**
	 * 初始化
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.encoding = filterConfig.getInitParameter("encoding");
		// 获取forceEncoding
		String forceEncodingParam = filterConfig.getInitParameter("forceEncoding");
		if(forceEncodingParam == null) {
			this.forceEncoding = true;
		} else if("true".equalsIgnoreCase(forceEncodingParam)) {
			this.forceEncoding = true;
		} else {
			this.forceEncoding = false;
		}
	}

}
