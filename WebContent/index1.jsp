<%@ page import="java.util.*,servlet.Paination" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table align="center" border="1" height="150">
  	<jsp:useBean id="pa" class="servlet.Paination" scope="session"/>
     <%      
         pa.selectBySQL();
         List<Object[]> notes=pa.getCurrentPageList();

         for(int m=0;m<notes.size();m++){
            
         	out.println("<tr align='center'  bgcolor='#FFCC99'>");
            Object[] note=notes.get(m);
            for(int n=0;n<note.length;n++){
             out.println("<td>"+note[n]+"</td>");
            }
            out.println("<br>");
            out.println("</tr>");
          }
     %>      
      <form action="index1.jsp" method="post" name="page" onsubmit="return checkPage(page)">
      <tr align="right">
       <td>
       共有<font color="red" size="1"><%=pa.getTotalRows() %></font>条记录
       当前是第<font color="red" size="1"><%=pa.getCurrentPage()+"/"+pa.getTotalPages() %></font>页
       <%if(pa.isHasPreviousPage())
       out.print("<a href='index2.jsp?requestPage=previousPage'>上一页</a>"); %>
       <%if(pa.isHasNextPage())
       out.print("<a href='index2.jsp?requestPage=nextPage'>下一页</a>"); %>
       
       </td>
       </tr>
       </form>

</table>

</body>
</html>