<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
  <div class="hide"><s:property value="user"/></div>
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
      <li class="active"><a href="login.jsp">
      ${user.nickname}
      </a></li>
      <li><a href="register.jsp">注册</a></li>
      <li><a href="#">联系我们</a></li>
    </ul>
  </div><!-- /.navbar-collapse -->
  <div class="page-header"></div>
</nav>
  <!------------------------------------ 导航结束 ------------------------------------>
  <!-- 列表开始------------------------------------------------------------------------- -->
<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">修改新闻</h3>
  </div>
  <div class="panel-body">
  <br/>
	<div class="col-lg-3">
		<ul class="nav nav-pills nav-stacked">
			<li><a href="personmanage.jsp">个人信息管理</a></li>
			<li class="dropdown active">
			       <a href="getnewslist?type_id=0&check=1" class="dropdown-toggle" data-toggle="dropdown">新闻内容管理 <b class="caret"></b></a>
			       <ul class="dropdown-menu">
			          <li><a href="getnewslist?type_id=1&check=1">时政</a></li>
			          <li class="divider"></li>
			          <li><a href="getnewslist?type_id=2&check=1">科技</a></li>
			          <li class="divider"></li>
			          <li><a href="getnewslist?type_id=3&check=1">娱乐</a></li>
			          <li class="divider"></li>
			          <li><a href="getnewslist?type_id=4&check=1">体育</a></li>
			          <li class="divider"></li>
			          <li><a href="getnewslist?type_id=5&check=1">游戏</a></li>
			          <li class="divider"></li>
			          <li><a href="getnewslist?type_id=6&check=1">财经</a></li>
			       </ul>
			</li>
			<li><a href="addnews.jsp">发布新闻</a></li>
			<li><a href="developerinfo.jsp">开人人员介绍</a></li>			
			<li><a href="logoutsuccess.jsp">注销</a></li>
		</ul>	
	</div>
	<div class="col-lg-9">
		<div class="hide"><s:property value="news"/></div>
		<form class="form-horizontal" action="newsmanage?type=edit&news_id=${news.newsId}" method="post">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">标题：</label>
		    <div class="col-sm-6">
		      <input name="title" type="text" class="form-control" id="title" placeholder="新闻标题" value="${news.title}">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">内容：</label>
		    <div class="col-sm-6">
		      <textarea class="form-control" style="height:200px;" id="newscontent" name="content" placeholder="新闻内容">${news.content}</textarea>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label"></label>
		    <div class="col-sm-2">
		      <button type="submit" class="btn btn-primary btn-block">提交修改</button>
		    </div>
		  </div>
		</form>
		</div>
  </div>
</div> 
  <!-- 列表结束------------------------------------------------------------------------- -->
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
