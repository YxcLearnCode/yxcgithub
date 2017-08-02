package com.sise.yxc.service;

import java.util.Iterator;
import java.util.List;

import com.sise.yxc.dao.UserinfoDAO;
import com.sise.yxc.model.Userinfo;

public class UserService {
 /**
 * 用户信息DAO
 */
 private UserinfoDAO userinfodao;
 /**
  * dao的get&set方法
  */

public UserinfoDAO getUserinfodao() {
	return userinfodao;
}

public void setUserinfodao(UserinfoDAO userinfodao) {
	this.userinfodao = userinfodao;
}
  /**
   * 登录校验
   * 检验username&password,返回结果给userinfo实体
   */
 public Userinfo CheckLogin(String username,String password){
	List userlist=userinfodao.findByUsername(username);
	Iterator iter=userlist.iterator();
	if (iter.hasNext()) {
		Userinfo userinfo=(Userinfo) iter.next();
		  if (userinfo.getPassword().equals(password)) {
			System.out.println("登录成功！");
			return userinfo;
		}else {
			System.out.println("登录失败！密码错误！");
			return null;
		}
	}
	System.out.println("登录失败,用户名不存在!");
	return null;
 }
 
 /**
  * 注册新用户
  */
 public boolean RegisterUser(String username,String password,String nickname,String sex,String info){
	  List userlist=userinfodao.findByUsername(username);
	  Iterator iter=userlist.iterator();
	  if (!iter.hasNext()) {
		Userinfo newuser=new Userinfo();
		newuser.setUsername(username);
		newuser.setPassword(password);
		newuser.setNickname(nickname);
		newuser.setSex(sex);
		newuser.setInfo(info);
		newuser.setIsadmin("n");
		userinfodao.save(newuser);
		System.out.println("注册成功！");
		return true;
	}
	  System.out.println("注册失败,用户已经存在");
	  return false;
 }
 /**
  * 更新用户信息
  */
 public void UpdateUser(Userinfo user){
	 userinfodao.attachDirty(user);
	 System.out.println("用户信息更新成功!");
 }
}

