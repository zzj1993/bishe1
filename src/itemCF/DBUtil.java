package itemCF;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 100�û���1000��Ӱ
 * @author junzai
 *
 */
public class DBUtil {
	public Connection getConn() throws ClassNotFoundException, SQLException{
		Connection conn=null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "123456");
		return conn;
	}
	//ѵ����
	public Map<Integer,Map<Integer,List<Double>>> loadMovieLensTrain() throws ClassNotFoundException, SQLException, IOException{
		Map<Integer,Map<Integer,List<Double>>> mm = new TreeMap<Integer,Map<Integer,List<Double>>>();
		Map<Integer,List<Double>> m = new TreeMap<Integer,List<Double>>();
		
		Connection conn = getConn();
		//����Ҫ��movieid���򣬲�Ȼ���渳ֵ������ʱ�������Ϊif��else��map�ǳнӵ�
		PreparedStatement pst = conn.prepareStatement("select * from base1 order by movieid asc,userid asc");
		ResultSet rs = pst.executeQuery();
		int userid,movieid,score;
		double timestamp;
		
		while(rs.next()){
			userid = rs.getInt(2);
			movieid = rs.getInt(3);
			score = rs.getInt(4);
			timestamp = rs.getDouble(5);
			
			
			if(mm.get(movieid)==null){
				m = new TreeMap<Integer,List<Double>>();
				List<Double> list = new ArrayList<Double>();
				list.add((double)score);
				list.add(timestamp);
				
				m.put(userid, list);
				mm.put(movieid, m);
			}else{
				List<Double> list = new ArrayList<Double>();
				list.add((double)score);
				list.add(timestamp);
				m.put(userid, list);
				mm.put(movieid, m);
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
		
		return mm;
	}
	//���Լ�
	public Map<Integer,Map<Integer,Integer>> loadMovieLensTest() throws ClassNotFoundException, SQLException{
		Map<Integer,Map<Integer,Integer>> mm = new TreeMap<Integer,Map<Integer,Integer>>();
		//user-ratings
		Map<Integer,Integer> m = new TreeMap<Integer,Integer>();
		
		Connection conn = getConn();
		PreparedStatement pst = conn.prepareStatement("select * from test1 order by movieid asc,userid asc");
		ResultSet rs = pst.executeQuery();
		int userid,movieid,score,timestamp;
		while(rs.next()){
			userid = rs.getInt(2);
			movieid = rs.getInt(3);
			score = rs.getInt(4);
			timestamp = rs.getInt(5);
			
			if(mm.get(movieid)==null){
				m = new TreeMap<Integer,Integer>();
				m.put(userid, score);
				mm.put(movieid, m);
			}else{
				m.put(userid, score);
				mm.put(movieid, m);
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
		return mm;
	}

	
	//ʱ���һ�������ҵ�������С��ʱ��
	public double[] Max_Min() throws ClassNotFoundException, SQLException{
		double a[] = new double[2];
		Connection conn = getConn();
		PreparedStatement pst = conn.prepareStatement("select max(timestamp),min(timestamp) from base1");
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			a[0] = rs.getDouble(1);
			a[1] = rs.getDouble(2);
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
		return a;
	}
	
	//�õ�һ���˵Ĺۿ���¼
	public List<String> get1MovieRating(int userid) throws ClassNotFoundException, SQLException{
//		Map<Integer,Double> m = new TreeMap<Integer,Double>();
		List<String> list = new ArrayList<String>();
		Connection conn = getConn();
		PreparedStatement pst = conn.prepareStatement("select name from movie a,base b where a.id=b.movieid and userid=?");//select movieid,score from base1 where userid=?
		pst.setInt(1, userid);
		ResultSet rs = pst.executeQuery();
		String movie;
		int movieid;
		double rating;
		int totalcount=0;//��������
		int pagesize = 20;//ÿҳ������
		int pagecount;
		while(rs.next()){
//			movieid = rs.getInt(1);
//			rating = rs.getDouble(2);
//			m.put(movieid, rating);
			movie = rs.getString(1);
			list.add(movie);
			totalcount += 1;//1.ȷ�����ݵ�����
		}
		
		//2.ȷ����ʾ��ҳ��
		if(totalcount%pagesize==0){
			pagecount = totalcount/pagesize;
		}else if(totalcount%pagesize>0){
			pagecount = totalcount/pagesize+1;
		}else{
			pagecount = 0;
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
		return list;
	}
}
