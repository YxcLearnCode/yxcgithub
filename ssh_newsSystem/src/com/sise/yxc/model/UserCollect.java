package com.sise.yxc.model;

/**
 * UserCollect entity. @author MyEclipse Persistence Tools
 */

public class UserCollect implements java.io.Serializable {

	// Fields

	private Integer count;
	private Userinfo userinfo;
	private News news;

	// Constructors

	/** default constructor */
	public UserCollect() {
	}

	/** full constructor */
	public UserCollect(News newsByNewsId, News newsByUserId) {
		this.userinfo = userinfo;
		this.news = news;
	}

	// Property accessors

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	

	
	

}