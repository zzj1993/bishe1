package userCF;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBUtil {
	public Connection getConn() throws ClassNotFoundException, SQLException{
		Connection conn=null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456");
		return conn;
	}
	//ѵ����
	public Object[] loadMovieLensTrain() throws ClassNotFoundException, SQLException, IOException{
		Object prefer[] = new Object[1000];//������List����ΪList�����Զ���ʼ����Ҫ��˳��add
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		Connection conn = getConn();
		PreparedStatement pst = conn.prepareStatement("select * from base order by userid");
		ResultSet rs = pst.executeQuery();
		int userid,movieid,score,timestamp;
		
		while(rs.next()){
			userid = rs.getInt(2);
			movieid = rs.getInt(3);
			score = rs.getInt(4);
			timestamp = rs.getInt(5);
			
			
			if(prefer[userid]==null){
				map = new HashMap<Integer,Integer>();//������clear����Ȼ���������Ԫ�ش��ȥ��map�ᱻ����
				map.put(movieid, score);
				prefer[userid]=map;
			}else{
				map.put(movieid, score);
				prefer[userid]=map;
			}
		}
		if(rs!=null){
			rs.close();
		}
		if(pst!=null){
			pst.close();
		}
		if(conn!=null){
			conn.close();
		}
		
		return prefer;
	}
	//���Լ�
	public Object[] loadMovieLensTest() throws ClassNotFoundException, SQLException{
		Object prefer[]=new Object[1000];
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		Connection conn = getConn();
		PreparedStatement pst = conn.prepareStatement("select * from test where id<=100");
		ResultSet rs = pst.executeQuery();
		int userid,movieid,score,timestamp;
		int i=0;
		while(rs.next()){
			userid = rs.getInt(2);
			movieid = rs.getInt(3);
			score = rs.getInt(4);
			timestamp = rs.getInt(5);
			
			if(prefer[userid]==null){
				map = new HashMap<Integer,Integer>();//������clear����Ȼ���������Ԫ�ش��ȥ��map�ᱻ����
				map.put(movieid, score);
				prefer[userid]=map;
			}else{
				map.put(movieid, score);
				prefer[userid]=map;
			}			
		}
		if(rs!=null){
			rs.close();
		}
		if(pst!=null){
			pst.close();
		}
		if(conn!=null){
			conn.close();
		}
		return prefer;
	}	
}
