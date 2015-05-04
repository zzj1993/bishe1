package itemCF;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Recommend {
	//������Ŀ��ƽ������
	public double getAverage(Map<Integer,Map<Integer,List<Double>>> mm,int itemID){		
		double count=0.0,sum=0.0;
		
		//���ﲻ����for(int item:prefer[userID])
		Map<Integer,List<Double>> m = mm.get(itemID);
		for(Entry<Integer,List<Double>> entry:m.entrySet()){
			sum += entry.getValue().get(0);
		}
		count = m.size();
		
		if(count==0)
			return 0;
		else
			return (double)sum/count;
	}
	
	//ƽ����Ȩ���ԣ�Ԥ��userID��itemID������
	public double getRating(Map<Integer, Map<Integer, List<Double>>> mm,int itemID,int userID,int k) throws ClassNotFoundException, SQLException{
		double avgOtherItem=0.0;//���û�����������Ŀ��ƽ������
		double simSums=0.0;
		double weightAvg=0.0;//��Ȩƽ��
		int itemid;
		double similarity;
		double score;
		double timestamp;//8��ͷ
		double guiyi;
		int year;
		double weight=0.0;//ʱ��Ȩ��
		
		ItemCF uc = new ItemCF();
		//��ȡ�����Ƶ�K����Ŀ
		List<Map.Entry<Integer, List<Double>>> itemSim = uc.topKMatches(mm,itemID,userID,k);
		Iterator<Map.Entry<Integer, List<Double>>> it = itemSim.iterator();
		Entry<Integer, List<Double>> entry;
		
		DBUtil db = new DBUtil();
		double a[] = db.Max_Min();
//		System.out.println(itemSim);//�������û���20���������û��������ֲ���6�������Իᱨ��ָ��
//		System.out.println(m);
		while(it.hasNext()){
			entry = it.next();
			itemid =entry.getKey();
			similarity = entry.getValue().get(0);//�õ����ƶ�
			timestamp = entry.getValue().get(1);//�õ�ʱ���
//			timestamp = Math.round(entry.getValue().get(1));//�õ�ʱ���			
//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//			String sd = sdf.format(new Date(time*1000));
//			sd = sd.substring(0, 4);
//			year = Integer.parseInt(sd);
//			System.out.println(year);
			//��һ������ʱ��
			guiyi = (timestamp-a[1])/(a[0]-a[1]);
			
			//�õ��û�userID��itemid������
			Map<Integer,List<Double>> m = mm.get(itemid);
			score = m.get(userID).get(0);//list(0)��score
			
			
			if(itemid!=itemID){
				avgOtherItem = getAverage(mm,itemid);
				weight = 1.0/(1+Math.exp(-guiyi));
//				weight = Math.exp(-0.1*(2015-year));//��ǰ��2015�꣬ʱ�����a=0.05
//				weight = Math.pow(1-Math.abs(-score), 2);//��ǰ���ּ�ȥҪ�Ѿ�������
				//�ۼ�
				simSums += similarity*weight;				
				weightAvg += (score-avgOtherItem)*similarity*weight;
//				simSums += similarity;				
//				weightAvg += (score*weight-avgOtherItem)*similarity;
//				simSums += similarity;				
//				weightAvg += (score-avgOtherItem)*similarity;
			
			}
		}

		double avgItem = getAverage(mm,itemID);//����Ŀ�������û�����
		if(simSums==0)
			return avgItem;
		else
			return (avgItem+weightAvg/simSums);
			
	}
	
	/**
	 * @param userID ��userID���û��Ƽ���Ʒ
	 * @param k �����û���
	 * @param n �Ƽ���Ʒ����
	 */
	public void getAllUserRating(int userID,int k,int n) throws ClassNotFoundException, SQLException, IOException{
		DBUtil db = new DBUtil();
		Map<Integer,Map<Integer,List<Double>>> mm1 = db.loadMovieLensTrain();//item-user
		Map<Integer,Map<Integer,Integer>> mm2 = db.loadMovieLensTest();
		
		Connection conn = db.getConn();
		//��ձ�
//		PreparedStatement pst = conn.prepareStatement("truncate result");
//		pst.executeUpdate();
		//�õ����е�movie����1650��м���Щ���ȱ
		String sql = "SELECT distinct movieid from base1 order by movieid asc";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs1 = pst.executeQuery();
		//�õ�userID���۹���movie
		sql = "select movieid from base1 where userid=?";		
		pst = conn.prepareStatement(sql);
		pst.setInt(1, userID);		
		ResultSet rs2 = pst.executeQuery();
		
		Set<Integer> s1 = new HashSet<Integer>();
		Set<Integer> s2 = new HashSet<Integer>();
		while(rs1.next()){
			s1.add(rs1.getInt(1));//������Ŀ��924��
		}
		while(rs2.next()){
			s2.add(rs2.getInt(1));
		}
		s1.removeAll(s2);
		
		double rating = 0.0;
		PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\junzai\\Desktop\\1.test"));
		//��main������ֻ����һ����������Ļ�����Ҫ��գ�����Ļ��������
//		pst = conn.prepareStatement("truncate result1");
//		pst.executeUpdate();
		//��Ԥ��������		
		pst = conn.prepareStatement("insert into result1(userid,movieid,score) values(?,?,?)");
				
		for(int movieid:s1){
			rating = getRating(mm1,movieid,userID,5);
			pw.write(userID+"\t"+movieid+"\t"+rating+"\n");
			
			pst.setInt(1, userID);
			pst.setInt(2, movieid);//itemid=i
			pst.setDouble(3, rating);
			pst.executeUpdate();
		}
		if(pst!=null){
			pst.close();
		}
		pw.close();
	}
}
