package com.sise.yxc.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sise.yxc.model.Userinfo;
import com.sise.yxc.service.UserService;

public class PersonmanageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 页面参数
	 */
	private String type;//标识操作是否是更新
	
	private String username;
	private String password;
	private String nickname;
	private String sex;
	private String info;
	/*
	 * 返回参数
	 */
	private Userinfo user;
	
	/*
	 * 服务类
	 */
	private static UserService userservice;

	/*
	 * setter和getter方法
	 */
	public Userinfo getUser() {
		return user;
	}

	public void setUser(Userinfo user) {
		this.user = user;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		PersonmanageAction.userservice = userservice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	@Override
	public String execute() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();	
		Userinfo user = (Userinfo)session.get("user");
		if("update".equals(type)){
			user.setUserId(user.getUserId());
			System.out.println(user.getUserId());
			user.setUsername(username);
			user.setPassword(password);
			user.setNickname(nickname);
			user.setSex(sex);
			user.setInfo(info);
			userservice.UpdateUser(user);
			System.out.println("更新成功");
		}
		setUser((Userinfo)session.get("user"));
		return "personmanage";
	}
	
	
}
