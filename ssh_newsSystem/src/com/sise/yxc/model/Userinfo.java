package com.sise.yxc.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */

public class Userinfo implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String username;
	private String password;
	private String nickname;
	private String sex;
	private String info;
	private String isadmin;
	private Set newses = new HashSet(0);
	private Set userCollects = new HashSet(0);

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(String username, String password, String sex, String isadmin) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.isadmin = isadmin;
	}

	/** full constructor */
	public Userinfo(String username, String password, String nickname,
			String sex, String info, String isadmin, Set newses) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.sex = sex;
		this.info = info;
		this.isadmin = isadmin;
		this.newses = newses;
	}

	// Property accessors
	public Set getUserCollects() {
		return userCollects;
	}

	public void setUserCollects(Set userCollects) {
		this.userCollects = userCollects;
	}
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getIsadmin() {
		return this.isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	public Set getNewses() {
		return this.newses;
	}

	public void setNewses(Set newses) {
		this.newses = newses;
	}

}