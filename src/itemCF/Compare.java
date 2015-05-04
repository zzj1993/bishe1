package itemCF;
/** ֻ���û�1��
 * MAE=0.9136606209206743
RMSE=1.322334112463504
hit=6.0
recall=0.043795620437956206
precision=0.3
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//K=  5		10		20		40		80		160
//MAE=1.07	1.02	1.06	1.07	1.06	1.06//ŷʽ����
//MAE=1.1	1.1		1.07	1.09	1.07	1.07//�������ƶ�
//MAE=0.91	1.04	1.02	1.0		1.01	0.99//�����������ƶ�
//MAE=1.1	1.09	1.12	1.02	1.02	1.03//pearson
//Tanimoto���ƶȣ�Jaccardϵ������Manhattan���ƶȣ�LogLikelihood��������Ȼ���ƶȣ�
//ֻ�ʺ�����ֻ��0,1���֡�Spearmonһ������ѧ���о���С��ģ�����
//��ʱ�����࣬100�����ݣ�4000ms+
public class Compare {
	DBUtil db = new DBUtil();
	Connection conn;
	PreparedStatement pst1;
	PreparedStatement pst2;
	ResultSet rs1;
	ResultSet rs2;
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		Compare c = new Compare();
		c.MAE_RMSE_recall_precision();
	}
	
	//����ƽ���������;��������
	public void MAE_RMSE_recall_precision() throws ClassNotFoundException, SQLException{
		long t1 = System.currentTimeMillis();
		
		double sum1=0.0,sum2=0.0;
		double mae;
		double rmse;
		double recall;
		double precision;
		int count=0;
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> result = new ArrayList<Integer>();
		conn = db.getConn();
		//137����userid=1���Լ�����
		pst1 = conn.prepareStatement("SELECT movieid from test1 where userid<=5 order by movieid");
		rs1 = pst1.executeQuery();
		while(rs1.next()){
			list1.add(rs1.getInt(1));//test����userid<=5��movie����
		}
		//ȡǰ20��Ϊ�Ƽ���Ŀ		
		pst2 = conn.prepareStatement("SELECT movieid from result1 where userid=? order by score desc limit 20");
		for(int i=1;i<=5;i++){
			pst2.setInt(1, i);//�ҳ�5���û����Ե�ǰN���Ƽ�
			rs2 = pst2.executeQuery();
			while(rs2.next()){//���Ƽ���ǰ20*5��Ž���
				list2.add(rs2.getInt(1));
			}
		}
		
		/*****����MAE-RMSE******/
		pst1 = conn.prepareStatement("SELECT sum(abs(a.score-b.score)),sum(pow(a.score-b.score,2)),count(*) from result1 a,test1 b where a.movieid=b.movieid and a.userid=b.userid");
		rs1 = pst1.executeQuery();
		while(rs1.next()){
			sum1 = rs1.getDouble(1);
			sum2 = rs1.getDouble(2);
			count = rs1.getInt(3);
		}
		mae = sum1/count;
		rmse = sum2/count;
		System.out.println("MAE="+mae);
		System.out.println("RMSE="+rmse);
		
		/*******����recall-precision******/	
		result.clear();
		result.addAll(list1);
		result.retainAll(list2);
		double hit = result.size();//hit����test1��user1��movie && �Ƽ���1��ǰ20movie
		recall = hit/list1.size();
		precision = hit/list2.size();
		System.out.println("recall="+recall);
		System.out.println("precision="+precision);
		if(rs1!=null){
			rs1.close();
		}
		if(rs2!=null){
			rs2.close();
		}
		if(pst1!=null){
			pst1.close();
		}
		if(pst2!=null){
			pst2.close();
		}
		if(conn!=null){
			conn.close();
		}
		long t2 = System.currentTimeMillis();
		System.out.println((t2-t1)+"ms");
	}
}
