<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%!  int pageSize=4;
 int pageCount;
 int showPage;
 %>

<!-- 连接数据库并从数据库中调取记录-->
<%
 Connection con;
 Statement sql;
 ResultSet rs;

 try{
	 Class.forName("com.mysql.jdbc.Driver"); 
 }catch(ClassNotFoundException e){
 }

 try{
	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","123456");
  sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
  //返回可滚动的结果集 
  rs=sql.executeQuery("select * from result1 where userid=1 order by score desc limit 0,20");
  //将游标移到最后一行 
  rs.last();
  //获取最后一行的行号 
  int recordCount=rs.getRow();
  //计算分页后的总数 
  pageCount=(recordCount%pageSize==0)?(recordCount/pageSize):(recordCount/pageSize+1);

  //获取用户想要显示的页数：
  String integer=request.getParameter("showPage");
  if(integer==null){
   integer="1";
  }
  try{
	  showPage=Integer.parseInt(integer);
  }catch(NumberFormatException e){
   	  showPage=1;
  }
  if(showPage<=1){
   	  showPage=1;
  }
  if(showPage>=pageCount){
      showPage=pageCount;
  }

  //如果要显示第showPage页，那么游标应该移动到的position的值是：
  int position=(showPage-1)*pageSize+1;
  //设置游标的位置
  rs.absolute(position);
  //用for循环显示本页中应显示的的记录
  for(int i=1;i<=pageSize;i++){  
 %>
   <table>
    <tr> 
     <th><%=rs.getInt("userid") %></th>
     <td>发表于：<%=rs.getInt("movieid") %></td>
    </tr>
    <tr >
     <th colspan="3"><textarea><%=rs.getDouble("score") %></textarea></th>
    </tr>
   </table>

 <%  
   rs.next();
  } 
  rs.close();
  con.close();
  }
  catch(Exception e){
  e.printStackTrace();}
 %>
 <br>
 第<%=showPage %>页（共<%=pageCount %>页）
 <br>
 <a href="ShowMessages.jsp?showPage=1">首页</a>
 <a href="ShowMessages.jsp?showPage=<%=showPage-1%>">上一页</a>
<% //根据pageCount的值显示每一页的数字并附加上相应的超链接
  for(int i=1;i<=pageCount;i++){
 %>
   <a href="ShowMessages.jsp?showPage=<%=i%>"><%=i%></a>
<% }
 %> 
 <a href="ShowMessages.jsp?showPage=<%=showPage+1%>">下一页</a>
 <a href="ShowMessages.jsp?showPage=<%=pageCount%>">末页</a>
 <!-- 通过表单提交用户想要显示的页数 -->
 <form action="" method="get">
  跳转到第<input type="text" name="showPage" size="4">页
  <input type="submit" name="submit" value="跳转">
 </form> 
</body>
</html>