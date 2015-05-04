package userCF;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Recommend {
	//�����û���ƽ������
	public double getAverage(Object prefer[],int userID){		
		double count=0.0,sum=0.0;
		
		//���ﲻ����for(int item:prefer[userID])
		Map<Integer,Integer> m = (Map) prefer[userID];
		Iterator<Integer> it = m.keySet().iterator();
		while(it.hasNext()){
			sum += m.get(it.next());
		}
		count = m.size();
		return (double)sum/count;
	}
	
	//ƽ����Ȩ���ԣ�Ԥ��userID��itemID������
	public double getRating(Object prefer[],int userID,int itemID,int k){
		double avgOther=0.0;
		double simSums=0.0;
		double weightAvg=0.0;//��Ȩƽ��
		int otherid;
		double similarity;
		
		UserCF uc = new UserCF();
		List<Map.Entry<Integer, Double>> userSim = uc.topKMatches(prefer,userID,itemID,k);
		Iterator<Map.Entry<Integer, Double>> it = userSim.iterator();
		Entry<Integer, Double> entry;
		Map<Integer,Integer> m;
		while(it.hasNext()){
			entry = it.next();
			otherid =entry.getKey();
			similarity = entry.getValue();
			
			
			
			if(otherid!=userID){
				avgOther = getAverage(prefer,otherid);//���Ƚ��û���ƽ����
				//�ۼ�
				simSums += Math.abs(similarity);
				
				m = (Map)prefer[otherid];
				weightAvg += (m.get(itemID)-avgOther)*similarity;
			}
		}
		
		double avgUser = getAverage(prefer,userID);
	
		if(simSums==0)
			return avgUser;
		else 
			return (avgUser+weightAvg/simSums);
	}
	
	public void getAllUserRating(int userid) throws ClassNotFoundException, SQLException, IOException{
		DBUtil db = new DBUtil();
		Object prefer1[] = db.loadMovieLensTrain();
		Object prefer2[] = db.loadMovieLensTest();
		
		PrintWriter pw = new PrintWriter(new FileWriter("..\\1.test"));
		
		Connection conn = db.getConn();
		PreparedStatement pst = conn.prepareStatement("truncate result2");
		pst.executeUpdate();
		pst = conn.prepareStatement("insert into result2(userid,movieid,score) values(?,?,?)");
		double rating = 0.0;
		
//		Iterator<Entry<Integer,Integer>> it;
//		Entry<Integer, Integer> entry;
		int itemid;
//		m = (Map)prefer2[1];
//		System.out.println(m.entrySet());
		for(int i=0;i<prefer2.length;i++){	
			if(prefer2[i]!=null){//������Щ�û�û��
				Map<Integer,Integer> m = (Map)prefer2[i];
				Iterator<Entry<Integer,Integer>> it = m.entrySet().iterator();
				while(it.hasNext()){
					Entry<Integer, Integer> entry = it.next();
					itemid = entry.getKey();
					rating = getRating(prefer1,i,itemid,160);
					pw.write(i+"\t"+itemid+"\t"+rating+"\n");
					
					pst.setInt(1, i);//userid=i
					pst.setInt(2, itemid);
					pst.setDouble(3, rating);
					pst.executeUpdate();
				}
			}
		}
		if(pst!=null){
			pst.close();
		}
		pw.close();
	}
}
