package com.sise.yxc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * News entity. @author MyEclipse Persistence Tools
 */

public class News implements java.io.Serializable {

	// Fields

	private Integer newsId;
	private Category category;
	private Userinfo userinfo;
	private String content;
	private String title;
	private Date modifyTime;
	private Set userCollects = new HashSet(0);
	// Constructors

	/** default constructor */
	public News() {
	}

	public Set getUserCollects() {
		return userCollects;
	}

	public void setUserCollects(Set userCollects) {
		this.userCollects = userCollects;
	}

	/** full constructor */
	public News(Category category, Userinfo userinfo, String content,
			String title, Date modifyTime) {
		this.category = category;
		this.userinfo = userinfo;
		this.content = content;
		this.title = title;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public Integer getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}