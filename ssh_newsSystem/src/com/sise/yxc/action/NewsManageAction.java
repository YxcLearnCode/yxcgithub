package com.sise.yxc.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.sise.yxc.model.News;
import com.sise.yxc.service.NewsService;

public class NewsManageAction extends ActionSupport{
/**
 * 页面参数
 */
	private String news_id;
	private String title;
	private String content;
	private String type;//标识操作类型
	
 /*
  * 返回参数
  */
	private List newslist;
	private News news;
	/*
	 * get&set
	 */
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List getNewslist() {
		return newslist;
	}
	public void setNewslist(List newslist) {
		this.newslist = newslist;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	private static NewsService newsservice;
	
	public static NewsService getNewsservice() {
		return newsservice;
	}
	public void setNewsservice(NewsService newsservice) {
		NewsManageAction.newsservice = newsservice;
	}
	@Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
		if ("delete".equals(type)) {
			newsservice.DeleteNewsbyID(Integer.parseInt(news_id));
			
		}else if ("edit".equals(type)) {
			newsservice.UpdateNews(Integer.parseInt(news_id), title, content);
		}else if ("get".equals(type)) {
			setNews(newsservice.FindByID(Integer.parseInt(news_id)));
			return "editnews";
		}else if ("view".equals(type)) {
			setNews(newsservice.FindByID(Integer.parseInt(news_id)));
			return "newscontent";
		}
		setNewslist(newsservice.GetAllNews());
		return "allnewslist";
		}
}
