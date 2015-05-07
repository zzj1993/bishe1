package servlet;

import itemCF.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class userServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		
		String userid = request.getParameter("userID");
		String pwd = request.getParameter("userPwd");
		boolean ok = checkLogin(Integer.parseInt(userid));
		if(ok){
			request.getSession().setAttribute("user", userid);
//			request.getRequestDispatcher("main.jsp").forward(request, response);
			request.getRequestDispatcher("more.jsp").forward(request, response);
		}
	}
	public boolean checkLogin(int userid) {
		try {
			DBUtil db = new DBUtil();
			Connection conn = db.getConn();
			PreparedStatement pst = conn.prepareStatement("select * from base1 where userid=?");
			pst.setInt(1, userid);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				 return true;
			}

		} catch (Exception ef) {
			ef.printStackTrace();
		}
		return false;
	}
}
