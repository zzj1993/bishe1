package itemCF;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ItemCF {
	//�����û�֮������ƶ�
	public double sim_item_pearson(Map<Integer,Map<Integer,List<Double>>> mm,int item1,int item2) throws ClassNotFoundException, SQLException{
		//��ͬ�û�	
		ArrayList<Integer> comUser = new ArrayList<Integer>();
		//�������Ѿ��ź��򣬲�����˫��for�����ǵĽ���(10��ms)��hash��ײ��죬0~1ms
		Map<Integer,List<Double>> m1 = mm.get(item1);
		Set<Integer> set1 = m1.keySet();
		
		Map<Integer,List<Double>> m2 = mm.get(item2);
		Set<Integer> set2 = m2.keySet();
		
		//�ҳ���ͬ�û�
		for(int i:set2){
			if(set1.contains(i))
				comUser.add(i);
		}
//		System.out.println(comUser);//movie 1,2��ͬ�û�Ϊ1,42,72
		
		if(comUser.size()==0)//��ͬ�����û�����
			return -1;
		//����ƫ��֮�ͣ����ж���double�ͣ���ֹ���ʱ�õ�����
		double sum1=0,sum2=0;
		//��ƽ��֮��
		double sum1Sq=0,sum2Sq=0;
		//��˻�֮�� ��XiYi
		double sumMulti = 0;
		double num1=0.0;
		double num2=0.0;
		double result=0.0;		
		
		//ͨ���������û��Ĺ�ͬ������Ŀ�������ƶ�
		for(int user:comUser){
			sum1 += m1.get(user).get(0);//������������֮��
			sum2 += m2.get(user).get(0);
			
			sum1Sq += Math.pow(m1.get(user).get(0), 2);//��ƽ��֮��
			sum2Sq += Math.pow(m2.get(user).get(0), 2);
			
			sumMulti += m1.get(user).get(0)
					*m2.get(user).get(0);

		}		
		num1 = sumMulti - (sum1*sum2/comUser.size());
		num2 = Math.sqrt( (sum1Sq-Math.pow(sum1,2)/comUser.size())*(sum2Sq-Math.pow(sum2,2)/comUser.size()));  
	
		if(num2==0)                                                
			return 0;  
		else{
			result = num1/num2;
			return result;
		}		
	}

	//��ȡ��item�����Ƶ�K����Ŀ
	public List<Map.Entry<Integer, List<Double>>> topKMatches(Map<Integer,Map<Integer,List<Double>>> mm,int itemID,int userID,int k) throws ClassNotFoundException, SQLException{
		//���item-timestamp
		Map<Integer,Double> itemMap = new HashMap<Integer,Double>();
		//�ҳ�mm��userID���۹�������item
		Map<Integer,List<Double>> m;
		for(Entry<Integer,Map<Integer,List<Double>>> entry:mm.entrySet()){
			m = entry.getValue();
			if(m.containsKey(userID)){				
				itemMap.put(entry.getKey(), m.get(userID).get(1));//item-timestamp
			}				
		}

		//��Ŀ-���ƶ�-ʱ��������ظ�
		Map<Integer,List<Double>> itemSim = new HashMap<Integer,List<Double>>();
		double sim=0.0;
		List<Double> li;
		//����������
		for(Map.Entry<Integer,Double> entry:itemMap.entrySet()){//item������userID�Ѿ����۹�����Ŀ�����ƶ�						
			int other = entry.getKey();
			double timestamp = entry.getValue();
			if(other!=itemID){
				li = new ArrayList<Double>();
				sim = sim_item_pearson(mm,itemID,other);
				li.add(sim);
				li.add(timestamp);
				if(sim>0){//���ƶ�>0ʱ�ŷŽ�ȥ������û������					
					itemSim.put(other,li);
				}
			}
		}	
		List<Map.Entry<Integer, List<Double>>> itemSim_sort = new ArrayList<Map.Entry<Integer, List<Double>>>(itemSim.entrySet());
	    Collections.sort(itemSim_sort, new Comparator<Map.Entry<Integer, List<Double>>>() {//����value����
	        public int compare(Map.Entry<Integer, List<Double>> o1,
	          Map.Entry<Integer, List<Double>> o2) {//��������list(0)
	         double result = o2.getValue().get(0) - o1.getValue().get(0);
	         if(result > 0)
	         	return 1;
	         else if(result == 0)
	         	return 0;
	         else 
	         	return -1;
	        }
	       });
	
	    //���ƶȴ�С�������У�����Ҫȡ��K��
		if(itemSim_sort.size()<=k){//���С��k��ֻѡ����Щ���Ƽ�
			return itemSim_sort;
		}else{//�������k��ѡ��������ߵ��û�
			itemSim_sort = itemSim_sort.subList(0, k);
			return itemSim_sort;
		}
	}
}
