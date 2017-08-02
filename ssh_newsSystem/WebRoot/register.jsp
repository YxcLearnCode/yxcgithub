<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新闻发布系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <link href="css/bootstrap.css" rel="stylesheet" media="screen">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.js"></script>
    <s:head/><!-- 错误信息变成Css红色 -->
  </head>
  
  <body>
  <div class="container">
  <!------------------------------------ 导航开始 ------------------------------------>
  <nav class="navbar" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">新闻发布系统</a>
  </div>
  <div class="col-xs-4"></div>
  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav nav-pills navbar-right">
      <li><a href="index.jsp">主页</a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">新闻分类 <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="getnewslist?type_id=1">时政</a></li>
          <li class="divider"></li>
          <li><a href="getnewslist?type_id=2">科技</a></li>
          <li class="divider"></li>
          <li><a href="getnewslist?type_id=3">娱乐</a></li>
          <li class="divider"></li>
          <li><a href="getnewslist?type_id=4">体育</a></li>
          <li class="divider"></li>
          <li><a href="getnewslist?type_id=5">游戏</a></li>
          <li class="divider"></li>
          <li><a href="getnewslist?type_id=6">财经</a></li>
        </ul>
      </li>
      <li><a href="login.jsp">
      <%if(request.getAttribute("nickname")!=null){
    	  out.print(request.getAttribute("nickname"));
      }else{
    	  out.print("登陆");
      }
      %>
      </a></li>
      <li class="active"><a href="register.jsp">注册</a></li>
      <li><a href="#">联系我们</a></li>
    </ul>
  </div><!-- /.navbar-collapse -->
  <div class="page-header"></div>
</nav>
  <!------------------------------------ 导航结束 ------------------------------------>
  <!-- 注册开始------------------------------------------------------------------------- -->
<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">用户注册</h3>
  </div>
  <div class="panel-body">
	<form class="form-horizontal" action="register" method="post">
		</br>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">账号：</label>
		    
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="username" placeholder="username">
		      <s:fielderror fieldName="username" style="backgound:red"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">密码：</label>
		    
		    <div class="col-sm-4">
		      <input type="password" class="form-control" name="password" placeholder="Password">
		      <s:fielderror fieldName="password"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">昵称：</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="nickname" placeholder="nickname">
		      <s:fielderror fieldName="nickname"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">性别：</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="sex" placeholder="sex">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">介绍：</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="info" placeholder="information">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label"></label>
		    <div class="col-sm-4">
		      <button type="submit" class="btn btn-primary">&nbsp&nbsp&nbsp注册&nbsp&nbsp&nbsp</button>&nbsp&nbsp&nbsp&nbsp已注册?去<a href="login.jsp">登录</a>
		    </div>
		  </div>
	</form>
  </div>
</div>
	  
  <!-- 注册结束------------------------------------------------------------------------- -->
  <!------------------------------------ 底部开始 ------------------------------------>
  <footer>
  <div class="page-header"></div>
  <div class="row">
  <div class="col-xs-2">HR.Software School</div>
  <div class="col-xs-9"></div>2017.6
  </div>
  </footer>
  <!------------------------------------ 底部结束 ------------------------------------>
  </div>
  </body>
</html>
