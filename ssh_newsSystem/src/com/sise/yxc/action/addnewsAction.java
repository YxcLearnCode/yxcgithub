package com.sise.yxc.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sise.yxc.model.Category;
import com.sise.yxc.model.Userinfo;
import com.sise.yxc.service.NewsService;

public class addnewsAction extends ActionSupport {
/**
 * 页面参数
 */
	private String title;
	private String category;
	private String content;
	
/**
 * 服务类
 */
    private static NewsService newsservice;
    
 /**
  * 返回参数
  */
    private List newslist;

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}


public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}



public static NewsService getNewsservice() {
	return newsservice;
}

public void setNewsservice(NewsService newsservice) {
	addnewsAction.newsservice = newsservice;
}

public List getNewslist() {
	return newslist;
}

public void setNewslist(List newslist) {
	this.newslist = newslist;
}
   @Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	   ActionContext actionContext=ActionContext.getContext();
	   Map<String,Object> session=actionContext.getSession();
	   System.out.println(title);
	   Userinfo user=(Userinfo) session.get("user");
	   newsservice.addNews(title, Integer.parseInt(category),user.getUserId(), content);
	   setNewslist(newsservice.GetAllNews());
	   return "newsmanage";
	}
}
