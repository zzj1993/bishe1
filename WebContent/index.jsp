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
    
    <title>��Ӱ�Ƽ�ϵͳ</title>
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
   alert("�û��Ų���Ϊ��!");
   form.userID.focus();
   return false;
  }
   else if(isNaN(form.userID.value))
{
  alert("�û��ű���Ϊ���֣�");
  form.userID.focus();
  return false;
}
   else if(form.count.value=="")
  {
   alert("�Ƽ�������Ϊ��");
   form.count.focus();
   return false;
  }
else if(isNaN(form.count.value))
{
  alert("�Ƽ�������Ϊ���֣�");
  form.count.focus();
  return false;
}
 }
</script>
</head>
  
<body>
<form action="userServ" method="post">   
<p align="center"><font size="+3" face="�����п�" color="#ffffff">��ӭ�����Ӱ�Ƽ�ϵͳ</font><br><br><br><br></p>
<FORM action="movie" method="post" name="form" onSubmit="return userFormCheck();">
<p align="center">
 �û���:<INPUT type="text" name="userID"><br><br/>
 ��&nbsp&nbsp��:<input type="password" name="userPwd"><br></br>
<INPUT type="submit"  value="��¼" name="login" style="font-size:13pt;" face="�����п�" color="#ffffff">    

</FORM> <br>
</form>
  </body>
  
</html>
