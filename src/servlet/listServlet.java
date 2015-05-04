package servlet;

import itemCF.DBUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class listServlet extends HttpServlet {
	
	 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		 String currentPage = request.getParameter("currentPage");
		 PageResult pageResult = new PageResult();
		    
		     pageResult.setCurrentPage(Integer.parseInt(currentPage));     

		     pageResult.setSize(5);  

//		     List<Product> list = dao.list(pageResult.getCurrentPage(), pageResult.getSize());

//		 pageResult.setList(list);

		 DBUtil db = new DBUtil();
		 Map<Integer, Double> map=null;
		try {
			map = db.get1MovieRating(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 pageResult.setCount(map.size());
		 //�����ǰҳ���ڵ�һҳ������һҳ���ǵ�һҳ������Ϊ��ǰҳ��һҳ

		 pageResult.setPrePage(pageResult.getPrePage());
		 //�����ǰҳ��βҳ������һҳ����βҳ������Ϊ��ǰҳ����һҳ

		 pageResult.setNextPage(pageResult.getNextPage());
		 pageResult.setCount(map.size());
		 pageResult.setTotalPage(pageResult.getTotalPage());
		 request.setAttribute("pageResult", pageResult);

		 try {
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		 }
}
