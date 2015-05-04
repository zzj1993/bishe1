package servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportMovie {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\junzai\\Desktop\\movies.dat"));
		String s;
		String[] ss;
		String sql = "insert into movie(id,name,type) values(?,?,?)";
		Connection conn = getConn();
		PreparedStatement pst = conn.prepareStatement(sql);
		while((s=br.readLine())!=null){
			ss = s.split("::");
			pst.setInt(1, Integer.parseInt(ss[0]));
			pst.setString(2, ss[1]);
			pst.setString(3, ss[2]);
			pst.executeUpdate();
		}		
		if(pst!=null){
			pst.close();
		}
		if(br!=null){
			br.close();
		}
	}
	public static Connection getConn() throws ClassNotFoundException, SQLException{
		Connection conn=null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456");
		return conn;
	}

}
