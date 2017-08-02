package com.sise.yxc.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sise.yxc.service.UserService;

public class RegisterAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 页面参数
	 */
	private String username;
	private String password;
	private String nickname;
	private String sex;
	private String info;
	
	/*
	 * getter&setter
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	/*
	 * 服务类
	 */
	private static UserService userservice;

	
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		RegisterAction.userservice = userservice;
	}
	@Override
	public String execute() throws Exception {
		boolean result = userservice.RegisterUser(username, password, nickname, sex, info);
		if(result){
			return "registersuccess";
		}
		return "registerfailure";
	}
}
