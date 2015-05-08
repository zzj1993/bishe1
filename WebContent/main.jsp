<%@ page import="java.util.*" %> 
<%@ page import="common0503.Result" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
//	int userid = Integer.parseInt(session.getAttribute("user").toString());
	int userid = 1;
	Map<String,String> map=null;
	Map<String,String> map1=null;
	Map<String,String> map2=null;
	Result rt = new Result();
	try{
		map = rt.get1MovieRating(userid);
		map1 = rt.IselectRecByUID(userid);
		map2 = rt.UselectRecByUID(userid);
	}catch(Exception e){
		e.printStackTrace();
	}
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>success</title>
<link type="text/css" rel="stylesheet" href="css/reset.css">
<link type="text/css" rel="stylesheet" href="css/main.css">
<link type="text/css" rel="stylesheet" href="css/jquery.slideBox.css"/>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.slideBox.min.js"></script>

<link rel="stylesheet" type="text/css" href="template/ue-content/templates/images/ue_grid.css" />
<link rel="stylesheet" type="text/css" href="template/ue-content/templates/images/style.css" />
<link rel="stylesheet" type="text/css" href="template/ue-content/templates/css/style.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script language="javascript" src="script/jquery.easing.min.js"></script>
<script language="javascript" src="script/custom.js"></script>

<script>
jQuery(function($){
	$('#demo').slideBox({
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'linear',//swing,linear//滚动特效
		delay : 3,//滚动延迟时间，单位：秒
		hideClickBar : false,//不自动隐藏点选按键
		clickBarRadius : 10
	});
});
function login(){
	location.href="login.html";
}
function register(){
	location.href="register.html";
}
</script>
</head>

<body>
<div class="headerBar">
	<div class="header">
		<div class="comWidth">
			<div class="login fr">
				<div class="loginmenu">
					<a title="登录或注册"></a>
				</div>
					<ul>
        				<li class="openlogin"><a href="http://www.jq-school.com/" onclick="return false;">登录</a></li>
        				<li class="reg"><a href="http://www.jq-school.com/" onclick="return false;">注册</a></li>
      				</ul>
			</div>
		</div>
	</div>
	<div class="logoBar red_logo">
		<div class="comWidth">			
			<h3 class="welcome_title">movieRec</h3>
		</div>
	</div>
		
	<div class="navBox">
		<div class="comWidth clearfix">
			<div class="shopClass fl">
				<h3>navigation</h3>
				<div class="shopClass_show">
					
					<table>
						<tr class="hang">
							<td><a href="#">Animation</a></td>
							<td><a href="#">Children's</a></td>
						</tr>
						<tr class="hang">
							<td><a href="#">Comedy</a></td>
							<td><a href="#">Adventure</a></td>
						</tr>
						<tr class="hang">
							<td><a href="#">Fantasy</a></td>
							<td><a href="#">Romance</a></td>
						</tr>
						<tr class="hang">
							<td><a href="#">Drama</a></td>
							<td><a href="#">Action</a></td>
						</tr>
						<tr class="hang">
							<td><a href="#">Thriller</a></td>
							<td><a href="#">Horror</a></td>
						</tr>
						<tr class="hang">
							<td><a href="#">Crime</a></td>
							<td><a href="#">Sci-Fi</a></td>
						</tr>
						<tr class="hang">
							<td><a href="#">Documentary</a></td>
							<td><a href="#">Country</a></td>
						</tr>
						<tr class="hang">
							<td><a href="#">War</a></td>
							<td><a href="#">Musical</a></td>
						</tr>
						<tr class="hang">
							<td><a href="#">Mystery</a></td>
							<td><a href="#">Other</a></td>
						</tr>
					</table>
				</div>
			</div>
			</div>
			</div>
</div>
<div class="banner comWidth clearfix">
	<div id="demo" class="banner_bar banner_big slideBox">
		<ul class="imgBox items">
			<li><a href="#" title="speed7"><img src="images/banner/speed7.jpg" alt="banner"></a></li>
			<li><a href="#" title="fuchou2"><img src="images/banner/fuchou2.jpg" alt="banner"></a></li>
			<li><a href="#" title="speed7"><img src="images/banner/speed7.jpg" alt="banner"></a></li>
			<li><a href="#" title="fuchou2"><img src="images/banner/fuchou2.jpg" alt="banner"></a></li>
			<li><a href="#" title="speed7"><img src="images/banner/speed7.jpg" alt="banner"></a></li>
		</ul>

	</div>
</div>
<div class="TList comWidth">
	<div class="movieList">
		<table class="hovertable"> 
			<tr><th>history</th></tr>
			<%int i=1;%>
			<%for(String movie:map.keySet()){
				if(i<=19){
			%>						
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td><a href="#"><%=movie%></a></td>
			</tr> 
			<%i++;}else{break;}}%>
			<tr>
				<td><a href="more.jsp">查看更多>></a></td>
			</tr>					 
		</table>	
	</div>
	<div class="movieList">
		<table class="hovertable"> 
			<tr><th>guess you like</th></tr>
			<%for(String movie:map1.keySet()){%>						
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td><a href="#"><%=movie%></a></td>
			</tr> 
			<%}%>						 
		</table>
	</div>
	<div class="movieList">
		<table class="hovertable"> 
			<tr><th>others also see</th></tr>
			<%for(String movie:map2.keySet()){%>						
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td><a href="#"><%=movie%></a></td>
			</tr> 
			<%}%>						 
		</table>
	</div>
</div>


<div class="hr_25"></div>
<div class="footer">
	<p><a href="#">公司简介</a><i>|</i><a href="#">相关公告</a><i>|</i> <a href="#">招纳贤士</a><i>|</i><a href="#">联系我们</a><i>|</i>客服热线：123-456-7890</p>
	<p>Copyright &copy; 2006 - 2015 版权所有&nbsp;&nbsp;&nbsp;京ICP备09037834号&nbsp;&nbsp;&nbsp;京ICP证B1034-8373号&nbsp;&nbsp;&nbsp;某市公安局XX分局备案编号：123456789123</p>
	<p class="web"><a href="#"><img src="images/webLogo.jpg" alt="logo"></a><a href="#"><img src="images/webLogo.jpg" alt="logo"></a><a href="#"><img src="images/webLogo.jpg" alt="logo"></a><a href="#"><img src="images/webLogo.jpg" alt="logo"></a></p>
</div>



<div id="loginalert">
  <div class="pd20 loginpd">
    <h3><i class="closealert fr"></i>
      <div class="clear"></div>
    </h3>
    <div class="loginwrap">
      <div class="loginh">
        <div class="fl">会员登录</div>
        <div class="fr">还没有账号<a id="sigup_now" href="http://www.jq-school.com/" onclick="return false;">立即注册</a></div>
        <div class="clear"></div>
      </div>
      <h3><span>邮箱登录</span><span class="login_warning">用户名或密码错误</span>
        <div class="clear"></div>
      </h3>
      <div class="clear"></div>
      <form action="" method="post" id="login_form">
        <div class="logininput">
          <input type="text" name="username" class="loginusername" value="邮箱/用户名" />
          <input type="text" class="loginuserpasswordt" value="密码" />
          <input type="password" name="password" class="loginuserpasswordp" style="display:none" />
        </div>
        <div class="loginbtn">
          <div class="loginsubmit fl">
            <input type="submit" value="登录" />
            <div class="loginsubmiting">
              <div class="loginsubmiting_inner"></div>
            </div>
          </div>
          <div class="logcheckbox fl">
            <input id="bcdl" type="checkbox" checked="true" />
            保持登录</div>
          <div class="fr"><a href="http://www.jq-school.com/">忘记密码?</a></div>
          <div class="clear"></div>
        </div>
      </form>
    </div>
  </div>
  <div class="thirdlogin">
    <div class="pd50">
      <h4>用第三方帐号直接登录</h4>
      <ul>
        <li id="sinal"><a href="http://weibo.com/jqueryschool">微博账号登录</a></li>
        <li id="qql"><a href="http://t.qq.com/jqueryschool">QQ账号登录</a></li>
        <div class="clear"></div>
      </ul>
      <div class="clear"></div>
    </div>
  </div>
</div>
<div id="reg_setp">
  <div class="back_setp">返回</div>
  <div class="blogo"></div>
  <div id="setp_quicklogin">
    <h3>您可以选择以下第三方帐号直接登录jq-school，一分钟完成注册</h3>
    <ul class="quicklogin_socical">
      <li class="quicklogin_socical_weibo"><a href="http://www.jq-school.com/">微博帐号注册</a></li>
      <li class="quicklogin_socical_qq"><a href="http://www.jq-school.com/">QQ帐号注册</a></li>
    </ul>
  </div>
</div>
</body>
</html>