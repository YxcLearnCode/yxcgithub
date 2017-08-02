package com.sise.yxc.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sise.yxc.model.Userinfo;
import com.sise.yxc.service.UserService;

public class loginAction extends ActionSupport {
  /**
   * 页面参数
   */
	private String username;
	private String password;
	
	/**
	 * 返回参数
	 */
	private String nickname;
	/**
	 * get&set方法
	 */

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 服务类
	 */
	private static UserService userservice;
	public static UserService getUserservice() {
		return userservice;
	}

	public static void setUserservice(UserService userservice) {
		loginAction.userservice = userservice;
	}

	@Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
		Userinfo userinfo=userservice.CheckLogin(username, password);
		if (userinfo!=null) {
			ActionContext actionContext=ActionContext.getContext();
			Map<String,Object> session=actionContext.getSession();
			session.put("user", userinfo);
			session.put("nickname", userinfo.getNickname());
			setNickname(userinfo.getNickname());
			return "success";
			
		}
			return "failure";
		}
}
