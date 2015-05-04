package userCF;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//��ʱ30 000 ms
public class UserMain {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {		
		DBUtil db = new DBUtil();
		UserCF uc = new UserCF();
		Recommend re = new Recommend();
		
//		int prefer1[][] = db.loadMovieLensTrain();
//		lf.getAllUserRating();
//		int prefer1[][] = lf.loadMovieLensTrain();
		
		
//		Object prefer1[] = db.loadMovieLensTrain();
//		Object prefer2[] = db.loadMovieLensTest();
		
		long t1 = System.currentTimeMillis();
//		Map map = (Map) prefer[1];
//		
//		System.out.println(map.keySet());
/*		Map<Integer,Integer> map = new HashMap<Integer,Integer>();

		
		Object a[] = new Object[10];
		map.put(7, 3);
		map.put(8, 3);
		map.put(9, 3);
		a[3]=map;
		
			if(a[2]==null){
				map = new HashMap<Integer,Integer>();//������clear����Ȼ���������Ԫ�ش��ȥ��map�ᱻ����
				map.put(11, 22);
				a[2]=map;
			}else{
				map.put(111, 222);
				a[2]=map;
			}
			System.out.println(a[3]);
			System.out.println(a[2]);*/

//		uc.sim_user_pearson(prefer, 1, 2);//1 ms
//		re.getAverage(prefer, 1);//0 ms
//		System.out.println(re.getAverage(prefer, 1)+"avg1");
//		uc.topKMatches(prefer1, 1, 1, 20);//0ms 
//		re.getRating(prefer, 1, 6, 20);//10 ms��forѭ���У������û���δ���۹���������Ŀ������
//		re.getAllUserRating();//400 000 ms��Լ7 min��200 000
				
		long t2 = System.currentTimeMillis();
		System.out.println((t2-t1)+"ms");
			
	}
	public List<String> recommend(int userid) throws ClassNotFoundException, SQLException, IOException{
		List<String> list = new ArrayList<String>();
		
		DBUtil db = new DBUtil();
		Connection conn = db.getConn();
		PreparedStatement pst = conn.prepareStatement("truncate result2");
		pst.executeUpdate();
		Recommend re = new Recommend();
		re.getAllUserRating(userid);//
		
		String sql = "select name from movie as a,result2 as b where a.id=b.movieid and userid=? order by score desc limit 0,20";//select movieid from result2 where userid=? order by score desc limit 0,20
		pst = conn.prepareStatement(sql);
		pst.setInt(1, userid);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			list.add(rs.getString(1));
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
