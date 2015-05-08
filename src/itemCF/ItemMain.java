package itemCF;
/**
 * Map<int,Map>实现
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//35 000ms左右
public class ItemMain {
	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ParseException {
		long t1 = System.currentTimeMillis();
		DBUtil db = new DBUtil();
		Recommend re = new Recommend();
		ItemCF uc = new ItemCF();

//		int len=0;
//		Map<Integer,Map<Integer,List<Double>>> mm = db.loadMovieLensTrain();
//		Map<Integer,List<Integer>> m = mm.get(1);
//		for(Map.Entry<Integer, List<Integer>> entry:m.entrySet()){
//			int userid = entry.getKey();
//			List<Integer> list = entry.getValue();
//			System.out.println(userid+" "+list.get(0)+" "+list.get(1));
//		}
//		double sim = uc.sim_item_pearson(mm,1, 2);//0~10ms
//		System.out.println("sim="+sim);
//		double avg = re.getAverage(mm, 1);//0~10ms
//		System.out.println("avg="+avg);
//		List<Map.Entry<Integer, Double>> list = uc.topKMatches(mm, 10, 1, 20);//60~70ms 
//		System.out.println("list="+list);
//		System.out.println(re.getRating(mm, 10, 1, 20));//40~60ms
		Connection conn = db.getConn();
		PreparedStatement pst = conn.prepareStatement("truncate result1");
		pst.executeUpdate();
		
		//为一个用户预测剩余项目并写到文本中用了541ms，写到数据库中用了24020ms
		//但是只能将结果插入数据库中，compare需要用到
//		for(int i=1;i<=5;i++){
//			re.getAllUserRating(i, 1, 1);
//		}
//		re.getAllUserRating(1, 1, 1);
		System.out.println(db.get1MovieRating(1));
		
		long t2 = System.currentTimeMillis();
		System.out.println((t2-t1)+"ms");


	}
	
	public List<String> recommend(int userid) throws ClassNotFoundException, SQLException, IOException{
		List<String> list = new ArrayList<String>();		
		DBUtil db = new DBUtil();
		Connection conn = db.getConn();
		PreparedStatement pst = conn.prepareStatement("truncate result1");
		pst.executeUpdate();
		Recommend re = new Recommend();
		re.getAllUserRating(userid, 1, 1);//
		
		String sql = "select name from movie as a,result1 as b where a.id=b.movieid and userid=? order by score desc limit 0,20";//select movieid from result1 where userid=? order by score desc limit 0,20
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
