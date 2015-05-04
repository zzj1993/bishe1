<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=GB2312" />
    <base href="<%=basePath%>">
    
    <title>电影推荐系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="javascript" type="">
  function userFormCheck()
 {
   if(form.userID.value=="")
  {
   alert("用户号不能为空!");
   form.userID.focus();
   return false;
  }
   else if(isNaN(form.userID.value))
{
  alert("用户号必须为数字！");
  form.userID.focus();
  return false;
}
   else if(form.count.value=="")
  {
   alert("推荐数不能为空");
   form.count.focus();
   return false;
  }
else if(isNaN(form.count.value))
{
  alert("推荐数必须为数字！");
  form.count.focus();
  return false;
}
 }
</script>
</head>
  
<body>
<form action="userServ" method="post">   
<p align="center"><font size="+3" face="华文行楷" color="#ffffff">欢迎进入电影推荐系统</font><br><br><br><br></p>
<FORM action="movie" method="post" name="form" onSubmit="return userFormCheck();">
<p align="center">
 用户号:<INPUT type="text" name="userID"><br><br/>
 密&nbsp&nbsp码:<input type="password" name="userPwd"><br></br>
<INPUT type="submit"  value="登录" name="login" style="font-size:13pt;" face="华文行楷" color="#ffffff">    

</FORM> <br>
</form>
  </body>
  
</html>
