package userCF;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class UserCF {
	//�����û�֮������ƶ�
	public double sim_user_pearson(Object prefer[],int user1,int user2){
		ArrayList<Integer> sim = new ArrayList<Integer>();
		//�������Ѿ��ź��򣬲�����˫��for�����ǵĽ���(10��ms)��hash��ײ��죬0~1ms
		Map<Integer,Integer> map1 = (Map) prefer[user1];
		Set<Integer> set1 = map1.keySet();
		
		Map<Integer,Integer> map2 = (Map) prefer[user2];
		Set<Integer> set2 = map2.keySet();
		//�ҵ���ͬ��Ŀ
		for(int i:set2){
			if(set1.contains(i)){
				sim.add(i);
//				System.out.println(i);
			}
		}

//		System.out.println(sim.size());

		if(sim.size()==0)//��ͬ������Ŀ����
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
		
		for(int item:sim){
			
			sum1 += map1.get(item);//����ƫ��֮��
			sum2 += map2.get(item);
			
			sum1Sq += Math.pow(map1.get(item), 2);//��ƽ��֮��
			sum2Sq += Math.pow(map2.get(item), 2);
			
			sumMulti += map1.get(item)*map2.get(item);			
		}
		
		num1 = sumMulti - (sum1*sum2/sim.size());
		num2 = Math.sqrt( (sum1Sq-Math.pow(sum1,2)/sim.size())*(sum2Sq-Math.pow(sum2,2)/sim.size()));  
		if(num2==0)                                                
			return 0;

		result = num1/num2;
//		System.out.println("result="+result);
		return result;
	}
	
	//��ȡ��item���ֵ�K���������û�
	public List<Map.Entry<Integer, Double>> topKMatches(Object prefer[],int user,int itemID,int k){
		Set<Integer> userSet = new HashSet<Integer>();
		//�ҳ�����prefer�����۹�itemID���û�������userSet
		/*for(int i=0;i<prefer.length;i++){			
			if(prefer[i][itemID]!=0){
				userSet.add(i);//���۹���ĿitemID����
			}
		}*/
		for(int i=0;i<prefer.length;i++){	
			if(prefer[i]!=null){
				Map<Integer,Integer> m = (Map)prefer[i];				
				if(m.containsKey(itemID))
					userSet.add(i);
			}
			
//			if(m.containsKey(itemID))
//				userSet.add(i);//���۹���ĿitemID����
		}
//		System.out.println("userSet.size"+userSet.size());
		//���ƶ�-�û���Ӧ�ò��������ƶ�һ�����û������ظ�
		Map<Integer,Double> userSim = new HashMap<Integer,Double>();
		double sim=0.0;
		//����������
		for(int other:userSet){
			if(other!=user){				
				sim = sim_user_pearson(prefer,user,other);				
				userSim.put(other,sim);
			}
		}
		List<Map.Entry<Integer, Double>> userSim_sort = new ArrayList<Map.Entry<Integer, Double>>(userSim.entrySet());
	    Collections.sort(userSim_sort, new Comparator<Map.Entry<Integer, Double>>() {//����value����
	        public int compare(Map.Entry<Integer, Double> o1,
	          Map.Entry<Integer, Double> o2) {
	         double result = o2.getValue() - o1.getValue();
	         if(result > 0)
	         	return 1;
	         else if(result == 0)
	         	return 0;
	         else 
	         	return -1;
	        }
	       });
	
	    //���ƶȴ�С�������У�����Ҫȡ��K��
		if(userSim_sort.size()<=k){//���С��k��ֻѡ����Щ���Ƽ�
			return userSim_sort;
		}else{//�������k��ѡ��������ߵ��û�
			userSim_sort = userSim_sort.subList(0, k);
			return userSim_sort;
		}
	}
}
