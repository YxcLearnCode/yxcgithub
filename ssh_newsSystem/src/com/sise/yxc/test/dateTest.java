package com.sise.yxc.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sise.yxc.dao.CategoryDAO;
import com.sise.yxc.model.Category;

public class dateTest {
public static void main(String[] args) {
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	CategoryDAO categorydao = (CategoryDAO) ac.getBean("CategoryDAO");
	List categorylist = categorydao.findAll();
	for(java.util.Iterator iter = categorylist.iterator();iter.hasNext();){
		Category category  = (Category)iter.next();
		System.out.println(category.getTypeId()+"___"+category.getTypeName());
}
}
}
