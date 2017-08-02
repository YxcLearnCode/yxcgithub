package com.sise.yxc.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.sise.yxc.service.NewsService;

public class GetnewslistAction extends ActionSupport {
 /**
  * 页面参数
  */
 private String type_id;
 private String check;//识别请求的是0:newslist 还是1:newslist-manage
 /**
  * get&set方法
  * @return
  */
public String getType_id() {
	return type_id;
}
public void setType_id(String type_id) {
	this.type_id = type_id;
}
public String getCheck() {
	return check;
}
public void setCheck(String check) {
	this.check = check;
}
 
/**
 * 返回参数
 */
private List newslist;
public List getNewslist() {
	return newslist;
}
public void setNewslist(List newslist) {
	this.newslist = newslist;
}
/**
 * 服务类
 */

private static NewsService newsservice;


public static NewsService getNewsservice() {
	return newsservice;
}
public  void setNewsservice(NewsService newsservice) {
	GetnewslistAction.newsservice = newsservice;
}
@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	    System.out.println(check);
	    if (Integer.parseInt(type_id)==0) {
			setNewslist(newsservice.GetAllNews());
		}else {
			setNewslist(newsservice.FindBytype(Integer.parseInt(type_id)));
		}
	    if (check!=null) {
	    	return "newslist-manage";
			
		}else {
			return "newslist";
		}
	    
		
	}











}
