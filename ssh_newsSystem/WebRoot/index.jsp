<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav nav-pills navbar-right">
      <li class="active"><a href="index.jsp">主页</a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">新闻分类 <span class="caret"></span></a>
        <ul class="dropdown-menu" role="menu">
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
      <li><a href="register.jsp">注册</a></li>
      <li><a href="#">联系我们</a></li>
    </ul>
  </div><!-- /.navbar-collapse -->
  <div class="page-header"></div>
</nav>
  <!------------------------------------ 导航结束 ------------------------------------>
  <!------------------------------------ 图片滚动开始 ------------------------------------>
  <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
	<div class="item active">
	  <img src="img/lunbo1.png" alt="0">
	  <div class="carousel-caption">
	    <h3></h3>
	    <p>中国002航母假想CG！四弹射器搭载固定翼预警机</p>
	  </div>
	</div>
		<div class="item">
	  <img src="img/lunbo2.png" alt="1">
	  <div class="carousel-caption">
	    <h3></h3>
	    <p>15号量产型黄皮歼20亮相！与波音客机同框出镜</p>
	  </div>
	</div>
		<div class="item">
	  <img src="img/lunbo3.png" alt="1">
	  <div class="carousel-caption">
	    <h3></h3>
	    <p>俄罗斯机械武器</p>
	  </div>
	</div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
  </a>
</div>
	<script type="text/javascript">
	$('.carousel').carousel({
	  interval: 2000
	});
	</script>
  <!------------------------------------ 图片滚动结束 ------------------------------------>
  <!------------------------------------ 新闻分类开始 ------------------------------------>
  <!-- 1 -->
  <div class="row">
  <div class="col-xs-4">
     <h2 class="page-header">时政</h2>
     <div class="thumbnail">
     <img src="img/shizheng.jpg">
     <p>在朝鲜阅兵式上，朝鲜人民军多型自行火炮亮相，履带式地盘上背着敞开式、半封闭、封闭式炮塔，炮管粗壮，看上去相当霸气.</p>
     <form action="getnewslist?type_id=1" method="post"><button type="submit" class="btn btn-primary btn-xs">更多</button></form>
	</div>
  </div>
  <div class="col-xs-4">
     <h2 class="page-header">科技</h2>
     <div class="thumbnail">
     <img src="img/keji.jpg">
     <p>这是一组来自于摄影师Igor Demchenko的超现实作品，描述人类城市与自然动物的关系。在他的作品里，体型巨大的动物才是城市中的主角。摄影师意在用这种形式唤起公众保护动物的意识。</p>
		<form action="getnewslist?type_id=2" method="post"><button type="submit" class="btn btn-primary btn-xs">更多</button></form>
     </div>
  </div>
  <div class="col-xs-4">
     <h2 class="page-header">娱乐</h2>
     <div class="thumbnail">
     <img src="img/yule.jpg">
     <p> 今年2017快乐男声经过一番残酷的300进30大淘杀，全国30强已于上周六正式诞生。李健、陈粒在场上“互怼”抢人争得火热，而罗志祥却在暗中与选手结盟，率先组成了一支唱跳型的“猪猪战队”。。</p>
		<form action="getnewslist?type_id=3" method="post"><button type="submit" class="btn btn-primary btn-xs">更多</button></form>
	</div>
  </div>
  </div>
  <!-- 1 -->
  <!-- 2 -->
  <div class="row">
  <div class="col-xs-4">
     <h2 class="page-header">体育</h2>
     <div class="thumbnail">
     <img src="img/sport.jpg">
     <p>北京时间6月18日，联合会杯继续进行，欧洲杯冠军葡萄牙2比2憾平墨西哥。C罗助攻夸雷斯马进球，埃尔南德斯扳平，塞德里克第86分钟进球，莫雷诺第91分钟扳平.</p>
<form action="getnewslist?type_id=4" method="post"><button type="submit" class="btn btn-primary btn-xs">更多</button></form>
     </div>
  </div>
  <div class="col-xs-4">
     <h2 class="page-header">游戏</h2>
     <div class="thumbnail">
     <img src="img/youxi.jpg">
     <p><br/>《尘埃4》于6月9日发售，已经过去十天左右的时间。根据Steamspy的数据统计，《尘埃4》的销量已经突破4万件。</p>
<form action="getnewslist?type_id=5" method="post"><button type="submit" class="btn btn-primary btn-xs">更多</button></form>
	</div>
  </div>
  <div class="col-xs-4">
     <h2 class="page-header">财经</h2>
     <div class="thumbnail">
     <img src="img/caijin.jpg">
     <p>5月全国汽车销量数据在忧虑中出炉，同比微降0 ．1%；与此同时，1-5月销量累计增速也比1-4月继续回落。市场期望值走低，宏观经济形势以及消费环境的低迷状态，让众多汽车生产厂商的表现继续分化，有人欢喜有人愁。</p>
<form action="getnewslist?type_id=6" method="post"><button type="submit" class="btn btn-primary btn-xs">更多</button></form>
     </div>
  </div>
  </div>
  <!-- 2 -->
  <!------------------------------------ 新闻分类结束 ------------------------------------>
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
