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
</head>
<style>
.history{
	float:left;
	margin-right:15px;
}
.like{
	float:left;
	margin-right:15px;
}
.alsosee{
	float:left;
}
</style>

<body>
<div class="history">
					历史记录<br>
					<table style="width:300px;  border:blue solid 1px;"> 
						<tr> 
  							<td></td> 
						</tr> 
						<%int i=1; %>
						<%for(String movie:list){
							%>
						<tr>
							<td width=10%><%=i++ %></td>
  							<td style="overflow:hidden" width=90%><%=movie%></td> 
						</tr> 
						<%}%>
						 
					</table>	
</div>
<div class="like">

				猜你喜欢<br>
					<table style="width:300px; border:blue solid 1px;"> 
						<tr> 
  							<td></td> 
						</tr> 
						<%int j=1; %>
						<%for(String movie:list1){
							%>
						<tr>
							<td width=10%><%=j++ %></td>
  							<td style="overflow:hidden" width=90%><%=movie%></td> 
						</tr> 
						<%}%>
					</table>
</div>
<div class="alsosee">
				其他人还看了<br>
					<table style="width:300px; border:blue solid 1px;">  
						<tr> 
  							<td></td> 
						</tr>
						<%int k=1; %>
						<%for(String movie:list2){
							%>
						<tr>
							<td width=10%><%=k++ %></td>
  							<td style="overflow:hidden" width=90%><%=movie%></td> 
						</tr> 
						<%}%> 
					</table>
</div>
</body>
</html>