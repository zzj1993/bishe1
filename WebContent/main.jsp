<%@ page import="java.util.*" %> 
<%@ page import="itemCF.DBUtil,itemCF.ItemMain" %>
<%@ page import="userCF.UserMain"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	int userid = Integer.parseInt(session.getAttribute("user").toString());
	//Map<Integer,Double> m=null;
	List<String> list=null;
	
	try{
		DBUtil db = new DBUtil();
		//m = db.get1MovieRating(userid); = 
		list = db.get1MovieRating(userid);
	}catch(Exception e){
		e.printStackTrace();
	}
	
	ItemMain im = new ItemMain();
	List<String> list1 = im.recommend(userid);
	
	UserMain  um= new UserMain();
	List<String> list2 = um.recommend(userid);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>success</title>
<link type="text/css" rel="stylesheet" href="css/reset.css">
<link type="text/css" rel="stylesheet" href="css/main.css">
</head>

<body>
<div class="headerBar">
	<div class="topBar">
		<div class="comWidth">
			<div class="rightArea">
				欢迎来到movieRec！<a href="#">[登录]</a><a href="#">[免费注册]</a>
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
				<div class="shopClass_list hide">
					<div class="shopClass_cont">
						<dl class="shopList_item">
							<dt>电脑装机</dt>
							<dd>
								<a href="#">文字啊</a><a href="#">文字字啊</a><a href="#">文字字字啊</a><a href="#">文字啊</a><a href="#">文字</a><a href="#">文字啊</a>
							</dd>
						</dl>
						<dl class="shopList_item">
							<dt>电脑装机</dt>
							<dd>
								<a href="#">文字啊</a><a href="#">文字字啊</a><a href="#">文字字字啊</a><a href="#">文字啊</a><a href="#">文字</a><a href="#">文字啊</a><a href="#">文字啊</a><a href="#">文字字啊</a><a href="#">文字字字啊</a><a href="#">文字啊</a><a href="#">文字</a><a href="#">文字啊</a><a href="#">文字啊</a>
							</dd>
						</dl>
						<dl class="shopList_item">
							<dt>电脑装机</dt>
							<dd>
								<a href="#">文字啊</a><a href="#">文字字啊</a><a href="#">文字字字啊</a><a href="#">文字啊</a><a href="#">文字</a><a href="#">文字啊</a><a href="#">文字啊</a><a href="#">文字字啊</a><a href="#">文字字字啊</a><a href="#">文字啊</a><a href="#">文字</a><a href="#">文字啊</a><a href="#">文字啊</a>
							</dd>
						</dl>
						<dl class="shopList_item">
							<dt>电脑装机</dt>
							<dd>
								<a href="#">文字啊</a><a href="#">文字字啊</a><a href="#">文字字字啊</a><a href="#">文字啊</a><a href="#">文字</a><a href="#">文字啊</a><a href="#">文字啊</a><a href="#">文字字啊</a><a href="#">文字字字啊</a><a href="#">文字啊</a><a href="#">文字</a><a href="#">文字啊</a><a href="#">文字啊</a>
							</dd>
						</dl>
						<dl class="shopList_item">
							<dt>电脑装机</dt>
							<dd>
								<a href="#">文字啊</a><a href="#">文字字啊</a><a href="#">文字字字啊</a><a href="#">文字啊</a><a href="#">文字</a><a href="#">文字啊</a><a href="#">文字啊</a><a href="#">文字字啊</a><a href="#">文字字字啊</a><a href="#">文字啊</a><a href="#">文字</a><a href="#">文字啊</a><a href="#">文字啊</a>
							</dd>
						</dl>
						<div class="shopList_links">
							<a href="#">文字测试内容等等<span></span></a><a href="#">文字容等等<span></span></a>
						</div>
					</div>
				</div>
			</div>
			</div>
			</div>
</div>
<div class="banner comWidth clearfix">
	<div class="banner_bar banner_big">
		<ul class="imgBox">
			<li><a href="#"><img src="images/banner/speed7.jpg" alt="banner"></a></li>
			<li><a href="#"><img src="images/banner/fuchou2.jpg" alt="banner"></a></li>
		</ul>
		<div class="imgNum">
			<a href="#" class="active"></a><a href="#"></a><a href="#"></a><a href="#"></a>
		</div>
	</div>
</div>
<div class="showBox">
	<div class="movieList comHeight">
		<table class="hovertable"> 
			<tr><th>history</th></tr>
			<%int i=1;%>
				<%for(String movie:list){
					if(i<=19){
				%>						
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td><a href="#"><%=movie%></a></td>
			</tr> 
			<%i++;}else{break;}}%>
			<tr>
				<td><a href="#">查看更多>></a></td>
			</tr>					 
		</table>	
	</div>
	<div class="movieList comHeight">
		<table class="hovertable"> 
			<tr><th>guess you like</th></tr>
			<%for(String movie:list1){%>						
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td><a href="#"><%=movie%></a></td>
			</tr> 
			<%}%>						 
		</table>
	</div>
	<div class="movieList comHeight">
		<table class="hovertable"> 
			<tr><th>others also see</th></tr>
			<%for(String movie:list2){%>						
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

</body>
</html>