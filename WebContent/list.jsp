<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>货品列表</title>
</head>

<body>
<form action="/list" method="post">
<table border="1">
 <tr>
 	<td>货品名称</td> 
 	<td><input type="text" name="productName"></td> 
 </tr>
 <tr>
	<td>品牌</td> 
    <td><input type="text" name="brand"></td> 
 </tr>

 <tr>

 <td>零售价</td> 

 <td><input type="text" name="salePrice"></td> 

 </tr>

 <tr align="center">

 <td colspan="2"><input type="submit" value="查询"></td> 

 </tr>

</table>

</form>

<table border="1">

 <tr>

 <th>货品</th>

 <th>零售价</th>

 <th>品牌</th>

 <th>折扣</th>

 <th>供应商</th>

 <th>操作</th>

 </tr>

 <c:forEach items="${pageResult.list}" var="product">

 <tr>

 <td>${product.productName}</td>

 <td>${product.salePrice}</td>

 <td>${product.brand}</td>

 <td>${product.cutoff}</td>

 <td>${product.supplier}</td>

 <td><a href='/employee?cmd=remove&id=${employee.id}'>删除</a>  <a href='/employee?cmd=edit&id=${employee.id}'>修改</a></td>

 </tr>

 </c:forEach>

 <tr>

 <th><a href="/list?currentPage=${pageResult.firstPage}">首页</a>${pageResult.firstPage}</th>

 <th><a href="/list?currentPage=${pageResult.prePage}">上一页</a>${pageResult.prePage}</th>

 <th><a href="/list?currentPage=${pageResult.nextPage}">下一页</a>${pageResult.nextPage}</th>

 <th><a href="/list?currentPage=${pageResult.totalPage}">尾页</a>${pageResult.totalPage}</th>

 <th>当前${pageResult.currentPage}/${pageResult.totalPage}页</th>

 <th>总条数:${pageResult.count}</th>

 </tr>

</table>

</body>

</html>