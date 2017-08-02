package com.sise.yxc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.registry.infomodel.User;

import com.sise.yxc.dao.CategoryDAO;
import com.sise.yxc.dao.NewsDAO;
import com.sise.yxc.model.Category;
import com.sise.yxc.model.News;
import com.sise.yxc.model.Userinfo;
import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.net.www.content.text.plain;

public class NewsService {
/**
 * DAO
 *
 */
 private NewsDAO newsdao;
 private CategoryDAO categorydao;
 /**
  * getter/setter方法
  */
public NewsDAO getNewsdao() {
	return newsdao;
}
public void setNewsdao(NewsDAO newsdao) {
	this.newsdao = newsdao;
}
public CategoryDAO getCategorydao() {
	return categorydao;
}
public void setCategorydao(CategoryDAO categorydao) {
	this.categorydao = categorydao;
}
 /**
  * 根据种类编号查询新闻
  */
 public List<News> FindBytype(int type_id){
	  Category category=categorydao.findById(type_id);
	  Set<News> newsset=category.getNewses();
	  List<News> newslist=new ArrayList<News>(newsset);
	  for (Iterator<News> iter=newslist.iterator(); iter.hasNext();) {
		  News news = (News) iter.next();
		  System.out.println(news.getTitle());
	}
	  return newslist;
	  
 }
 /**
  * 获取所有新闻
  */
 public List<News> GetAllNews(){
	 return newsdao.findAll();
 }
 
 /**
  * 向数据库添加新闻
  */
 public void addNews(String title,int type_id,int user_id,String content){
	 News news=new News();
	 news.setTitle(title);
	 news.setContent(content);
	 Category category=new Category();
     category.setTypeId(type_id);
	 news.setCategory(category);
	 Userinfo user=new Userinfo();
	 user.setUserId(user_id);
	 news.setUserinfo(user);
	 news.setModifyTime(new Date());
	 newsdao.save(news);
	 System.out.println("添加新闻成功！");
	 
 }
 
 
 /**
  * 根据id删除新闻
  */
 
 public void DeleteNewsbyID(int news_id){
	News news=newsdao.findById(news_id);
	newsdao.delete(news);
	System.out.println("删除成功!");
	
 }
 
 /**
  * 根据Id更新新闻
  */
 public void UpdateNews(int news_id,String title,String content){
	  News news=newsdao.findById(news_id);
	  news.setNewsId(news.getNewsId());
	  news.setTitle(title);
	  news.setContent(content);
	  newsdao.attachDirty(news);
	  System.out.println(news_id+"更新成功!");
 }
 
 /**
  * 根据新闻Id查找新闻
  */
 public News FindByID(int news_id){
	 return newsdao.findById(news_id);
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}
